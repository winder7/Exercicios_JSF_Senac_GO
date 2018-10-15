package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @Autor Winder Rezende
 * @Data 13/10/2018
 */
public class Arquivo {
    private String CaminhoArq;
    public static String Separador = ";";
    public static List<String> Objeto;
    public static List<String> dados;

    public Arquivo(String nomeArquivo) {
        try {
            // obtem percurso para o arquivo
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            CaminhoArq = ec.getRealPath("/");
            if (CaminhoArq.endsWith("\\") == false) {
                CaminhoArq += "\\";
            }
            CaminhoArq += "resources\\" + nomeArquivo;
            System.out.println(CaminhoArq);
        } catch (Exception ex) {
            System.out.println("Erro Construtor de TextFileOnResource:" + ex.getMessage());
            CaminhoArq = null;
        }
    }

    public void Ler(String tipo) {
        Objeto = new ArrayList<>();
        dados = new ArrayList<>();
        try {
            BufferedReader ler = new BufferedReader(new FileReader(CaminhoArq));
            String linha = "";
            while (ler.ready()) {
                linha = ler.readLine();
                if (linha != null && linha.contains(tipo) && !linha.equals("")) {
                    Objeto.add(linha);
                } else if (linha != null && !linha.equals("")) {
                    dados.add(linha);
                }
            }
            ler.close();
        } catch (IOException e) {
            System.out.println("Classe Aquivo Ler:" + e);
        }
    }

    public void Salvar() {
        File caminhoArq = new File(CaminhoArq);       
        try {
            caminhoArq.createNewFile();
            Path path = Paths.get(caminhoArq.toString());
            Files.write(path, dados);
        } catch (IOException e) {
            System.out.println("Classe Aquivo Gravar:" + e);
        }
    }

    //Teste
    public static void main(String[] args) {
        Arquivo a = new Arquivo("animais.txt");
        a.Ler("c;");
        for (int i = 0; i < Objeto.size(); i++) {
            System.out.println(Objeto.get(i));
            String linha = Arquivo.Objeto.get(i);
            String col[] = linha.split(Arquivo.Separador);
            System.out.println(Arrays.toString(col));
        }
        //a.adicionaLinha("c;f;g");
    }
}
