package BetaInc;

import Persistencia.Arquivo;
import Util.Formatar;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @Autor m159255
 * @Data 08/10/2018
 */
@ManagedBean
@RequestScoped
public class ClienteBean {

    private String nome;
    private String dtNascimento;
    private String cpf;
    private String rg;
    private String endereco;
    private double qtdReais;
    private List<ClienteBean> cliente = new ArrayList<>();
    Arquivo arquivo;

    public ClienteBean() {
        arquivo = new Arquivo("empresas.txt");
        this.Obter();
    }

    public ClienteBean(String nome, String dtNascimento, String cpf, String rg, String endereco, double qtdReais) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.qtdReais = qtdReais;
    }

    public void add() {
        ClienteBean novo = new ClienteBean(this.nome, Formatar.Data(this.dtNascimento, "yyyy-MM-dd", "dd/MM/yyyy"), this.cpf, this.rg, this.endereco, this.qtdReais);
        cliente.add(novo);
        nome = "";
        dtNascimento = "";
        cpf = "";
        rg = "";
        endereco = "";
        qtdReais = 0.0;
        Salvar();
    }

    public void editar(ClienteBean c) {
        nome = c.getNome();
        dtNascimento = Formatar.Data(c.getDtNascimento(), "dd/MM/yyyy", "yyyy-MM-dd");
        cpf = c.getCpf();
        rg = c.getRg();
        endereco = c.getEndereco();
        qtdReais = c.getQtdReais();
        cliente.remove(c);
        Salvar();
    }

    public void remover(ClienteBean c) {
        cliente.remove(c);
        Salvar();
    }

    private void Obter() {
        arquivo.Ler("@Beta;");
        for (int i = 0; i < Arquivo.Objeto.size(); i++) {
            String linha = Arquivo.Objeto.get(i);
            String col[] = linha.split(Arquivo.Separador);
            ClienteBean novo = new ClienteBean(col[1], col[2], col[3], col[4], col[5], Double.parseDouble(col[6]));
            cliente.add(novo);
        }
    }

    private void Salvar() {
        for (int i = 0; i < cliente.size(); i++) {
            ClienteBean c = cliente.get(i);
            String conteudo = 
                    "@Beta" + Arquivo.Separador 
                    + c.getNome() + Arquivo.Separador 
                    + c.getDtNascimento() + Arquivo.Separador 
                    + c.getCpf() + Arquivo.Separador 
                    + c.getRg() + Arquivo.Separador 
                    + c.getEndereco() + Arquivo.Separador 
                    + c.getQtdReais();
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

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getQtdReais() {
        return qtdReais;
    }

    public void setQtdReais(double qtdReais) {
        this.qtdReais = qtdReais;
    }

    public List<ClienteBean> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteBean> cliente) {
        this.cliente = cliente;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
}
