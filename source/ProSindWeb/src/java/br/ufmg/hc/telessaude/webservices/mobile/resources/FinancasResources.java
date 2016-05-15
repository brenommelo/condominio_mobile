/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.resources;

import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.DespesaDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.ReceitaDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Condominio;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Despesa;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Receita;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.LogonException;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
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
@Path("/financas")
public class FinancasResources extends CustomResources {

    ReceitaDAOImpl receitaDAOImpl = new ReceitaDAOImpl();
    DespesaDAOImpl despesaDAOImpl = new DespesaDAOImpl();

    /**
     *
     * @param parametro
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar_despesas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarDespesas(final String parametro) {
        Despesa despesa = GsonUtils.getSimpleInstance().fromJson(parametro, Despesa.class);
        try {
            Boolean duplicado = despesaDAOImpl.verificarDulicidade(despesa);
            if (!duplicado) {
                despesa.setInclusao(Calendar.getInstance().getTime());
                despesaDAOImpl.saveOrUpdate(despesa);
                return GsonUtils.getSimpleInstance().toJson(despesa, Despesa.class);
            } else {
                return "Erro! Receita já cadastrada!";
            }
        } catch (DAOException ex) {
            Logger.getLogger(FinancasResources.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Erro!";
    }

    @POST
    @Path("/retornar_extrato")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarExtrato(final String parametro) {
        Condominio condominio = GsonUtils.getSimpleInstance().fromJson(parametro, Condominio.class);
        Calendar calInicio = Calendar.getInstance();
        Calendar calFim = Calendar.getInstance();
        calInicio.set(Calendar.DAY_OF_MONTH, 1);
        calInicio.set(Calendar.HOUR_OF_DAY, 1);
        calInicio.set(Calendar.MINUTE, 1);
        calInicio.set(Calendar.SECOND, 1);

        calFim.setTime(calInicio.getTime());
        calFim.add(Calendar.MONTH, 1);
        try {
            Extrato extrato = new Extrato(
                    receitaDAOImpl.listarReceitas(condominio.getId(), calInicio, calFim),
                    despesaDAOImpl.listarDespesas(condominio.getId(), calInicio, calFim));
            return GsonUtils.getSimpleInstance().toJson(extrato, Extrato.class);
        } catch (DAOException ex) {
            Logger.getLogger(FinancasResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Erro!";
    }

    @POST
    @Path("/salvar_receitas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarReceitas(final String parametro) {
        Receita receita = GsonUtils.getSimpleInstance().fromJson(parametro, Receita.class);
        try {
            Boolean duplicado = receitaDAOImpl.verificarDulicidade(receita);
            if (!duplicado) {
                receita.setInclusao(Calendar.getInstance().getTime());
                receitaDAOImpl.saveOrUpdate(receita);
                return GsonUtils.getSimpleInstance().toJson(receita, Receita.class);
            } else {
                return "Erro! Receita já cadastrada!";
            }
        } catch (DAOException ex) {
            Logger.getLogger(FinancasResources.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Erro!";
    }

    class Extrato {

        private List<Receita> listaReceitas;
        private List<Despesa> listaDespesas;

        public Extrato(List<Receita> listaReceitas, List<Despesa> listaDespesas) {
            this.listaReceitas = listaReceitas;
            this.listaDespesas = listaDespesas;
        }

        public List<Receita> getListaReceitas() {
            return listaReceitas;
        }

        public void setListaReceitas(List<Receita> listaReceitas) {
            this.listaReceitas = listaReceitas;
        }

        public List<Despesa> getListaDespesas() {
            return listaDespesas;
        }

        public void setListaDespesas(List<Despesa> listaDespesas) {
            this.listaDespesas = listaDespesas;
        }
    }
}
