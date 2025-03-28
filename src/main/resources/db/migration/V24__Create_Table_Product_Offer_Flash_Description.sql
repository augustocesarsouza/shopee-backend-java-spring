CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_product_offer_flash_descriptions
(
    product_offer_flash_descriptions_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    products_offer_flash_id UUID NOT NULL,
    descriptions VARCHAR(5000) NOT NULL,

    CONSTRAINT fk_products_offer_flash_product_offer_flash_descriptions
        FOREIGN KEY (products_offer_flash_id)
            REFERENCES tb_products_offer_flash (products_offer_flash_id)
                ON DELETE CASCADE
);