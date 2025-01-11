CREATE TABLE notification_message_log (
    id SERIAL PRIMARY KEY,                         -- Unique ID for the notification log entry.
    event_type VARCHAR(255) NOT NULL,               -- Event type that triggered the notification (e.g., 'order_shipped').
    channel VARCHAR(50) NOT NULL,                          -- channel that the notification was sent to.(sms, mail etc)
    user_id INT NOT NULL,
    template_id INT NOT NULL,                      -- Template ID used to generate the message (foreign key to notification_templates).
    message_content TEXT,                         -- Final content of the message in JSON/TEXT format (email body, etc.).
    status VARCHAR(50) DEFAULT 'PENDING',          -- Status of the notification (e.g., 'PENDING', 'SENT', 'FAILED').
    sent_at TIMESTAMP,                             -- Timestamp of when the notification was sent.
    error_message TEXT,                            -- Error message in case of failure.
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp when the record was created.
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp of the last update.
    --,CONSTRAINT fk_template FOREIGN KEY (template_id) REFERENCES notification_templates(id) ON DELETE CASCADE
);


CREATE INDEX idx_user_id ON notification_message_log (user_id);
CREATE INDEX idx_status ON notification_message_log (status);
CREATE INDEX idx_sent_at ON notification_message_log (sent_at);
