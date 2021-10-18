package com.batch.voiture.config;

import java.io.File;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.voiture.entity.PhotoVoiture;

@Component
public class PhotoVoitureItemProcessor implements ItemProcessor<PhotoVoiture, File> {

	@Override
	public File process(PhotoVoiture pv) throws Exception {
		File file = pv.getFile();
		return file;
	}

}
