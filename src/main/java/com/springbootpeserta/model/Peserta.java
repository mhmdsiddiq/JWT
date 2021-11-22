package com.springbootpeserta.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "peserta")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peserta {
	@Id
	@GeneratedValue(strategy =	GenerationType.IDENTITY)
	private long id;
	private String nama;
	private String notelp;
	private String alamat;
	private String username;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true, name = "idjk", referencedColumnName = "idjk")
	private JenisKelamin idjk;
}
