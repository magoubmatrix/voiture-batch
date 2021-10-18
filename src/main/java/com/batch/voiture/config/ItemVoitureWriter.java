package com.batch.voiture.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.batch.voiture.entity.VoitureReader;
import com.batch.voiture.entity.VoitureWriter;

@Component
public class ItemVoitureWriter implements ItemWriter<VoitureWriter> {

	@Autowired
	KafkaTemplate<Long, VoitureWriter> template;
	
	@Override
	public void write(List<? extends VoitureWriter> voitures) throws Exception {
	  voitures.forEach(v -> {template.send("voiture", v); System.out.println(v); });
	}

}
