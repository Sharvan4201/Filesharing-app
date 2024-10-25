CREATE TABLE files (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    version INT,
    data LONGBLOB
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE file_shares (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fileDb_id BIGINT,
    sender_id BIGINT,
    receiver_id BIGINT,
    date TIMESTAMP,
    FOREIGN KEY (fileDb_id) REFERENCES files(id),
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);