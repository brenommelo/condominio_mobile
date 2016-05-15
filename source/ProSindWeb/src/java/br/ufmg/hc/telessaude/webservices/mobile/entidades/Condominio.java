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
@Table(name = "condominios",  schema = "prosind")
public class Condominio  {

    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date inclusao;
    private String nome;
        @Column(name = "numero_blocos")
    private Integer numeroBlocos;
        @Column(name = "numero_apartamentos")
    private Integer numeroApartamentos;
        @Column(name = "possui_portaria")
    private Boolean possuiPortaria;
        @Column(name = "numero_funcionarios")
    private Integer numeroFuncionarios;
        @Column(name = "codigo_acesso")
    private String codigoAcesso;
    
        @JoinColumn(name = "id_endereco")
    @ManyToOne(optional = true)
    private Endereco endereco;

    

    public Condominio() {
    }

    public Condominio(Long id) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumeroBlocos() {
        return numeroBlocos;
    }

    public void setNumeroBlocos(Integer numeroBlocos) {
        this.numeroBlocos = numeroBlocos;
    }

    public Integer getNumeroApartamentos() {
        return numeroApartamentos;
    }

    public void setNumeroApartamentos(Integer numeroApartamentos) {
        this.numeroApartamentos = numeroApartamentos;
    }

    public Boolean isPossuiPortaria() {
        return possuiPortaria;
    }

    public void setPossuiPortaria(Boolean possuiPortaria) {
        this.possuiPortaria = possuiPortaria;
    }

    public Integer getNumeroFuncionarios() {
        return numeroFuncionarios;
    }

    public void setNumeroFuncionarios(Integer numeroFuncionarios) {
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public String getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(String codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    
}
