/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.dao.impl;

import br.com.una.pa.condominio.mobile.dao.PessoaUnidadeDAO;
import br.com.una.pa.condominio.mobile.entidades.PessoaUnidade;

/**
 *
 * @author breno.melo
 */
public class PessoaUnidadeDAOImpl extends DaoBase<PessoaUnidade> implements PessoaUnidadeDAO {

    public PessoaUnidadeDAOImpl() {
        super(PessoaUnidade.class);
    }

}
