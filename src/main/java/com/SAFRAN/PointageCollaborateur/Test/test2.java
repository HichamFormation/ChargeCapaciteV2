package com.SAFRAN.PointageCollaborateur.Test;

import java.util.TreeSet;

public class test2 {

	public static void main(String[] args) {
		
		 TreeSet<Double> set = new TreeSet<Double>();
	      set.add(12.00);
	      set.add(-5d);
	      set.add(102.00);
	      set.add(12.00);
	      set.add(-52d);
	      //Vous pouvez voir que, malgré l'insertion dans le désordre
	      //L'affichage se fait dans l'ordre
	      System.out.println(set);

	}

}
