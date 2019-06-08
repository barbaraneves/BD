


--Mostrar filmes disponivéis.
select distinct f.titulo from cliente as c, filme as f where (f.id) not in (select distinct id_filme from locacoes);


-- Mostra cliente que mais alugou filme.

select nome, numero_vezes
from mais_alocou
where numero_vezes >= all (select numero_vezes from mais_alocou)

-- Mostra filmes mais alugados.

select nome, numero_vezes
from mais_alugados
where numero_vezes >= all (select numero_vezes from mais_alugados)

-- Mostra filmes por uma determinada categoria.
select  titulo from filme where id_categoria = 1; -- Categoria passada no lugar de 1	

-- Filmes mais antigos
select titulo, ano_publicacao 
from filme
where ano_publicacao <= 2010
order by (ano_publicacao) asc;