/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.controller;

import br.com.sysfarm.facade.Facade;
import br.com.sysfarm.model.Fornecedor;
import br.com.sysfarm.model.ItemProduto;
import br.com.sysfarm.model.Produto;
import br.com.sysfarm.model.Usuario;
import br.com.sysfarm.util.FormatarMascara;
import br.com.sysfarm.util.Util;
import br.com.sysfarm.view.AddFuncionarioView;
import br.com.sysfarm.view.AddProdutoView;
import br.com.sysfarm.view.CadastroFornecedor;
import br.com.sysfarm.view.CaixaView;
import br.com.sysfarm.view.DashboardView;
import br.com.sysfarm.view.EditFornecedorView;
import br.com.sysfarm.view.EditFuncionarioView;
import br.com.sysfarm.view.FornecedorView;
import br.com.sysfarm.view.LoginView;
import br.com.sysfarm.view.ProdutoView;
import br.com.sysfarm.view.UsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author santo
 */
public class Controller extends KeyAdapter implements ActionListener {

    private LoginView loginView;
    private DashboardView dashboardView;
    private CaixaView caixaView;
    private UsuarioView usuarioView;
    private AddFuncionarioView addFuncionarioView;
    private FornecedorView fornecedorView;
    private CadastroFornecedor cadastroFornecedor;
    private ProdutoView produtoView;
    private ItemProduto itemProduto;
    private Produto produto;
    private EditFornecedorView editFornecedorView;
    private Facade facade;
    private Usuario usuario;
    private Fornecedor fornecedor;
    private EditFuncionarioView editFuncionarioView;
    private AddProdutoView addProdutoView;

    public Controller() {
        //INICIA A CONEXÃO COM O BANCO DE DADOS
        ConexaoBD.getInstance();

        facade = new Facade();
        loginView = new LoginView();
        usuarioView = new UsuarioView();
        dashboardView = new DashboardView();
        caixaView = new CaixaView();
        fornecedorView = new FornecedorView();
        produtoView = new ProdutoView();
        addProdutoView = new AddProdutoView(produtoView, true);
        editFornecedorView = new EditFornecedorView(fornecedorView, true);
        cadastroFornecedor = new CadastroFornecedor(fornecedorView, true);
        addFuncionarioView = new AddFuncionarioView(usuarioView, true);
        editFuncionarioView = new EditFuncionarioView(usuarioView, true);
        control();
        loginView.setVisible(true);
    }

