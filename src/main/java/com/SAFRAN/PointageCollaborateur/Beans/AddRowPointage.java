package com.SAFRAN.PointageCollaborateur.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;
import com.SAFRAN.PointageCollaborateur.model.Pointage;

@ManagedBean(name = "addRowPointage")
@SessionScoped
public class AddRowPointage implements Serializable {

	private List<Pointage> pointages = null;
	private List<String> lists = null;
	private String idAffaire;
	private Date date1= new Date();
	
	String matricule;

	GregorianCalendar cal = new GregorianCalendar();
	String week = "S" + (cal.get(Calendar.WEEK_OF_YEAR));
	String month = new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
	int year = cal.get(Calendar.YEAR);
	String [] daysOfWeek=new String[7];
	
	@ManagedProperty("#{pointageservice}")
	private PointageService pointageservice;

	@ManagedProperty("#{pointageservicebean}")
	private PointageServiceBean psb;

	@PostConstruct
	public void init() {
		matricule = System.getProperty("user.name");
		pointages = psb.createPointage(0);
		this.pointages = pointageservice.consulterPointageByDate(week, month, year, matricule);
		idAffaire = "";
		this.lists = listAffaires();
		getDaysFromWeek(this.date1);
	}


	public String getIdAffaire() {
		return idAffaire;
	}

	public void setIdAffaire(String idAffaire) {
		this.idAffaire = idAffaire;
	}

	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	public PointageServiceBean getPsb() {
		return psb;
	}

	public void setPsb(PointageServiceBean psb) {
		this.psb = psb;
	}

	public PointageService getPointageservice() {
		return pointageservice;
	}

	public void setPointageservice(PointageService pointageservice) {
		this.pointageservice = pointageservice;
	}

	public List<Pointage> getPointages() {
		return pointages;
	}

	public void setPointages(List<Pointage> pointages) {
		this.pointages = pointages;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public String[] getDaysOfWeek() {
		return daysOfWeek;
	}


	public void setDaysOfWeek(String[] daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public void onRowEdit(RowEditEvent event) {
		// message de modification:
		Pointage p = new Pointage();
		Affaire af;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			String sem = "S" + (calendar.get(Calendar.WEEK_OF_YEAR));
			String mois = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
			int annee = calendar.get(Calendar.YEAR);
			p = (Pointage) event.getObject();
			af = pointageservice.consulterAffaire(idAffaire);
			p.setAffaires(af);
			Collaborateur collaborateur = pointageservice.consulterCollaborateur(matricule);
			p.setCollaborateurs(collaborateur);
			p.setSemaine(sem);
			p.setYear(annee);
			p.setMois(mois);
			pointageservice.addPointage(p);
		}

		catch (Exception e) {

			pointageservice.updatePointage((Pointage) event.getObject());
			
		}
		FacesMessage msg = new FacesMessage("Pointage Modifié");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Pointage Rejeté");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onDateSelect(SelectEvent event) throws IOException {

		this.date1 = (Date) event.getObject();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		String sem = "S" + (calendar.get(Calendar.WEEK_OF_YEAR));
		String mois = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
		int annee = calendar.get(Calendar.YEAR);
		this.pointages = pointageservice.consulterPointageByDate(sem, mois, annee, matricule);
		
		this.getDaysFromWeek(this.date1);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("AddRowPointage.xhtml");
	}

	public String DeleteRow(Pointage p) {

		pointageservice.deletPointage(p);
		pointages.remove(p);

		return "AddRowPointage?faces-redirect=true";
	}

	public String onAddNew() {
		// Add one new pointage to the table:

		Pointage P = psb.ajouterpointage(new Pointage());
		pointages.add(P);
		FacesMessage msg = new FacesMessage("Nouveau Pointage Ajouté");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "AddRowPointage?faces-redirect=true";
	}

	public List<String> listAffaires() {
		List<String> lst = new ArrayList<String>();
		List<Affaire> afrs = pointageservice.list_Affaire();
		for (Affaire af : afrs) {
			lst.add(af.getId_Affaire());
		}

		return lst;
	}
	
 public void getDaysFromWeek(Date date) {
	 Calendar cal= Calendar.getInstance();
	 cal.setTime(date);
	 cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	 SimpleDateFormat  dtf= new SimpleDateFormat("dd/MM");
     for (int i = 0; i < 7; i++) {
    	 this.daysOfWeek[i] = dtf.format(cal.getTime());
         cal.add(Calendar.DAY_OF_MONTH, 1);
     }
 }
}