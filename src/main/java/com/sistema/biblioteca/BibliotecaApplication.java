package  com.sistema.biblioteca;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.biblioteca.config.DBConfig;
import com.sistema.biblioteca.controller.LivroController;
import com.sistema.biblioteca.model.dao.LivroDAO;
import com.sistema.biblioteca.view.LivroView;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

    LivroView livroView = new LivroView();
    LivroDAO livroDAO = new LivroDAO();
    LivroController livroController = new LivroController(livroDAO);

    DBConfig.createTables();

    Scanner scanner = new Scanner(System.in);
    SistemaBiblioteca sistema = new SistemaBiblioteca(livroController, livroView, scanner);

    sistema.iniciar();
	}

}
