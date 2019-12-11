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


import org.primefaces.model.chart.PieChartModel;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;

@ManagedBean(name = "piecharts")
@SessionScoped
public class PieCharts implements Serializable{

	
	private String activite;
	private Set<String> lists = null;
	
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

	public PieChartModel ProdByAffaire()  {
		
		PieChartModel productCategory = new PieChartModel();
		Calendar calendar = Calendar.getInstance();
		String mois = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];	
		List<Object[]> res = pointageservice.Production(mois, activite) ;
		
		 for(Object[] o: res)
		 {
					 
			 productCategory.set((String)o[0], (Number) o[1]);
		
		 }
		 
	    productCategory.setTitle("Production par affaire");
	            //set legend position to 'e' (east), other values are 'w', 's' and 'n'
	    productCategory.setLegendPosition("e");
	         //enable tooltips		
	        //show labels inside pie chart
		productCategory.setShowDataLabels(true);
	      //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
		 //productCategory.setDataFormat("value");
	    //format: %d for 'value', %s for 'label', %d%% for 'percent'	
	   //pie sector colors
	   	productCategory.setSeriesColors("f00,0f0,00f,ffa,aff,faf,fff,ddd,dda,dad,add,bbb,bba,bab,abb");
		productCategory.setExtender("chartExtender");
		 return productCategory;
	}
	

}
