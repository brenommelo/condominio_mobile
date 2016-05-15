/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author breno
 */
public class UsuarioDAOImplTest {
    
    public UsuarioDAOImplTest() {
    }
    
 


    /**
     * Test of findAll method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
      
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.findAll();
        assertEquals(expResult, result);
    }

    
}
