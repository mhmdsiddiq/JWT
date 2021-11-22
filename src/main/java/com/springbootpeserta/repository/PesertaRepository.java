package com.springbootpeserta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootpeserta.model.Bootcamp;
import com.springbootpeserta.model.Nama;
import com.springbootpeserta.model.Peserta;

public interface PesertaRepository extends JpaRepository <Peserta, Long>{
	List<Peserta> findByNama(String nama);

	@Query(value="SELECT * from peserta WHERE notelp is NULL",
			nativeQuery = true)
	List<Peserta> findTidakPunyaTilpun();
	
	Peserta findByUsername(String username);
	
	@Query(value="SELECT distinct nama from peserta",
			nativeQuery = true)
	List<Nama> findDistinctNama();
	
	@Query(value="SELECT distinct alamat from peserta",
			nativeQuery = true)
	List<Nama> findDistinctAlamat();
}