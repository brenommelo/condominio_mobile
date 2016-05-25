/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.resource;

import br.com.una.pa.condominio.mobile.controller.CondominioController;
import br.com.una.pa.condominio.mobile.controller.ConfiguracaoController;
import br.com.una.pa.condominio.mobile.entidades.Condominio;
import br.com.una.pa.condominio.mobile.entidades.Estado;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
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
@Path("/cadastro")
public class CadastroResources extends CustomResources {

    CondominioController condominioController = new CondominioController();

    @POST
    @Path("/salvarCadastro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cadastroCondominio(final String objetoJson) {
        Condominio condominio = GsonUtils.getInstanceWithStringDateAdapter().fromJson(objetoJson, Condominio.class);
        Condominio retorno = condominioController.salvarCondominio(condominio);
        return GsonUtils.getSimpleInstance().toJson(retorno, Condominio.class);
    }

}
