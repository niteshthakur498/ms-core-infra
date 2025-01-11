INSERT INTO notification_templates (
    template_name,      -- The name of the template (e.g., 'order_shipped_email')
    template_type,      -- The type of template (e.g., 'email', 'sms')
    subject,            -- The subject of the email (optional)
    body,               -- The body of the template (HTML or plain text)
    created_at,         -- Timestamp when the template was created
    updated_at          -- Timestamp when the template was last updated
)
VALUES (
    'order_shipped_email',    -- Template name
    'email',                  -- Template type (e.g., email, sms, push, etc.)
    'Your order has been shipped',  -- Subject (can be NULL if not applicable)
    '<!doctype html><html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"><head> <meta charset="UTF-8"> <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" name="viewport"> <meta content="ie=edge" http-equiv="X-UA-Compatible"> <title>Email</title></head><body><h1></h1><div>Welcome <b th:text="${textString}"></b></div><hr><div> Your username is <b th:text="${textString}"></b></div></body></html>',  -- Body (HTML template for email)
    CURRENT_TIMESTAMP,        -- Created at (auto-generated to current time)
    CURRENT_TIMESTAMP         -- Updated at (auto-generated to current time)
);