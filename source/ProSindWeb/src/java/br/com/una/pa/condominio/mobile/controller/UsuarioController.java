/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.controller;

import br.com.una.pa.condominio.mobile.dao.impl.UsuarioDAOImpl;
import br.com.una.pa.condominio.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author breno
 */
public class UsuarioController {

    UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();

    public UsuarioController() {
    }

    public Usuario login(Usuario usuario) {
        try {
            return usuarioDAOImpl.login(usuario);
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
