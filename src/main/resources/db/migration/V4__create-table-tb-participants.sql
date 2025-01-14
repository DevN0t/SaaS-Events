CREATE TABLE IF NOT EXISTS events.tb_participants(
    id serial NOT NULL PRIMARY KEY,
    event_id INT NOT NULL,
    branch_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    birth DATE NOT NULL,
    phone VARCHAR(255) NOT NULL,
    emergency_phone VARCHAR(255) NOT NULL,
    reference VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NULL
    )