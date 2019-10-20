/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.dao;

import br.com.sysfarm.controller.ConexaoBD;
import br.com.sysfarm.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author santo
 */
public class UsuarioDAO implements IUsuarioDAO{
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public Usuario buscar(Usuario usuario) throws Exception {
        //BUSCA APENAS PELO LOGIN, SUA FUNÇÃO É VERIFICAR SE O LOGIN EXISTE
         statement= ConexaoBD.conect.prepareStatement("SELECT * FROM usuario WHERE login='"+usuario.getLogin()+"'");
         resultSet=statement.executeQuery();
         resultSet.next();
        //VERIFICA SE RETORNOU ALGUM RESULTADO SE NÃO RETORNA NULL
        if (!resultSet.isFirst()) {
           return null;
        }
        //SE PASSOU DO IF SIGNIFICA QUE ACHOU O USUÁRIO, LOGO VAI RETURNAR O USUÁRIO ENCONTRADO
        Usuario usuario1= new Usuario();
        usuario1.setId(resultSet.getInt("id"));
        usuario1.setNome(resultSet.getString("nome"));
        usuario1.setEmail(resultSet.getString("email"));
        usuario1.setLogin(resultSet.getString("login"));
        usuario1.setTelefone(resultSet.getString("telefone"));
        usuario1.setSenha(resultSet.getString("senha"));
        return usuario1;
    }   

    @Override
    public boolean isGerente(int id) throws Exception {
        //BUSCA NA TABELA GERENTE SE EXISTE O ID ESPECIFICADO
        statement= ConexaoBD.conect.prepareStatement("SELECT * FROM gerente WHERE usuario_id='"+id+"'");
        resultSet=statement.executeQuery();
        resultSet.next();
        //RETORNA FALSE SE NÃO ACHAR
        if (!resultSet.isFirst()) {
           return false;
        }
        //RETORNA true SE ACHAR
        return true;
    }

    @Override
    public void persisteAtendente(Usuario usuario) throws Exception {
        //ALIMENTAR O STATEMENT DO USUÁRIO
        if(usuario.getId()==null)
            alimentarStatementUsuario(usuario);
        Usuario temp;
        temp=buscarPorCPF(usuario);
        statement=ConexaoBD.conect.prepareStatement("INSERT INTO atendente (usuario_id) values (?)");
        statement.setInt(1, temp.getId());
        statement.executeUpdate();
    }

    @Override
    public void persisteGerente(Usuario usuario) throws Exception {
        //ALIMENTAR O STATEMENT DO USUÁRIO
        if(usuario.getId()==null)
            alimentarStatementUsuario(usuario);
        Usuario temp;
        temp=buscarPorCPF(usuario);
        statement=ConexaoBD.conect.prepareStatement("INSERT INTO gerente (usuario_id) values (?)");
        statement.setInt(1, temp.getId());
        statement.executeUpdate();
    }

    @Override
    public Usuario buscarPorCPF(Usuario usuario) throws Exception {
        //BUSCA APENAS PELO CPF, SUA FUNÇÃO É VERIFICAR SE O CPF EXISTE
         statement= ConexaoBD.conect.prepareStatement("SELECT * FROM usuario WHERE cpf='"+usuario.getCpf()+"'");
         resultSet=statement.executeQuery();
         resultSet.next();
        //VERIFICA SE RETORNOU ALGUM RESULTADO, SE NÃO, RETORNA NULL
        if (!resultSet.isFirst()) {
           return null;
        }
        //SE PASSOU DO IF SIGNIFICA QUE ACHOU O USUÁRIO, LOGO VAI RETURNAR O USUÁRIO ENCONTRADO
        Usuario usuario1= new Usuario();
        usuario1.setId(resultSet.getInt("id"));
        usuario1.setNome(resultSet.getString("nome"));
        usuario1.setEmail(resultSet.getString("email"));
        usuario1.setCpf(resultSet.getString("cpf"));
        usuario1.setLogin(resultSet.getString("login"));
        usuario1.setTelefone(resultSet.getString("telefone"));
        usuario1.setSenha(resultSet.getString("senha"));
        return usuario1;
    }
    private void alimentarStatementUsuario(Usuario usuario) throws Exception{
        //CADA INTERROGAÇÃO É CORRESPONDENTE AOS CAMPOS QUE DESEJA INSERIR
        statement = ConexaoBD.conect.prepareStatement("insert into usuario (nome,login,senha,cpf,email,telefone) values(?,?,?,?,?,?)");
        //ALIMENTA O PRIMEIRO CAMPO: NOME
        statement.setString(1, usuario.getNome());
        //ALIMENTA O SEGUNDO CAMPO: LOGIN
        statement.setString(2, usuario.getLogin());
        //ALIMENTA O TERCEIRO CAMPO: SENHA
        statement.setString(3, usuario.getSenha());
        //ALIMENTA O QUARTO CAMPO: CPF
        statement.setString(4, usuario.getCpf());
        //ALIMENTA O QUINTO CAMPO: EMAIL
        statement.setString(5, usuario.getEmail());
        //ALIMENTA O SEXTO CAMPO: TELEFONE
        statement.setString(6, usuario.getTelefone());
        //MANDA AS INFORMAÇÕES PRO BANCO DE DADOS
        statement.executeUpdate();
    }

