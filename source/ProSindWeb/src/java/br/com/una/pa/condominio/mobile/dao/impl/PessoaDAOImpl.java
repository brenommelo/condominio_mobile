/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.dao.impl;

import br.com.una.pa.condominio.mobile.dao.PessoaDAO;
import br.com.una.pa.condominio.mobile.entidades.Pessoa;

/**
 *
 * @author breno.melo
 */
public class PessoaDAOImpl extends DaoBase<Pessoa> implements PessoaDAO {

    public PessoaDAOImpl() {
        super(Pessoa.class);
    }

}
