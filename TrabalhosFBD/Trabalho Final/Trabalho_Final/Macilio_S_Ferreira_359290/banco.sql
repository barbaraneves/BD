begin;

create database trabalho_final;


CREATE TABLE Cliente (
                id int not null,
                cpf char (11),
                nome varchar(50),
                dataNasc date ,
                endereco varchar(50),  -- Alterei, tem que alterar no MER.
                PRIMARY KEY (id)
);


CREATE TABLE Cliente_normal (
                id_cliente int not null,
                dias_prazo int,
                PRIMARY KEY (id_cliente),
                FOREIGN KEY (id_cliente) REFERENCES Cliente (id) on delete cascade on update cascade
);


CREATE TABLE Cliente_vip (
                id_cliente int not null,
                qtd_semana int,
                PRIMARY KEY (id_cliente),
                FOREIGN KEY (id_cliente) REFERENCES Cliente (id) on delete cascade on update cascade
);


CREATE TABLE Telefone (
                id_cliente int not null,
                telefone char(11),
                PRIMARY KEY (id_cliente, telefone),
                FOREIGN KEY (id_cliente) REFERENCES Cliente (id) on delete cascade on update cascade
);


CREATE TABLE Locadora (
                id int not null,
                cnpj char(15) not null, 
                nome varchar(50),
                PRIMARY KEY (id)
);


CREATE TABLE Categoria (
                id int not null,
                nome varchar(50),
                PRIMARY KEY (id)
);


CREATE TABLE Ator (
                id int not null,
                nome varchar(50) not null,
                nome_artstico varchar(50),
                PRIMARY KEY (id)
);


CREATE TABLE Produtora (
                id int not null,
                nome varchar(50),
                PRIMARY KEY (id)
);


CREATE TABLE Filme (
                id int not null,
                titulo varchar(50),
                ano_publicacao int,
                id_categoria int,
                PRIMARY KEY (id),
                FOREIGN KEY (id_categoria) REFERENCES Categoria (id)
);


CREATE TABLE Locacoes (
                id_cliente int not null,
                id_filme int not null,
                data_emprestimo date,
                data_devolucao date,
                valor_pago decimal (10,2), -- Alterei, tem que alterar no MER. 
                PRIMARY KEY (id_cliente, id_filme),
                FOREIGN KEY (id_cliente) REFERENCES Cliente (id),  
                FOREIGN KEY (id_filme) REFERENCES Filme (id)

);


CREATE TABLE Info_filmes (
                id_ator int not null,
                id_filme int not null,
                id_produtora int not null,
                PRIMARY KEY (id_ator, id_filme, id_produtora),
                FOREIGN KEY (id_ator) REFERENCES Ator (id),
                FOREIGN KEY (id_produtora) REFERENCES Produtora (id),
                FOREIGN KEY (id_filme) REFERENCES Filme (id)
);

commit;


