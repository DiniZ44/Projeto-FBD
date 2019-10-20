/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.business;

import br.com.sysfarm.dao.IProdutoDAO;
import br.com.sysfarm.dao.ProdutoDAO;
import br.com.sysfarm.model.ItemProduto;
import br.com.sysfarm.util.Util;

/**
 *
 * @author santo
 */
public class ProdutoBusiness implements IProdutoBusiness{

    private IProdutoDAO produtoDAO;
    public ProdutoBusiness() {
        
        produtoDAO = new ProdutoDAO();
    
    }
    
    

    @Override
    public ItemProduto buscar(ItemProduto itemProduto) throws Exception {
        ItemProduto temp = produtoDAO.buscar(itemProduto);
        if(temp != null){
            return temp;
        }
        return null;
        
        
    }
    
    

    @Override
    public void salvar(ItemProduto itemProduto) throws Exception {
       produtoDAO.salvar(itemProduto);
    }
    
    
}
