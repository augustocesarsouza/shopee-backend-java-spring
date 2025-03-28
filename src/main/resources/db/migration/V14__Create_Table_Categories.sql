CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_categories
(
    categories_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    img_category VARCHAR(130) NOT NULL,
    alt_value VARCHAR(100) NOT NULL,
    title VARCHAR(130) NOT NULL
);