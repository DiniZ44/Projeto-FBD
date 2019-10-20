/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.dao;

import br.com.sysfarm.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santo
 */
public interface IUsuarioDAO {
    public Usuario buscar(Usuario usuario) throws Exception;
    public Usuario buscarPorCPF(Usuario usuario) throws Exception;
    public boolean isGerente(int id) throws Exception;
    public void persisteAtendente(Usuario usuario) throws Exception;
    public void persisteGerente(Usuario usuario) throws Exception;
    public ArrayList<String> buscarUsuarios(String nome) throws Exception;
    public Usuario buscarPorNome(String nome) throws Exception;
    public Usuario buscarGerentePorId(int id) throws Exception;
    public void editar(Usuario usuario) throws Exception;
    public void excluirGerente(int id) throws Exception;
    public void excluirAtendente(int id) throws Exception;
}
