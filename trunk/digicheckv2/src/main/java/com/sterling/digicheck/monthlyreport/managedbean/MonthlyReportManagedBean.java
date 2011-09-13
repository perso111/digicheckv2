package com.sterling.digicheck.monthlyreport.managedbean;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import com.sterling.common.util.JSFUtil;
import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.batch.service.BatchService;
import com.sterling.digicheck.branchoffice.exception.BranchOfficeException;
import com.sterling.digicheck.branchoffice.service.BranchOfficeService;
import com.sterling.digicheck.monthlyreport.view.MonthlyReportView;
import com.sterling.digicheck.user.view.UserView;

@ManagedBean(name="monthlyReportManagedBean")
@RequestScoped
public class MonthlyReportManagedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8478305475988511100L;
	
	@ManagedProperty("#{branchOfficeService}")
	BranchOfficeService branchOfficeService;	
	private String branchOfficeCode;
	@ManagedProperty("#{batchService}")
	private BatchService batchService;
	private List<MonthlyReportView> reportList = null;		
	private String month;
	private String year;
	
	public MonthlyReportManagedBean() {
		this.branchOfficeCode = JSFUtil.getSessionAttribute(UserView.class, "user").getSucursalId();
	}
	
	public List<SelectItem> getBranchOfficeItems(){
		List<SelectItem> bItems = null;
		try {
			bItems = branchOfficeService.branchOfficeItems();
		} catch (BranchOfficeException e) {
			e.printStackTrace();
		}
		return bItems;
	}
	
	
	public String performSearch(){
		if(branchOfficeCode.equals("-1")){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Seleccione al menos una Sucursal", "Seleccione al menos una Sucursal");
		}else{
			
			try {
				batchService.searchMonthlyReport(month, year, branchOfficeCode);
			} catch (BatchException e) {
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Hubo un error al generar el Reporte Mensual.", "Hubo un error al generar el Reporte Mensual.");
			}
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			NumberFormat numberFormat2 = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(0);
			numberFormat.setMinimumFractionDigits(0);
			MonthlyReportView monthlyReportView = null;
			reportList = new ArrayList<MonthlyReportView>();
			for (int i = 0; i < 25; i++) {
				monthlyReportView = new MonthlyReportView();
				String day = String.valueOf((int)(Math.random()*31));
				day = day.length() == 1 ? "0" + day :day;
				monthlyReportView.setDate(day+"/08/2010");
				monthlyReportView.setReference(numberFormat.format(Math.random()*999999999).replaceAll(",", ""));			
				monthlyReportView.setBank("BANORTE");
				monthlyReportView.setCurrency("USD");
				monthlyReportView.setAccount(numberFormat.format(Math.random()*999999).replaceAll(",", ""));
			    monthlyReportView.setDocNum(numberFormat.format(Math.random()*9));
			    numberFormat2.setMaximumFractionDigits(2);
				numberFormat2.setMinimumFractionDigits(2);
				monthlyReportView.setAmount(numberFormat2.format(Math.random()*99999));
			    reportList.add(monthlyReportView);			    
			 }			
		}
		return null;
	}
		

	public List<MonthlyReportView> getReportList() {
		return reportList;
	}

	public void setReportList(List<MonthlyReportView> reportList) {
		this.reportList = reportList;
	}

	public String getBranchOfficeCode() {
		return branchOfficeCode;
	}

	public void setBranchOfficeCode(String branchOfficeCode) {
		this.branchOfficeCode = branchOfficeCode;
	}

	public void setBranchOfficeService(BranchOfficeService branchOfficeService) {
		this.branchOfficeService = branchOfficeService;
	}
		
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}		

}
