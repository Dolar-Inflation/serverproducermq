package frdmplayer.Strategies.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.CrudTemplate.PhoneCrudTemplate;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;


import frdmplayer.services.UpdateDataService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhoneDTOConsumerStrategy implements KafkaConsumerStrategy {



    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;
    private final PhoneCrudTemplate phoneCrudTemplate;


//TODO написать try catch для всех стратегий
    //TODO протестить работу CrudTemplate-ов для relation и employee также расширить один из методов для полного обновления связанных строчек таблиц
    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka) {
            case CREATE -> phoneCrudTemplate.create((PhoneNumberDTO) obj);
            case DELETE ->{
                try {
                    phoneCrudTemplate.deleteById(((PhoneNumberDTO) obj).getId());
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
                try {
                    PhoneNumberDTO phoneDTO = (PhoneNumberDTO) obj;
                    phoneCrudTemplate.patch(phoneDTO.getId(), phoneDTO);
                }
                catch(EntityNotFoundException e) {
                    System.err.println("<UNK>: " + e.getMessage());
                }
            }

            case READ -> {
//                PhoneNumberDTO phoneDTOList = phoneCrudTemplate.readById(((PhoneNumberDTO) obj).getId());
//                System.out.println(phoneDTOList);
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

