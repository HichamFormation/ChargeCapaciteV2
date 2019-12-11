package com.SAFRAN.PointageCollaborateur.Beans;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.swing.JOptionPane;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;

@ManagedBean(name = "barcharts")
@SessionScoped
public class BarCharts implements Serializable{
	
	private String activite;
	private Set<String> lists;
	
	@ManagedProperty("#{pointageservice}")
	private PointageService pointageservice;
	
	@PostConstruct
	public void init() {
		
		this.lists = listActivites();
		if(!lists.isEmpty()) {
			
			this.activite=lists.iterator().next();
		}
		
	
	}

	public String getActivite() {

		return activite;
	}
	
	public void setActivite(String activite) {
		this.activite = activite;
	}

	public Set<String> getLists() {
		return lists;
	}

	public void setLists(Set<String> lists) {
		this.lists = lists;
	}

	public PointageService getPointageservice() {
		return pointageservice;
	}


	public void setPointageservice(PointageService pointageservice) {
		this.pointageservice = pointageservice;
	}


	public Set<String> listActivites() {
		List<String> lst = new ArrayList<String>();
		List<Affaire> afrs = pointageservice.list_Affaire();
		for (Affaire af : afrs) {
			lst.add(af.getActivite());
		}
		 Set<String> set = new HashSet<String>(lst);
		 set.remove("");
		 set.remove(null);
		return set;
	}
	
	public BarChartModel Coll_ByAffaire() {
		BarChartModel CollByAff = new BarChartModel();
		Calendar calendar = Calendar.getInstance();
		String mois = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
		ChartSeries col_aff = new ChartSeries();
		col_aff.setLabel("Nbr de Collaborateur par affaire");
		if(!this.getActivite().trim().equals("")) {
		List<Object[]> res = pointageservice.Coll_Affaire(mois, activite) ;
		
		 for(Object[] o: res)
		 {
					 
			 col_aff.set((String)o[0], (Number) o[1]);				
		
		 }
		 
             CollByAff.addSeries(col_aff);
		 
	    	 CollByAff.setTitle("Bar Chart");
		     CollByAff.setLegendPosition("ne");
	 
	         Axis xAxis = CollByAff.getAxis(AxisType.X);
	         xAxis.setLabel("Affaires");
	 
	         Axis yAxis = CollByAff.getAxis(AxisType.Y);
	         yAxis.setLabel("Collaborateurs");
	         yAxis.setMin(0);
	         CollByAff.setSeriesColors("f00,0f0,00f,ffa,aff,faf,fff,ddd,dda,dad,add,bbb,bba,bab,abb");
	         CollByAff.setExtender("chartExtender");
		}
		return CollByAff;
	}

}
