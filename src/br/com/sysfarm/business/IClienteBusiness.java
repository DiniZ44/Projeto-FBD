/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.business;

import br.com.sysfarm.model.Cliente;

/**
 *
 * @author santo
 */
public interface IClienteBusiness {
    public void salvarCliente(Cliente cliente) throws Exception;
}
