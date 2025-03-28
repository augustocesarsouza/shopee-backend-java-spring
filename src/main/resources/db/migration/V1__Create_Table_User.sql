CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_users
(
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name  VARCHAR(100),
    email  VARCHAR(100),
    gender  VARCHAR(50),
    phone  VARCHAR(30),
    password_hash  VARCHAR(255),
    salt  VARCHAR(255),
    cpf  VARCHAR(14),
    birth_date  TIMESTAMP,
    confirm_email SMALLINT DEFAULT 0
);