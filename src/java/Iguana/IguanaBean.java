package Iguana;

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
public class IguanaBean {
    private String nome;
    private int idade;
    private List<IguanaBean> iguana = new ArrayList<>();
    Arquivo arquivo;
    
    public IguanaBean(){
        arquivo = new Arquivo("animais.txt");
        this.Obter();
    }

    public IguanaBean(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String addIguana(){
        IguanaBean novo = new IguanaBean(this.nome, this.idade);
        iguana.add(novo);
        nome = "";
        idade = 0;
        Salvar();
        return "iguanalista";
    }
    
    public void removeCadastrado(IguanaBean c){
        iguana.remove(c);
        Salvar();
    }
    
    public String editarCadadatarado(IguanaBean c){
        nome = c.getNome();
        idade = c.getIdade();
        iguana.remove(c);
        Salvar();
        return "iguanacad";
    }
    
    private void Obter() {
        arquivo.Ler("@i;");
        for (int i = 0; i < Arquivo.Objeto.size(); i++) {
            String linha = Arquivo.Objeto.get(i);
            String col[] = linha.split(Arquivo.Separador);
            IguanaBean novo = new IguanaBean(col[1], Integer.parseInt(col[2]));
            iguana.add(novo);
        }
    }

    private void Salvar() {
        for (int i = 0; i < iguana.size(); i++) {
            IguanaBean c = iguana.get(i);
            String conteudo = "@i" + Arquivo.Separador + c.getNome() + Arquivo.Separador + c.getIdade();
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<IguanaBean> getIguana() {
        return iguana;
    }

    public void setIguana(List<IguanaBean> iguana) {
        this.iguana = iguana;
    }
}
