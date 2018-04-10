create table escola (
eid integer, 
enome varchar(20), 
local_concentracao varchar(20), 
primary key(eid));

create table quesito (
qid integer, 
qdescricao varchar(20), 
primary key(qid));

create table julgador (
jid integer, 
jnome varchar(20), 
sexo char, 
primary key(jid));

create table nota (
qid integer, 
jid integer, 
eid integer, 
nota real, 
primary key(qid,jid, eid),
constraint nota_fk_1 foreign key (qid) references quesito(qid),
constraint nota_fk_2 foreign key(jid) references julgador(jid),                 
constraint nota_fk_3 foreign key(eid) references escola(eid));

insert into escola values (1, 'Unidos de Quixada', 'Quixada');
insert into escola values (2, 'Num ispaia', 'Morada Nova');
insert into escola values (3, 'Camaleoes da Vila', 'Fortaleza');

insert into quesito values(1, 'enredo');
insert into quesito values(2, 'porta bandeira');

insert into julgador values(1, 'Jonas', 'M');
insert into julgador values(2, 'Maria', 'F');
insert into julgador values(3, 'Joao', 'M');

insert into nota values(1, 1, 1, 10);
insert into nota values(1, 2, 1, 10);
insert into nota values(1, 3, 1, 10);
insert into nota values(2, 1, 1, 8);
insert into nota values(2, 2, 1, 7);
insert into nota values(1, 1, 3, 4);
