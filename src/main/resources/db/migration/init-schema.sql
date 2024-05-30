CREATE TABLE category
(
    id              BIGINT       NOT NULL,
    created_at      datetime     NULL,
    last_updated_at datetime     NULL,
    status          SMALLINT     NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT       NOT NULL,
    created_at      datetime     NULL,
    last_updated_at datetime     NULL,
    status          SMALLINT     NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    price           DOUBLE       NOT NULL,
    category_id     BIGINT       NULL,
    image_url       VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);