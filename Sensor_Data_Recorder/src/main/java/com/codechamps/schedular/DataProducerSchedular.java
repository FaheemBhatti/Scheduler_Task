package com.codechamps.schedular;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codechamps.model.Sensor;
import com.codechamps.schedular.repo.SensorRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Component
@Slf4j
public class DataProducerSchedular {
	
	private final SensorRepository sensorRepo;
	
	@Scheduled(cron = "${cron.interval}")
	public void dataProducer() {
		log.info("Scheduled job triggered");
		int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
		
		Sensor sensor = new Sensor();
		sensor.setCreationTimeStamp(LocalDateTime.now());
		sensor.setContent(randomNum);
		sensorRepo.save(sensor);
	}
}
