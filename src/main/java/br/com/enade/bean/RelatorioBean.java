/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.enade.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.enade.util.Relatorio;
/**
 *
 * @author claud
 */
@Named
@ViewScoped
public class RelatorioBean implements Serializable{

   
	private static final long serialVersionUID = 1L;

	private String relatorio;
    
    private Map<String, String> tipos;
    
    public RelatorioBean() {
        tipos = new HashMap<String, String>();
        tipos.put("Relatorio de Alunos", "ListaAlunos");
        tipos.put("Relatorio de Alunos X Notas", "AlunoXNota");
        tipos.put("Relatorio de Alunos sem Prova", "AlunosPendentesSemProva");
        tipos.put("Relatorio de Alunos com Prova Pendente", "AlunosPendentesComProva");
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public Map<String, String> getTipos() {
        return tipos;
    }

    public void setTipos(Map<String, String> tipos) {
        this.tipos = tipos;
    }
    
    public void gerarRelatorio() {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio(this.relatorio);
    }
    
}