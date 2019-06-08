CREATE TRIGGER deletarFilme
BEFORE DELETE ON Filme
FOR EACH ROW
EXECUTE PROCEDURE deletarFilme();

CREATE FUNCTION deletarFilme() RETURNS TRIGGER AS $$

BEGIN
	delete from Locacoes where old.idFilme = idFilme;
END;

$$ LANGUAGE PLPGSQL;


