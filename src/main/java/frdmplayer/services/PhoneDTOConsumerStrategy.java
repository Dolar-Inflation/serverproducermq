package frdmplayer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneDTOConsumerStrategy implements KafkaConsumerStrategy {

    private final SaveDataService saveDataService;
    private final DeleteDataById deleteDataById;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;



    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case CREATE -> saveDataService.savePhoneNumberDTO((PhoneNumberDTO) obj);
            case DELETE -> deleteDataById.deleteEmployeePhoneDataById((PhoneNumberDTO) obj);
        }
    }

    @Override
    public String getClassName() {
        return PhoneNumberDTO.class.getSimpleName();
    }


}

