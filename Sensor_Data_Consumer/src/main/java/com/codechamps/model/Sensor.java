package com.codechamps.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Sensor")
@Table(name = "sensor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sensor {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "creation_time_stamp")
	private LocalDateTime creationTimeStamp;
	
	@Column(name = "content")
	private Integer content;
}
