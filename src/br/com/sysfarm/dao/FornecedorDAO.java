/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.dao;

import br.com.sysfarm.controller.ConexaoBD;
import br.com.sysfarm.model.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public class FornecedorDAO implements IFornecedorDAO {

    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public void salvar(Fornecedor fornecedor) throws Exception {
        if (fornecedor.getId() == null) {
            statement = ConexaoBD.conect.prepareStatement("INSERT INTO fornecedor (descricao,cnpj,contato) values (?,?,?)");

            statement.setString(1, fornecedor.getNome());
            statement.setString(2, fornecedor.getCnpj());
            statement.setString(3, fornecedor.getContato());

            statement.executeUpdate();
        }
    }

    @Override
    public Fornecedor buscar(Fornecedor cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fornecedor buscarPorCPF(Fornecedor cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fornecedor buscraPorNome(Fornecedor cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fornecedor editar(Fornecedor cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> buscarClientes(String nome) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
