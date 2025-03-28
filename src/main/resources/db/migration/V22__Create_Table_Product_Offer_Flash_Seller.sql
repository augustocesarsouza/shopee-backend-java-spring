CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_product_offer_flash_sellers
(
    product_offer_flash_sellers_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_seller_product_id UUID NOT NULL,
    products_offer_flash_id UUID NOT NULL,

    CONSTRAINT fk_user_seller_product_product_offer_flash_sellers FOREIGN KEY (user_seller_product_id) REFERENCES tb_user_seller_products (user_seller_products_id) ON DELETE CASCADE
);