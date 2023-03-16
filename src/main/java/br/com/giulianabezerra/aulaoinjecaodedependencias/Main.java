package br.com.giulianabezerra.aulaoinjecaodedependencias;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    new MigracaoUsuario(
        new FileReader(),
        new BdWriter()).migrar();
  }
}

record User(String email, String username, String password) {
}

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

class FileReader implements Reader<User> {
  public List<User> read() {
    System.out.println("Lendo usuários de arquivo...");
    return List.of(new User("email@test.com", "username", "password"));
  }
}

class BdWriter implements Writer<User> {
  public void write(List<User> users) {
    System.out.println("Escrevendo usuários no banco...");
    System.out.println(users);
  }
}
