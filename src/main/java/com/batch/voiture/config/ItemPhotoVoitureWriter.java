package com.batch.voiture.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ItemPhotoVoitureWriter implements ItemWriter<File> {
	  int i = 0;
	@Override
	public void write(List<? extends File> files) throws Exception {
		
		files.forEach( f -> {try {
			FileInputStream fis = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(f.getName());
			try {
				while((i = fis.read()) != -1){
					fos.write(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		
			
	}

}
