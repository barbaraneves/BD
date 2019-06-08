
-- Visões

-- Visão com Filmes mais Alugados
create or replace view alugados (nome, numero_vezes) as (
select filme.titulo as nome, count(*) AS numero_vezes
from locacoes join filme on (where filme.id = locacoes.id_filme)
group by filme.titulo
);

-- Visão com Clientes que Mais Alugaram Filmes
create or replace view mais_alocou as ( 
select id, nome, cpf, count(id_cliente) as numero_vezes
FROM cliente join locacoes on (id = id_cliente)
group by id, nome
order by numero_vezes desc
);

-- Triggers e Funcão
-- Impede a inserção de uma alocação.

create or replace function impede_alocacao() returns trigger as $$
declare
ano_emprestimo int := (select extract (year from NEW.data_emprestimo) from locacoes where id_cliente = NEW.id_cliente and id_filme = NEW.id_filme);
ano_devolucao int := (select extract (year from NEW.data_devolucao) from locacoes where id_cliente = NEW.id_cliente and id_filme = NEW.id_filme);
begin
if(ano_devolucao < ano_emprestimo or ano_devolucao = null )
then
   raise exception 'Impossível inserir Locação!!';
else
raise notice 'Inserção autorizada!!';
end if;
return NEW;
end;
$$ language plpgsql;

create trigger impede_aluguel
before insert or update on locacoes
for each row execute procedure impede_alocacao();

-- Privilégios do Banco e Autorizações

-- Usuário
create user usuario with password '12345';

-- Adm do Banco
create user adiministrador with password 'adim12345';

-- Papel de Adm
create role adm;

-- Direitos Usuário
grant select on all tables in schema public to usuario;

-- direitos do Adm
grant all privileges on database trabalho_final to adm;


