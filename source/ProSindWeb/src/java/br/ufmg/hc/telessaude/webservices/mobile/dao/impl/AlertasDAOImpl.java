/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.AlertasDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class AlertasDAOImpl extends DaoBase<Alerta> implements AlertasDAO {

    public AlertasDAOImpl() {
        super(Alerta.class);
    }

    public List<Alerta> retornarAlertas(Usuario user) throws DAOException {
        return this.findByRestrictions(0,
                !user.isSindico() ? Restrictions.eq("enderecadasAoSindico", false) : null,
                Restrictions.ge("fimExibicao", Calendar.getInstance().getTime()),
                Restrictions.or(
                        Restrictions.eq("condominio.id", user.getCondominio().getId()),
                        Restrictions.eq("destinatario.id", user.getId())
                )
        );
    }

    public Alerta retornarAlertas(Alerta alert) throws DAOException {
        Calendar calInicio = Calendar.getInstance();
        Calendar calFim = Calendar.getInstance();
        calInicio.setTime(alert.getFimExibicao());
        calFim.setTime(alert.getFimExibicao());
        calInicio.add(Calendar.HOUR_OF_DAY, -1);
        calFim.add(Calendar.HOUR_OF_DAY, 1);

        List<Alerta> lista = this.findByRestrictions(0,
                Restrictions.ilike("descricao", alert.getDescricao()),
                Restrictions.between("fimExibicao", calInicio.getTime(), calFim.getTime()),
                alert.getDestinatario() != null ? Restrictions.or(
                        alert.getCondominio() != null && alert.getCondominio().getId() != null
                        ? Restrictions.eq("condominio.id", alert.getCondominio().getId())
                        : Restrictions.eq("condominio.id", null),
                        Restrictions.eq("destinatario.id", alert.getDestinatario().getId())
                ) : Restrictions.eq("condominio.id", alert.getCondominio().getId())
        );
        if (lista != null && !lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }

}
