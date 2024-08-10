package com.system.remedios.Repository;

import com.system.remedios.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
     List<Medicine> findAllByAtivoTrue();
}
