/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.resource;

import br.com.una.pa.condominio.mobile.controller.FinanceiroController;
import br.com.una.pa.condominio.mobile.entidades.Despesa;
import br.com.una.pa.condominio.mobile.entidades.Receita;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.LogonException;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
import java.util.ArrayList;
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
@Path("/financeiro")
public class FinanceiroResources extends CustomResources {

    FinanceiroController financeiroController = new FinanceiroController();

    /**
     *
     * @param receitaJson
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar_receita")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarReceita(final String receitaJson) {

        Receita receita = GsonUtils.getSimpleInstance().fromJson(receitaJson, Receita.class);
        if (receita == null || receita.getCondominio() == null
                || receita.getCondominio().getId() == null || receita.getValor() == null
                || receita.getNome() == null || receita.getNome().isEmpty()) {
            return null;
        } else {
            financeiroController.salvarReceita(receita);
        }

        return GsonUtils.getSimpleInstance().toJson(receita, Receita.class);
    }

    /**
     *
     * @param despesaJson
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar_despesa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarDespesa(final String despesaJson) {

        Despesa despesa = GsonUtils.getSimpleInstance().fromJson(despesaJson, Despesa.class);
        if (despesa == null || despesa.getCondominio() == null
                || despesa.getCondominio().getId() == null || despesa.getValor() == null
                || despesa.getNome() == null || despesa.getNome().isEmpty()) {
            return null;
        } else {
            financeiroController.salvarDespesa(despesa);
        }

        return GsonUtils.getSimpleInstance().toJson(despesa, Despesa.class);
    }

    @POST
    @Path("/remover_receita")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String removerReceita(final String objJson) {
        Boolean removeu = false;
        Receita objeto = GsonUtils.getSimpleInstance().fromJson(objJson, Receita.class);
        if (objeto == null || objeto.getId() == null) {
//            return null;
            removeu = false;
        } else {
            removeu = financeiroController.remover(objeto);
        }

        return GsonUtils.getSimpleInstance().toJson(removeu, Boolean.class);
    }

    @POST
    @Path("/remover_despesa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String removerDespesa(final String objJson) {
        Boolean removeu = false;
        Despesa objeto = GsonUtils.getSimpleInstance().fromJson(objJson, Despesa.class);
        if (objeto == null || objeto.getId() == null) {
            removeu = false;
        } else {
            removeu = financeiroController.remover(objeto);
        }

        return GsonUtils.getSimpleInstance().toJson(removeu, Boolean.class);
    }

    @POST
    @Path("/consultar_despesa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String consultarDespesa(final String objJson) {
        List<Despesa> listaRetorno = new ArrayList();
        Despesa objeto = GsonUtils.getSimpleInstance().fromJson(objJson, Despesa.class);
        if (objeto == null || objeto.getId() == null || objeto.getInclusao() == null || objeto.getRealizacao() == null) {
            return null;
        } else {
            listaRetorno = financeiroController.consultar(objeto, objeto.getInclusao(), objeto.getRealizacao());
        }

        return GsonUtils.getSimpleInstance().toJson(objeto, Receita[].class);
    }

    @POST
    @Path("/consultar_receita")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String consultarReceita(final String objJson) {
        List<Receita> listaRetorno = new ArrayList();
        Receita objeto = GsonUtils.getSimpleInstance().fromJson(objJson, Receita.class);
        if (objeto == null || objeto.getId() == null || objeto.getInclusao() == null || objeto.getRealizacao() == null) {
            return null;
        } else {
            listaRetorno = financeiroController.consultar(objeto, objeto.getInclusao(), objeto.getRealizacao());
        }

        return GsonUtils.getSimpleInstance().toJson(listaRetorno, Receita[].class);
    }

}
