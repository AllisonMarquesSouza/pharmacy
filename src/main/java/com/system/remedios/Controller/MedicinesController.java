package com.system.remedios.Controller;

import com.system.remedios.domain.Medicine;
import com.system.remedios.dtos.MedicineDtoPost;
import com.system.remedios.dtos.MedicineDtoPut;
import com.system.remedios.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/medicine")
@Log4j2
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request, param invalid", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized, the user didn't authenticate", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden, you don't have permission", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class MedicinesController {
    private final MedicineService medicineService;

    @Operation(summary =  "List All", method = "GET", description ="Method to list all medicines", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @GetMapping("/listAll")
    public ResponseEntity<List<Medicine>> listAll(){
        return ResponseEntity.ok(medicineService.listAll());
    }

    @Operation(summary =  "Find by id", method = "GET", description ="Method to find out medicine by ID", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Medicine> findById(@PathVariable long id){
        return ResponseEntity.ok(medicineService.findByIdOrThrowBadRequestException(id));
    }

    @Operation(summary =  "Save", method = "POST", description ="Method to save a medicine", responses = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PostMapping("/save")
    public ResponseEntity<Medicine> save(@RequestBody @Valid MedicineDtoPost medicinePost, UriComponentsBuilder uriBuilder){
        Medicine save = medicineService.save(medicinePost);
        URI uri = uriBuilder.path("/remedios/save/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @Operation(summary =  "Replace", method = "PUT", description ="Method to update a medicine", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PutMapping("/replace")
    public ResponseEntity<Void> replace(@RequestBody @Valid MedicineDtoPut medicineDtoPut){
        medicineService.replace(medicineDtoPut);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary =  "Active", method = "PATCH", description ="Method to active the medicine", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PatchMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable long id){
        medicineService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary =  "Inactive", method = "PATCH", description ="Method to inactive the medicine", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PatchMapping("/inactive/{id}")
    public ResponseEntity<Void> inactive(@PathVariable long id){
        medicineService.inactive(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary =  "Delete", method = "POST", description ="Method to delete medicine by ID", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFull(@PathVariable Long id){
        medicineService.deleteByIdFull(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
