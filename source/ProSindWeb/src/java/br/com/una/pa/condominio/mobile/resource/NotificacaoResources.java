/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.resource;

import br.com.una.pa.condominio.mobile.controller.ConfiguracaoController;
import br.com.una.pa.condominio.mobile.controller.NotificacaoController;
import br.com.una.pa.condominio.mobile.entidades.Estado;
import br.com.una.pa.condominio.mobile.entidades.Municipio;
import br.com.una.pa.condominio.mobile.entidades.Notificacao;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author breno.melo
 */
@Path("/notificacao")
public class NotificacaoResources extends CustomResources {

    NotificacaoController notificacaoController = new NotificacaoController();

    @POST
    @Path("/retornar_notificacoes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarNotificacao(final String objetoJson) {
        Notificacao notificacao = GsonUtils.getSimpleInstance().fromJson(objetoJson, Notificacao.class);
        List<Notificacao> lista = notificacaoController.retornarNotificacoes(
                notificacao.getCondominio().getId(),
                notificacao.getUnidade().getId(),
                notificacao.getTipoNotificacao().getId(),
                notificacao.getSolicitacaoSindico()
        );

        return GsonUtils.getSimpleInstance().toJson(lista, Notificacao[].class);
    }

    @POST
    @Path("/salvar_notificacao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarNotificacao(final String objetoJson) {
        Notificacao notificacao = GsonUtils.getSimpleInstance().fromJson(objetoJson, Notificacao.class);
        Notificacao retorno = notificacaoController.salvarNotificacao(notificacao);

        return GsonUtils.getSimpleInstance().toJson(retorno, Notificacao.class);
    }

}
