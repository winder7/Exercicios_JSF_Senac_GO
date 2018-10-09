package BetaInc;

import java.util.ArrayList;
import java.util.Date;
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
    private Date dtNacimento;
    private String endereco;
    private double qtdReais;
    private List<ClienteBean> cliente = new ArrayList<>();
    
    public ClienteBean() {
    }

    public void add(){
    
    }
    
    public void editar(){
    
    }
    
    public void remover(){
    
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNacimento() {
        return dtNacimento;
    }

    public void setDtNacimento(Date dtNacimento) {
        this.dtNacimento = dtNacimento;
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
}
