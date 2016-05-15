/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.resources;

import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.AlertasDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.UsuarioAlertaDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.UsuarioDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.VisualizacaoAlerta;
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
@Path("/alertas")
public class AlertasResources extends CustomResources {

    private AlertasDAOImpl alertasDAOImpl = new AlertasDAOImpl();
    private UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    private UsuarioAlertaDAOImpl usuarioAlertaDAOImpl = new UsuarioAlertaDAOImpl();
    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/retornar_alertas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornarAlertas(final String usuario) {
        try {
            Usuario usu = GsonUtils.getSimpleInstance().fromJson(usuario, Usuario.class);
            if (usu == null || usu.getId() == null) {
                return null;
            } else if (usu.getNome().equalsIgnoreCase("sindico")
                    || usu.getNome().equalsIgnoreCase("cond")
                    || usu.getNome().equalsIgnoreCase("mod")) {
                return GsonUtils.getSimpleInstance().toJson(retornarAlertasTeste(), Alerta[].class);
            }
            usu = usuarioDAOImpl.findById(usu.getId());

            List<Alerta> listaRetorno = alertasDAOImpl.retornarAlertas(usu);
            if (listaRetorno != null && !listaRetorno.isEmpty()) {
                return GsonUtils.getSimpleInstance().toJson(listaRetorno.toArray(), Alerta[].class);
            }
            listaRetorno = new ArrayList();
            listaRetorno.add(retornarAlerta());
            return GsonUtils.getSimpleInstance().toJson(listaRetorno.toArray(), Alerta[].class);
        } catch (DAOException ex) {
            Logger.getLogger(AlertasResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Erro ao carregar alertas";
    }

    public List<Alerta> retornarAlertasTeste() {

        List<Alerta> listaLertas = new ArrayList();
        Alerta alerta = new Alerta();
        alerta.setDescricao("Temos um novo inquilino");
        alerta.setImagem("fa-check-square");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-success");
        listaLertas.add(alerta);
        alerta = new Alerta();
        alerta.setDescricao("Atenção, amanha o fornecimento de água será suspenso pela copasa");
        alerta.setImagem("fa-exclamation-triangle");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-danger");
        listaLertas.add(alerta);
        alerta = new Alerta();
        alerta.setDescricao("Assembléia geral para eleição de sindico agendada para dia 15/12/2016 as 10:00 h");
        alerta.setImagem("fa-bell");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-warning");
        listaLertas.add(alerta);
        alerta = new Alerta();
        alerta.setDescricao("Informamos a todos que o horário do recolhimento do lixo passou a ser realizado nas segundas as 16:00 h");
        alerta.setImagem("fa-info-circle");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setTipo("alert-info");
        listaLertas.add(alerta);
        return listaLertas;
    }

    /**
     *
     *
     * @param jsonAlerta
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar_alertas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvarAlertas(final String jsonAlerta) {
        try {
            Alerta alerta = GsonUtils.getInstanceWithStringDateAdapter().fromJson(jsonAlerta, Alerta.class);
            alerta = montarAlerta(alerta, false);
            if (alerta.getApartamento() != null && !alerta.getApartamento().isEmpty()) {
                List<Usuario> lista = usuarioDAOImpl.retornarUsuarioPorApto(alerta.getApartamento());
                if (lista == null || lista.isEmpty()) {
                    return "Erro! Apartamento ainda não cadastrado";
                } else {
                    for (Usuario usuario : lista) {
                        alerta.setDestinatario(usuario);
                        alerta.setCondominio(null);
                        salvarAlerta(alerta);
                    }
                }
            } else {
                alerta = salvarAlerta(alerta);
                if (alerta == null) {
                    return "Erro! não pode inserir alertas iguais";
                } else {

                  return GsonUtils.getSimpleInstance().toJson(alerta, Alerta.class);
                }
            }

        } catch (DAOException ex) {
            Logger.getLogger(AlertasResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Erro ao salvar alerta";
    }
 
    public Alerta salvarAlerta(Alerta alerta) {
        try {
            Alerta validacao = alertasDAOImpl.retornarAlertas(alerta);
            if (validacao != null && validacao.getId() != null) {
                return null;
            } else {
                return alertasDAOImpl.saveOrUpdate(alerta);
            }
        } catch (DAOException ex) {
            Logger.getLogger(AlertasResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Alerta retornarAlerta() {
        Alerta alerta = new Alerta();
        alerta.setDescricao("Nenhum novo alerta disponível!");
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setImagem("fa-check-square");
        alerta.setTipo("alert-success");
        return alerta;
    }

    public Alerta montarAlerta(Alerta alet, Boolean paraSindico) {
        Alerta alerta = new Alerta();
        alerta.setDescricao(alet.getDescricao());
        alerta.setInclusao(Calendar.getInstance().getTime());
        alerta.setCondominio(alet.getCondominio());
        alerta.setEnderecadasAoSindico(paraSindico);
        alerta.setApartamento(alet.getApartamento());
        if (alerta.getFimExibicao() == null) {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
            alerta.setFimExibicao(cal.getTime());
        }
        if (alet.getTipo().equalsIgnoreCase("alert-danger")) {
            alerta.setImagem("fa-exclamation-triangle");
            alerta.setTipo("alert-danger");
        } else if (alet.getTipo().equalsIgnoreCase("alert-warning")) {
            alerta.setImagem("fa-bell");
            alerta.setTipo("alert-warning");
        } else if (alet.getTipo().equalsIgnoreCase("alert-info")) {
            alerta.setImagem("fa-info-circle");
            alerta.setTipo("alert-info");
        } else {
            alerta.setImagem("fa-check-square");
            alerta.setTipo("alert-success");
        }

        return alerta;

    }

}
