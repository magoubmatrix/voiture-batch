package com.batch.voiture.config;





import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import com.batch.voiture.entity.PhotoVoiture;
import com.batch.voiture.entity.VoitureReader;
import com.batch.voiture.entity.VoitureWriter;

import lombok.Data;
import lombok.val;


@Configuration
@EnableBatchProcessing
@Data
public class BatchConfig {

	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
    private final ItemProcessor<VoitureReader, VoitureWriter> voitureProcessor;
    private final ItemReader<VoitureReader> voitureRead;
    private final ItemVoitureWriter voitureWriter;
    private final TaskletStep taskletStep;
     
    // job photoVoiture
    
    
   // private final PhotoVoitureReader photoReader;
    private final PhotoVoitureItemProcessor photoProcessor;
    private final ItemPhotoVoitureWriter photoWriter;
    private final PhotoVoitureTasklet photoTasklet;
   
     @Bean
    // @Primary
	public Job voitureJob() throws Exception {
		return  jobBuilderFactory.get("batchVoiture")
				.incrementer(new RunIdIncrementer())
				.start(stepVoiture())
				.next(stepTasklet())
				.build();
	}
     
    
	
	@Bean
	public Step stepVoiture()  {
		return   stepBuilderFactory.get("step1")
				//.allowStartIfComplete(false)
				.<VoitureReader, VoitureWriter>chunk(2)
				.reader(voitureRead)
				.processor(voitureProcessor)
				.writer(voitureWriter)
				.build();
		}
	
     
	  @Bean
	  public Step stepTasklet() {
		  return  stepBuilderFactory.get("step2")
			.tasklet(taskletStep)
			.build();
			
		}


	  
	    // @Bean
		 public Resource getResource(){
			 return  new PathResource("C:/Users/mahjoub/Desktop/voiture3.jpg");
		 }
	  
	     
		 
		 
	     @Value(value = "voiture1.jpeg")
	     public Resource resource;

	@Bean
	@StepScope
	public FlatFileItemReader<VoitureReader> employeReader()  {
		//resource = new PathResource("C:/Users/mahjoub/Desktop/cars.csv");
		FlatFileItemReader<VoitureReader> reader = new FlatFileItemReader<VoitureReader>();
		reader.setResource(getResource());
		reader.setLineMapper(new DefaultLineMapper<VoitureReader>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setDelimiter(";");
				setNames("marque","modele","immatriculation","prix");
				
			}});
			setFieldSetMapper(new VoiutureRowMapperField());
			afterPropertiesSet();
		}});
		return reader;
	}

	
	
	
	 @Bean
	 @Primary
	 	public Job photoVoitureJob() throws Exception {
	 		return  jobBuilderFactory.get("batchPhotoVoiture")
	 				.incrementer(new RunIdIncrementer())
	 				//.start(stepPhotoVoiture())
	 				.start(phototVoitureTasklet())
	 				.build();
	 	}
	 
	 
	// @Value(value = "*.jpg")
	// public Resource resources;
	 
	
	 
	 
	 @Bean
	 public Step stepPhotoVoiture() {
			
			return   stepBuilderFactory.get("stepPhoto")
					//.allowStartIfComplete(false)
					.<PhotoVoiture, File>chunk(1)
					.reader(readerPhoto())
					.processor(photoProcessor)
					.writer(photoWriter)
					.build();
			}
	 
	 
	  @Bean
	  public Step phototVoitureTasklet() {
		  return  stepBuilderFactory.get("stepPhotoTasklet")
			.tasklet(photoTasklet)
			.build();
			
		}
	 
	
	 
	 @Bean
	 public ItemReader<PhotoVoiture> readerPhoto(){
		 
		 ItemReader<PhotoVoiture> item = new ItemReader<PhotoVoiture>() {
         
			@Override
			public PhotoVoiture read()
					throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			
				PhotoVoiture v = new PhotoVoiture();
				File file = resource.getFile();
				v.setName(file.getName());
				v.setFile(file);
		 		return v;
				}
				
				
		};
		 return item;
		 
		
	 }
	 
	 
	// @Bean
	 public Tasklet photoTasklet(){
		 Tasklet tasklet = new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
			    File file = getResource().getFile();
			    Path path = Paths.get(file.getName());
			    Files.delete(path);
			    if(file.delete()){
			    	System.out.println("le fichier " + file.getName() + " est effacé avec succés");
			    }
			    else{
			    	System.out.println("le fichier " + file.getName() + " n'est pas pu etre supprimé ");
			    }
				return null;
			}
		};
		 return tasklet;
	 }
	 
	
	 
	 
	
}
