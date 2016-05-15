/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.resources;

import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.LogonException;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author breno.melo
 */
@Path("/alertas_sindico")
public class AlertasSindicoResources extends CustomResources {


    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/retornar_alertas_sindico")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String  retornarAlertas(final String usuario) {
        List<Alerta> listaLertas = new ArrayList();
        Alerta alerta = new Alerta();
        alerta.setDescricao("Lampada queimada");
        alerta.setImagem("fa-check-square");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-success");
        listaLertas.add(alerta);
        alerta = new Alerta();
        alerta.setDescricao("Muito barulho no apto 202");
        alerta.setImagem("fa-exclamation-triangle");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-danger");
        listaLertas.add(alerta);
        alerta = new Alerta();
        alerta.setDescricao("Revisao de boletos");
        alerta.setImagem("fa-bell");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-warning");
        listaLertas.add(alerta);
        alerta = new Alerta();
        alerta.setDescricao("Vou me mudar amanha as 10h da manha, favor informar os demais condominos");
        alerta.setImagem("fa-info-circle");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-info");
        listaLertas.add(alerta);
        
        return GsonUtils.getSimpleInstance().toJson(listaLertas.toArray(), Alerta[].class);
    }

}
