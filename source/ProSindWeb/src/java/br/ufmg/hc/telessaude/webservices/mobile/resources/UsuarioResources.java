/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.resources;

import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.AlertasDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.CondominioDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.EnderecoDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.UsuarioDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Alerta;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Condominio;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Endereco;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.LogonException;
import br.ufmg.hc.telessaude.webservices.mobile.utils.GsonUtils;
import java.util.Calendar;
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
@Path("/usuario")
public class UsuarioResources extends CustomResources {

    private final UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    private final EnderecoDAOImpl enderecoDAOImpl = new EnderecoDAOImpl();
    private final CondominioDAOImpl condominioDAOImpl = new CondominioDAOImpl();
    private final AlertasDAOImpl alertasDAOImpl = new AlertasDAOImpl();

    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/logon")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String logon(final String usuario) {

        Usuario usu = GsonUtils.getSimpleInstance().fromJson(usuario, Usuario.class);
        if (usu == null || usu.getLogin() == null || usu.getLogin().isEmpty()) {
            return null;
        }
        if ((usu.getLogin() != null && !usu.getLogin().isEmpty()) && (usu.getSenha() == null || usu.getSenha().isEmpty())) {
            usu.setApartamento("102");
            usu.setAtivo(true);
            usu.setEmail("asd@asd.com");
            usu.setId(1l);
            usu.setInclusao(Calendar.getInstance().getTime());
            usu.setSenha("123456");
            usu.setTelefone("3125984534");
            usu.setUltimoLogin(Calendar.getInstance().getTime());
            usu.setValidadoSindico(true);
            if (usu.getLogin().equalsIgnoreCase("sindico")) {
                usu.setModerador(false);
                usu.setSindico(true);
                usu.setNome("Usuario sindico");
            } else if (usu.getLogin().equalsIgnoreCase("cond")) {
                usu.setModerador(false);
                usu.setSindico(false);
                usu.setNome("Usuario condomino");
            } else if (usu.getLogin().equalsIgnoreCase("mod")) {
                usu.setNome("Usuario moderador");
                usu.setSindico(true);
                usu.setModerador(true);

            }
        } else if (usu.getLogin() != null && !usu.getLogin().isEmpty() && usu.getSenha() != null && !usu.getSenha().isEmpty()) {
            try {
                usu = usuarioDAOImpl.logon(usu.getLogin(), usu.getSenha());
            } catch (DAOException ex) {
                Logger.getLogger(UsuarioResources.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return GsonUtils.getSimpleInstance().toJson(usu, Usuario.class);
    }

    @POST
    @Path("/salvar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvar(final String usuario) {
        if (usuario == null || usuario.isEmpty()) {
            return null;
        }
        Usuario usu = GsonUtils.getSimpleInstance().fromJson(usuario, Usuario.class);

        try {
            if (usu != null && usu.getCondominio() != null) {

                if (usu.getCondominio().getId() != null) {
                    usu.setCondominio(condominioDAOImpl.findById(usu.getCondominio().getId()));
                } else {

                    Endereco end = validarDuplicidadeEndereco(usu.getCondominio().getEndereco());
                    if (end != null && end.getId() != null) {
                        usu.getCondominio().setEndereco(end);
                    } else {
                        enderecoDAOImpl.saveOrUpdate(usu.getCondominio().getEndereco());
                    }
                    Condominio cond = validarDuplicidadeCondominio(usu.getCondominio());
                    if (cond != null && cond.getId() != null) {
                        usu.setCondominio(cond);
                    } else {
                        condominioDAOImpl.saveOrUpdate(usu.getCondominio());
                    }
                }
            }

            if (validarDuplicidadeUsuario(usu) && usu.getCondominio() != null && usu.getCondominio().getId() != null) {
                usu.setValidadoSindico(true);
                usu.setInclusao(Calendar.getInstance().getTime());
                usu.setAtivo(true);
                usuarioDAOImpl.saveOrUpdate(usu);
                criarAlerta(usu);
            } else {
                return "Erro! Usuário já cadastrado.";
            }
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return GsonUtils.getSimpleInstance().toJson(usu, Usuario.class);
    }

    public Endereco validarDuplicidadeEndereco(Endereco end) {
        try {
            return enderecoDAOImpl.retornarEndereco(end);

        } catch (DAOException ex) {
            Logger.getLogger(UsuarioResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Condominio validarDuplicidadeCondominio(Condominio cond) {
        try {
            return condominioDAOImpl.retornarCondominio(cond);
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean validarDuplicidadeUsuario(Usuario usu) {
        Usuario validacao = null;
        if (usu == null) {
            return false;
        }
        try {
            validacao = usuarioDAOImpl.logon(usu.getLogin(), usu.getSenha());
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validacao == null || validacao.getId() == null;
    }

    public Alerta criarAlerta(Usuario usu) {
        try {
            Alerta alerta = new Alerta();
//            UsuarioAlerta usuarioAlerta = new UsuarioAlerta();
//            usuarioAlerta.setAtivo(true);
            Calendar calFim = Calendar.getInstance();
            calFim.add(Calendar.DAY_OF_MONTH, 2);
            alerta.setDescricao("Novo cadastro realizado! " + usu.getNome() + " - " + usu.getApartamento());
            alerta.setImagem("fa-info-circle");
            alerta.setInclusao(Calendar.getInstance().getTime());
            alerta.setTipo("alert-info");
            alerta.setCondominio(usu.getCondominio());
            alerta.setFimExibicao(calFim.getTime());
            return alertasDAOImpl.saveOrUpdate(alerta);
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
