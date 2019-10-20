/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.dao;


import br.com.sysfarm.model.Fornecedor;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public interface IFornecedorDAO {
    public void salvar(Fornecedor fornecedor) throws Exception;
    public Fornecedor buscar(Fornecedor cliente) throws Exception;
    public Fornecedor buscarPorCPF(Fornecedor cliente) throws Exception;
    public Fornecedor buscraPorNome(Fornecedor cliente) throws Exception;
    public Fornecedor editar(Fornecedor cliente) throws Exception;
    public ArrayList<String> buscarClientes(String nome) throws Exception;
        
}
