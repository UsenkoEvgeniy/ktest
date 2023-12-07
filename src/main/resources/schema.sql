CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    name          VARCHAR(250)                        NOT NULL,
    email         VARCHAR(254)                        NOT NULL,
    creation_date TIMESTAMP                           NOT NULL,
    password      VARCHAR(50)                         NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_user_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS quotes
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    content       VARCHAR(7000)                       NOT NULL,
    creation_date TIMESTAMP                           NOT NULL,
    modified_date TIMESTAMP,
    author_id     BIGINT                              NOT NULL,
    CONSTRAINT pk_quotes PRIMARY KEY (id),
    CONSTRAINT fk_quotes_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS quotes_votes
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_id       BIGINT,
    quote_id      BIGINT,
    vote          INTEGER,
    creation_date TIMESTAMP                           NOT NULL,
    CONSTRAINT pk_quotes_likes PRIMARY KEY (id),
    CONSTRAINT fk_ql_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_ql_quote_id FOREIGN KEY (quote_id) REFERENCES quotes (id) ON DELETE CASCADE,
    CONSTRAINT uq_ql_user_quote UNIQUE (user_id, quote_id)
);
