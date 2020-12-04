DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS phone;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR2(200) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    password VARCHAR2(50) NOT NULL
);

CREATE TABLE phone (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    number INT NOT NULL,
    citycode INT NOT NULL,
    countrycode INT NOT NULL,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id)
);
