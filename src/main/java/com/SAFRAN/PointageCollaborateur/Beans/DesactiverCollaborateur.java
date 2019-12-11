package com.SAFRAN.PointageCollaborateur.Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;

@ManagedBean(name = "desactiverCollaborateur")
@SessionScoped
public class DesactiverCollaborateur {
	
	private List<Collaborateur> filteredCollaborateur;
	
	@ManagedProperty("#{pointageservice}")
	private PointageService pointageservice;


	public List<Collaborateur> getFilteredCollaborateur() {
		return filteredCollaborateur;
	}


	public void setFilteredCollaborateur(List<Collaborateur> filteredCollaborateur) {
		this.filteredCollaborateur = filteredCollaborateur;
	}


	public PointageService getPointageservice() {
		return pointageservice;
	}


	public void setPointageservice(PointageService pointageservice) {
		this.pointageservice = pointageservice;
	}
	
    public String Desactivation(Collaborateur c) {
		
		pointageservice.desactiverCollaborateur(c);
		return "ListCollaborateur?faces-redirect=true";
		
	}
}
