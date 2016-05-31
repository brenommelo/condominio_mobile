/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.resource;

import br.com.una.pa.condominio.mobile.controller.ConfiguracaoController;
import br.com.una.pa.condominio.mobile.entidades.Estado;
import br.com.una.pa.condominio.mobile.entidades.Municipio;
import br.com.una.pa.condominio.mobile.entidades.TipoCondominio;
import br.com.una.pa.condominio.mobile.entidades.TipoUnidade;
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
@Path("/configuracao")
public class ConfiguracaoResources extends CustomResources {

    ConfiguracaoController configuracaoController = new ConfiguracaoController();

    @POST
    @Path("/retornar_estados")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarEstados(final String receitaJson) {

        List<Estado> lista = configuracaoController.listarEstados();

        return GsonUtils.getSimpleInstance().toJson(lista, Estado[].class);
    }

    @POST
    @Path("/retornar_municipios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarMunicipios(final String receitaJson) {
        Long idEstado = GsonUtils.getSimpleInstance().fromJson(receitaJson, Long.class);
        List<Municipio> lista = configuracaoController.listarMunicipios(idEstado);

        return GsonUtils.getSimpleInstance().toJson(lista, Municipio[].class);
    }
    @POST
    @Path("/retornar_tipo_condominio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarTipoCondominio(final String objetoJson) {
        List<TipoCondominio> lista = configuracaoController.listarTipoCondominio();

        return GsonUtils.getSimpleInstance().toJson(lista, TipoCondominio[].class);
    }
    @POST
    @Path("/retornar_tipo_unidade")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarTipoUnidade(final String objetoJson) {
        List<TipoUnidade> lista = configuracaoController.listarTipoUnidade();
        
        return GsonUtils.getSimpleInstance().toJson(lista.toArray(), TipoUnidade[].class);
    }

}
