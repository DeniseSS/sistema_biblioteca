package com.sistema.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConfig {

  private static final String URL = "jdbc:postgresql://localhost:5432/book";

  private static final String USER = "postgres";

  private static final String PASSWORD = "admin";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void createTables() {
    try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {
      String sql = "CREATE TABLE IF NOT EXISTS livro (id SERIAL PRIMARY KEY, titulo VARCHAR(255), autor VARCHAR(255), num_paginas INT)";
      stmt.executeUpdate(sql);
      System.out.println("Tabela criada com sucesso!");
    } catch (SQLException e) {
      System.out.println("Erro ao criar tabela: " + e.getMessage());
    }
  }
}