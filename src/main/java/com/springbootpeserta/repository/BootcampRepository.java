package com.springbootpeserta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootpeserta.model.Bootcamp;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {
	@Query(value="SELECT * from bootcamp INNER JOIN batch on batch.idbatch = bootcamp.idbatch INNER JOIN peserta on peserta.id = bootcamp.idpeserta",
			nativeQuery = true)
	List<Bootcamp> findAllBootcampNative();
	
	@Query(value="SELECT * from bootcamp INNER JOIN batch on batch.idbatch = bootcamp.idbatch INNER JOIN peserta on peserta.id = bootcamp.idpeserta WHERE peserta.idjk = 3 OR peserta.idjk = 2",
			nativeQuery = true)
	List<Bootcamp> findPerempuanDanBanci();
}
