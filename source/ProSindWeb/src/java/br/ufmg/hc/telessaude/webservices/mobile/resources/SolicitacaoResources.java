/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.resources;

import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.RespostasSolicitacaoDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.SolicitacaoDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.UsuarioDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.RespostaSolicitacao;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.SolicitacaoAoSindico;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.LogonException;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author breno.melo
 */
@Path("/solicitacao")
public class SolicitacaoResources extends CustomResources {

    UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    SolicitacaoDAOImpl solicitacaoDAOImpl = new SolicitacaoDAOImpl();
    RespostasSolicitacaoDAOImpl respostasSolicitacaoDAOImpl = new RespostasSolicitacaoDAOImpl();

    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar_solicitacao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarSolicitacao(final String solicitacaoAoSindico) {
        try {
            SolicitacaoAoSindico solicitacao = GsonUtils.getInstanceWithStringDateAdapter().fromJson(solicitacaoAoSindico, SolicitacaoAoSindico.class);

            solicitacao.setInclusao(Calendar.getInstance().getTime());
            solicitacao.setStatus(SolicitacaoAoSindico.STATUS_ANALISE);
            solicitacao.setSolicitante(usuarioDAOImpl.findById(solicitacao.getSolicitante().getId()));
            solicitacao.setCondominio(solicitacao.getSolicitante().getCondominio());
            SolicitacaoAoSindico validacao = solicitacaoDAOImpl.retornarSolicitacoes(solicitacao);
            if (validacao == null || validacao.getId() == null) {
                solicitacaoDAOImpl.saveOrUpdate(solicitacao);
            } else {
                return "Erro! Solicitação já efetuada. Aguarde a resposta do síndico";
            }

            return "Salvo com sucesso";
        } catch (DAOException ex) {
            Logger.getLogger(SolicitacaoResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Erro ao salvar!";
    }

    /**
     *
     * @param solicitacao
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/alterar_status_solicitacao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String alterarStatus(final String solicitacaoAoSindico) {
        RespostaSolicitacao resp = GsonUtils.getInstanceWithStringDateAdapter().fromJson(solicitacaoAoSindico, RespostaSolicitacao.class);
//         SolicitacaoAoSindico solicitacao = GsonUtils.getInstanceWithStringDateAdapter().fromJson(solicitacaoAoSindico, SolicitacaoAoSindico.class);
         SolicitacaoAoSindico solicitacao = resp.getSolicitacaoAoSindico();
         if(solicitacao!=null&&solicitacao.getId()!=null){
             try {
                 String status = solicitacao.getStatus();
               solicitacao=  solicitacaoDAOImpl.findById(solicitacao.getId());
               solicitacao.setStatus(SolicitacaoAoSindico.STATUS_FINALIZADO);
               solicitacaoDAOImpl.saveOrUpdate(solicitacao);
               return "Alterado com sucesso!";
             } catch (DAOException ex) {
                 Logger.getLogger(SolicitacaoResources.class.getName()).log(Level.SEVERE, null, ex);
                 return "Erro ao alterar o status";
             }
             
         }
         
         
         return null;
         
    }
    /**
     *
     * @param solicitacao
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar_resposta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarResposta(final String resposta) {
         RespostaSolicitacao resp = GsonUtils.getInstanceWithStringDateAdapter().fromJson(resposta, RespostaSolicitacao.class);
             try {
               resp.setInclusao(Calendar.getInstance().getTime());
               respostasSolicitacaoDAOImpl.saveOrUpdate(resp);
               return "Salvo com sucesso!";
             } catch (DAOException ex) {
                 Logger.getLogger(SolicitacaoResources.class.getName()).log(Level.SEVERE, null, ex);
                 return "Erro ao salvar";
             
         }
         
    }
    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/retornar_solicitacao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarSolicitacao(final String usuario) {
        try {
            Usuario usu = GsonUtils.getSimpleInstance().fromJson(usuario, Usuario.class);
            usu= usuarioDAOImpl.findById(usu.getId());
            List<SolicitacaoAoSindico> listaSoli = null;
            if(usu==null){
                return null;
            }
            if (usu.isSindico()) {
                listaSoli = solicitacaoDAOImpl.retornarListaSolicitacao(usu.getCondominio().getId(), null, SolicitacaoAoSindico.STATUS_ANALISE);
            } else{
                listaSoli = solicitacaoDAOImpl.retornarListaSolicitacao(null, usu.getId(), SolicitacaoAoSindico.STATUS_ANALISE);
                
            }
            if(listaSoli!=null&&!listaSoli.isEmpty()){
                for (SolicitacaoAoSindico solicit : listaSoli) {
                    solicit.setListaRespostas(respostasSolicitacaoDAOImpl.retornarResposta(solicit.getId()));
                }
                return GsonUtils.getInstanceWithStringDateAdapter().toJson(listaSoli.toArray(), SolicitacaoAoSindico[].class);
            }
            return null;
        } catch (DAOException ex) {
            Logger.getLogger(SolicitacaoResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Erro! Favor contactar o suporte. ";

    }
}
