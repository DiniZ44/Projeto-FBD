/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.util;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author santo
 */
public class Util {
    public static final String CODIGO_VAZIO = "Campo Vazio!";
    public static final String CODIGO_NAO_ENCONTRADO = "Produto não encontrado!";
    public static final String ATENDENTE="Atendente";
    public static final String GERENTE="Gerente";
    
    public static void limparText(JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component c = panel.getComponent(i);
            if (c instanceof JTextField) {
                JTextField field = (JTextField) c;
                field.setText("");
            }
            if (c instanceof JComboBox) {
                JComboBox jComboBox = (JComboBox) c;
                jComboBox.setSelectedIndex(0);
            }
            if(c instanceof JPasswordField){
                JPasswordField jPasswordField= (JPasswordField) c;
                jPasswordField.setText("");
            }
        }
    }
}
