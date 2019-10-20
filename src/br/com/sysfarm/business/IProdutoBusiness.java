/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.business;

import br.com.sysfarm.model.ItemProduto;

/**
 *
 * @author santo
 */
public interface IProdutoBusiness {
     public ItemProduto buscar(ItemProduto itemProduto) throws Exception;
     public void salvar(ItemProduto itemProduto) throws Exception;
    
}
