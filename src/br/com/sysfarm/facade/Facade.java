/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.facade;

import br.com.sysfarm.business.FornecedorBusiness;
import br.com.sysfarm.business.IFornecedorBusiness;
import br.com.sysfarm.business.IProdutoBusiness;
import br.com.sysfarm.business.IUsuarioBusiness;
import br.com.sysfarm.business.ProdutoBusiness;
import br.com.sysfarm.business.UsuarioBusiness;
import br.com.sysfarm.model.Fornecedor;
import br.com.sysfarm.model.ItemProduto;
import br.com.sysfarm.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public class Facade implements IFacade{
    private IUsuarioBusiness usuarioBusiness;
    private IFornecedorBusiness fornecedorBusiness;
    private IProdutoBusiness produtoBusiness;

    public Facade() {
        this.usuarioBusiness = new UsuarioBusiness();
        this.fornecedorBusiness= new FornecedorBusiness();
        this.produtoBusiness= new ProdutoBusiness();
    }

    @Override
    public Usuario logar(Usuario usuario) throws Exception {
        return usuarioBusiness.logar(usuario);
    }

    @Override
    public boolean isGerente(int id) throws Exception {
        return usuarioBusiness.isGerente(id);
    }

    @Override
    public void salvarFuncionario(Usuario usuario) throws Exception {
        usuarioBusiness.salvarFuncionario(usuario);
    }

    @Override
    public ArrayList<String> buscarUsuariosPorNome(String nome) throws Exception {
        return usuarioBusiness.buscarUsuariosPorNome(nome);
    }

    @Override
    public Usuario buscarUsuarioPorNome(String nome) throws Exception {
        return usuarioBusiness.buscarPorNome(nome);
    }

    @Override
    public void editarUsuario(Usuario usuario) throws Exception {
        usuarioBusiness.editar(usuario);
    }

    @Override
    public void salvarFornecedor(Fornecedor fornecedor) throws Exception {
        fornecedorBusiness.salvar(fornecedor);
    }

    @Override
    public void salvarProduto(ItemProduto itemProduto) throws Exception {
       produtoBusiness.salvar(itemProduto);
    }

    @Override
    public ItemProduto buscarProduto(ItemProduto itemProduto) throws Exception {      
        return produtoBusiness.buscar(itemProduto);
    }
    
}
