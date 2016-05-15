/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.commons.WebServiceskeys;
import br.ufmg.hc.telessaude.webservices.mobile.dao.UsuarioAlertaDAO;
import br.ufmg.hc.telessaude.webservices.mobile.dao.UsuarioDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.VisualizacaoAlerta;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author weslley.matos
 */
public class UsuarioAlertaDAOImpl extends DaoBase<VisualizacaoAlerta> implements UsuarioAlertaDAO {

    public UsuarioAlertaDAOImpl() {
        super(VisualizacaoAlerta.class);
    }


}
