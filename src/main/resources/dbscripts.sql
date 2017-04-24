CREATE TABLE shoppingIte1 (
    id bigserial NOT NULL PRIMARY KEY,
    nume character(500),
    cantitate int,
    persoana character(500),
    pret numeric(7,2),
    adauga DATE not null default CURRENT_DATE,
);

select (nume,cantitate) from shoppingitem1 where persoana='darius'
