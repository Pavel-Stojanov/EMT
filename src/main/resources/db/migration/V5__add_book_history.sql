CREATE TABLE book_history
(
    id BIGINT NOT NULL,
    CONSTRAINT pk_bookhistory PRIMARY KEY (id)
);

CREATE TABLE book_history_descriptions
(
    book_id     BIGINT NOT NULL,
    description VARCHAR(255)
);

ALTER TABLE book_history_descriptions
    ADD CONSTRAINT fk_book_history_descriptions_on_book_history FOREIGN KEY (book_id) REFERENCES book_history (id);