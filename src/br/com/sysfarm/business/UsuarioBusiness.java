/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.business;

import br.com.sysfarm.dao.IUsuarioDAO;
import br.com.sysfarm.dao.UsuarioDAO;
import br.com.sysfarm.model.Usuario;
import br.com.sysfarm.util.Util;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public class UsuarioBusiness implements IUsuarioBusiness{
    private IUsuarioDAO usuarioDAO;

    public UsuarioBusiness() {
        usuarioDAO= new UsuarioDAO();
    }
    
    @Override
    public Usuario logar(Usuario usuario) throws Exception {
        verificarCampos(usuario);
        Usuario usuario1= usuarioDAO.buscar(usuario);
        if(usuario1!=null){
            if(usuario.getLogin().equals(usuario1.getLogin()) && usuario.getSenha().endsWith(usuario1.getSenha())){
                return usuario1;
            }else             
                throw new Exception("Login ou Senha não conferem!");
            
        }else{
            throw new Exception("Usuário não Encontrado!");
        }
    }
    private void verificarCampos(Usuario usuario) throws Exception{
        //Verificar se o campo login está em branco, caso sim, lança uma exceção
        if(usuario.getLogin().isEmpty()){
            throw new Exception("Digite o login!");
        }
         //Verificar se o campo senha está em branco, caso sim, lança uma exceção
        if(usuario.getSenha().isEmpty()){
            throw new Exception("Digite a senha!");
        }
    }

    @Override
    public boolean isGerente(int id) throws Exception {
        return usuarioDAO.isGerente(id);
    }

    @Override
    public void salvarFuncionario(Usuario usuario) throws Exception {
        if(usuarioDAO.buscarPorCPF(usuario)==null){
            if(usuario.getTipo().equals(Util.ATENDENTE)){
                usuarioDAO.persisteAtendente(usuario);
            }else if(usuario.getTipo().equals(Util.GERENTE)){
                usuarioDAO.persisteGerente(usuario);
            }else{
                throw new Exception("Tipo de usuário não reconhecido");
            }
        }else{
            throw new Exception("O usuário já existe!");
        }
    }

    @Override
    public ArrayList<String> buscarUsuariosPorNome(String nome) throws Exception {
        ArrayList<String> nomes=usuarioDAO.buscarUsuarios(nome);
        if(nomes!=null)
            return nomes;
        else
            throw new Exception("Não existe esse nome");
    }

    @Override
    public Usuario buscarPorNome(String nome) throws Exception {
        Usuario temp= usuarioDAO.buscarPorNome(nome);
        if(temp!=null)
            return temp;
        else 
            throw new Exception("O usuário não existe!");
    }

    @Override
    public void editar(Usuario usuario) throws Exception {
        Usuario temp= usuarioDAO.buscarGerentePorId(usuario.getId());
        
        if(temp!=null && usuario.getTipo().equals(Util.ATENDENTE)){
            usuarioDAO.excluirGerente(usuario.getId());
            usuarioDAO.persisteAtendente(usuario);
        }else if(temp==null && usuario.getTipo().equals(Util.GERENTE)){
            usuarioDAO.excluirAtendente(usuario.getId());
            usuarioDAO.persisteGerente(usuario);
        }
        usuarioDAO.editar(usuario);
    }
}
