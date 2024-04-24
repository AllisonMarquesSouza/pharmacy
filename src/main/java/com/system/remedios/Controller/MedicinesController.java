package com.system.remedios.Controller;

import com.system.remedios.DataConnection.MedicineData;
import com.system.remedios.Medicines.RegisterMedicine;
import com.system.remedios.Repository.MedicineRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remedios")
//localhost:8080/remedios
public class MedicinesController {
    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/listAll")
    public ResponseEntity<List<MedicineData>> listAll(){
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    @PostMapping("/save")
    //@Valid -> valid the dto , here is the Validation in DataRegisterMedicines
    public ResponseEntity<MedicineData> save(@RequestBody @Valid RegisterMedicine registerMedicine){
        MedicineData medicine = new MedicineData(registerMedicine);
        return new ResponseEntity<>(medicineRepository.save(medicine), HttpStatus.CREATED);
    }
}
