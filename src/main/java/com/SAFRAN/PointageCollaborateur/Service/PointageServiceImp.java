package com.SAFRAN.PointageCollaborateur.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SAFRAN.PointageCollaborateur.Dao.PointageDao;
import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;
import com.SAFRAN.PointageCollaborateur.model.Pointage;

@Transactional
@ManagedBean(name="pointageservice")
@Service
@ApplicationScoped
public class PointageServiceImp implements PointageService{
	
	private PointageDao pointagedao;
	
	

	public void setPointagedao(PointageDao pointagedao) {
		this.pointagedao = pointagedao;
	}

	@Override
	public String addCollaborateur(Collaborateur c) {
		
		return pointagedao.addCollaborateur(c);
	}

	@Override
	public void updateCollaborateur(Collaborateur c) {
		
		pointagedao.updateCollaborateur(c);
		
	}

	@Override
	public Collaborateur consulterCollaborateur(String matricule) {
		
		return pointagedao.consulterCollaborateur(matricule);
	}

	@Override
	public String desactiverCollaborateur(Collaborateur c) {
		
		return pointagedao.desactiverCollaborateur(c);
	}

	@Override
	public List<Pointage> List_CollaborateurByAffaire(String id_Affaire) {
		
		return pointagedao.List_CollaborateurByAffaire(id_Affaire);
	}

	@Override
	public List<Collaborateur> List_Collaborateur() {
		
		return pointagedao.List_Collaborateur();
	}

	@Override
	public String addAffaire(Affaire a) {
		
		return pointagedao.addAffaire(a);
	}

	@Override
	public void updateAffaire(Affaire a) {
		
		pointagedao.updateAffaire(a);
	}

	@Override
	public Affaire consulterAffaire(String id_Affaire) {
		
		return pointagedao.consulterAffaire(id_Affaire);
	}

	@Override
	public String desactiverAffaire(Affaire a) {
		
		return pointagedao.desactiverAffaire(a);
	}

	@Override
	public List<Affaire> list_AffaireBydate(Date d1, Date d2) {
		
		return pointagedao.list_AffaireBydate(d1, d2);
	}

	@Override
	public List<Affaire> list_Affaire() {
		
		return pointagedao.list_Affaire();
	}

	@Override
	public Pointage addPointage(Pointage p) {
		
		return pointagedao.addPointage(p);
	}

	@Override
	public List<Pointage> updatePointageByDate(String semaine, String mois, int year, String matricule) {
		
		return pointagedao.updatePointageByDate(semaine, mois, year, matricule);
	}

	@Override
	public List<Pointage> consulterPointageByDate(String semaine, String mois, int year, String matricule) {
		
		return pointagedao.consulterPointageByDate(semaine, mois, year, matricule);
	}

	@Override
	public double totalePointageByMonth(String mois, String matricule) {
		
		return 0;
	}


	@Override
	public Pointage consulterPointage(Long id_Pointage) {
		// TODO Auto-generated method stub
		return pointagedao.consulterPointage(id_Pointage);
	}

	@Override
	public void updatePointage(Pointage p) {
		
		pointagedao.updatePointage(p);
		
	}

	@Override
	public void deletPointage(Pointage p) {
		
		 pointagedao.deletPointage(p);
	}

	@Override
	public List<Pointage> listPointage() {
		// TODO Auto-generated method stub
		return pointagedao.listPointage();
	}



	@Override
	public PieChartModel ProductionByAffaire(String activite) {
		// TODO Auto-generated method stub
		return pointagedao.ProductionByAffaire(activite);
	}

	@Override
	public BarChartModel CollaborateurByAffaire() {
		// TODO Auto-generated method stub
		return pointagedao.CollaborateurByAffaire();
	}

	@Override
	public List Production(String mois, String activite) {
		
		return pointagedao.Production(mois, activite);
	}

	@Override
	public List Coll_Affaire(String mois, String activite) {
		
		return pointagedao.Coll_Affaire(mois, activite);
	}

	@Override
	public Collaborateur findCollab(String matricule, String password) {
		// TODO Auto-generated method stub
		return pointagedao.findCollab(matricule, password);
	}
}