/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufmg.hc.telessaude.webservices.mobile.dao;

import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author weslley.matos
 */
public interface UsuarioDAO extends GenericDao<Usuario>{
    
    /**
     * 
     * @param username
     * @param password
     * @return
     * @throws DAOException 
     */
    public Usuario logon(final String username, final String password) throws DAOException;
    
     public List<Usuario> consultarPorNome(String nome)throws DAOException;
    
    /**
     * 
     * @param filter
     * @param first
     * @param maxResults
     * @return
     * @throws DAOException 
     */
    public List<Usuario> findAll(final HashMap filter, final int first, final int maxResults) throws DAOException;
    
    /**
     * 
     * @param filter
     * @return 
     */
    public Long getDataCount(final HashMap filter);
    
    public Usuario findByName(String nome);
    
    public boolean exists(String username, Long id);
}
