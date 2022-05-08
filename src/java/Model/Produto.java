/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author nathi
 */
package Model;

public class Produto {

    protected int id;
    protected String nome;
    protected double valor;
    protected String descricao;
    protected double desconto;
    protected double valorPosDesconto;

    public Produto() {
    }

    public Produto(int id, String nome, double valor, String descricao, double desconto) {
        super();
        this.id = id;
        
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
        setDesconto(desconto);
    }

    public Produto(int id, String nome, double valor, String descricao, double desconto, double valorPosDesconto) {
        super();
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.desconto = desconto;
        this.valorPosDesconto = valorPosDesconto;
    }

    public Produto(String nome, double valor, String descricao, double desconto) {
        super();
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
        setDesconto(desconto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        setValorPosDesconto(this.valor, this.desconto);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
        setValorPosDesconto(this.valor, this.desconto);
    }

    private void setValorPosDesconto(double valor, double desconto) {
        if (desconto > 0 && valor > 0) {
            double valorDoDesconto = (desconto / 100) * valor;
            this.valorPosDesconto = valor - valorDoDesconto;
        }
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", valor=" + valor + ", descricao=" + descricao + ", desconto=" + desconto + ", valorPosDesconto=" + valorPosDesconto + '}';
    }

    public double getValorPosDesconto() {
        return valorPosDesconto;
    }
}
