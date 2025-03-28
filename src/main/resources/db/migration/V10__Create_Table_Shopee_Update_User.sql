CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_java_shopee_shopee_update_users
(
    shopee_update_users_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    shopee_updates_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_shopee_updates_shopee_update_users FOREIGN KEY (shopee_updates_id) REFERENCES tb_shopee_updates (shopee_updates_id) ON DELETE CASCADE,
    CONSTRAINT fk_user_shopee_update_users FOREIGN KEY (user_id) REFERENCES tb_users (user_id) ON DELETE CASCADE
);