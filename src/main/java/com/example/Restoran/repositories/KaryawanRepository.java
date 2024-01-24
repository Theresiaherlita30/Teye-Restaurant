package com.example.Restoran.repositories;

import com.example.Restoran.model.Karyawan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
	Optional<Karyawan> findById(Long id);
//	Karyawan save(Karyawan karyawan);
//	boolean existsById(Long id);
//	void deleteById(Karyawan karyawan);

	void deleteById(Long id);
}