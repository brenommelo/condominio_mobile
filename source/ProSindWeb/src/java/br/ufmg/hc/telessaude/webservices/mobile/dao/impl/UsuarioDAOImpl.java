/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.dao.impl;

import br.ufmg.hc.telessaude.webservices.mobile.commons.WebServiceskeys;
import br.ufmg.hc.telessaude.webservices.mobile.dao.UsuarioDAO;
import br.ufmg.hc.telessaude.webservices.mobile.entidades.Usuario;
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
public class UsuarioDAOImpl extends DaoBase<Usuario> implements UsuarioDAO {

    public UsuarioDAOImpl() {
        super(Usuario.class);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws DAOException
     */
    @Override
    public Usuario logon(final String username, final String password) throws DAOException {
        final List<Usuario> users = findByRestrictions(0, Restrictions.eq("login", username), Restrictions.eq("senha", password));
        if (users.size() > 0) {
            return users.get(0);
        }

        return null;
    }
    public List<Usuario> retornarUsuarioPorApto(String apto) throws DAOException {
        return findByRestrictions(0,
                Restrictions.ilike("apartamento", apto, MatchMode.EXACT)
        );
        
    }

    @Override
    public List<Usuario> findAll(final HashMap filter, final int first, final int maxResults) throws DAOException {
        final StringBuilder hql = new StringBuilder();
        hql.append("SELECT u.id, pf.nome, u.usuario, pf.estabelecimento, u.ativo");
        if (filter.containsKey(WebServiceskeys.TIPO_CONSULTA)) {
            hql.append(" FROM Usuario u, Permissao p ");
            hql.append("JOIN p.tipoConsulta tc ");
            hql.append("JOIN u.profissional pf ");
            hql.append("JOIN p.usuario us ");
            hql.append("WHERE u.id = us.id ");
            hql.append("AND u.ativo = :status ");
        } else {
            hql.append(" FROM Usuario u ");
            hql.append("JOIN u.profissional pf ");
            hql.append("WHERE u.ativo = :status ");
        }

        if (filter.containsKey(WebServiceskeys.NOME)) {
            hql.append("AND pf.nome LIKE :nome ");
        }

        if (filter.containsKey(WebServiceskeys.ESTABELECIMENTO)) {
            hql.append("AND pf.estabelecimento LIKE :estabelecimento ");
        }

        if (filter.containsKey(WebServiceskeys.TIPO_CONSULTA)) {
            hql.append("AND tc.id = :tipoConsulta ");
        }

        final Session session = HibernateUtil.currentSession();
        final Query query = session.createQuery(hql.toString());

        query.setParameter("status", filter.get(WebServiceskeys.STATUS));

        if (filter.containsKey(WebServiceskeys.NOME)) {
            query.setParameter("nome", "%" + filter.get(WebServiceskeys.NOME) + "%");
        }

        if (filter.containsKey(WebServiceskeys.ESTABELECIMENTO)) {
            query.setParameter("estabelecimento", "%" + filter.get(WebServiceskeys.ESTABELECIMENTO) + "%");
        }

        if (filter.containsKey(WebServiceskeys.TIPO_CONSULTA)) {
            query.setParameter("tipoConsulta", filter.get(WebServiceskeys.TIPO_CONSULTA));
        }

        query.setFirstResult(first);
        query.setMaxResults(maxResults);

        final List<Object[]> list = query.list();
        final List<Usuario> usuarios = new ArrayList<>();

        for (Object[] obj : list) {
            final Usuario user = new Usuario();
            user.setId((Long) obj[0]);


            usuarios.add(user);
        }

        return usuarios;
    }

    @Override
    public Long getDataCount(final HashMap filter) {
        final StringBuilder hql = new StringBuilder();
        if (filter.containsKey(WebServiceskeys.TIPO_CONSULTA)) {
            hql.append("SELECT COUNT(u.id) FROM Usuario u, Permissao p ");
            hql.append("JOIN p.tipoConsulta tc ");
            hql.append("JOIN u.profissional pf ");
            hql.append("JOIN p.usuario us ");
            hql.append("WHERE u.id = us.id ");
            hql.append("AND u.ativo = :status ");
        } else {
            hql.append("SELECT COUNT(u.id) FROM Usuario u ");
            hql.append("JOIN u.profissional pf ");
            hql.append("WHERE u.ativo = :status ");
        }

        if (filter.containsKey(WebServiceskeys.NOME)) {
            hql.append("AND pf.nome LIKE :nome ");
        }

        if (filter.containsKey(WebServiceskeys.ESTABELECIMENTO)) {
            hql.append("AND pf.estabelecimento LIKE :estabelecimento ");
        }

        if (filter.containsKey(WebServiceskeys.TIPO_CONSULTA)) {
            hql.append("AND tc.id = :tipoConsulta ");
        }

        session = HibernateUtil.currentSession();
        final Query query = session.createQuery(hql.toString());

        query.setParameter("status", filter.get(WebServiceskeys.STATUS));

        if (filter.containsKey(WebServiceskeys.NOME)) {
            query.setParameter("nome", "%" + filter.get(WebServiceskeys.NOME) + "%");
        }

        if (filter.containsKey(WebServiceskeys.ESTABELECIMENTO)) {
            query.setParameter("estabelecimento", "%" + filter.get(WebServiceskeys.ESTABELECIMENTO) + "%");
        }

        if (filter.containsKey(WebServiceskeys.TIPO_CONSULTA)) {
            query.setParameter("tipoConsulta", filter.get(WebServiceskeys.TIPO_CONSULTA));
        }

        return (Long) query.uniqueResult();
    }

    @Override
    public Usuario findByName(String nome) {

        try {
            final DetachedCriteria crit = DetachedCriteria.forClass(c);
            final Criteria criteria = crit.getExecutableCriteria(HibernateUtil.currentSession());

//            criteria.createAlias("usuario", "u");
            criteria.add(Restrictions.eq("usuario", nome));

            return (Usuario) criteria.uniqueResult();

        } catch (HibernateException ex) {
//             
        }
        return null;
    }

    @Override
    public List<Usuario> consultarPorNome(String nome) throws DAOException {

        try {
            final DetachedCriteria crit = DetachedCriteria.forClass(c);
            final Criteria criteria = crit.getExecutableCriteria(HibernateUtil.currentSession());

            criteria.createAlias("profissional", "pro");
            if (nome != null && !nome.isEmpty()) {
                criteria.add(Restrictions.eq("pro.nome", nome));
            }

            return criteria.list();

        } catch (HibernateException ex) {
//             
        }
        return null;
    }

    @Override
    public boolean exists(String username, Long id) {

        final StringBuilder hql = new StringBuilder();
        hql.append("select obj.id from Usuario obj ");
        hql.append("where obj.usuario = :username ");
        boolean use_id = (id != null && id != 0);
        if (use_id) {
            hql.append(" and obj.id <> :id");
        }
        session = HibernateUtil.currentSession();
        final Query query = session.createQuery(hql.toString());
        query.setParameter("username", username);
        if (use_id) {
            query.setParameter("id", id);
        }
        boolean exists = false;
        try {
            query.setMaxResults(1);
            exists = query.uniqueResult() != null;
        } catch (Exception e) {
        }
        return exists;
    }

}
