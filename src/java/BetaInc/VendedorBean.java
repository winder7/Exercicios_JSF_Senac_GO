package BetaInc;

import Util.Formatar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 * @Autor m159255
 * @Data 08/10/2018
 */
@ManagedBean
//@RequestScoped
@SessionScoped
public class VendedorBean {
    private String nome;
    private String dtNascimento;
    private String endereco;
    private List<ClienteBean> cliente = new ArrayList<>();
    private List<VendedorBean> vendedor = new ArrayList<>();
    
    
    public VendedorBean() {
    }

    public VendedorBean(String nome, String dtNascimento, String endereco, List<ClienteBean> cliente) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
        this.cliente = cliente;
    }
    
    public void add(){
        VendedorBean novo = new VendedorBean(this.nome, Formatar.Data(this.dtNascimento, "yyyy-MM-dd", "dd/MM/yyyy"), this.endereco, this.cliente);
        vendedor.add(novo);
        nome = "";
        dtNascimento = "";
        cliente = null;
        
    }
    
    public void editar(VendedorBean v){
        nome = v.getNome();
        dtNascimento = Formatar.Data(v.getDtNascimento(), "dd/MM/yyyy", "yyyy-MM-dd");
        endereco = v.getEndereco();
        vendedor = null;
        vendedor.remove(v);
    }
    
    //Getters e Setters
    public void remover(VendedorBean v){
        vendedor.remove(v);
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<ClienteBean> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteBean> cliente) {
        this.cliente = cliente;
    }

    public List<VendedorBean> getVendedor() {
        return vendedor;
    }

    public void setVendedor(List<VendedorBean> vendedor) {
        this.vendedor = vendedor;
    }
}
