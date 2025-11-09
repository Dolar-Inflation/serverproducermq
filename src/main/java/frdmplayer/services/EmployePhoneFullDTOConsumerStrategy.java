package frdmplayer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployePhoneFullDTOConsumerStrategy implements KafkaConsumerStrategy {
    private final SaveDataService saveDataService;
    private final DeleteDataById deleteDataById;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;


    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case PATCH -> updateDataService.updateData((EmployePhoneFullDTO) obj);
        }
    }

    @Override
    public String getClassName() {
        return EmployePhoneFullDTO.class.getSimpleName();
    }
}
