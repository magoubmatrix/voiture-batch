package com.batch.voiture.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoitureWriter {

	private String marque;
	private String modele;
	private String[] strDate = new String [3];
	private double prix;
}
