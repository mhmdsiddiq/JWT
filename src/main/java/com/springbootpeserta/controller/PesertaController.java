package com.springbootpeserta.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpeserta.config.JwtTokenUtil;
import com.springbootpeserta.model.Nama;
import com.springbootpeserta.model.Peserta;
import com.springbootpeserta.repository.PesertaRepository;
import com.springbootpeserta.service.JWTUserDetailsService;

@RestController
@RequestMapping("/peserta")
public class PesertaController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private JWTUserDetailsService userDetailsService;
	
	@Autowired
	PesertaRepository pesertaRepo;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Peserta authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtUtil.generateToken(userDetails);
		System.out.println(authenticationRequest.getPassword());
		return ResponseEntity.ok(token);
	}
	
	private void authenticate(String username, String password) throws Exception	{
		try	{
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e)	{
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e)	{
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	
	@PostMapping("/")
	public String postData(@RequestBody Peserta peserta) {
		pesertaRepo.save(peserta);
		return "Berhasil ditambahkan";
	}
	
	@GetMapping("/")
	public List<Peserta> getAnu() {
		return pesertaRepo.findAll();
	}
	
	@GetMapping("/nama/{nama}")
	public List<Peserta> getAllDataByNama(@PathVariable(value="nama") String nama) {
		return pesertaRepo.findByNama(nama);
	}
	
	

	

	
	@PostMapping("/deletePesertaById/{id}")
	public String deletePeserta(@PathVariable long id) {
		pesertaRepo.deleteById(id);
		return "The data where id = " +id +" was deleted";
	}
	
	@GetMapping("/update")
	public String updateId(@RequestBody Peserta peserta) {
		pesertaRepo.save(peserta);
		return "Berhasil diubah";
	}
	

	
	@GetMapping("/tidakPunyaTilpun")
	public List<Peserta>  tidakPunyaTilpun() {
		return pesertaRepo.findTidakPunyaTilpun();
	}
	
	@GetMapping("/distinctNama")
	public List<Nama>  cariNama() {
		return pesertaRepo.findDistinctNama();
	}
	
	@GetMapping("/distinctAlamat")
	public List<Nama>  cariAlamat() {
		return pesertaRepo.findDistinctAlamat();
	}


}
