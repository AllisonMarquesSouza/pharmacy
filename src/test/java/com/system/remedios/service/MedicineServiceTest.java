package com.system.remedios.service;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import com.system.remedios.Repository.MedicineRepository;
import com.system.remedios.domain.Medicine;
import com.system.remedios.dtos.MedicineDtoPost;
import com.system.remedios.dtos.MedicineDtoPut;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Log4j2
class MedicineServiceTest {
    @InjectMocks
    private MedicineService medicineService;
    @Mock
    private MedicineRepository medicineRepositoryMock;

    private Medicine existingMedicine;

    @BeforeEach
    void setUp() {
        existingMedicine = new Medicine(1L, "Buscopam", Via.ORAL, 100,
                LocalDate.now().plusDays(10), Laboratory.MEDLEY, true);

    }

    @Test
    @DisplayName("ListAll returns MedicineDate when is successful")
    void list_ReturnsListOfMedicineDataActive_WhenSuccessful() {
        Mockito.when(medicineRepositoryMock.findAllByAtivoTrue())
                .thenReturn(List.of(existingMedicine));

        List<Medicine> medicineResponse = medicineService.listAll();

        assertNotNull(medicineResponse);
        assertEquals(medicineResponse.size(), 1);
        assertEquals(medicineResponse.get(0), existingMedicine);
    }
    @Test
    @DisplayName("findByIdOrThrowBadRequestException return MedicineDate when is successful")
    void findById_ReturnMedicineDate_WhenSuccessful() {
        Mockito.when(medicineRepositoryMock.findById(2L))
                .thenReturn(Optional.ofNullable(existingMedicine));

        Medicine medicineByID = medicineService.findByIdOrThrowBadRequestException(2L);

        assertNotNull(medicineByID);
        assertEquals(medicineByID, existingMedicine);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when find Medicine by id")
    void findByIdCaseErrorMedicineNotFound() {
        Mockito.when(medicineRepositoryMock.findById(2L))
                .thenThrow(new EntityNotFoundException("Medicine Not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> medicineService.findByIdOrThrowBadRequestException(2L));

        assertSame(exception.getClass(), EntityNotFoundException.class);
        assertEquals(exception.getMessage(), "Medicine Not found");
    }

    @Test
    @DisplayName("Save MedicineDate return when is successful")
    void SaveMedicineDate_ReturnWhenSuccessful() {
        Mockito.when(medicineRepositoryMock.save(ArgumentMatchers.any(Medicine.class)))
                .thenReturn(existingMedicine);

        Medicine medicineSaved = medicineService.save(MedicineDtoPost.builder().build());

        assertNotNull(medicineSaved);
        assertEquals(medicineSaved, existingMedicine);
    }
    @Test
    @DisplayName("Should update Medicine when is successful")
    void updateMedicineWhenSuccessful() {
        Medicine updatedMedicine = new Medicine(1L, "new-medicine", Via.ORAL, 10,
                LocalDate.of(2025, 1, 1), Laboratory.MEDLEY, true);

        Mockito.when(medicineRepositoryMock.findById(1L))
                .thenReturn(Optional.of(existingMedicine));

        Mockito.when(medicineRepositoryMock.save(Mockito.any(Medicine.class))).thenReturn(updatedMedicine);

        MedicineDtoPut medicineDtoPut = new MedicineDtoPut(1L, "new-medicine", Via.ORAL, 10,
                LocalDate.of(2025, 1, 1), Laboratory.MEDLEY, true);

        assertDoesNotThrow(() -> medicineService.replace(medicineDtoPut));

        Mockito.verify(medicineRepositoryMock).save(Mockito.argThat(m ->
                m.getId().equals(1L) &&
                        m.getName().equals("new-medicine") &&
                        m.getQuantity() == 10 &&
                        m.getValidity().equals(LocalDate.of(2025, 1, 1)) &&
                        m.getLaboratory().equals(Laboratory.MEDLEY) &&
                        m.getAtivo()
        ));
    }

    @Test
    @DisplayName("DeleteById MedicineDate when is successful")
    void deleteById_WhenSuccessful() {
        Mockito.when(medicineRepositoryMock.findById(2L))
                .thenReturn(Optional.ofNullable(existingMedicine));

        assertDoesNotThrow(() -> medicineService.deleteByIdFull(2L));
        verify(medicineRepositoryMock).deleteById(2L);
    }

}