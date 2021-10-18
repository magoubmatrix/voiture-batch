package com.batch.voiture.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.batch.voiture.entity.VoitureReader;


public class VoiutureRowMapperField implements FieldSetMapper<VoitureReader> {

	@Override
	public VoitureReader  mapFieldSet(FieldSet fieldSet) throws BindException {
		VoitureReader voiture = new VoitureReader();
		voiture.setMarque(fieldSet.readString("marque"));
		voiture.setModele(fieldSet.readString("modele"));
		voiture.setImmatriculation(fieldSet.readString("immatriculation"));
		voiture.setPrix(fieldSet.readDouble("prix"));
	
		return voiture;
	}
}