CREATE TABLE IF NOT EXISTS events.tb_branch(
    id serial NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    logo VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NOT NULL
    )