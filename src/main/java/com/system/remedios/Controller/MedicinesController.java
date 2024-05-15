package com.system.remedios.Controller;

import com.system.remedios.domain.MedicineData;
import com.system.remedios.requests.MedicinePostRequestBody;
import com.system.remedios.requests.MedicinePutRequestBody;
import com.system.remedios.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "open-api")
//localhost:8080/remedios
public class MedicinesController {
    private final MedicineService medicineService;

    @Operation(summary =  "List all medicines", method = "POST",
            description ="List all medicines",
            tags = {"Medicines"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedicineData.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),})
    @GetMapping("/listAll")
    public ResponseEntity<List<MedicineData>> listAll(){
        return ResponseEntity.ok(medicineService.listAll());
    }

    @Operation(summary = "Search the medicine for id",
            description ="Search for details of medicine for id",
            tags = {"Remedy"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedicineData.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),})
    @GetMapping("/findById/{id}")
    public ResponseEntity<MedicineData> findById(@PathVariable long id){
        return ResponseEntity.ok(medicineService.findByIdOrThrowBadRequestException(id));
    }



    @Operation(summary = "Register a medicine",
            description ="Register a medicine",
            tags = {"Medicine"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PostMapping("/save")
    @Transactional
    public ResponseEntity<MedicineData> save(@RequestBody @Valid MedicinePostRequestBody medicinePost, UriComponentsBuilder uriBuilder){

        MedicineData save = medicineService.save(medicinePost);

        URI uri = uriBuilder.path("/remedios/save/{id}").buildAndExpand(save.getId()).toUri();


        return ResponseEntity.created(uri).body(save);
    }

    @Operation(summary = "Replace the data of medicine",
            description ="Update the register of medicines",
            tags = {"Medicine"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PutMapping("/replace")
    @Transactional
    public ResponseEntity<Void> replace(@RequestBody @Valid MedicinePutRequestBody medicinePutRequestBody){
        medicineService.replace(medicinePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Activate the register of the medicine",
            description ="Activate the register of the medicine",
            tags = {"Medicine"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PutMapping("/active/{id}")
    @Transactional
    public ResponseEntity<Void> active(@PathVariable long id){
        medicineService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Disable the register of the medicine",
            description ="Disable the register of the medicine",
            tags = {"Medicine"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @DeleteMapping("/inactive/{id}")
    @Transactional
    public ResponseEntity<Void> inactive(@PathVariable long id){
        medicineService.inactive(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Remove the medicine ",
            description ="Remove the medicine",
            tags = {"Medicine"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteFull(@PathVariable long id){
        medicineService.deleteByIdFull(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
