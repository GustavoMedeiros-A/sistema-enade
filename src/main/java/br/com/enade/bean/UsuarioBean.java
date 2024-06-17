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

import br.com.enade.dao.TipoUsuarioDao;
import br.com.enade.dao.UsuarioDao;
import br.com.enade.model.Tbtipousuario;
import br.com.enade.model.Tbusuario;
import br.com.enade.tx.Transacional;
import br.com.enade.util.Relatorio;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tbusuario usuario;

	@Inject
	private UsuarioDao dao;

	@Inject
	private TipoUsuarioDao tipoUsuarioDao;

	@Inject
	private LoginBean login;

	@SuppressWarnings("unused")
	private List<Tbusuario> usuarios;

	private Long usuarioId;

	private Long tipoUsuarioId;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<Tbtipousuario> getTipoUsuarios() {
		return this.tipoUsuarioDao.listaTodos();
	}

	public void carregarUsuarioId() {
		System.out.println("Carregando usuario");
		this.usuario = this.dao.buscarPorId(usuarioId);
	}

	public void gravarTipoUsuario() {
		Tbtipousuario tbtipousuario = this.tipoUsuarioDao.buscarPorId(this.tipoUsuarioId);
		this.usuario.setTbTipoUsuarioidTipoUsuario(tbtipousuario);
	}

	public Tbtipousuario getTipoUsuarioDeUsuario() {
		return this.usuario.getTbTipoUsuarioidTipoUsuario();
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando usuario " + this.usuario.getNomeUsuario());

		if (this.usuario.getIdUsuario() == null && login.getUsuarioLogado().getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() != 2) {
			this.dao.adiciona(this.usuario);
		} else if (login.getUsuarioLogado().getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() != 2) {
			this.gravarTipoUsuario();
			this.dao.atualiza(this.usuario);
		}

		this.usuarios = this.dao.listaTodos();
		this.usuario = new Tbusuario();
	}

	@Transacional
	public void gravarAluno() {
		if (this.usuario.getIdUsuario() == null) {
			this.usuario.setTbTipoUsuarioidTipoUsuario(dao.buscarTipo());
			this.dao.adiciona(this.usuario);
			this.usuarios = this.dao.listaTodos();
		}
		this.usuario = new Tbusuario();

	}

	@Transacional
	public void salvar() {
		System.out.println("Gravando usuario " + this.usuario.getNomeUsuario());

		if (this.usuario.getIdUsuario() == null && login.getUsuarioLogado().getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() != 1) {
			this.gravarTipoUsuario();
			if (this.usuario.getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() == 2) {
				this.dao.adiciona(this.usuario);
			}
		} else if (login.getUsuarioLogado().getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() != 1) {
			this.gravarTipoUsuario();
			if (this.usuario.getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() == 2) {
				this.dao.atualiza(this.usuario);
			}
		}

		this.usuarios = this.dao.listaTodos();
		this.usuario = new Tbusuario();

	}

	@Transacional
	public void remover(Tbusuario usuario) {
		System.out.println("Removendo usuario" + usuario.getNomeUsuario());
		this.dao.remove(usuario);
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
		String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
				+ File.separator + "uniacademia.png";

		pdf.add(Image.getInstance(logo));
	}

	public void gerarRelatorio() throws IOException {
		Relatorio relatorio = new Relatorio();
		this.usuarios = dao.listaTodos();
		relatorio.getRelatorioAluno(this.usuarios);
	}

	public String cadastroAluno() {
		return "cadastroAluno?faces-redirect=true";
	}

	public String voltar() {
		return "login?faces-redirect=true";
	}

	public List<Tbusuario> getUsuarios() {
		return this.dao.listaTodos();
	}

	public Tbusuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Tbusuario usuario) {
		this.usuario = usuario;
	}

	public void carregar(Tbusuario ususario) {
		this.usuario = ususario;
	}

	public void novo() {
		this.usuario = new Tbusuario();

	}

	public Long getTipoUsuarioId() {
		return tipoUsuarioId;
	}

	public void setTipoUsuarioId(Long tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}

}
