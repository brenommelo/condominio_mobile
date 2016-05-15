/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.AlertasDAO;
import br.ufmg.hc.telessaude.webservices.mobile.dao.DuvidaDAO;
import br.ufmg.hc.telessaude.webservices.mobile.dao.RespostaDuvidaDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Duvidas;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.RespostasDuvida;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class RespostaDuvidaDAOImpl extends DaoBase<RespostasDuvida> implements RespostaDuvidaDAO {

    public RespostaDuvidaDAOImpl() {
        super(RespostasDuvida.class);
    }
    public List<RespostasDuvida> retornarRespostas(Long idDuvida) throws DAOException{
        return this.findByRestrictions(0, 
                Restrictions.eq("duvida.id", idDuvida)
        );
    }
}
