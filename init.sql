-- Tabela Użytkowników (Klientów Banku)
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    pesel VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Kont Bankowych
CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(26) UNIQUE NOT NULL,
    balance DECIMAL(19, 2) DEFAULT 0.00 CHECK (balance >= 0), -- Zabezpieczenie przed debetem na poziomie bazy!
    currency VARCHAR(3) DEFAULT 'PLN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tabela Transakcji (Historia Przelewów)
CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    sender_account_id BIGINT,
    receiver_account_id BIGINT,
    amount DECIMAL(19, 2) NOT NULL CHECK (amount > 0),
    title VARCHAR(255) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_account_id) REFERENCES accounts(id),
    FOREIGN KEY (receiver_account_id) REFERENCES accounts(id)
);

-- Wylanie pierwszego betonu (Dane startowe)
INSERT INTO users (first_name, last_name, pesel, email) 
VALUES ('Jan', 'Kowalski', '90010112345', 'jan.kowalski@example.com');

INSERT INTO accounts (user_id, account_number, balance, currency) 
VALUES (1, '11222233334444555566667777', 5000.00, 'PLN');