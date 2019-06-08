
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


insert into Cliente values 
        (1,'88866555576', 'Jorge',   '1937-11-10','Rua do Horto, 35,São Paulo, SP'),
        (2,'33344555587', 'Fernando','1955-12-08','Rua da lapa, 34, São Paulo, SP'),
        (3,'98765432168', 'Jennifer','1941-06-20','Av. Arthur de Lima, 54, Santo Andre, SP'),
        (4,'12345678966', 'João',    '1965-01-09','Rua das flores, 751, São Paulo, SP'),
        (5,'99988777767', 'Alice',   '1968-01-19','Rua Souza Lima, 35, Curitiba, PR'),
        (6,'66688444476', 'Ronaldo', '1962-09-15','Rua Rebouças,65, Piracicaba, SP'),
        (7,'45345345376', 'Joice',   '1972-07-31','Av. Lucas Obes, 74,São Paulo, SP'),
        (8,'98798798733', 'André',   '1969-03-29','Rua Timbira, 35,São Paulo, SP'),
        (9,'77987654321', 'Frodo',   '1955-07-21','Condado, 666, Terra Média, NZ'),
        (10,'66816623175', 'Legolas', '1949-12-09','Montanha dos Elfos, 77, Floresta Negra, NZ');

insert into Cliente_normal values
        (1,7),
        (2,7),
        (7,7),
        (6,7),
        (5,7);

insert into Cliente_vip values
        (3, 2),
        (4, 2),
        (9, 2),
        (8, 2),
        (10, 2);


insert into Telefone values
    (1,  '08899782334'),
    (1,  '08592871294'),
    (2,  '08597549690'),
    (7,  '08896123871'),
    (10, '08591811032');

insert into Locadora values
    (1, '40432544010147','MegaFilmes'),
    (2, '41398722111564','MxLocações'),
    (3, '98765234123456','Cinemania'),
    (4, '38127378134273','MegaLook'),
    (5, '12313653026622','CineFox'),
    (6, '31876327088823','FoxLocadora'),
    (7, '82347237433073','MFLocation'),
    (8, '73821738233347','Virtual Location'),
    (9, '32874287333858','DCFilmes'),
    (10,'12773542711189','MarvelFilmes');



insert into Categoria values
    (1, 'Aventura'),
    (2, 'Comédia'),
    (3, 'Ficção Cientifíca'),
    (4, 'Guerra'),
    (5, 'Mitologia'),
    (6, 'Ação'),
    (7, 'Suspense'),
    (8, 'Heróis'),
    (9, 'Terror'),
    (10,'Drama');


insert into Ator values
    (1,  'Johnny Depp',   'Jack Sparrow'),
    (2,  'Orlando Broom', 'Will Turner'),
    (3,  'Keira Knightley', 'Elizabeth Swan'),
    (4,  'Rebecca Hall ', 'Carina Smyth'),
    (5,  'Kevin McNally', 'Joshiamee Gibbs '),
    (6,  'Chris Hemsworth', 'Thor'),
    (7,  'Matheus Naschtergaele', 'João Grilo'),
    (8,  'Selton Mello', 'Chicó'),
    (9,  'Denise Fraga', 'Dora'),
    (10, 'Bruno Garcia', 'Vincentão');


insert into Produtora values 
    (1, 'Waner Broos'),
    (2, 'Walt Disney'),
    (3, 'Sony'),
    (4, 'Universal'),
    (5, 'New Line'),
    (6, 'Marvel'),
    (7, 'DC Comics'),
    (8, 'Comlumbia Pictures'),
    (9, 'Rosa Filmes'),
    (10,'Universal Pictures');


insert into Filme values 
    (1, 'Senhor dos Anéis 3 - O Retorno do Rei', 2003, 1),
    (2, 'Fúria de Titãs', 1981, 5),
    (3, 'Senhor dos Anéis 2 - As Duas Torres', 2002, 1),
    (4, 'As Crônicas de Nárnia - Principe Caspian', 2008, 5),
    (5, 'Capitão América 2 - O Soldado Invernal', 2014, 3),
    (6, 'Senhor dos Anéis 1 - A sociedade do Anel', 2001, 1),
    (7, 'Transformers - A era da Extinção', 2014, 3),
    (8, 'Hércules', 2014, 5),
    (9, 'O Resgate do Soldado Ryan', 1998, 4),
    (10, 'Piratas do Caribe - No fim do Mundo', 2007, 6);


insert into Locacoes values  
    (1,7, '2014-11-19','2014-11-24', 20.0), 
    (10,2,'2014-10-28','2014-11-07', 30.0), 
    (1,8, '2014-09-07','2014-09-20', 23.0),        
    (9,3, '2013-07-01','2013-07-16', 30.0),
    (8,4, '2014-09-07','2014-09-14', 30.0), 
    (4,10,'2014-09-07','2014-09-25', 30.9), 
    (1,1, '2014-10-11','2014-10-23', 22.5), 
    (2,8, '2013-01-07','2013-01-10', 20.0), 
    (7,5, '2012-05-07','2012-05-19', 22.5), 
    (5,1, '2011-06-02','2011-06-12', 21.5);


insert into Info_filmes values
    (1,7,3),
    (10,1,5),
    (9,10,1),
    (6,2,10),
    (4,8,6),
    (5,3,8),
    (7,9,2),
    (2,4,9),
    (8,6,7),
    (3,5,5);



-- Privilégios do Banco

-- Usuário
create user usuario with password '12345';

-- Adm do Banco
create user adiministrador with password 'adim12345';

-- Papel de Adm
create role adm;

-- direitos Usuário
grant select on all tables in schema public to usuario;

-- direitos do Adm
grant all privileges on database trabalho_final to adm;

commit;


-- criar trigger -> feito!!
-- função para mostrar clientes e usar join.. feito
-- consultas usando álgebra relacional.. Não vou Fazer!!
-- adicionar código das viewns... feito