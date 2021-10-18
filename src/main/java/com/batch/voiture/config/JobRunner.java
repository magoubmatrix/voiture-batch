package com.batch.voiture.config;

import java.util.Date;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class JobRunner {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Scheduled(cron = "*/20 * * * * *")
	public  void runBatch(){
		
		JobParametersBuilder param  = new JobParametersBuilder();
		//param.addString("inputFile", "cars.csv");
		param.addString("key", UUID.randomUUID().toString());
		param.addDate("date", new Date(),true);
		try {
			JobExecution execution = jobLauncher.run(job, param.toJobParameters());
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
