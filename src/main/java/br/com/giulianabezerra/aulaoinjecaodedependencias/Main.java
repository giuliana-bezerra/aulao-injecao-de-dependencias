package br.com.giulianabezerra.aulaoinjecaodedependencias;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    new MigracaoUsuario().migrar();
  }
}

record User(String email, String username, String password) {
}

class MigracaoUsuario {
  FileReader reader = new FileReader();
  BdWriter writer = new BdWriter();

  public void migrar() {
    // Ler dados em A
    List<User> users = reader.read();
    // Escrever dados em B
    writer.write(users);
  }
}

class FileReader {
  List<User> read() {
    System.out.println("Lendo usuários de arquivo...");
    return List.of(new User("email@test.com", "username", "password"));
  }
}

class BdWriter {
  void write(List<User> users) {
    System.out.println("Escrevendo usuários no banco...");
    System.out.println(users);
  }
}
