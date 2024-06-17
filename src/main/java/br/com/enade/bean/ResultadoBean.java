package br.com.enade.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import br.com.enade.dao.ResultadoDao;
import br.com.enade.model.Tbresultado;
import br.com.enade.tx.Transacional;

@Named
@ViewScoped
public class ResultadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tbresultado resultado;

	private Long resultId;

	@SuppressWarnings("unused")
	@Inject
	private UsuarioBean usuario;

	private List<Tbresultado> listResultado;

	@Inject
	private ResultadoDao resultDao;

	public List<Tbresultado> getListResultado() {
		return this.resultDao.listarTodos();
	}

	@Transacional
	public String gravar() {

		if (this.resultado.getIdResultado() == null) {
			this.resultDao.adiciona(this.resultado);
		} else {
			this.resultDao.atualiza(resultado);
		}

		this.listResultado = this.resultDao.listarTodos();
		this.resultado = new Tbresultado();
		this.resultado = new Tbresultado();

		return "resultado?faces-redirect=true";
	}

	@Transacional
	public void remover(Tbresultado result) {
		this.resultDao.remove(result);
		this.listResultado = this.resultDao.listarTodos();
	}

	public Tbresultado getResultado() {
		return resultado;
	}

	public void setResultado(Tbresultado resultado) {
		this.resultado = resultado;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		// cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
				+ File.separator + "images" + File.separator + "prime_logo.png";

		pdf.add(Image.getInstance(logo));
	}

}
