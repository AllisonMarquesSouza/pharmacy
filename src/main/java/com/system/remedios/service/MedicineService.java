package com.system.remedios.service;

import com.system.remedios.Mapper.MedicineMapper;
import com.system.remedios.Repository.MedicineRepository;
import com.system.remedios.domain.MedicineData;
import com.system.remedios.requests.MedicinePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;


    public List<MedicineData> listAll(){
        return medicineRepository.findAllByAtivoTrue();
    }

    public MedicineData findByIdOrThrowBadRequestException(long id){
        return medicineRepository.getReferenceById(id);
    }

    public void save(MedicineData medicineData){
        MedicineData save = medicineRepository.save(medicineData);
        save.active();
    }

    public MedicineData replace(MedicinePutRequestBody medicinePutRequestBody){
        MedicineData medicineSaved = findByIdOrThrowBadRequestException(medicinePutRequestBody.getId());
        MedicineData medicine = MedicineMapper.INSTANCE.toMedicine(medicinePutRequestBody);
        medicine.setId(medicineSaved.getId());
        return medicineRepository.save(medicine);
    }

    public void deleteByIdFull(long id){
        medicineRepository.deleteById(id);
    }

    public void inactive(long id){
        MedicineData medicine = medicineRepository.getReferenceById(id);
        medicine.inactive();
    }

    public void active(long id){
        MedicineData medicine = medicineRepository.getReferenceById(id);
        medicine.active();
    }


}
