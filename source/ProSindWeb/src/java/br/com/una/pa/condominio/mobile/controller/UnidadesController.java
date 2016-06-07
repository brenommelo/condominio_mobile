/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.controller;

import br.com.una.pa.condominio.mobile.dao.impl.UnidadeDAOImpl;
import br.com.una.pa.condominio.mobile.entidades.Unidade;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author breno
 */
public class UnidadesController {

    UnidadeDAOImpl unidadeDAOImpl = new UnidadeDAOImpl();

    public UnidadesController() {
    }

    public Unidade[] salvarUnidade(Unidade[] unidade) {
        for (Unidade uni : unidade) {
            if (validarDuplicidadeUnidade(uni)) {
                try {
                    unidadeDAOImpl.saveOrUpdate(uni);
                } catch (DAOException ex) {
                    Logger.getLogger(UnidadesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return unidade;
    }

    public Boolean validarDuplicidadeUnidade(Unidade unidade) {
        try {
            return unidadeDAOImpl.verificarSeJaExiste(unidade);
        } catch (DAOException ex) {
            Logger.getLogger(UnidadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
