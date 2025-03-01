CREATE TABLE IF NOT EXISTS API_ROUTE (
    id BIGINT PRIMARY KEY,
    route_path VARCHAR(255) NOT NULL,
    service_id VARCHAR(255),
    uri VARCHAR(255) NOT NULL,
    method VARCHAR(10),
    strip_prefix BOOLEAN,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    replenish_rate INTEGER,
    burst_capacity INTEGER
);