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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskletStep implements Tasklet {

	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		Path file = Paths.get("C:/Users/mahjoub/Desktop/voitures.csv");
	    Files.delete(file);
	    File f = file.toFile();
	    if(f.delete())
	    	log.info("le fichier est supprimé avec succes");
	    else{
	    	log.info("un probleme au cours de la supprission");
	    }
		return null;
	}

}
