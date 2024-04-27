package com.system.remedios.Controller;

import com.system.remedios.Mapper.MedicineMapper;
import com.system.remedios.Repository.MedicineRepository;
import com.system.remedios.domain.MedicineData;
import com.system.remedios.requests.MedicinePostRequestBody;
import com.system.remedios.requests.MedicinePutRequestBody;
import com.system.remedios.service.MedicineService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remedios")
@Log4j2
@RequiredArgsConstructor
//localhost:8080/remedios
public class MedicinesController {
    private final MedicineService medicineService;
    private final MedicineRepository medicineRepository;

    @GetMapping("/listAll")
    public ResponseEntity<List<MedicineData>> listAll(){
        return ResponseEntity.ok(medicineService.listAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<MedicineData> findById(@PathVariable long id){
        return ResponseEntity.ok(medicineService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping("/save")
    @Transactional//This is the form of identification change in database
    public ResponseEntity<MedicineData> save(@RequestBody @Valid MedicinePostRequestBody medicinePost, UriComponentsBuilder uriBuilder){
        MedicineData mapperMedicine = MedicineMapper.INSTANCE.toMedicine(medicinePost);

        MedicineData medicineData = new MedicineData(mapperMedicine.getId(),
                mapperMedicine.getName(), mapperMedicine.getVia(),
                mapperMedicine.getLot(), mapperMedicine.getQuantity(),
                mapperMedicine.getValidity(), mapperMedicine.getLaboratory(),
                mapperMedicine.getAtivo());
        //create constructor and to send all data

        medicineRepository.save(medicineData);
        //save using the Repository directly, because the service request a MedicinePostRequestBody

        URI uri = uriBuilder.path("/remedios/save/{id}").buildAndExpand(medicineData.getId()).toUri();
        //here create the uri where was created the Medicine, Headers in Postman (Location)

        return ResponseEntity.created(uri).body(medicineData);
    }

    @PutMapping("/replace")
    @Transactional
    public ResponseEntity<MedicineData> replace(@RequestBody @Valid MedicinePutRequestBody medicinePutRequestBody){
        return new ResponseEntity<>(medicineService.replace(medicinePutRequestBody), HttpStatus.OK);
    }

    @PutMapping("/active/{id}")
    @Transactional
    public ResponseEntity<Void> active(@PathVariable long id){
        medicineService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/inactive/{id}")
    @Transactional
    public ResponseEntity<Void> inactive(@PathVariable long id){
        medicineService.inactive(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteFull(@PathVariable long id){
        medicineService.deleteByIdFull(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
