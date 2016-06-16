/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.dao.impl;

import br.com.una.pa.condominio.mobile.dao.PessoaDAO;
import br.com.una.pa.condominio.mobile.entidades.Pessoa;
import br.ufmg.hc.telessaude.webservices.mobile.exceptions.DAOException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author breno.melo
 */
public class PessoaDAOImpl extends DaoBase<Pessoa> implements PessoaDAO {

    public PessoaDAOImpl() {
        super(Pessoa.class);
    }

    public Boolean verificarSeJaExiste(Pessoa pessoa) throws DAOException {
        List<Pessoa> lista = this.findByRestrictions(0,
                Restrictions.eq("nascimento", pessoa.getNascimento()),
                Restrictions.ilike("nome", pessoa.getNome()),
                Restrictions.eq("sexo", pessoa.getSexo())
        );

        return lista != null && lista.size() > 0;
    }
    public Pessoa salvarPessoa(Pessoa pessoa) throws DAOException{
        try {
            session = HibernateUtil.currentSession();
            transaction = session.beginTransaction();
            transaction.begin();
            session.saveOrUpdate(pessoa.getUsuario());
            session.saveOrUpdate(pessoa);
            transaction.commit();
            return pessoa;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw new DAOException(ex.getMessage());
        } finally {
            this.closeSession();
        }
        
    }

}
