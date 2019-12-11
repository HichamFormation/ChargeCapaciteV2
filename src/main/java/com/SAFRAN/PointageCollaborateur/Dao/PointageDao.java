package com.SAFRAN.PointageCollaborateur.Dao;

import java.util.Date;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;
import com.SAFRAN.PointageCollaborateur.model.Pointage;




public interface PointageDao {
	
	/****** CRUD Collaborateur ******/
	
	public String addCollaborateur(Collaborateur c);
	public Collaborateur updateCollaborateur(Collaborateur c);
	public Collaborateur consulterCollaborateur(String matricule);
	public Collaborateur findCollab(String matricule,String password);
	public String desactiverCollaborateur(Collaborateur c);
	public List<Pointage> List_CollaborateurByAffaire(String id_Affaire);
	public List<Collaborateur> List_Collaborateur();

	/****** CRUD Affaire ******/
	
	public String addAffaire(Affaire a);
	public void updateAffaire(Affaire a);
	public Affaire consulterAffaire(String id_Affaire);
	public String desactiverAffaire(Affaire a);
	public List<Affaire> list_AffaireBydate(Date d1,Date d2);
	public List<Affaire> list_Affaire();
	
	/****** CRUD Pointage ******/
	
	public Pointage addPointage(Pointage p);
	public void updatePointage(Pointage p);
	public void deletPointage(Pointage p);
	public List<Pointage> listPointage();
	public Pointage consulterPointage(Long id_Pointage);
	public List<Pointage> updatePointageByDate(String semaine, String mois,int year, String matricule);
	public List<Pointage> consulterPointageByDate(String semaine, String mois,int year, String matricule);
	public double totalePointageByMonth(String mois,String matricule);
	
	/****** Charts ******/
	
	public PieChartModel ProductionByAffaire(String activite);
	public BarChartModel CollaborateurByAffaire();
	public List Production(String mois,String activite);
	public List Coll_Affaire(String mois,String activite);
}
