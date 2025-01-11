CREATE TABLE notification_queue (
    id SERIAL PRIMARY KEY,                        -- Unique ID for the queued notification.
    event_type VARCHAR(255) NOT NULL,              -- Event type that triggered the notification.
    user_id INT NOT NULL,                         -- ID of the user to whom the notification is to be sent.
    message_content TEXT,                        -- Content of the notification (e.g., email body, SMS message).
    scheduled_at TIMESTAMP,                       -- Time when the notification is scheduled to be sent.
    status VARCHAR(50) DEFAULT 'PENDING',         -- Status of the notification in the queue (e.g., 'PENDING', 'PROCESSING').
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp when the notification was added to the queue.
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp of the last update.
);

CREATE INDEX idx_scheduled_at ON notification_queue (scheduled_at);
CREATE INDEX idx_queue_status ON notification_queue (status);
