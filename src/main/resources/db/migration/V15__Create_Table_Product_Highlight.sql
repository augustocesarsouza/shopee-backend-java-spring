CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_product_highlights
(
    product_highlights_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(130) NOT NULL,
    img_product VARCHAR(130) NOT NULL,
    img_top VARCHAR(130) NOT NULL,
    quantity_sold DOUBLE PRECISION NOT NULL
);