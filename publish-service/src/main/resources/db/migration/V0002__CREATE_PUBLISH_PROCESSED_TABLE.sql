CREATE TABLE IF NOT EXISTS PUBLISH_REQUEST_PROCESSED(
    ID          BIGINT PRIMARY KEY,
    event_code  VARCHAR(255) NOT NULL,    -- The event code (acts as topic identifier)
    source      VARCHAR(255) NOT NULL,        -- Source of the message
    message_content     JSONB NOT NULL,              -- The payload (message body, can be JSON)
    status      VARCHAR(1) DEFAULT 'U',-- Message processing status (U-Unprocessed, P-> Processed, D-> Deferred, F->Failed)
    retry_count INT DEFAULT 0,           -- Number of retries attempted
    last_attempt_timestamp TIMESTAMP,    -- Last retry timestamp
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Message creation time
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP   -- Time of last update
);

CREATE SEQUENCE seq_PUBLISH_REQUEST_PROCESSED
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;