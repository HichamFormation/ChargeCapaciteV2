package com.SAFRAN.PointageCollaborateur.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Affaire;
import com.SAFRAN.PointageCollaborateur.model.Pointage;

@ManagedBean(name = "pointageservicebean")
@ApplicationScoped

public class PointageServiceBean {
	
	
	
	
	public List<Pointage> createPointage(int size) {
        List<Pointage> list = new ArrayList<Pointage>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Pointage());
        }
         
        return list;
    }
	/* ajouterpointage
	 * pointage p: objet de pointage 
	 * return objet pointage null
	 */
	public Pointage ajouterpointage(Pointage P) {
		
		Pointage p=new Pointage();
		return p;
	}

	
	
}
