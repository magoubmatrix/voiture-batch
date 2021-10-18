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
import org.springframework.scheduling.annotation.Scheduled;

public class JobPhotoRunner {
	@Autowired
	JobLauncher jobLauncher1;
	
	@Autowired
	Job job1;
	
	@Scheduled(cron = "*/10 * * * * *")
	public  void runBatch(){
		
		JobParametersBuilder param  = new JobParametersBuilder();
		//param.addString("inputFile", "cars.csv");
		param.addString("key", UUID.randomUUID().toString());
		param.addDate("date", new Date(),true);
		try {
			JobExecution execution = jobLauncher1.run(job1, param.toJobParameters());
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
