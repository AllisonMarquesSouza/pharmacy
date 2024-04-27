package com.system.remedios.Repository;

import com.system.remedios.domain.MedicineData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<MedicineData, Long> {

     List<MedicineData> findAllByAtivoTrue();
//     MedicineData findByIdAtivoTrue(Long id);
}
