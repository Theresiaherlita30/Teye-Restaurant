package com.example.Restoran.controller;

import com.example.Restoran.model.Karyawan;
import com.example.Restoran.repositories.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/karyawan")
public class KaryawanController {

    private final KaryawanRepository karyawanRepository;

    @Autowired
    public KaryawanController(KaryawanRepository karyawanRepository) {
        this.karyawanRepository = karyawanRepository;
    }

    @GetMapping("/")
    public List<Karyawan> getAllKaryawan() {
        return karyawanRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Karyawan> getKaryawanById(@PathVariable Long id) {
        Optional<Karyawan> karyawan = karyawanRepository.findById(id);
        return karyawan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Karyawan createKaryawan(@RequestBody Karyawan karyawan) {
        return karyawanRepository.save(karyawan);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Karyawan> updateKaryawan(@PathVariable Long id, @RequestBody Karyawan newKaryawan) {
        Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(id);
        if (optionalKaryawan.isPresent()) {
            Karyawan existingKaryawan = optionalKaryawan.get();
            existingKaryawan.setNama(newKaryawan.getNama());
            existingKaryawan.setDivisi(newKaryawan.getDivisi());
            // set properti lainnya sesuai kebutuhan

            Karyawan updatedKaryawan = karyawanRepository.save(existingKaryawan);
            return new ResponseEntity<>(updatedKaryawan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	/*
	 * @DeleteMapping("/destroy/{id}") public ResponseEntity<Void>
	 * deleteKaryawan(@PathVariable Long id) { Optional<Karyawan> karyawan =
	 * karyawanRepository.findById(id); if (karyawan.isPresent()) {
	 * karyawanRepository.deleteById(karyawan.get()); return
	 * ResponseEntity.noContent().build(); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */
    
    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<HttpStatus> deleteKaryawan(@PathVariable Long id) {
        try {
            Optional<Karyawan> karyawan = karyawanRepository.findById(id);
            if (karyawan.isPresent()) {
                karyawanRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
