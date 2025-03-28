CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_product_discoveries_of_days
(
    product_discoveries_of_days_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(130) NOT NULL,
    img_product VARCHAR(130) NOT NULL,
    img_part_bottom VARCHAR(130) NULL,
    discount_percentage INTEGER NULL,
    is_ad BOOLEAN NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    quantity_sold DOUBLE PRECISION NULL
);