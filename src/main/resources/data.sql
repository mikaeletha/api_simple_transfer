INSERT INTO users (full_name, cpf_cnpj, email, password, user_type, created_at, updated_at)
VALUES
('Maria Silva', '11111111111', 'maria@email.com', '123456', 'COMMON', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('João Santos', '22222222222', 'joao@email.com', '123456', 'COMMON', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Loja Exemplo', '33333333000199', 'loja@email.com', '123456', 'MERCHANT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO wallets (balance, user_id, created_at, updated_at)
VALUES
(1000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(500.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(0.00, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);