CREATE TABLE carros (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        modelo VARCHAR(255),
                        marca VARCHAR(255),
                        ano_fabricacao SMALLINT,
                        quilometragem INT,
                        qtd_donos TINYINT,
                        preco DECIMAL(19, 2),
                        cor VARCHAR(255),
                        combustivel VARCHAR(255),
                        observacoes TEXT,
                        data_de_cadastro TIMESTAMP,
                        status VARCHAR(50)
);