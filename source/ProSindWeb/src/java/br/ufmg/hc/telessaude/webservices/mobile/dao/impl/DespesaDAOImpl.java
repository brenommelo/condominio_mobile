/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.DespesaDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Despesa;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Receita;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class DespesaDAOImpl extends DaoBase<Despesa> implements DespesaDAO {

    public DespesaDAOImpl() {
        super(Despesa.class);
    }

    public Boolean verificarDulicidade(Despesa despesa) throws DAOException {
        List<Despesa> listaResult
                = this.findByRestrictions(0,
                        Restrictions.eq("valor", despesa.getValor()),
                        Restrictions.eq("condominio.id", despesa.getCondominio().getId()),
                        Restrictions.eq("separacaoContabil.id", despesa.getSeparacaoContabil().getId()),
                        Restrictions.ilike("descricao", despesa.getDescricao())
                );

        return listaResult != null && !listaResult.isEmpty();
    }
    public List<Despesa> listarDespesas(Long idCondominio, Calendar inicio, Calendar fim) throws DAOException{
        return this.findByRestrictions(0, 
                Restrictions.eq("condominio.id", idCondominio),
                Restrictions.between("inclusao", inicio.getTime(), fim.getTime())
                );
        
        
    }
}
