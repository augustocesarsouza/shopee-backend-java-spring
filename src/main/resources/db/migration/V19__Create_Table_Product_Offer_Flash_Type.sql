CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_product_offer_flash_types
(
    product_offer_flash_types_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    img_product VARCHAR(130) NOT NULL,
    option_type VARCHAR(100) NOT NULL,
    products_offer_flash_id UUID NOT NULL,
    title_img VARCHAR(100) NULL,

    CONSTRAINT fk_products_offer_flash_product_offer_flash_types FOREIGN KEY (products_offer_flash_id) REFERENCES tb_products_offer_flash (products_offer_flash_id) ON DELETE CASCADE
);