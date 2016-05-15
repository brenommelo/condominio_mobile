/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.ReceitaDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Receita;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class ReceitaDAOImpl extends DaoBase<Receita> implements ReceitaDAO {

    public ReceitaDAOImpl() {
        super(Receita.class);
    }

    public Boolean verificarDulicidade(Receita receita) throws DAOException {
        List<Receita> listaResult
                = this.findByRestrictions(0,
                        Restrictions.eq("valor", receita.getValor()),
                        Restrictions.eq("condominio.id", receita.getCondominio().getId()),
                        Restrictions.eq("separacaoContabil.id", receita.getSeparacaoContabil().getId()),
                        Restrictions.ilike("tipo", receita.getTipo()),
                        receita.getPagador() != null ? Restrictions.eq("pagador.id", receita.getPagador().getId()) : null
                );

        return listaResult != null && !listaResult.isEmpty();
    }
    public List<Receita> listarReceitas(Long idCondominio, Calendar inicio, Calendar fim) throws DAOException{
        return this.findByRestrictions(0, 
                Restrictions.eq("condominio.id", idCondominio),
                Restrictions.between("inclusao", inicio.getTime(), fim.getTime())
                );
        
        
    }

}
