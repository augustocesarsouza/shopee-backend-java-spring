CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_product_offer_flash_details
(
    product_offer_flash_details_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    products_offer_flash_id UUID NOT NULL,
    details JSONB NOT NULL,

    CONSTRAINT fk_products_offer_flash_product_offer_flash_details
        FOREIGN KEY (products_offer_flash_id)
            REFERENCES tb_products_offer_flash (products_offer_flash_id)
                ON DELETE CASCADE
);