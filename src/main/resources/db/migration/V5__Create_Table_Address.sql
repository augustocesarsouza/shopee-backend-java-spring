CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_address
(
    address_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name  VARCHAR(100) NOT NULL,
    phone_number  VARCHAR(30)  NOT NULL,
    cep  VARCHAR(100)  NOT NULL,
    state_city  VARCHAR(100)  NOT NULL,
    neighborhood  VARCHAR(150)  NOT NULL,
    street  VARCHAR(150)  NOT NULL,
    number_home  VARCHAR(50)  NOT NULL,
    complement  VARCHAR(130),
    default_address  SMALLINT NOT NULL DEFAULT 0,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user_address FOREIGN KEY (user_id) REFERENCES tb_users (user_id) ON DELETE CASCADE
);