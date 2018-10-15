package Teste;

/**
 * @Autor Winder Rezende
 * @Data  10/10/2018
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PersistirArquivo {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:/arquivo.txt");
        List<String> linhas = Files.readAllLines(path);

        for (int i = 0; i < linhas.size(); i++) {
            System.out.println(linhas.get(i));
        }
//        String novoConteudo = 
//            linhas.get(0).substring(0, 7) + "conteudo" + linhas.get(0).substring(15);
//
//        linhas.remove(0);
//        linhas.add(0, novoConteudo);

        Files.write(path, linhas);
    }
}
