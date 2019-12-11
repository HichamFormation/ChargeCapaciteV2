package com.SAFRAN.PointageCollaborateur.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@ManagedBean(name="pointage")
public class Pointage implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_pointage;
	@Column(name="Lundi")
	private double j1;
	@Column(name="Mardi")
	private double j2;
	@Column(name="Mercredi")
	private double j3;
	@Column(name="Jeudi")
	private double j4;
	@Column(name="Vendredi")
	private double j5;
	@Column(name="Samedi")
	private double j6;
	@Column(name="Dimanche")
	private double j7;
	private String semaine;
	private String mois;
	private int year;
	@ManyToOne
	@JoinColumn(name="id_Affaire")
	private Affaire affaires;
	@ManyToOne
	@JoinColumn(name="idCollaborateur")
	private Collaborateur Collaborateurs;
	
	public Pointage() {

	}

	public Pointage(double j1, double j2, double j3, double j4, double j5, double j6, double j7, String semaine,
			String mois, int year, Affaire affaires, Collaborateur collaborateurs) {
		super();
		this.j1 = j1;
		this.j2 = j2;
		this.j3 = j3;
		this.j4 = j4;
		this.j5 = j5;
		this.j6 = j6;
		this.j7 = j7;
		this.semaine = semaine;
		this.mois = mois;
		this.year = year;
		this.affaires = affaires;
		Collaborateurs = collaborateurs;
	}

	public Long getId_pointage() {
		return id_pointage;
	}

	public void setId_pointage(Long id_pointage) {
		this.id_pointage = id_pointage;
	}

	public double getJ1() {
		return j1;
	}

	public void setJ1(double j1) {
		this.j1 = j1;
	}

	public double getJ2() {
		return j2;
	}

	public void setJ2(double j2) {
		this.j2 = j2;
	}

	public double getJ3() {
		return j3;
	}

	public void setJ3(double j3) {
		this.j3 = j3;
	}

	public double getJ4() {
		return j4;
	}

	public void setJ4(double j4) {
		this.j4 = j4;
	}

	public double getJ5() {
		return j5;
	}

	public void setJ5(double j5) {
		this.j5 = j5;
	}

	public double getJ6() {
		return j6;
	}

	public void setJ6(double j6) {
		this.j6 = j6;
	}

	public double getJ7() {
		return j7;
	}

	public void setJ7(double j7) {
		this.j7 = j7;
	}

	public String getSemaine() {
		return semaine;
	}

	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Affaire getAffaires() {
		return affaires;
	}

	public void setAffaires(Affaire affaires) {
		this.affaires = affaires;
	}

	public Collaborateur getCollaborateurs() {
		return Collaborateurs;
	}

	public void setCollaborateurs(Collaborateur collaborateurs) {
		Collaborateurs = collaborateurs;
	}

	@Override
	public String toString() {
		return "Pointage [id_pointage=" + id_pointage + ", j1=" + j1 + ", j2=" + j2 + ", j3=" + j3 + ", j4=" + j4
				+ ", j5=" + j5 + ", j6=" + j6 + ", j7=" + j7 + ", semaine=" + semaine + ", mois=" + mois + ", year="
				+ year + ", affaires=" + affaires.getNom_Affaire() + ", Collaborateur=" + Collaborateurs.getMatricule() + "]";
	}

	
	
	
	
}
