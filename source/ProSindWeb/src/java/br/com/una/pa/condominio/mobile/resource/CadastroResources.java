/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.resource;

import br.com.una.pa.condominio.mobile.controller.CondominioController;
import br.com.una.pa.condominio.mobile.controller.PessoaController;
import br.com.una.pa.condominio.mobile.controller.UnidadesController;
import br.com.una.pa.condominio.mobile.entidades.Condominio;
import br.com.una.pa.condominio.mobile.entidades.Pessoa;
import br.com.una.pa.condominio.mobile.entidades.Unidade;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
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
    PessoaController pessoaController = new PessoaController();
    UnidadesController unidadeController = new UnidadesController();

    @POST
    @Path("/salvar_condominio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cadastroCondominio(final String objetoJson) {
        Condominio condominio = GsonUtils.getInstanceWithStringDateAdapter().fromJson(objetoJson, Condominio.class);
        Condominio retorno = condominioController.salvarCondominio(condominio);
        return GsonUtils.getSimpleInstance().toJson(retorno, Condominio.class);
    }
    @POST
    @Path("/salvar_pessoa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cadastroPessoa(final String objetoJson) {
        Pessoa pessoa = GsonUtils.getInstanceWithStringDateAdapter().fromJson(objetoJson, Pessoa.class);
        Pessoa retorno = pessoaController.salvarPessoa(pessoa);
        return GsonUtils.getSimpleInstance().toJson(retorno, Condominio.class);
    }
    @POST
    @Path("/salvar_unidades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cadastroUnidades(final String objetoJson) {
        Unidade[] listaUnidades = GsonUtils.getInstanceWithStringDateAdapter().fromJson(objetoJson, Unidade[].class);
        Unidade[] retorno = unidadeController.salvarUnidade(listaUnidades);
        return GsonUtils.getSimpleInstance().toJson(retorno, Unidade[].class);
    }

}
