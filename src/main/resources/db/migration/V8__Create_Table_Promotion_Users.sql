CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_promotion_users
(
    promotion_users_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    promotion_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_promotion_promotion_users FOREIGN KEY (promotion_id) REFERENCES tb_promotions (promotions_id) ON DELETE CASCADE,
    CONSTRAINT fk_user_promotion_users FOREIGN KEY (user_id) REFERENCES tb_users (user_id) ON DELETE CASCADE
);