begin;

create database trabalho_final;


CREATE TABLE cliente (
                iD int not null,
                cpf char (11),
                nome varchar(50),
                dataNasc date ,
                endereco varchar(50),  -- Alterei, tem que alterar no MER.
                PRIMARY KEY (iD)
);


CREATE TABLE clienteNormal (
                iDCliente int not null,
                diasPrazo int,
                PRIMARY KEY (iDCliente),
                FOREIGN KEY (iDCliente) REFERENCES Cliente (iD) on delete cascade on update cascade
);


CREATE TABLE clienteVip (
                iDCliente int not null,
                qtdSemana int,
                PRIMARY KEY (iDCliente),
                FOREIGN KEY (iDCliente) REFERENCES Cliente (iD) on delete cascade on update cascade
);


CREATE TABLE Telefone (
                iDCliente int not null,
                telefone char(11),
                PRIMARY KEY (iDCliente, telefone),
                FOREIGN KEY (iDCliente) REFERENCES Cliente (iD) on delete cascade on update cascade
);


CREATE TABLE Locadora (
                iD int not null,
                cnpj char(15) not null, 
                nome varchar(50),
                PRIMARY KEY (iD)
);


CREATE TABLE Categoria (
                iD int not null,
                nome varchar(50),
                PRIMARY KEY (iD)
);


CREATE TABLE Ator (
                iD int not null,
                nome varchar(50) not null,
                nomeArtstico varchar(50),
                PRIMARY KEY (iD)
);


CREATE TABLE Produtora (
                iD int not null,
                nome varchar(50),
                PRIMARY KEY (iD)
);


CREATE TABLE Filme (
                iD int not null,
                titulo varchar(50),
                anoPublicacao int,
                IDCategoria int,
                PRIMARY KEY (iD),
                FOREIGN KEY (IDCategoria) REFERENCES Categoria (iD)
);


CREATE TABLE Locacoes (
                iDCliente int not null,
                iDFilme int not null,
                dataEmprestimo date,
                dataDevolucao date,
                valorPago decimal (10,2), -- Alterei, tem que alterar no MER. 
                PRIMARY KEY (iDCliente, iDFilme),
                FOREIGN KEY (iDCliente) REFERENCES Cliente (iD),  
                FOREIGN KEY (iDFilme) REFERENCES Filme (iD)

);


CREATE TABLE InfoFilmes (
                iDAtor int not null,
                iDFilme int not null,
                iDProdutora int not null,
                PRIMARY KEY (iDAtor, iDFilme, iDProdutora),
                FOREIGN KEY (iDAtor) REFERENCES Ator (iD),
                FOREIGN KEY (iDProdutora) REFERENCES Produtora (iD),
                FOREIGN KEY (iDFilme) REFERENCES Filme (iD)
);

commit;


