package com.springbootpeserta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batch")
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idbatch;
	private String batchname;
}
