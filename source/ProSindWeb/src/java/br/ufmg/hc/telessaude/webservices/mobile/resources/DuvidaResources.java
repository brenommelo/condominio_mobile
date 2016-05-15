/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.resources;

import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.DuvidasDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.RespostaDuvidaDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.dao.impl.UsuarioDAOImpl;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Duvidas;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
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
@Path("/forum")
public class DuvidaResources extends CustomResources {

    private final UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    private final DuvidasDAOImpl duvidasDAOImpl = new DuvidasDAOImpl();
    private final RespostaDuvidaDAOImpl respostaDuvidaDAOImpl = new RespostaDuvidaDAOImpl();

    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/retornar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String retornar(final String usuario) {
        try {
            Usuario user = GsonUtils.getSimpleInstance().fromJson(usuario, Usuario.class);
            user = usuarioDAOImpl.findById(user.getId());
          List<Duvidas> lista =  duvidasDAOImpl.retornarDuvidas(
                    user.isModerador() ? user.getId() : null,
                    false, true
            );
            for (Duvidas duvidas : lista) {
                duvidas.setListaRespostas(
                respostaDuvidaDAOImpl.retornarRespostas(duvidas.getId())
                );
                
            }

            return GsonUtils.getSimpleInstance().toJson(lista.toArray(), Duvidas[].class);
        } catch (DAOException ex) {
            Logger.getLogger(DuvidaResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/finalizar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String finalizar(final String duv) {
        try {
            Duvidas duvidas = GsonUtils.getSimpleInstance().fromJson(duv, Duvidas.class);
            duvidas.setAtendida(true);
            duvidasDAOImpl.saveOrUpdate(duvidas);

            return GsonUtils.getSimpleInstance().toJson(duvidas, Duvidas[].class);
        } catch (DAOException ex) {
            Logger.getLogger(DuvidaResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param usuario
     * @return
     * @throws LogonException
     */
    @POST
    @Path("/salvar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvar(final String duvidaString) {
        try {
            Duvidas duvida = GsonUtils.getSimpleInstance().fromJson(duvidaString, Duvidas.class);
            duvida.setAtendida(false);
            duvida.setAtiva(true);
            duvida.setInclusao(Calendar.getInstance().getTime());

            return GsonUtils.getSimpleInstance().toJson(duvidasDAOImpl.saveOrUpdate(duvida), Duvidas.class);
        } catch (DAOException ex) {
            Logger.getLogger(DuvidaResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
