/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.facade;

import br.com.sysfarm.business.IUsuarioBusiness;
import br.com.sysfarm.model.Fornecedor;
import br.com.sysfarm.model.ItemProduto;
import br.com.sysfarm.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public interface IFacade {
    
    public Usuario logar(Usuario usuario) throws Exception; 
    public boolean isGerente(int id) throws Exception;
    public void salvarFuncionario(Usuario usuario) throws Exception;
    public ArrayList<String> buscarUsuariosPorNome(String nome) throws Exception;
    public Usuario buscarUsuarioPorNome(String nome) throws Exception;
    public void editarUsuario(Usuario usuario) throws Exception;
    public void salvarFornecedor(Fornecedor fornecedor) throws Exception;
    
    public void salvarProduto(ItemProduto itemProduto) throws Exception;
    public ItemProduto buscarProduto(ItemProduto itemProduto) throws Exception;
}
