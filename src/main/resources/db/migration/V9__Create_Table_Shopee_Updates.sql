CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_updates
(
    shopee_updates_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100) NOT NULL,
    description VARCHAR(130) NOT NULL,
    date TIMESTAMP NOT NULL,
    img  VARCHAR(150) NOT NULL
);