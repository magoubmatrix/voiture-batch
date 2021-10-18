package com.batch.voiture.entity;

import javax.persistence.Convert;

import lombok.Data;

@Data

public class VoitureReader {

	private String marque;
	private String modele;
	private String immatriculation;
	private double prix;
}
