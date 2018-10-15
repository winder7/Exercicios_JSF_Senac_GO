package Cobra;

import Persistencia.Arquivo;
import Util.Formatar;
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
public class CobraBean {
    private String nome;
    private String dtCaptura;
    private String tamanho;
    private String peso;
    private List<CobraBean> cobra = new ArrayList<>();
    Arquivo arquivo;
    
    public CobraBean(){
        arquivo = new Arquivo("animais.txt");
        this.Obter();
    }

    public CobraBean(String nome, String dtCaptura, String tamanho, String peso) {
        this.nome = nome;
        this.dtCaptura = dtCaptura;
        this.tamanho = tamanho;
        this.peso = peso;
    }

    public String add(){
        CobraBean novo = new CobraBean(this.nome, Formatar.Data(this.dtCaptura, "yyyy-MM-dd", "dd/MM/yyyy"), this.tamanho, this.peso);
        cobra.add(novo);
        nome = "";
        dtCaptura = "";
        tamanho = "0";
        peso = "0";
        Salvar();
        return "cobralista";
    }
    
    public void removeCadastrado(CobraBean c){
        cobra.remove(c);
        Salvar();
    }
    
    public String editarCadadatarado(CobraBean c){
        nome = c.getNome();
        dtCaptura = Formatar.Data(c.getDtCaptura(), "dd/MM/yyyy", "yyyy-MM-dd");
        tamanho = c.getTamanho();
        peso = c.getPeso();
        cobra.remove(c);
        Salvar();
        return "cobracad";
    }
    
    private void Obter() {
        arquivo.Ler("@cb;");
        for (int i = 0; i < Arquivo.Objeto.size(); i++) {
            String linha = Arquivo.Objeto.get(i);
            String col[] = linha.split(Arquivo.Separador);
            CobraBean novo = new CobraBean(col[1], col[2], col[3], col[4]);
            cobra.add(novo);
        }
    }

    private void Salvar() {
        for (int i = 0; i < cobra.size(); i++) {
            CobraBean c = cobra.get(i);
            String conteudo = "@cb" + Arquivo.Separador + c.getNome() + Arquivo.Separador + c.getDtCaptura() + Arquivo.Separador + c.getTamanho() + Arquivo.Separador + c.getPeso();
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

    public String getDtCaptura() {
        return dtCaptura;
    }

    public void setDtCaptura(String dtCaptura) {
        this.dtCaptura = dtCaptura;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public List<CobraBean> getCobra() {
        return cobra;
    }

    public void setCobra(List<CobraBean> cobra) {
        this.cobra = cobra;
    }
}
