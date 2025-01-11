CREATE TABLE event_template_mapping (
    id SERIAL PRIMARY KEY,                -- Unique ID for the event-template mapping.
    event_code VARCHAR(255) NOT NULL,      -- Type of the event (e.g., 'user_signup', 'order_shipped').
    template_name VARCHAR(255) NOT NULL,              -- ID of the template to be used (foreign key to notification_templates).
    template_type VARCHAR(50) NOT NULL,
    description TEXT,                     -- Optional description of the event type.
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp when the mapping was created.
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp of the last update.
    ,CONSTRAINT uk_template UNIQUE (event_code,template_name)
    --,CONSTRAINT fk_template FOREIGN KEY (template_id) REFERENCES notification_templates(id) ON DELETE CASCADE
);

CREATE INDEX idx_event_type ON event_template_mapping (event_code);