    //ADICIONA EVENTOS DE CLICK NOS COMPONENTES
    private void control() {
        loginView.getJb_entrar().addActionListener(this);
        dashboardView.getJb_usuario().addActionListener(this);
        usuarioView.getJb_add_funcionario().addActionListener(this);
        addFuncionarioView.getJb_salvar().addActionListener(this);
        editFuncionarioView.getJc_buscarUsuario().getEditor().getEditorComponent().addKeyListener(this);
        usuarioView.getJb_editarFuncionario().addActionListener(this);
        editFuncionarioView.getJb_buscar().addActionListener(this);
        editFuncionarioView.getJb_editar().addActionListener(this);
        dashboardView.getJb_fornecedor().addActionListener(this);
        fornecedorView.getJb_cadastroFornecedor().addActionListener(this);
        fornecedorView.getJb_voltar().addActionListener(this);
        usuarioView.getJb_voltar().addActionListener(this);
        dashboardView.getJb_pdv().addActionListener(this);
        cadastroFornecedor.getJb_salvar().addActionListener(this);
        dashboardView.getJb_estoque().addActionListener(this);
        produtoView.getJb_voltar().addActionListener(this);
        dashboardView.getJb_logout().addActionListener(this);
        fornecedorView.getJb_editarFornecedor().addActionListener(this);
        editFuncionarioView.getJb_voltar().addActionListener(this);
        addProdutoView.getJb_buscar().addActionListener(this);
        addProdutoView.getJb_salvar().addActionListener(this);
        addProdutoView.getJb_novo().addActionListener(this);
        produtoView.getJb_cadastrarProduto().addActionListener(this);
        addProdutoView.getJt_codigo().addKeyListener(new EventoLeitorBarras());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //EVENTO DO BOTÃO ENTRAR DA TELA DE LOGIN
        if (e.getSource() == loginView.getJb_entrar()) {
            try {
                //PEGANDO AS INFORMAÇÕES DOS CAMPOS DA TELA DE LOGIN
                usuario = new Usuario();
                usuario.setLogin(loginView.getJt_login().getText());
                usuario.setSenha(loginView.getJt_senha().getText());
                //CHECA SE EXISTE O USUÁRIO
                if (usuario.getLogin().equals("admin") && usuario.getSenha().equals("admin")) {
                    loginView.setVisible(false);
                    dashboardView.setVisible(true);
                } else {
                    Usuario temp = facade.logar(usuario);
                    if (temp != null) {
                        loginView.setVisible(false);
                        //CHECA SE O USUÁRIO É UM GERENTE
                        if (facade.isGerente(temp.getId())) {
                            //SE FOR GERENTE ABRA A TELA INICIAL
                            dashboardView.setVisible(true);
                        } else {
                            //SE NÃO FOR GERENTE ABRA A TELA DO CAIXA
                            caixaView.setVisible(true);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(loginView, ex.getMessage());
            }
        }
        //QUANDO CLICA NO BOTÃO USUÁRIO NO DASHBOARD
        if (e.getSource() == dashboardView.getJb_usuario()) {
            dashboardView.setVisible(false);
            usuarioView.setVisible(true);
        }
        //QUANDO CLICA NO BOTÃO CADASTRAR FUNCIONÁRIO NA TELA USUÁRIO
        if (e.getSource() == usuarioView.getJb_add_funcionario()) {
            addFuncionarioView.setVisible(true);
        }
        //EVENTO DO BOTÃO SALVAR DA TELA DE CADASTRO DE FUNCIONÁRIO
        if (e.getSource() == addFuncionarioView.getJb_salvar()) {
            try {
                //ALIMENTA A VARIÁVEL USUARIO COM OS DADOS DO FORMULÁRIO
                usuario = new Usuario();
                usuario.setNome(addFuncionarioView.getJt_nome().getText());
                usuario.setEmail(addFuncionarioView.getJt_email().getText());
                usuario.setCpf(addFuncionarioView.getJt_cpf().getText());
                usuario.setTelefone(addFuncionarioView.getJt_telefone().getText());
                usuario.setLogin(addFuncionarioView.getJt_login().getText());
                usuario.setSenha(addFuncionarioView.getJt_senha().getText());
                usuario.setTipo(addFuncionarioView.getJc_tipo().getSelectedItem().toString());
                //VERIFICA SE PODE SALVAR E SALVA O USUARIO
                facade.salvarFuncionario(usuario);
                Util.limparText(addFuncionarioView.getJp_cpf());
                Util.limparText(addFuncionarioView.getJp_email());
                Util.limparText(addFuncionarioView.getJp_loginSenha());
                Util.limparText(addFuncionarioView.getJp_nome());
                Util.limparText(addFuncionarioView.getJp_telefone());

                //EXIBE A MENSAGEM CASO D~E TUDO CERTO, CASO NÃO, EXECUTA O EXCEPTION
                JOptionPane.showMessageDialog(addFuncionarioView, "Cadastrado com sucesso!");
                addFuncionarioView.dispose();
                // Util.limparText(addFuncionarioView.getJp_principal());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addFuncionarioView, ex.getMessage());

            }
        }
        //ABRE A TELA DE EDITAR FUNCIONÁRIO
        if (e.getSource() == usuarioView.getJb_editarFuncionario()) {
            editFuncionarioView.setVisible(true);
        }
        //QUNADO CLICA NO BOTÃO BUSCAR ELE ALIMENTA OS CAMPOS COM OS DADOS DO USUARIO
        if (e.getSource() == editFuncionarioView.getJb_buscar()) {
            try {
                usuario = facade.buscarUsuarioPorNome(editFuncionarioView.getJc_buscarUsuario().getSelectedItem().toString());
                editFuncionarioView.getJp_panelEditar().setVisible(true);
                editFuncionarioView.getJt_nome().setText(usuario.getNome());
                editFuncionarioView.getJt_email().setText(usuario.getEmail());
                editFuncionarioView.getJt_cpf().setText(FormatarMascara.CONVERTER(usuario.getCpf()));
                editFuncionarioView.getJt_telefone().setText(usuario.getTelefone());
                editFuncionarioView.getJt_login().setText(usuario.getLogin());
                editFuncionarioView.getJt_senha().setText(usuario.getSenha());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editFuncionarioView, ex.getMessage());
            }
        }
        //EVENTO DE CLICAR NO BOTÃO DE EDITAR DA TELA DE EDITAR USUARIO
        if (e.getSource() == editFuncionarioView.getJb_editar()) {
            usuario.setNome(editFuncionarioView.getJt_nome().getText());
            usuario.setEmail(editFuncionarioView.getJt_email().getText());
            usuario.setCpf(editFuncionarioView.getJt_cpf().getText());
            usuario.setTelefone(editFuncionarioView.getJt_telefone().getText());
            usuario.setLogin(editFuncionarioView.getJt_login().getText());
            usuario.setSenha(editFuncionarioView.getJt_senha().getText());
            usuario.setTipo(editFuncionarioView.getJc_tipo().getSelectedItem().toString());

            try {
                //MANDA O USUÁRIO PARA SER EDITADO NO BANCO
                facade.editarUsuario(usuario);
                JOptionPane.showMessageDialog(editFuncionarioView, "Editado com sucesso!");
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        //EVENTO DO BOTÃO FORNECEDOR NA TELA DO DASHBOARD
        if (e.getSource() == dashboardView.getJb_fornecedor()) {
            dashboardView.setVisible(false);
            fornecedorView.setVisible(true);
        }
        //EVENTO DO BOTÃO CADASTRO FUNCIONÁRIO NA TELA DO FORNECEDOR
        if (e.getSource() == fornecedorView.getJb_cadastroFornecedor()) {
            cadastroFornecedor.setVisible(true);
        }
        //FECHA A TELA FORNECEDOR E VOLTA PARA O DASHBOARD
        if (e.getSource() == fornecedorView.getJb_voltar()) {
            fornecedorView.dispose();
            dashboardView.setVisible(true);
        }
        //FECHA A TELA USUÁRIO E VOLTA AO DASHBOARD
        if (e.getSource() == usuarioView.getJb_voltar()) {
            usuarioView.dispose();
            dashboardView.setVisible(true);
        }
        //ABRE A TELA DE PDV NA TELA DASHBOARD
        if (e.getSource() == dashboardView.getJb_pdv()) {
            caixaView.setVisible(true);
        }
        //EVENTO DO BOTÃO SALVAR FORNECEDOR NA TELA DE CADASTRO DE FORNECEDOR
        if (e.getSource() == cadastroFornecedor.getJb_salvar()) {
            try {
                fornecedor = new Fornecedor();

                fornecedor.setNome(cadastroFornecedor.getJt_nome().getText());
                fornecedor.setCnpj(cadastroFornecedor.getJt_cnpj().getText());
                fornecedor.setContato(cadastroFornecedor.getJt_contato().getText());
                Util.limparText(cadastroFornecedor.getJp_cnpj());
                Util.limparText(cadastroFornecedor.getJp_contato());
                Util.limparText(cadastroFornecedor.getJp_nome());
                facade.salvarFornecedor(fornecedor);
                cadastroFornecedor.dispose();
                JOptionPane.showMessageDialog(cadastroFornecedor, "Salvo com sucesso!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(cadastroFornecedor, ex.getMessage());
            }
        }
        if (e.getSource() == dashboardView.getJb_estoque()) {
            dashboardView.dispose();
            produtoView.setVisible(true);
        }
        if (e.getSource() == produtoView.getJb_voltar()) {
            produtoView.dispose();
            dashboardView.setVisible(true);
        }
        if (e.getSource() == dashboardView.getJb_logout()) {
            Util.limparText(loginView.getJp_loginSenha());
            // usuario = null;
            dashboardView.dispose();
            loginView.setVisible(true);
        }
        if (e.getSource() == fornecedorView.getJb_editarFornecedor()) {
            editFornecedorView.setVisible(true);

        }
        if (e.getSource() == editFuncionarioView.getJb_voltar()) {
            editFuncionarioView.getJp_panelEditar().setVisible(false);
        }
        if (e.getSource() == produtoView.getJb_cadastrarProduto()) {
            addProdutoView.setVisible(true);
        }
        if (e.getSource() == addProdutoView.getJb_buscar()) {
            try {
                addProdutoView.getJt_codigo().setEnabled(true);
                isVisivbleComponentsProdutoView(false);
                produto = new Produto();
                if (addProdutoView.getJt_codigo().getText().isEmpty()) {
                    throw new Exception(Util.CODIGO_VAZIO);
                }
                produto.setCodigo(Long.valueOf(addProdutoView.getJt_codigo().getText()));
                produto.setDescricao(addProdutoView.getJt_descricao().getText());
                itemProduto = new ItemProduto();
                itemProduto.setProduto(produto);
                ItemProduto temp = facade.buscarProduto(itemProduto);
                if (temp == null) {
                    addProdutoView.getJl_mensagem().setText(Util.CODIGO_NAO_ENCONTRADO);
                    addProdutoView.getJl_mensagem().setVisible(true);
                } else {
                    addProdutoView.getJt_descricao().setText(temp.getProduto().getDescricao());
                    addProdutoView.getJt_preco().setText(String.valueOf(temp.getValor()));
                    addProdutoView.getJt_precoUnidade().setText(String.valueOf(temp.getValor_unitario()));
                    addProdutoView.getJt_dataVencimento().setText(temp.getData_venc());
                    addProdutoView.getJt_dataCompra().setText(temp.getData_comp());
                    addProdutoView.getJt_qtd().setText(String.valueOf(temp.getQtd()));
                    System.out.println(String.valueOf(temp.getValor()));
                    isVisivbleComponentsProdutoView(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                if (ex.getMessage().equals(Util.CODIGO_VAZIO)) {
                    addProdutoView.getJl_mensagem().setText(Util.CODIGO_VAZIO);
                    addProdutoView.getJl_mensagem().setVisible(true);
                }
            }
        }
        if (e.getSource() == addProdutoView.getJb_novo()) {
            try {
                if (addProdutoView.getJt_codigo().getText().isEmpty()) {
                    throw new Exception(Util.CODIGO_VAZIO);
                }
                addProdutoView.getJt_codigo().setEnabled(false);
                isVisivbleComponentsProdutoView(true);
            } catch (Exception ex) {
                if (ex.getMessage().equals(Util.CODIGO_VAZIO)) {
                    addProdutoView.getJl_mensagem().setText(Util.CODIGO_VAZIO);
                    addProdutoView.getJl_mensagem().setVisible(true);
                }
            }

        }
        if (e.getSource() == addProdutoView.getJb_salvar()) {
            try {
                produto = new Produto();
                itemProduto = new ItemProduto();
                produto.setCodigo(Long.parseLong(addProdutoView.getJt_codigo().getText()));
                produto.setDescricao(addProdutoView.getJt_descricao().getText());
                itemProduto.setProduto(produto);
                itemProduto.setData_comp(addProdutoView.getJt_dataCompra().getText());
                itemProduto.setData_venc(addProdutoView.getJt_dataVencimento().getText());
                itemProduto.setValor(Float.parseFloat(addProdutoView.getJt_preco().getText()));
                itemProduto.setValor_unitario(Float.parseFloat(addProdutoView.getJt_precoUnidade().getText()));

                facade.salvarProduto(itemProduto);
                JOptionPane.showMessageDialog(addProdutoView, "Produto Cadastrado com Sucesso!");
                Util.limparText(addProdutoView.getJp_codigo());
                Util.limparText(addProdutoView.getJp_descricao());
                Util.limparText(addProdutoView.getJp_item_produto());
                addProdutoView.getJt_codigo().setEnabled(true);
                isVisivbleComponentsProdutoView(false);
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //TODA VEZ QUE DIGITAR UMA TECLA NO COMBOBOX ELE CHECA SE É A TECLA ENTER
        //QUANDO APERTA A TECLA ENTER VAI BUSCAR O NOME PARECIDO NO BANCO DE DADOS E VAI PREENCHENDO O COMBOBOX
        if (e.getKeyCode() == e.VK_ENTER) {
            ArrayList<String> nomes;
            try {
                JTextField textField = (JTextField) editFuncionarioView.getJc_buscarUsuario().getEditor().getEditorComponent();
                System.out.println(textField.getText());
                nomes = facade.buscarUsuariosPorNome(textField.getText());
                editFuncionarioView.getJc_buscarUsuario().removeAllItems();
                for (String nome : nomes) {
                    editFuncionarioView.getJc_buscarUsuario().addItem(nome);
                }
                editFuncionarioView.getJc_buscarUsuario().showPopup();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editFuncionarioView, ex.getMessage());
                ex.printStackTrace();
                editFuncionarioView.getJc_buscarUsuario().setFocusable(false);
            }
            editFuncionarioView.getJc_buscarUsuario().setFocusable(true);
        }
    }

    private void isVisivbleComponentsProdutoView(boolean value) {
        addProdutoView.getJp_descricao().setVisible(value);
        addProdutoView.getJp_item_produto().setVisible(value);
    }

    public class EventoLeitorBarras extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == e.VK_ENTER){
                try {
                addProdutoView.getJt_codigo().setEnabled(true);
                isVisivbleComponentsProdutoView(false);
                produto = new Produto();
                if (addProdutoView.getJt_codigo().getText().isEmpty()) {
                    throw new Exception(Util.CODIGO_VAZIO);
                }
                produto.setCodigo(Long.valueOf(addProdutoView.getJt_codigo().getText()));
                produto.setDescricao(addProdutoView.getJt_descricao().getText());
                itemProduto = new ItemProduto();
                itemProduto.setProduto(produto);
                ItemProduto temp = facade.buscarProduto(itemProduto);
                if (temp == null) {
                    addProdutoView.getJl_mensagem().setText(Util.CODIGO_NAO_ENCONTRADO);
                    addProdutoView.getJl_mensagem().setVisible(true);
                } else {
                    addProdutoView.getJt_descricao().setText(temp.getProduto().getDescricao());
                    addProdutoView.getJt_preco().setText(String.valueOf(temp.getValor()));
                    addProdutoView.getJt_precoUnidade().setText(String.valueOf(temp.getValor_unitario()));
                    addProdutoView.getJt_dataVencimento().setText(temp.getData_venc());
                    addProdutoView.getJt_dataCompra().setText(temp.getData_comp());
                    addProdutoView.getJt_qtd().setText(String.valueOf(temp.getQtd()));
                    System.out.println(String.valueOf(temp.getValor()));
                    isVisivbleComponentsProdutoView(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                if (ex.getMessage().equals(Util.CODIGO_VAZIO)) {
                    addProdutoView.getJl_mensagem().setText(Util.CODIGO_VAZIO);
                    addProdutoView.getJl_mensagem().setVisible(true);
                }
            }
            }
        }
    }

}
