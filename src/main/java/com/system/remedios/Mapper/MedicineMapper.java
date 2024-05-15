package com.system.remedios.Mapper;

import com.system.remedios.domain.MedicineData;
import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.MedicinePostRequestBody;
import com.system.remedios.requests.MedicinePutRequestBody;
import com.system.remedios.requests.UserPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class MedicineMapper {
    public static final MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class);

    public abstract MedicineData toMedicine(MedicinePutRequestBody medicinePutRequestBody);
    public abstract MedicineData toMedicine(MedicinePostRequestBody medicinePostRequestBody);
    public abstract Usuario toUsuario(UserPostRequestBody userPostRequestBody);

}
