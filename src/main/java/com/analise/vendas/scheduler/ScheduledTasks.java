package com.analise.vendas.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.analise.vendas.service.FileService;

@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = 5000)
	public void process() {
		FileService.run();
	}
}
