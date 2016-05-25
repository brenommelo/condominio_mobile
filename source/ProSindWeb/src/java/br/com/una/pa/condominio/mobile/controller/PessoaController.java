/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.una.pa.condominio.mobile.controller;

import br.com.una.pa.condominio.mobile.entidades.Condominio;
import br.com.una.pa.condominio.mobile.entidades.Pessoa;

/**
 *
 * @author breno
 */
public class PessoaController {
    
    public PessoaController() {
    }

    public Condominio salvarPessoa(Pessoa pessoa) {
        if (validarDuplicidadePessoa(pessoa)) {
            
        }
        
        return null;
    }

    public Boolean validarDuplicidadePessoa(Pessoa pessoa) {
        return false;
    }
    
}
