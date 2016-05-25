/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.dao.impl;

import br.com.una.pa.condominio.mobile.dao.UnidadeDAO;
import br.com.una.pa.condominio.mobile.entidades.Unidade;

/**
 *
 * @author breno.melo
 */
public class UnidadeDAOImpl extends DaoBase<Unidade> implements UnidadeDAO {

    public UnidadeDAOImpl() {
        super(Unidade.class);
    }

}
