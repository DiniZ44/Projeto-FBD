/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.dao;

import br.com.sysfarm.controller.ConexaoBD;
import br.com.sysfarm.model.ItemProduto;
import br.com.sysfarm.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author santo
 */
public class ProdutoDAO implements IProdutoDAO{
    
    PreparedStatement statement;
    ResultSet resultSet;

    @Override
    public ItemProduto buscar(ItemProduto itemProduto) throws Exception {
        //BUSCA APENAS PELO LOGIN, SUA FUNÇÃO É VERIFICAR SE O LOGIN EXISTE
         statement= ConexaoBD.conect.prepareStatement("SELECT * FROM produto WHERE codigo='"+itemProduto.getProduto().getCodigo()+"'");
         resultSet=statement.executeQuery();
         resultSet.next();
        //VERIFICA SE RETORNOU ALGUM RESULTADO SE NÃO RETORNA NULL
        if (!resultSet.isFirst()) {
           return null;
        }
        //SE PASSOU DO IF SIGNIFICA QUE ACHOU O USUÁRIO, LOGO VAI RETURNAR O USUÁRIO ENCONTRADO
        Produto produto= new Produto();
        produto.setCodigo(resultSet.getLong("codigo"));
        ItemProduto item= new ItemProduto();
        produto.setDescricao(resultSet.getString("descricao"));
        item.setProduto(produto);
        statement= ConexaoBD.conect.prepareStatement("SELECT * FROM item_produto WHERE produto_id='"+item.getProduto().getCodigo()+"'");
        resultSet=statement.executeQuery();
        resultSet.next();
        item.setData_comp(resultSet.getString("data_compra"));
        item.setData_venc(resultSet.getString("vencimento"));
        item.setQtd(resultSet.getInt("qtd_compra"));
        item.setValor_unitario(resultSet.getFloat("valor_unitario"));
        item.setValor(resultSet.getFloat("porc_venda"));
        return item;
    }

    @Override
    public void salvar(ItemProduto itemProduto) throws Exception {
        statement=ConexaoBD.conect.prepareStatement("insert into produto (codigo,descricao) values (?,?)");
        statement.setLong(1, itemProduto.getProduto().getCodigo());
        statement.setString(2, itemProduto.getProduto().getDescricao());
        statement.executeUpdate();
        statement = ConexaoBD.conect.prepareStatement("insert into item_produto (valor_unitario,vencimento,porc_venda,qtd_compra,data_compra, produto_id) values(?,?,?,?,?,?)");
        //ALIMENTA O PRIMEIRO CAMPO: NOME
        statement.setFloat(1, itemProduto.getValor_unitario());
        //ALIMENTA O SEGUNDO CAMPO: LOGIN
        statement.setString(2, itemProduto.getData_venc());
        //ALIMENTA O TERCEIRO CAMPO: SENHA
        statement.setFloat(3, itemProduto.getValor());
        //ALIMENTA O QUARTO CAMPO: CPF
        statement.setInt(4, itemProduto.getQtd());
        //ALIMENTA O QUINTO CAMPO: EMAIL
        statement.setString(5, itemProduto.getData_comp());
        //ALIMENTA O SEXTO CAMPO: TELEFONE
        statement.setLong(6, itemProduto.getProduto().getCodigo());
        
        statement.executeUpdate();
        

    }
    
    

    
    
}
