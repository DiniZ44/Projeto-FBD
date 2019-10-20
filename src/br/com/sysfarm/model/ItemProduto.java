/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.model;

/**
 *
 * @author santo
 */
public class ItemProduto {
    private Integer id;
    private Float valor_unitario;
    private Float valor;
    private int qtd;
    private String data_comp;
    private String data_venc;
    private Produto produto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }



    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }


    public String getData_comp() {
        return data_comp;
    }

    public void setData_comp(String data_comp) {
        this.data_comp = data_comp;
    }

    public String getData_venc() {
        return data_venc;
    }

    public void setData_venc(String data_venc) {
        this.data_venc = data_venc;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
