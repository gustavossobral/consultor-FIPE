CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          telefone BIGINT NOT NULL,
                          cpf BIGINT NOT NULL UNIQUE,

                          logradouro VARCHAR(255) NOT NULL,
                          numero SMALLINT,
                          complemento VARCHAR(100),
                          bairro VARCHAR(100) NOT NULL,
                          cidade VARCHAR(100) NOT NULL,
                          uf CHAR(2) NOT NULL,
                          cep BIGINT NOT NULL
);