package frdmplayer.Strategies.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.CrudTemplate.PhoneCrudTemplate;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;


import frdmplayer.services.LockTableService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhoneDTOConsumerStrategy implements KafkaConsumerStrategy {





    private final PhoneCrudTemplate phoneCrudTemplate;
    private final LockTableService lockTableService;


    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka) {
            case CREATE ->{
                lockTableService.lockTable("phone");
                try {


                    phoneCrudTemplate.create((PhoneNumberDTO) obj);
                } finally {
                    lockTableService.unlockTable("phone");
                }
            }
            case DELETE ->{
                lockTableService.lockTable("phone");
                try {
                    phoneCrudTemplate.deleteById(((PhoneNumberDTO) obj).getId());
                    lockTableService.unlockTable("phone");
                }
                catch(EntityNotFoundException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
            case READALL -> {
                List<PhoneNumberDTO> phoneDTOList = phoneCrudTemplate.readAll();
                phoneDTOList.forEach(System.out::println);

            }
            case PATCH -> {
                lockTableService.lockTable("phone");
                try {
                    PhoneNumberDTO phoneDTO = (PhoneNumberDTO) obj;
                    phoneCrudTemplate.patch(phoneDTO.getId(), phoneDTO);
                    lockTableService.unlockTable("phone");
                }
                catch(EntityNotFoundException e) {
                    System.err.println("<UNK>: " + e.getMessage());
                }
            }

            case READ -> {
                try {


                    PhoneNumberDTO dto = (PhoneNumberDTO) obj;
                    PhoneNumberDTO found = phoneCrudTemplate.readById(dto.getId());
                    System.out.println("Из базы: " + found);
                }
                catch (EntityNotFoundException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }

            }
        }
    }

    @Override
    public String getClassName() {
        return PhoneNumberDTO.class.getSimpleName();
    }


}

