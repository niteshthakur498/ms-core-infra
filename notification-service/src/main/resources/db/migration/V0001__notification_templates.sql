CREATE TABLE notification_templates (
    id SERIAL PRIMARY KEY,                       -- Unique ID for the template.
    template_name VARCHAR(255) NOT NULL UNIQUE,          -- Name of the template (e.g., 'order_shipped_email').
    template_type VARCHAR(50) NOT NULL,           -- Type of template (e.g., 'email', 'sms').
    subject VARCHAR(255),                         -- Subject for the email template (optional).
    body TEXT NOT NULL,                           -- Body of the template (HTML or plain text).
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp when the template was created.
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp when the template was last updated.
);

CREATE INDEX idx_template_name ON notification_templates (template_name);
CREATE INDEX idx_template_type ON notification_templates (template_type);
