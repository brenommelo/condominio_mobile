/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.dao.impl;

import br.com.una.pa.condominio.mobile.dao.ConfiguracaoDAO;
import br.com.una.pa.condominio.mobile.entidades.Configuracao;

/**
 *
 * @author breno.melo
 */
public class ConfiguracaoDAOImpl extends DaoBase<Configuracao> implements ConfiguracaoDAO {

    public ConfiguracaoDAOImpl() {
        super(Configuracao.class);
    }

}
