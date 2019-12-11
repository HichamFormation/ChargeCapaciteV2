package com.SAFRAN.PointageCollaborateur.Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Pointage;

@ManagedBean(name = "checkPointage")
@SessionScoped
public class CheckPointage {
	
	private List<Pointage> filteredPointage;
	
	
	@ManagedProperty("#{pointageservice}")
	private PointageService pointageservice;


	public List<Pointage> getFilteredPointage() {
		return filteredPointage;
	}


	public void setFilteredPointage(List<Pointage> filteredPointage) {
		this.filteredPointage = filteredPointage;
	}


	public PointageService getPointageservice() {
		return pointageservice;
	}


	public void setPointageservice(PointageService pointageservice) {
		this.pointageservice = pointageservice;
	}

	
	public String ListPointage(Pointage p) {
		
		pointageservice.listPointage();
		
		return "ListePointage?faces-redirect=true";
	}
	
}
