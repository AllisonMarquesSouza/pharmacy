CREATE TABLE medicine(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE ,
    via ENUM('ORAL', 'NASAL', 'VENOSO', 'INTRAMUSCULAR', 'RETAL') NOT NULL,
    quantity INT NOT NULL,
    laboratory ENUM('MEDLEY', 'ACHE') NOT NULL,
    validity DATE NOT NULL,
    active TINYINT(1) NOT NULL DEFAULT 0
);