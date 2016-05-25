/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.controller;

import br.com.una.pa.condominio.mobile.entidades.Condominio;

/**
 *
 * @author breno
 */
public class CondominioController {

    public CondominioController() {
    }

    public Condominio salvarCondominio(Condominio condominio) {
        if (validarDuplicidadeCondominio(condominio)) {

        }

        return null;
    }

    public Boolean validarDuplicidadeCondominio(Condominio condominio) {
        return false;
    }

    public Boolean validarDuplicidadePessoa(Condominio condominio) {
        return false;
    }

    public Boolean validarDuplicidadeUnidades(Condominio condominio) {
        return false;
    }

}
