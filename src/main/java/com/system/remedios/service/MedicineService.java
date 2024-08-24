package com.system.remedios.service;

import com.system.remedios.Repository.MedicineRepository;
import com.system.remedios.domain.Medicine;
import com.system.remedios.dtos.MedicineDtoPost;
import com.system.remedios.dtos.MedicineDtoPut;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;


    public List<Medicine> listAll(){
        return medicineRepository.findAllByAtivoTrue();
    }

    public Medicine findByIdOrThrowBadRequestException(Long id){
        return medicineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
    }

    public Medicine save(MedicineDtoPost medicineDtoPost){
        Medicine medicine = new Medicine(medicineDtoPost);
        medicine.setAtivo(true);
        return medicineRepository.save(medicine);
    }

    public void replace(MedicineDtoPut medicineDtoPut){
        Medicine medicineSaved = findByIdOrThrowBadRequestException(medicineDtoPut.getId());
        Medicine medicine = new Medicine(medicineDtoPut);
        medicine.setId(medicineSaved.getId());
        medicineRepository.save(medicine);
    }

    public void deleteByIdFull(Long id){
        this.findByIdOrThrowBadRequestException(id);
        medicineRepository.deleteById(id);
    }

    public void inactive(Long id){
        Medicine medicine = findByIdOrThrowBadRequestException(id);
        medicine.setAtivo(false);
        medicineRepository.save(medicine);

    }

    public void active(Long id){
        Medicine medicine = findByIdOrThrowBadRequestException(id);
        medicine.setAtivo(true);
        medicineRepository.save(medicine);
    }


}
