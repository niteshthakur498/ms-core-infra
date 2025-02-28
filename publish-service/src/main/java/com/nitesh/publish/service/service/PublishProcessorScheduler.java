package com.nitesh.publish.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.publish.service.dto.KafkaMessage;
import com.nitesh.publish.service.entity.PublishRequestFailed;
import com.nitesh.publish.service.entity.PublishRequestProcessed;
import com.nitesh.publish.service.entity.PublishRequestQueue;
import com.nitesh.publish.service.repository.PublishRequestFailedRepository;
import com.nitesh.publish.service.repository.PublishRequestProcessedRepository;
import com.nitesh.publish.service.repository.PublishRequestQueueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@Slf4j
@Component
public class PublishProcessorScheduler {

    private final KafkaTemplate kafkaTemplate;
    private final PublishRequestQueueRepository requestQueueRepository;
    private final PublishRequestProcessedRepository requestProcessedRepository;
    private final PublishRequestFailedRepository requestFailedRepository;

    private final ExecutorService producerThreadPool = Executors.newFixedThreadPool(10);//need to correct

    @Scheduled(fixedDelay = 5000) // Runs every 5 seconds
    public void processPublishQueue() {
        List<PublishRequestQueue> unprocessedRequests =  requestQueueRepository.findUnprocessedRequests();
        markInProgress(unprocessedRequests);
        for (PublishRequestQueue request : unprocessedRequests) {
            producerThreadPool.submit(() -> processRequest(request));
        }
    }


    public void processRequest(PublishRequestQueue request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            KafkaMessage message = objectMapper.readValue(request.getMessageContent(),KafkaMessage.class);

            CompletableFuture<SendResult> future =  kafkaTemplate.send(request.getEventCode(),message);

            future.thenAccept(result -> {
                RecordMetadata metadata = result.getRecordMetadata();
                log.debug("Message sent successfully to topic " + metadata.topic() +
                        " partition " + metadata.partition() +
                        " offset " + metadata.offset());
                // Add your post-processing logic here
                postProcessOnSuccess(metadata,request);
            }).exceptionally(e -> {
                log.error("Error raised-->{} caused by {}", e.getMessage(), e.getCause().toString());
                // Add your failure handling logic here
                postProcessOnFailure(request.getEventCode(), request, e);
                return null; // required by exceptionally
            });


        } catch (JsonProcessingException e) {
            log.warn("Error raised-->{} caused by {}", e.getMessage(), e.getCause().toString());
        }
    }

    private void postProcessOnFailure(String eventCode, PublishRequestQueue request, Throwable e) {
        PublishRequestFailed requestProcessed = PublishRequestFailed.build(request);
        requestFailedRepository.save(requestProcessed);
    }

    private void postProcessOnSuccess(RecordMetadata metadata, PublishRequestQueue request) {
        PublishRequestProcessed requestProcessed = PublishRequestProcessed.build(request);
        requestProcessedRepository.save(requestProcessed);
    }


    @Transactional
    private void markInProgress(List<PublishRequestQueue> unprocessedRequests) {
        unprocessedRequests.forEach(request->request.setStatus("W"));
        requestQueueRepository.saveAll(unprocessedRequests);
    }


}
