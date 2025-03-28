CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_product_flash_sale_reviews
(
    product_flash_sale_reviews_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    message VARCHAR(300) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cost_benefit VARCHAR(100) NOT NULL,
    similar_to_ad VARCHAR(100) NOT NULL,
    star_quantity INTEGER NOT NULL,
    img_and_video_reviews_product TEXT[] NULL,
    products_offer_flash_id UUID NOT NULL,
    user_id UUID NOT NULL,
    variation VARCHAR(100) NOT NULL,

    CONSTRAINT fk_products_offer_flash_product_flash_sale_reviews
        FOREIGN KEY (products_offer_flash_id)
            REFERENCES tb_products_offer_flash (products_offer_flash_id)
                ON DELETE CASCADE,

    CONSTRAINT fk_user_id_product_flash_sale_reviews
            FOREIGN KEY (user_id)
                REFERENCES tb_users (user_id)
                    ON DELETE CASCADE
);