package entities;

import java.text.ParseException;
import java.util.List;

public interface CRUD {
    void Create(Funcionario funcionario);
    Funcionario Read(String codigoFuncionario);
    void Update(Funcionario funcionario); //adicionado para teste
    void Delete(String codigoFuncionario);
	List<Funcionario> ReadAll();
}