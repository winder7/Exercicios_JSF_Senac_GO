package Cachorro;

import Persistencia.Arquivo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @Autor Winder Rezende
 * @Data 23/09/2018, 00:06:18
 */
@ManagedBean
@RequestScoped
public class CachorroBean {

    private String nome;
    private String raca;
    private int idade;
    private List<CachorroBean> cachorro = new ArrayList<>();
    Arquivo arquivo;

    public CachorroBean() {
        arquivo = new Arquivo("animais.txt");
        this.Obter();
    }

    public CachorroBean(String nome, String raca, int idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public void addCachorro() {
        CachorroBean novo = new CachorroBean(this.nome, this.raca, this.idade);
        cachorro.add(novo);
        nome = "";
        raca = "";
        idade = 0;
        Salvar();
    }

    public void removeCadastrado(CachorroBean c) {
        cachorro.remove(c);
        Salvar();
    }

    public void editarCadadatarado(CachorroBean c) {
        nome = c.getNome();
        raca = c.getRaca();
        idade = c.getIdade();
        cachorro.remove(c);
        Salvar();
    }

    private void Obter() {
        arquivo.Ler("@c;");
        for (int i = 0; i < Arquivo.Objeto.size(); i++) {
            String linha = Arquivo.Objeto.get(i);
            String col[] = linha.split(Arquivo.Separador);
            CachorroBean novo = new CachorroBean(col[1], col[2], Integer.parseInt(col[3]));
            cachorro.add(novo);
        }
    }

    private void Salvar() {
        for (int i = 0; i < cachorro.size(); i++) {
            CachorroBean c = cachorro.get(i);
            String conteudo = "@c" + Arquivo.Separador + c.getNome() + Arquivo.Separador + c.getRaca() + Arquivo.Separador + c.getIdade();
            Arquivo.dados.add(conteudo);
        }
        arquivo.Salvar();
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<CachorroBean> getCachorro() {
        return cachorro;
    }

    public void setCachorro(List<CachorroBean> cachorro) {
        this.cachorro = cachorro;
    }
}
