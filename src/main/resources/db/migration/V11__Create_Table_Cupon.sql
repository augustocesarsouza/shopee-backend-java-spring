CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_cupons
(
    cupons_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_text VARCHAR(130) NOT NULL,
    second_text VARCHAR(130) NOT NULL,
    third_text VARCHAR(130) NOT NULL,
    date_validate_cupon TIMESTAMP NOT NULL,
    quantity_cupons INTEGER NOT NULL,
    what_cupon_number SMALLINT NOT NULL,
    second_img  VARCHAR(150) NOT NULL
);