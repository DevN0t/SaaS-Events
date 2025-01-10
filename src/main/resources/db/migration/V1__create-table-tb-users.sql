CREATE TABLE IF NOT EXISTS events.tb_users(
    id serial NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    branch INT NOT NULL
)