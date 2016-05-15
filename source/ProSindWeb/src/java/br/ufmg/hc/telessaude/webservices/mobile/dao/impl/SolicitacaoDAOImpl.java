/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.SolicitacaoDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.SolicitacaoAoSindico;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author weslley.matos
 */
public class SolicitacaoDAOImpl extends DaoBase<SolicitacaoAoSindico> implements SolicitacaoDAO {

    public SolicitacaoDAOImpl() {
        super(SolicitacaoAoSindico.class);
    }

    public SolicitacaoAoSindico retornarSolicitacoes(SolicitacaoAoSindico solicitacao) throws DAOException {
        List<SolicitacaoAoSindico> lista = this.findByRestrictions(0,
                
                Restrictions.eq("solicitante.id", solicitacao.getSolicitante().getId()),
                Restrictions.ilike("descricao", solicitacao.getDescricao())
               
        );
        return lista!=null&&!lista.isEmpty()?lista.get(0):null;
    }
    public List<SolicitacaoAoSindico> retornarListaSolicitacao(Long idCond,Long idUser,String status) throws DAOException{
        return this.findByRestrictions(0,
                idCond!=null?Restrictions.eq("condominio.id", idCond):null,
                idUser!=null?Restrictions.eq("solicitante.id", idUser):null,
                status!=null?Restrictions.ilike("status", status,MatchMode.ANYWHERE):null
        );
        
    }


}
