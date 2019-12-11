package com.SAFRAN.PointageCollaborateur.Dao;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;
import com.SAFRAN.PointageCollaborateur.model.Pointage;


public class PointageDaoImp implements PointageDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public String addCollaborateur(Collaborateur c) {
				
		em.persist(c);
		return "Collaborateur?faces-redirect=true";
	}

	@Override
	public Collaborateur updateCollaborateur(Collaborateur collaborateur) {
//		Collaborateur c=consulterCollaborateur(collaborateur.getMatricule());
		
//		Query req =em.createQuery("update Collaborateur c set c.mdp=:a, c.nom=:b, c.prenom=:d, c.email=:e, "
//				                   + "c.role=:f, c.statut=:g, c.etat=:h where c.matricule=:x and c.etat=true");
//		req.setParameter("x", matricule)       ;
//		req.setParameter("a", C.getMdp())      ;
//		req.setParameter("b", C.getNom())      ;
//		req.setParameter("d", C.getPrenom())   ;
//		req.setParameter("e", C.getEmail())    ;
//		req.setParameter("f", C.getRole())     ;
//		req.setParameter("g", C.getStatut())   ;
//		req.setParameter("h", C.isEtat())      ;
//		
//		req.executeUpdate();
		em.merge(collaborateur);		
		return collaborateur;
	}

	@Override
	public Collaborateur consulterCollaborateur(String matricule) {
		
		Collaborateur C=em.find(Collaborateur.class, matricule);
		if(C==null) throw new RuntimeException("employé introuvable");
		else if(C.isEtat()==false){throw new RuntimeException("employé désactivé");}
		return C;
	}

	@Override
	public String desactiverCollaborateur(Collaborateur c) {
		
		if(c.isEtat()==true) {
			c.setEtat(false);
			em.merge(c);
		}
		 return "ListCollaborateur?faces-redirect=true";
	}

	@Override
	public List<Pointage> List_CollaborateurByAffaire(String id_Affaire)  {
		
		Query req=em.createQuery("select p from Pointage p where p.affaires.id_Affaire=:x");
		req.setParameter("x", id_Affaire);
		return req.getResultList();
	}

	@Override
	public List<Collaborateur> List_Collaborateur() {
		
		Query req=em.createQuery("select c from Collaborateur c where c.etat=true ");
		return req.getResultList();
	}

	@Override
	public String addAffaire(Affaire a) {
		
		em.persist(a);
		return "Affaire?faces-redirect=true";
	}

	@Override
	public void updateAffaire(Affaire a) {
		Affaire Af=consulterAffaire(a.getId_Affaire());
		em.merge(Af);
	}

	@Override
	public Affaire consulterAffaire(String id_Affaire) {
		
		Affaire a=em.find(Affaire.class, id_Affaire);
		if(a==null) {throw new RuntimeException("affaire introuvable");}
		else if(a.isEtat()==false){throw new RuntimeException("affaire désactivé");}
		return a;
	}

	@Override
	public String desactiverAffaire(Affaire a) {
		
		if(a.isEtat()==true) {
			a.setEtat(false);
			em.merge(a);
		}
		 return "ListAffaire?faces-redirect=true";
	}

	@Override
	public List<Affaire> list_AffaireBydate(Date d1, Date d2) {
		
		Query req=em.createQuery("select a from Affaire a where a.dateCreation>=x and a.dateCreation<=y");
		req.setParameter("x", d1);
		req.setParameter("y", d2);
		return req.getResultList();
		
	}

	@Override
	public List<Affaire> list_Affaire() {
		
		Query req=em.createQuery("select a from Affaire a where a.etat=true  ");
		return req.getResultList();
	}

	@Override
	public Pointage addPointage(Pointage p) {
		
		em.persist(p);
		return p;
	}

	@Override
	public List<Pointage> updatePointageByDate(String semaine, String mois,int year, String matricule) {
		
		List<Pointage> pointages=consulterPointageByDate(semaine, mois, year, matricule);
		em.persist(pointages);
		return pointages;
		
	}

	@Override
	public List<Pointage> consulterPointageByDate(String semaine, String mois,int year, String matricule) {

		
		Query req=em.createQuery("select p from Pointage p where p.semaine=:x and p.mois=:y and p.year=:w and p.Collaborateurs.matricule=:z");
		req.setParameter("x", semaine);
		req.setParameter("y", mois);
		req.setParameter("w", year);
		req.setParameter("z", matricule);
		return req.getResultList();
	}
	
	@Override
	public double totalePointageByMonth(String mois, String matricule) {
		Query req=em.createQuery("select (sum(p.j1)+ sum(p.j2)+ sum(p.j3)+ sum(p.j4)+ sum(p.j5) )from Pointage p where  p.mois=:y  and p.Collaborateurs.matricule=:z");
		req.setParameter("y", mois);
		req.setParameter("z", matricule);
		List lst= req.getResultList();
		double total;
		total=(Double) lst.get(0);
		return total;
		
	}
	
	@Override
	public void updatePointage(Pointage p) {
		
		em.merge(p);
		
	}

	@Override
	public Pointage consulterPointage(Long id_Pointage) {
		
		Pointage P=em.find(Pointage.class, id_Pointage);
		if(P==null) throw new RuntimeException("Pointage introuvable");
		return P;
		
	}

	@Override
	public void deletPointage(Pointage p) {
		Object managed = em.merge(p);
		em.remove(managed);
	}

	@Override
	public List<Pointage> listPointage() {
		
		Query req=em.createQuery("select p from Pointage p ");
		return req.getResultList();
	}

	@Override
	public PieChartModel ProductionByAffaire(String activite) {
		
		PieChartModel productCategory = new PieChartModel();
		Query req=em.createQuery("select p.affaires.nom_Affaire,sum(p.j1+ p.j2+ p.j3+ p.j4+ p.j5) from Pointage p where  p.mois=:y and p.affaires.activite=:z group by p.affaires.nom_Affaire");
		Calendar calendar = Calendar.getInstance();
		String mois = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];	
		req.setParameter("y", mois);
		req.setParameter("z", activite);
		List<Object[]> res = req.getResultList(); 
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
	      		
		 return productCategory;
	}

	@Override
	public BarChartModel CollaborateurByAffaire()  {
		
		BarChartModel CollByAff = new BarChartModel();
		Query req=em.createQuery("select p.affaires.nom_Affaire,count(p.Collaborateurs.matricule) from Pointage p where  p.mois=:y and p.affaires.activite='electronique' group by p.affaires.nom_Affaire");
		Calendar calendar = Calendar.getInstance();
		String mois = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
		req.setParameter("y", mois);
		 ChartSeries col_aff = new ChartSeries();
		 col_aff.setLabel("Nbr de Collaborateur par affaire");
		List<Object[]> res = req.getResultList(); 
		 for (Object[] o: res)
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
		return CollByAff;
	}

	@Override
	public List Production(String mois,String activite) {
		
		Query req=em.createQuery("select p.affaires.nom_Affaire,sum(p.j1+ p.j2+ p.j3+ p.j4+ p.j5) from Pointage p where  p.mois=:y and p.affaires.activite=:z group by p.affaires.nom_Affaire");	
		req.setParameter("y", mois);
		req.setParameter("z", activite);
		return req.getResultList();
	}

	@Override
	public List Coll_Affaire(String mois, String activite) {

		Query req=em.createQuery("select p.affaires.nom_Affaire,count(p.Collaborateurs.matricule) from Pointage p where  p.mois=:y and p.affaires.activite=:z group by p.affaires.nom_Affaire");
		req.setParameter("y", mois);
		req.setParameter("z", activite);
		return req.getResultList();
	}

	@Override
	public Collaborateur findCollab(String matricule, String password) {
		   Query req=em.createQuery("select c from Collaborateur c where c.matricule=:x and c.mdp=:y");
		   req.setParameter("x", matricule);
		   req.setParameter("y",  password);

		   Collaborateur collaborateur;
		   try {
	
		   collaborateur = (Collaborateur) req.getSingleResult();
		   return collaborateur;
		   } catch (Exception e) {
		   System.out.println("Utilisateur "+matricule+" ou mot de passe non valides");
		   return null;
		}
	}
}