CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_product_option_image_bottoms
(
    product_option_image_bottoms_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    products_offer_flash_id UUID NOT NULL,
    list_image_url_bottom TEXT[] NOT NULL,

    CONSTRAINT fk_products_offer_flash_product_option_image_bottoms FOREIGN KEY (products_offer_flash_id) REFERENCES tb_products_offer_flash (products_offer_flash_id) ON DELETE CASCADE
);