/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.util;

import javax.swing.text.MaskFormatter;

/**
 *
 * @author santo
 */
public class FormatarMascara {
    public static String CONVERTER(String string){
        String temp=string;
        if (string!= null && !string.equals(""))
               temp = string.replaceAll("\\.", "").replaceAll("\\-", "");
          return temp;
    }
  

    
}
