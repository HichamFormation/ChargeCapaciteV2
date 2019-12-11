package com.SAFRAN.PointageCollaborateur.Test;


import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;
import com.SAFRAN.PointageCollaborateur.model.Pointage;

public class test1 {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		PointageService ps = (PointageService) ctx.getBean("pointageservice");
		
		/*ps.addPointage(new Pointage(9, 9, 9, 9, 8, 0, 0, "S10", "Mars", 2019, ps.consulterAffaire("PE/18.600052.05.06"), ps.consulterCollaborateur("L021364")));
        
		ps.addCollaborateur(new Collaborateur("LT21563", "4896", "AJEM",
				  "Hamza", "ajem.hamza@safrangroupe.com", "employé",
				  "technicien d'études", new Date(), true));*/
		
		
		/*
		 * List<Affaire> afr=ps.list_Affaire();
		 * 
		 * for (Affaire a : afr) {
		 * 
		 * System.out.println(a.toString()); }
		 */
		Pointage p =ps.consulterPointage((long) (21));
		p.setJ2(10.0);
		ps.updatePointage(p);
	}

}
