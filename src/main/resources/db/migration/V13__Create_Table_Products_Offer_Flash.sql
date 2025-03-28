CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_products_offer_flash
(
    products_offer_flash_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    img_product VARCHAR(130) NOT NULL,
    alt_value VARCHAR(130) NOT NULL,
    img_part_bottom VARCHAR(130) NULL,
    price_product DOUBLE PRECISION NOT NULL,
    popularity_percentage INTEGER NOT NULL,
    discount_percentage INTEGER NOT NULL,
    hour_flash_offer VARCHAR(80) NOT NULL,
    title VARCHAR(120) NOT NULL,
    tag_product VARCHAR(70) NOT NULL
);