/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author breno.melo
 */
@Entity
@Table(name = "resposta_solicitcoes", schema = "prosind")
public class RespostaSolicitacao {

    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date inclusao;

    private String descricao;

    @JoinColumn(name = "id_responsavel")
    @ManyToOne(optional = true)
    private Usuario responsavel;

    @JoinColumn(name = "id_solicitacao")
    @ManyToOne(optional = true)
    private SolicitacaoAoSindico solicitacaoAoSindico;



    public RespostaSolicitacao() {
    }

    public RespostaSolicitacao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInclusao() {
        return inclusao;
    }

    public void setInclusao(Date inclusao) {
        this.inclusao = inclusao;
    }

    public Object getClone() {
        try {
            return (super.clone());
        } catch (CloneNotSupportedException e) {
            return (null);
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SolicitacaoAoSindico getSolicitacaoAoSindico() {
        return solicitacaoAoSindico;
    }

    public void setSolicitacaoAoSindico(SolicitacaoAoSindico solicitacaoAoSindico) {
        this.solicitacaoAoSindico = solicitacaoAoSindico;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

}
