package Gato;

import Persistencia.Arquivo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @Autor Winder Rezende
 * @Data  23/09/2018, 00:06:18
 */
@ManagedBean
@RequestScoped
public class GatoBean {
    private String nome;
    private String raca;
    private int idade;
    private List<GatoBean> gato = new ArrayList<>();
    Arquivo arquivo;
    
    public GatoBean(){
        arquivo = new Arquivo("animais.txt");
        this.Obter();
    }

    public GatoBean(String nome, String raca, int idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public void addGato(){
        GatoBean novo = new GatoBean(this.nome, this.raca, this.idade);
        gato.add(novo);
        nome = "";
        raca = "";
        idade = 0;
        Salvar();
    }
    
    public String listarGato(){
        return "gatolista";
    }
    
    public void removeCadastrado(GatoBean c){
        gato.remove(c);
        Salvar();
    }
    
    public String editarCadadatarado(GatoBean c){
        nome = c.getNome();
        raca = c.getRaca();
        idade = c.getIdade();
        gato.remove(c);
        Salvar();
        return "gatocad";
    }
    
    private void Obter() {
        arquivo.Ler("@g;");
        for (int i = 0; i < Arquivo.Objeto.size(); i++) {
            String linha = Arquivo.Objeto.get(i);
            String col[] = linha.split(Arquivo.Separador);
            GatoBean novo = new GatoBean(col[1], col[2], Integer.parseInt(col[3]));
            gato.add(novo);
        }
    }

    private void Salvar() {
        for (int i = 0; i < gato.size(); i++) {
            GatoBean c = gato.get(i);
            String conteudo = "@g" + Arquivo.Separador + c.getNome() + Arquivo.Separador + c.getRaca() + Arquivo.Separador + c.getIdade();
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

    public List<GatoBean> getGato() {
        return gato;
    }

    public void setGato(List<GatoBean> gato) {
        this.gato = gato;
    }
}
