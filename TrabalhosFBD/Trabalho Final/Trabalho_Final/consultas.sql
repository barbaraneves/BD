-- Consultas

-- 01 - Clientes cadastrados.

select * from cliente;

-- 02 -  Clientes que Alugaram Filmes.

select distinct c.id, c.nome, c.cpf, c.endereco, c.dataNasc 
from cliente as c, locacoes as l
where c.id = l.id_cliente
order by id asc;

-- 03 - Filmes disponíveis => Sem usar Junções.
select titulo, ano_publicacao, id_categoria
from filme
where (id) not in (select  id_filme from locacoes)
order by titulo asc;

-- 04 - Filmes alugados => Usando Junções.
select id, titulo, ano_publicacao 
from filme join alugados on (titulo = nome and (numero_vezes) between 3 and (select count (id_filme) from locacoes)) 
order by numero_vezes desc;

-- 05 - Filmes disponíveis que foram lançados antes de 2010.

select * from filme where ano_publicacao < 2010 order by (ano_publicacao, titulo) desc;

-- 06 - Cliente que mais Alocou => Usando Junções.
select c.id, c.nome, c.cpf, c.endereco, c.dataNasc 
from cliente as c join mais_alocou as m on (c.id = m.id and m.numero_vezes >= all (select numero_vezes from mais_alocou));

-- 07 - Telefones Clientes para um determinado usuário

select c.nome, t.telefone from cliente c, telefone t
where c.id = t.id_cliente and id = 7;


-- 08 - Clientes que mais Alugaram Filmes sem usar a Visão
select id, nome, cpf, endereco, dataNasc from cliente, locacoes where id = id_cliente group by id,nome having (count(id_filme)) >= 3
order by id asc