    @Override
    public ArrayList<String> buscarUsuarios(String nome) throws Exception {
        ArrayList<String> nomes= new ArrayList<>();
        try {
            System.out.println(nome);
            statement= ConexaoBD.conect.prepareStatement("select nome from usuario where nome ilike '%"+nome+"%' order by nome");
            resultSet=statement.executeQuery();
            resultSet.next();
            if(!resultSet.isFirst())
                return null;
        do {            
            nomes.add(resultSet.getString("nome"));
        } while (resultSet.next());
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nomes;
    }

    @Override
    public Usuario buscarPorNome(String nome) throws Exception {
        //BUSCA APENAS PELO CPF, SUA FUNÇÃO É VERIFICAR SE O nome EXISTE
         statement= ConexaoBD.conect.prepareStatement("SELECT * FROM usuario WHERE nome='"+nome+"'");
         resultSet=statement.executeQuery();
         resultSet.next();
        //VERIFICA SE RETORNOU ALGUM RESULTADO, SE NÃO, RETORNA NULL
        if (!resultSet.isFirst()) {
           return null;
        }
        //SE PASSOU DO IF SIGNIFICA QUE ACHOU O USUÁRIO, LOGO VAI RETURNAR O USUÁRIO ENCONTRADO
        Usuario usuario1= new Usuario();
        usuario1.setId(resultSet.getInt("id"));
        usuario1.setNome(resultSet.getString("nome"));
        usuario1.setEmail(resultSet.getString("email"));
        usuario1.setCpf(resultSet.getString("cpf"));
        usuario1.setLogin(resultSet.getString("login"));
        usuario1.setTelefone(resultSet.getString("telefone"));
        usuario1.setSenha(resultSet.getString("senha"));
        return usuario1;
    }

    @Override
    public void editar(Usuario usuario) throws Exception {
        //CÓDIGO SQL PARA EDITAR UM USUARIO ESPECÍFICO
        statement= ConexaoBD.conect.prepareStatement("UPDATE usuario set nome= ?, email= ?, cpf= ?, telefone= ?, login=?, senha=? WHERE id="+usuario.getId());
        //INSERINDO DADOS PARA MANDAR AO BANCO
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getEmail());
        statement.setString(3, usuario.getCpf());
        statement.setString(4, usuario.getTelefone());
        statement.setString(5, usuario.getLogin());
        statement.setString(6, usuario.getSenha());
        //ENVIA PRO BANCO
        statement.executeUpdate();
    }

    @Override
    public Usuario buscarGerentePorId(int id) throws Exception {
        //BUSCA APENAS PELO LOGIN, SUA FUNÇÃO É VERIFICAR SE O ID EXISTE
         statement= ConexaoBD.conect.prepareStatement("SELECT * FROM gerente WHERE usuario_id='"+id+"'");
         resultSet=statement.executeQuery();
         resultSet.next();
        //VERIFICA SE RETORNOU ALGUM RESULTADO SE NÃO RETORNA NULL
        if (!resultSet.isFirst()) {
           return null;
        }
        return new Usuario();
    }

    @Override
    public void excluirGerente(int id) throws Exception {
        System.out.println(id);
        statement=ConexaoBD.conect.prepareStatement("DELETE FROM gerente where usuario_id="+id);
        statement.execute();
    }

    @Override
    public void excluirAtendente(int id) throws Exception {
        System.out.println(id);
        statement=ConexaoBD.conect.prepareStatement("DELETE FROM atendente where usuario_id="+id);
        statement.execute();
    }
}
