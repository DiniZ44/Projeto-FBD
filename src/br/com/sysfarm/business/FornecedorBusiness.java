/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.business;

import br.com.sysfarm.dao.FornecedorDAO;
import br.com.sysfarm.dao.IFornecedorDAO;
import br.com.sysfarm.model.Fornecedor;

/**
 *
 * @author santo
 */
public class FornecedorBusiness implements IFornecedorBusiness{
    private IFornecedorDAO fornecedorDAO;

    public FornecedorBusiness() {
        fornecedorDAO= new FornecedorDAO();
    }

    @Override
    public void salvar(Fornecedor fornecedor) throws Exception {
        checarComponentes(fornecedor);
        fornecedorDAO.salvar(fornecedor);
    }
    
    private void checarComponentes(Fornecedor fornecedor) throws Exception{
        if(fornecedor.getNome().isEmpty()){
            throw new Exception("Digite o nome!");
        }else if(fornecedor.getCnpj().isEmpty()){
             throw new Exception("Digite o CNPJ");
        }else if(fornecedor.getContato().isEmpty()){
             throw new Exception("Digite o email");
        }
    }
    
    
}
