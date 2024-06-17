/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.enade.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.enade.dao.ResultadoDao;
import br.com.enade.model.Tbresultado;

/**
 *
 * @author claud
 */
@Named
@ViewScoped
public class DashboardBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;
	private BarChartModel barModel;
	private List<Tbresultado> resultados;

	@Inject
	private ResultadoDao resdao;

	public DashboardBean() {
		resultados = resdao.listarTodos();
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public void listarResultado() {
		try {
			resultados = resdao.listarTodos();
			graficar(resultados);
		} catch (Exception e) {
                    throw new RuntimeException(e);
		}

	}

	private void graficar(List<Tbresultado> listaResultado) {
		pieModel = new PieChartModel();

		for (Tbresultado resultado : listaResultado) {
			pieModel.set(resultado.getTbUsuarioidUsuario().getNomeUsuario(), resultado.getValorObtido());
		}
		pieModel.setTitle("Alunos");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
	}

	public List<Tbresultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Tbresultado> resultados) {
		this.resultados = resultados;
	}

}
