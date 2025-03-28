CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_flash_sale_product_all_infos
(
    flash_sale_product_all_infos_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    products_offer_flash_id UUID NOT NULL,
    product_reviews_rate DOUBLE PRECISION NOT NULL,
    quantity_sold INTEGER NOT NULL,
    favorite_quantity DOUBLE PRECISION NOT NULL,
    quantity_evaluation DOUBLE PRECISION NOT NULL,
    coins INTEGER NULL,
    credit_card VARCHAR(130) NULL,
    voltage VARCHAR(80) NULL,
    quantity_piece INTEGER NULL,
    size VARCHAR(80) NULL,
    product_have_insurance BOOLEAN NOT NULL DEFAULT false,

    CONSTRAINT fk_products_offer_flash_flash_sale_product_all_infos FOREIGN KEY (products_offer_flash_id) REFERENCES tb_products_offer_flash (products_offer_flash_id) ON DELETE CASCADE
);