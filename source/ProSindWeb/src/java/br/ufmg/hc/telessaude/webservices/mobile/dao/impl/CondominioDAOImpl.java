/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.CondominioDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Condominio;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author weslley.matos
 */
public class CondominioDAOImpl extends DaoBase<Condominio> implements CondominioDAO {

    public CondominioDAOImpl() {
        super(Condominio.class);
    }

    public Condominio retornarCondominio(Condominio cod) throws DAOException{
        List<Condominio> lista = this.findByRestrictions(0,
                Restrictions.ilike("nome", cod.getNome()),
                Restrictions.eq("endereco.id", cod.getEndereco().getId())
        );
        
        if(lista!=null&&!lista.isEmpty()){
            return lista.get(0);
        }
        return null;
    }

}
