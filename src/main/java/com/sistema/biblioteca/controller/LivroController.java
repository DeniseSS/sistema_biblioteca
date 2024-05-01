package  com.sistema.biblioteca.controller;

import java.util.List;

import com.sistema.biblioteca.model.dao.IDAO;
import com.sistema.biblioteca.model.entity.Livro;

public class LivroController {

  private final IDAO<Livro> livroDAO;

  public LivroController(IDAO<Livro> livroDAO) {
    this.livroDAO = livroDAO;
  }

  public String cadastrarLivro(Livro livro) {
    livroDAO.cadastrar(livro);
    return "Livro cadastrado com sucesso!";
  }

  public String atualizarLivro(Livro livro) {
    livroDAO.atualizar(livro);
    return "Livro atualizado com sucesso!";
  }

  public String excluirLivro(int id) {
    livroDAO.excluir(id);
    return "Livro excluído com sucesso!";
  }

  public Livro buscarLivro(int id) {
    return (Livro) livroDAO.buscar(id);
  }

  public List<Livro> listarLivros() {
    return livroDAO.listar();
  }
}
