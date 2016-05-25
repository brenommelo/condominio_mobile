/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.dao.impl;

import br.com.una.pa.condominio.mobile.dao.CondominioDAO;
import br.com.una.pa.condominio.mobile.entidades.Condominio;

/**
 *
 * @author breno.melo
 */
public class CondominioDAOImpl extends DaoBase<Condominio> implements CondominioDAO {

    public CondominioDAOImpl() {
        super(Condominio.class);
    }

}
