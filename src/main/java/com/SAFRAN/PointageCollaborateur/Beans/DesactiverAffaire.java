package com.SAFRAN.PointageCollaborateur.Beans;


import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;




import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;


@ManagedBean(name = "desactiverAffaire")
@SessionScoped
public class DesactiverAffaire {

	private List<Affaire> filteredAffaires;
	
		
	@ManagedProperty("#{pointageservice}")
	private PointageService pointageservice;

	public List<Affaire> getFilteredAffaires() {
		return filteredAffaires;
	}

	public void setFilteredAffaires(List<Affaire> filteredAffaires) {
		this.filteredAffaires = filteredAffaires;
	}

	public PointageService getPointageservice() {
		return pointageservice;
	}

	public void setPointageservice(PointageService pointageservice) {
		this.pointageservice = pointageservice;
	}
	
	
	public String Desactivation(Affaire a) {
		
		pointageservice.desactiverAffaire(a);
		return "ListAffaire?faces-redirect=true";
		
	}
}
