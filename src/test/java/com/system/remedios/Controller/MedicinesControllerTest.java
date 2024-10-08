package com.system.remedios.Controller;

import com.system.remedios.domain.Medicine;
import com.system.remedios.dtos.MedicineDtoPost;
import com.system.remedios.dtos.MedicinePostRequestCreator;
import com.system.remedios.dtos.MedicineDtoPut;
import com.system.remedios.dtos.MedicinePutRequestCreator;
import com.system.remedios.service.MedicineService;
import com.system.remedios.util.MedicineCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@Log4j2
class MedicinesControllerTest {
    @InjectMocks
    private MedicinesController medicinesController;
    @Mock
    private MedicineService medicineServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(medicineServiceMock.listAll())
                .thenReturn(List.of(MedicineCreator.createValidMedicineData()));

        BDDMockito.when(medicineServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(MedicineCreator.createMedicineDataToBeSaved());

        BDDMockito.when(medicineServiceMock.save(ArgumentMatchers.any(MedicineDtoPost.class)))
                .thenReturn(MedicineCreator.createMedicineDataToBeSaved());

        BDDMockito.doNothing().when(medicineServiceMock).replace(ArgumentMatchers.any(MedicineDtoPut.class));

        BDDMockito.doNothing().when(medicineServiceMock).active(ArgumentMatchers.anyLong());

        BDDMockito.doNothing().when(medicineServiceMock).inactive(ArgumentMatchers.anyLong());


        BDDMockito.doNothing().when(medicineServiceMock).deleteByIdFull(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("List returns MedicineDate true active when is successful")
    void list_ReturnsListOfMedicineDataActive_WhenSuccessful() {
        String expectedName = MedicineCreator.createValidMedicineData().getName();

        List<Medicine> medicineResponse = medicinesController.listAll().getBody();

        Assertions.assertThat(medicineResponse).isNotNull();

        Assertions.assertThat(medicineResponse).isNotEmpty().hasSize(1);

        Assertions.assertThat(medicineResponse.get(0).getName()).isEqualTo(expectedName);

    }
    @Test
    @DisplayName("FindById return MedicineDate when is successful")
    void findById_ReturnMedicineDate_WhenSuccessful() {
        Long expectedId = MedicineCreator.createMedicineDataToBeSaved().getId();

        Medicine medicineResponse = medicinesController.findById(1L).getBody();

        Assertions.assertThat(medicineResponse).isNotNull();

        Assertions.assertThat(medicineResponse.getId()).isNotNull();

        Assertions.assertThat(medicineResponse.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Save MedicineDate return when is successful")
    void SaveMedicineDate_ReturnWhenSuccessful() {
        Medicine expectedMedicine = MedicineCreator.createMedicineDataToBeSaved();
        Medicine save = medicineServiceMock.save(MedicinePostRequestCreator.medicinePostRequestBody());

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save).isEqualTo(expectedMedicine);

    }

    @Test
    @DisplayName("Replace MedicineDate when is successful")
    void replaceMedicineDate_ReturnWhenSuccessful() {

        ResponseEntity<Void> entity = medicinesController.replace(MedicinePutRequestCreator.medicinePutRequestBody());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat((entity.getStatusCode())).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(() -> medicinesController.replace(MedicinePutRequestCreator.medicinePutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Active MedicineDate when is successful")
    void activeMedicineDate_ReturnWhenSuccessful() {
        ResponseEntity<Void> active = medicinesController.active(1);

        Assertions.assertThat(active).isNotNull();
        Assertions.assertThat((active.getStatusCode())).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(() -> medicinesController.active(1))
                .doesNotThrowAnyException();

    }
    @Test
    @DisplayName("Inactive MedicineDate when is successful")
    void inactiveMedicineDate_ReturnWhenSuccessful() {
        ResponseEntity<Void> inactive = medicinesController.inactive(1);

        Assertions.assertThat(inactive).isNotNull();
        Assertions.assertThat((inactive.getStatusCode())).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(() -> medicinesController.inactive(1))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("DeleteById MedicineDate when is successful")
    void deleteById_WhenSuccessful() {
        ResponseEntity<Void> delete = medicinesController.deleteFull(1L);

        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat((delete.getStatusCode())).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(() -> medicinesController.deleteFull(1L))
                .doesNotThrowAnyException();

    }

}