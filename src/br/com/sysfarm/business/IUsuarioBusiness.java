/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.business;

import br.com.sysfarm.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public interface IUsuarioBusiness {
    public Usuario logar(Usuario usuario) throws Exception; 
    public boolean isGerente(int id) throws Exception;
    public void salvarFuncionario(Usuario usuario) throws Exception;
    public ArrayList<String> buscarUsuariosPorNome(String nome) throws Exception;
    public Usuario buscarPorNome(String nome) throws Exception;
    public void editar(Usuario usuario) throws Exception;
}
