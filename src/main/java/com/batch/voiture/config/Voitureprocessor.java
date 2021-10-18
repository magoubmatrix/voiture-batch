package com.batch.voiture.config;

import java.util.GregorianCalendar;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.voiture.entity.VoitureReader;
import com.batch.voiture.entity.VoitureWriter;

@Component
public class Voitureprocessor implements ItemProcessor<VoitureReader,VoitureWriter> {

	@Override
	public VoitureWriter process(VoitureReader voiture) throws Exception {
		VoitureWriter writer = new VoitureWriter();
		writer.setMarque(voiture.getMarque());
		writer.setModele(voiture.getModele());
		writer.setStrDate(mapStringToCalendar(voiture.getImmatriculation()));
		writer.setPrix(voiture.getPrix());
		return writer;
	}
	
	public String[] mapStringToCalendar(String str){
		if(str.length() != 10)
			throw new RuntimeException("la date declarer est incorrecte");
		String time = str.replace('/', '-');
		String day =  time.substring(0,2);
		String month =  time.substring(3,5);
		String year =  time.substring(6,10);
		String [] date = {year,month,day};
		return date;
		
	}

}
