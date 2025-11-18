package frdmplayer.Strategies.Mapping;

import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Phone;
import frdmplayer.Interfaces.DtoMappinStrategy;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapStrategy implements DtoMappinStrategy<Phone, PhoneNumberDTO> {

    @Override
    public PhoneNumberDTO toDto(Phone entity){
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setType(entity.getType());
        return dto;
    }
    @Override
    public Phone toEntity(PhoneNumberDTO dto){
        Phone entity = new Phone();
        entity.setNumber(dto.getNumber());
        entity.setType(dto.getType());
        return entity;
    }
    @Override
    public boolean supports(Class<?> entityClass) {
        return Phone.class.equals(entityClass);
    }
    @Override
    public Class<Phone> getEntityClass() {
        return Phone.class;
    }
    @Override
    public Class<PhoneNumberDTO> getDtoClass() {
        return PhoneNumberDTO.class;
    }
}
