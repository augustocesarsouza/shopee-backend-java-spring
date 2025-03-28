CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_user_seller_products
(
    user_seller_products_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(130) NOT NULL,
    img_profile VARCHAR(100) NOT NULL,
    img_floating VARCHAR(100) NOT NULL,
    last_login TIMESTAMP NOT NULL,
    reviews INTEGER NOT NULL,
    chat_response_rate INTEGER NOT NULL,
    account_creation_date TIMESTAMP NOT NULL,
    quantity_of_product_sold INTEGER NOT NULL,
    usually_responds_to_chat_in VARCHAR(100) NOT NULL,
    followers INTEGER NOT NULL
);