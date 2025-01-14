CREATE TABLE IF NOT EXISTS events.tb_events(
    id serial NOT NULL PRIMARY KEY,
    branch_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    location VARCHAR(255) NULL,
    contact VARCHAR(255) NOT NULL,
    online BOOLEAN NOT NULL,
    status BOOLEAN NOT NULL,
    date TIMESTAMP(6) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NOT NULL
    )