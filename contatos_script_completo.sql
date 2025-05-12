USE product_api;
-- Insere dados na tabela product
INSERT INTO product (name, description, price, quantity) VALUES
('Caneta Azul', 'Caneta esferográfica azul', 2.50, 100),
('Caderno Espiral', 'Caderno com 100 folhas', 12.00, 50),
('Lápis HB', 'Lápis grafite escolar', 1.20, 200),
('Borracha Branca', 'Borracha macia para apagar lápis', 0.80, 150),
('Apontador', 'Apontador com depósito', 1.50, 120),
('Régua 30cm', 'Régua de plástico transparente', 2.00, 80),
('Tesoura Escolar', 'Tesoura sem ponta', 3.20, 60),
('Cola Branca', 'Cola branca líquida escolar', 2.80, 90),
('Caderno Universitário', 'Caderno 200 folhas capa dura', 18.00, 40),
('Marca-texto Amarelo', 'Marca-texto fluorescente', 3.00, 70),
('Canetinha Colorida', 'Conjunto com 12 cores', 10.00, 50),
('Compasso', 'Compasso para desenho técnico', 5.50, 30),
('Papel Sulfite A4', 'Resma com 500 folhas', 22.00, 20),
('Envelope Pardo', 'Envelope para documentos', 0.60, 100),
('Bloco de Notas', 'Bloco adesivo pequeno', 4.50, 85),
('Grampeador', 'Grampeador pequeno com grampos', 8.00, 35),
('Clips', 'Clips de metal 100 unidades', 2.00, 95),
('Pasta L', 'Pasta plástica transparente', 1.70, 60),
('Pasta Sanfonada', 'Pasta organizadora com 12 divisões', 9.50, 25),
('Caneta Preta', 'Caneta esferográfica preta', 2.50, 100),
('Caneta Vermelha', 'Caneta esferográfica vermelha', 2.50, 100),
('Corretivo', 'Corretivo em fita', 6.00, 40),
('Marcador Quadro Branco', 'Marcador azul para quadro branco', 4.00, 55),
('Lousa Pequena', 'Lousa branca individual', 15.00, 10),
('Estojo Escolar', 'Estojo com zíper', 12.00, 25),
('Pincel', 'Pincel para pintura escolar', 3.80, 30),
('Tinta Guache', 'Conjunto com 6 cores', 7.90, 18),
('Caderno de Desenho', 'Caderno com folhas A4 para desenho', 14.00, 22),
('Caneta Gel', 'Caneta gel preta', 4.20, 40),
('Cartolina Colorida', 'Folha de cartolina A3', 1.10, 60);


-- Criação da tabela contatos
CREATE TABLE contatos (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone BIGINT NOT NULL,
    aniversario DATE NOT NULL
);

-- Inserção dos dados
INSERT INTO contatos (id, nome, telefone, aniversario) VALUES
(1, 'Maria', 912345678, '2001-03-20'),
(2, 'João', 312345679, '1980-01-09'),
(3, 'Pedro', 312345671, '1999-11-12'),
(4, 'Ana', 321234568, '2000-12-01'),
(5, 'Fernando', 312345679, '2003-05-12'),
(6, 'Pedro', 315461549, '2000-01-29');

-- 1. Telefones duplicados com nomes e quantidade
SELECT c.nome, c.telefone, t.quantidade
FROM contatos c
JOIN (
    SELECT telefone, COUNT(*) AS quantidade
    FROM contatos
    GROUP BY telefone
    HAVING COUNT(*) > 1
) t ON c.telefone = t.telefone;

-- 2. Pessoa com maior idade
SELECT nome
FROM contatos
ORDER BY aniversario ASC
LIMIT 1;

-- 3. Pessoas com DDD 31
SELECT nome
FROM contatos
WHERE telefone DIV 10000000 = 31;

-- 4. Três pessoas mais novas
SELECT nome
FROM contatos
ORDER BY aniversario DESC
LIMIT 3;