package br.com.giulianabezerra.aulaoinjecaodedependencias;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  ApplicationRunner runner(MigracaoUsuario migracaoUsuario) {
    return args -> migracaoUsuario.migrar();
  }
}

record User(String email, String username, String password) {
}

@Component
class MigracaoUsuario {
  Reader<User> reader;
  Writer<User> writer;

  public MigracaoUsuario(Reader<User> reader, Writer<User> writer) {
    this.reader = reader;
    this.writer = writer;
  }

  public void migrar() {
    // Ler dados em A
    List<User> users = reader.read();
    // Escrever dados em B
    writer.write(users);
  }
}

interface Reader<T> {
  List<T> read();
}

interface Writer<T> {
  void write(List<T> itens);
}

@Component
class FileReader implements Reader<User> {
  public List<User> read() {
    System.out.println("Lendo usuários de arquivo...");
    return List.of(new User("email@test.com", "username", "password"));
  }
}

@Component
class BdWriter implements Writer<User> {
  public void write(List<User> users) {
    System.out.println("Escrevendo usuários no banco...");
    System.out.println(users);
  }
}
