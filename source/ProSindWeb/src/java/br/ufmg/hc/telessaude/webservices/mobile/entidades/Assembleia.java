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

/**
 *
 * @author breno.melo
 */
@Entity
@Table(name = "assembleias",  schema = "prosind")
public class Assembleia  {

    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date inclusao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_realizacao", columnDefinition = "timestamp default current_timestamp")
    private Date dataRealizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "primeira_chamada", columnDefinition = "timestamp default current_timestamp")
    private Date primeiraChamada;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "segunda_chamada", columnDefinition = "timestamp default current_timestamp")
    private Date segundaChamada;
    private String tipo;
    @Column(name = "local_realizacao")
    private String localRealizacao;
    private Boolean ativa;
    
        @JoinColumn(name = "id_endereco")
    @ManyToOne(optional = true)
    private Condominio condominio;


    

    public Assembleia() {
    }

    public Assembleia(Long id) {
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


    public Date getPrimeiraChamada() {
        return primeiraChamada;
    }

    public void setPrimeiraChamada(Date primeiraChamada) {
        this.primeiraChamada = primeiraChamada;
    }

    public Date getSegundaChamada() {
        return segundaChamada;
    }

    public void setSegundaChamada(Date segundaChamada) {
        this.segundaChamada = segundaChamada;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getLocalRealizacao() {
        return localRealizacao;
    }

    public void setLocalRealizacao(String localRealizacao) {
        this.localRealizacao = localRealizacao;
    }

    public Boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

 


    
}
