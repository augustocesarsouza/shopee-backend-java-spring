CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_user_cupons
(
    user_cupons_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cupon_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_cupon_user_cupons FOREIGN KEY (cupon_id) REFERENCES tb_cupons (cupons_id) ON DELETE CASCADE,
    CONSTRAINT fk_user_user_cupons FOREIGN KEY (user_id) REFERENCES tb_users (user_id) ON DELETE CASCADE
);