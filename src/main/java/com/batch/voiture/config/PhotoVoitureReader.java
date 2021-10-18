package com.batch.voiture.config;

import java.io.File;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.batch.voiture.entity.PhotoVoiture;

import lombok.Data;

//@Component

public class PhotoVoitureReader implements ItemReader<PhotoVoiture> {

	@Autowired
	private Resource resource;
	
	@Override
	public PhotoVoiture read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		PhotoVoiture v = new PhotoVoiture();
		File file = resource.getFile();
		v.setName(file.getName());
		v.setFile(file);
          
 		return v;
	}

}
