/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.EnderecoDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Endereco;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author weslley.matos
 */
public class EnderecoDAOImpl extends DaoBase<Endereco> implements EnderecoDAO {

    public EnderecoDAOImpl() {
        super(Endereco.class);
    }

    public Endereco retornarEndereco(Endereco end) throws DAOException{
        
        List<Endereco> lista = this.findByRestrictions(0,
                Restrictions.ilike("cep", end.getCep())
        );
        if(lista!=null&&!lista.isEmpty()){
            return lista.get(0);
        }
       return null;
    }

}
