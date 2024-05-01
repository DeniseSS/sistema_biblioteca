package  com.sistema.biblioteca.model.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sistema.biblioteca.config.DBConfig;
import com.sistema.biblioteca.model.entity.Livro;

public class LivroDAO implements IDAO<Livro> {

  @Override
  public void cadastrar(Livro entidade) {
      try (Connection connection = DBConfig.getConnection();
           PreparedStatement statement = connection.prepareStatement("INSERT INTO livro (titulo, autor, num_paginas) VALUES (?, ?, ?)")) {
          statement.setString(1, entidade.getTitulo());
          statement.setString(2, entidade.getAutor());
          statement.setInt(3, entidade.getNumPaginas());
          statement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

  @Override
  public void atualizar(Livro entidade) {
      try (Connection connection = DBConfig.getConnection();
           PreparedStatement statement = connection.prepareStatement("UPDATE livro SET titulo=?, autor=?, num_paginas=? WHERE id=?")) {
          statement.setString(1, entidade.getTitulo());
          statement.setString(2, entidade.getAutor());
          statement.setInt(3, entidade.getNumPaginas());
          statement.setInt(4, entidade.getId());
          statement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

  @Override
  public void excluir(int id) {
      try (Connection connection = DBConfig.getConnection();
           PreparedStatement statement = connection.prepareStatement("DELETE FROM livro WHERE id=?")) {
          statement.setInt(1, id);
          statement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

  @Override
  public Livro buscar(int id) {
      Livro livro = null;
      try (Connection connection = DBConfig.getConnection();
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM livro WHERE id=?")) {
          statement.setInt(1, id);
          ResultSet resultSet = statement.executeQuery();
          if (resultSet.next()) {
              livro = new Livro(resultSet.getInt("id"), resultSet.getString("titulo"),
                      resultSet.getString("autor"), resultSet.getInt("num_paginas"));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return livro;
  }

  @Override
  public List<Livro> listar() {
      List<Livro> livros = new ArrayList<>();
      try (Connection connection = DBConfig.getConnection();
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM livro")) {
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
              Livro livro = new Livro(resultSet.getInt("id"), resultSet.getString("titulo"),
                      resultSet.getString("autor"), resultSet.getInt("num_paginas"));
              livros.add(livro);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return livros;
  }
}
