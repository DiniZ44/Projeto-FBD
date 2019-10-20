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
public class Cliente {
    private Integer id;
    private String nome;
    private String cpf;
    private String contato;
    private String endereco;

    public String getContato() {
        return contato;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
