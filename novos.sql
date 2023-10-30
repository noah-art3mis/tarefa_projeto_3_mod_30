ALTER TABLE tb_cliente
ADD COLUMN email varchar(50);

ALTER TABLE tb_produto
ADD COLUMN fabricante varchar(50);

CREATE TABLE tb_estoque (
    id serial PRIMARY KEY,
    produto_id bigint REFERENCES tb_produto(id),
    quantidade int NOT NULL DEFAULT 0
);

ALTER TABLE tb_estoque
ADD CHECK (quantidade >= 0);