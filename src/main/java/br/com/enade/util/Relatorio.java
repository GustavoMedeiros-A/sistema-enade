/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.enade.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.enade.model.Tbusuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author claud
 */
public class Relatorio implements Serializable{
    
   
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;
    private Connection con;

    public Relatorio() {
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) context.getExternalContext().getResponse();
    }
   
    public void getRelatorio(String relatorio){
        
        stream = this.getClass().getResourceAsStream("/reports/"+relatorio+".jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();
        
        try {
            
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            
            JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);
            
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename="+relatorio+".pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
            context.responseComplete();
            fecharConexao();
            
        } catch (JRException | IOException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void getRelatorioAluno(List<Tbusuario> listaUsuario) throws IOException{
        stream = this.getClass().getResourceAsStream("/reports/ListaAlunos.jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();
        
        try{
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(listaUsuario));
            //JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);
            
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=ListaAlunos.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
            context.responseComplete();
            fecharConexao();
            
        }catch(JRException ex){
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Connection getConexao(){        
        try {            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://mysqldb:3306/enade", "root", "root");
            return con;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    public void fecharConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}