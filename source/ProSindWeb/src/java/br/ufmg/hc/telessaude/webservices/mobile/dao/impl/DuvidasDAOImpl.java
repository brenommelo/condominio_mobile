/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.AlertasDAO;
import br.ufmg.hc.telessaude.webservices.mobile.dao.DuvidaDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Duvidas;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class DuvidasDAOImpl extends DaoBase<Duvidas> implements DuvidaDAO {

    public DuvidasDAOImpl() {
        super(Duvidas.class);
    }

    public List<Duvidas> retornarDuvidas(Long id, Boolean atendida, Boolean ativa) throws DAOException {
        return this.findByRestrictions(10,
                id != null ? Restrictions.eq("solicitante.id", id) : null,
                Restrictions.eq("atendida", atendida),
                Restrictions.eq("ativa", ativa)
        );
    }
}
