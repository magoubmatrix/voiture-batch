package com.batch.voiture.config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class PhotoVoitureTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
	        File file = new File("voiture1.jpeg");
		    Path path = Paths.get("voiture1.jpeg");
		    Files.delete(path);
		    if(file.delete()){
		    	System.out.println("le fichier " + file.getName() + " est effacé avec succés");
		    }
		    else{
		    	System.out.println("le fichier " + file.getName() + " n'est pas pu etre supprimé ");
		    }
			return null;
		
	
	}

}
