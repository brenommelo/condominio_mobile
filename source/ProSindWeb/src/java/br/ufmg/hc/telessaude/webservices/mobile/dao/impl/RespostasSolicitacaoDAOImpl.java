/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.dao.AlertasDAO;
import br.ufmg.hc.telessaude.webservices.mobile.dao.RespostaSolicitacaoDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.RespostaSolicitacao;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class RespostasSolicitacaoDAOImpl extends DaoBase<RespostaSolicitacao> implements RespostaSolicitacaoDAO {

    public RespostasSolicitacaoDAOImpl() {
        super(RespostaSolicitacao.class);
    }
    public List<RespostaSolicitacao> retornarResposta(Long idSolicitacao) throws DAOException{
        return this.findByRestrictions(0,
                Restrictions.eq("solicitacaoAoSindico.id", idSolicitacao)
        );
    }
   
}
