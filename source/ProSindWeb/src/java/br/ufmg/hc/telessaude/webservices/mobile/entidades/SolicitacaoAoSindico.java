/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.webservices.mobile.entidades;

import java.util.Date;
import java.util.List;
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
@Table(name = "solicitacoes_sindicos", schema = "prosind")
public class SolicitacaoAoSindico {

    @Transient
    public static final String STATUS_ANALISE = "Em análise";
    @Transient
    public static final String STATUS_FINALIZADO = "Finalizado";
    @Transient
    public static final String STATUS_INDEFERIDO = "Indeferido";
    @Transient
    private List<RespostaSolicitacao> listaRespostas;

    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date inclusao;
    private String tipo;
    private String titulo;
    private String descricao;
    @Column(nullable = true)
    private String observacao;
    @Column(nullable = true)
    private Integer porcentagemConcluida;
    @Column(nullable = true)
    private String status;
    @JoinColumn(name = "id_usuario")
    @ManyToOne(optional = true)
    private Usuario solicitante;
    @JoinColumn(name = "id_condominio")
    @ManyToOne(optional = true)
    private Condominio condominio;
    private Integer avaliacaoResposta;

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public SolicitacaoAoSindico() {
    }

    public SolicitacaoAoSindico(Long id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getPorcentagemConcluida() {
        return porcentagemConcluida;
    }

    public void setPorcentagemConcluida(Integer porcentagemConcluida) {
        this.porcentagemConcluida = porcentagemConcluida;
    }

    public Integer getAvaliacaoResposta() {
        return avaliacaoResposta;
    }

    public void setAvaliacaoResposta(Integer avaliacaoResposta) {
        this.avaliacaoResposta = avaliacaoResposta;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public List<RespostaSolicitacao> getListaRespostas() {
        return listaRespostas;
    }

    public void setListaRespostas(List<RespostaSolicitacao> listaRespostas) {
        this.listaRespostas = listaRespostas;
    }

}
