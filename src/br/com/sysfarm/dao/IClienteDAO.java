/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.dao;

import br.com.sysfarm.model.Cliente;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public interface IClienteDAO {
    
    
    public Cliente buscar(Cliente cliente) throws Exception;
    public Cliente buscarPorCPF(Cliente cliente) throws Exception;
    public Cliente buscraPorNome(Cliente cliente) throws Exception;
    public Cliente editar(Cliente cliente) throws Exception;
    public ArrayList<String> buscarClientes(String nome) throws Exception;
}
