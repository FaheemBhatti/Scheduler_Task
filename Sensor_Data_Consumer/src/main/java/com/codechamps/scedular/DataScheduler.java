package com.codechamps.scedular;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codechamps.model.Sensor;
import com.codechamps.respository.SensorRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DataScheduler  {
	
	private final SensorRepository sensorRepository;

	@Scheduled(cron = "${cron.interval}")
	public void dataGenerator() throws InterruptedException {
	
		
		Iterable<Sensor> allSensor = sensorRepository.findAll();
		double[][] initdata = getSineData(allSensor);
		final XYChart chart = QuickChart.getChart("Simple XChart Real-time Demo", "Radians", "Sine", "sine", initdata[0], initdata[1]);

		// Show it
		final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
		sw.displayChart();

		while (true) {

			

			Thread.sleep(100);

			Iterable<Sensor> allSensorRefreshed = sensorRepository.findAll();
			final double[][] data = getSineData(allSensorRefreshed);

			javax.swing.SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					chart.updateXYSeries("sine", data[0], data[1], null);
					sw.repaintChart();
				}
			});
		}

	}

	private static double[][] getSineData(Iterable<Sensor> sensorData) {
		LocalDateTime[] timeStamp = new LocalDateTime[100];
		Integer[] content = new Integer[100];
		
		Iterator<Sensor> eachSensor = sensorData.iterator(); 
		int i =  0;
		while(eachSensor.hasNext()) {
		
			timeStamp[i] = eachSensor.next().getCreationTimeStamp();
			content[i] = eachSensor.next().getContent();
			i++;
		}
		i = 0;

		double[] xData = new double[100];
		double[] yData = new double[100];
		for (int y = 0; y < timeStamp.length; y++) {
			String currentDateTime = timeStamp[y].toLocalTime().toString();
			currentDateTime = currentDateTime.replaceAll(":","");
			Double currentDateTimeInDouble = Double.parseDouble(currentDateTime);
			
			Integer currentContent = content[y];
			Double currentContentInDouble = Double.valueOf(currentContent);
			xData[y] = currentDateTimeInDouble;
			yData[i] = Math.sin(currentContentInDouble);
		}
		return new double[][] { xData, yData };
	}
}
