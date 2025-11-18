package frdmplayer.CrudTemplate;

import frdmplayer.Abstracts.CrudAbstractMethods;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Entity.Phone;
import frdmplayer.Interfaces.DtoMappinStrategy;
import frdmplayer.Repository.PhoneRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class PhoneCrudTemplate extends CrudAbstractMethods<Phone, PhoneNumberDTO> {
    private final PhoneRepository phoneRepository;
    private final DtoMappinStrategy<Phone, PhoneNumberDTO> phoneDtoMappinStrategy;

    public PhoneCrudTemplate(PhoneRepository phoneRepository, DtoMappinStrategy<Phone, PhoneNumberDTO> phoneDtoMappinStrategy) {

            super(phoneDtoMappinStrategy);
        this.phoneRepository = phoneRepository;
        this.phoneDtoMappinStrategy = phoneDtoMappinStrategy;
    }
    @Override
    protected boolean existsId(Integer id) {
        return phoneRepository.existsById(id);
    }

    @Override
    protected void performDelete(Integer id) {
        phoneRepository.deleteById(id);
        System.out.println("Deleted Employee with id " + id);

    }

    @Override
    protected List<Phone> performReadAll() {

        return phoneRepository.findAll();

    }
    @Override
    protected Optional<Phone> performReadById(Integer id) {

           //не работает т.к я задаю id в dto которое отправляю и поэтому exception не будет в любом случае
            return phoneRepository.findById(id);

//        return phoneRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Phone with id " + id + " not found")
    }
    @Override
    protected Phone performCreate (Phone entity) {
        return phoneRepository.save(entity);
    }
    @Override
    protected Phone performPatch(Integer id, PhoneNumberDTO dto) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone with id " + id + " not found"));
        if(dto.getType()!=null){phone.setType(dto.getType());}
        if(dto.getNumber()!=null){phone.setNumber(dto.getNumber());}
        return phoneRepository.save(phone);

    }
}
