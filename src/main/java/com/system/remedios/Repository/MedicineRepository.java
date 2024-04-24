package com.system.remedios.Repository;

import com.system.remedios.DataConnection.MedicineData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<MedicineData, Long> {

}
