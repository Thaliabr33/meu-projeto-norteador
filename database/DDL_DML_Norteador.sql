

CREATE TABLE Cliente
(
  id_cliente INT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  tipo_pessoa VARCHAR(50) NOT NULL,
  telefone VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE Veiculo 
(
  id_veiculo INT NOT NULL,
  marca VARCHAR(50) NOT NULL,
  modelo VARCHAR(50) NOT NULL,
  categoria VARCHAR(50) NOT NULL,
  id_cliente INT NOT NULL,
  PRIMARY KEY (id_veiculo),
  FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE Servico 
(
  id_servico INT NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  valor_padrao DECIMAL(10,2) NOT NULL,
  tarifa_por_categoria INT NOT NULL,
  PRIMARY KEY (id_servico)
);

CREATE TABLE OrdemServico
(
  id_os INT NOT NULL,
  data DATE NOT NULL,
  status VARCHAR(50) NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  desconto DECIMAL(10,2) NOT NULL,
  id_veiculo INT NOT NULL,
  PRIMARY KEY (id_os),
  FOREIGN KEY (id_veiculo) REFERENCES Veiculo (id_veiculo)
);

CREATE TABLE ItemServico
(
  id_item INT NOT NULL,
  valor_cobrado DECIMAL(10,2) NOT NULL,
  pontos_gerados INT NOT NULL,
  id_servico INT NOT NULL,
  id_os INT NOT NULL,
  PRIMARY KEY (id_item),
  FOREIGN KEY (id_servico) REFERENCES Servico (id_servico),
  FOREIGN KEY (id_os) REFERENCES OrdemServico(id_os)
);



INSERT INTO Cliente (id_cliente, nome, tipo_pessoa, telefone, email) VALUES 
(1, 'Thalia Lara', 'Fisica', '48999999999', 'tata@email.com'),
(2, 'Gustavo Luz', 'Juridica', '4833333333', 'gugu@email.com'),
(3, 'Joana', 'Fisica', '4888888888', 'jojo@email.com');

SELECT * FROM Cliente;



INSERT INTO Veiculo  (id_veiculo, marca, modelo, categoria, id_cliente) VALUES 
(1, 'Ford', 'Ka', 'Pequeno', 1),
(2, 'Chevrolet', 'Onix', 'Médio', 2),
(3, 'Yamaha', 'Fazer 250', 'Moto', 3);

SELECT * FROM Veiculo ;




INSERT INTO Servico  (id_servico, descricao, valor_padrao, tarifa_por_categoria) VALUES 
(1, 'Lavação completa', 80, 1),
(2, 'Impermeabilização de bancos', 150, 1),
(3, 'Polimento de rodas', 50, 0); 


SELECT * FROM Servico ;



INSERT INTO OrdemServico (id_os, data, status, total, desconto, id_veiculo) VALUES 
(1, '20250724', 'ABERTA', 130, 0, 1),
(2, '20250724', 'FECHADA', 150, 10, 2),
(3, '20250724', 'CANCELADA', 0, 0, 3);

SELECT * FROM OrdemServico;


INSERT INTO ItemServico (id_item, valor_cobrado, pontos_gerados, id_servico, id_os) VALUES 
(1, 80, 10, 1, 1),
(2, 50, 5, 3, 1),
(3, 150, 15, 2, 2);

SELECT * FROM ItemServico;




SELECT * FROM Cliente;
SELECT * FROM Veiculo ;
SELECT * FROM Servico ;
SELECT * FROM OrdemServico;
SELECT * FROM ItemServico;
