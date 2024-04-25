package com.system.remedios.Repository;

import com.system.remedios.domain.MedicineData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<MedicineData, Long> {


}
