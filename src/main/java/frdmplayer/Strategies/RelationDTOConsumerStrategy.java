package frdmplayer.Strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import frdmplayer.services.DeleteDataById;
import frdmplayer.services.SaveDataService;
import frdmplayer.services.UpdateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelationDTOConsumerStrategy implements KafkaConsumerStrategy {
    private final SaveDataService saveDataService;
    private final DeleteDataById deleteDataById;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;

    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case CREATE -> saveDataService.saveEmployePhoneRelation((EmployePhoneDTO) obj);
            case DELETE -> deleteDataById.deleteRelationDataById((EmployePhoneDTO) obj);

        }
    }

    @Override
    public String getClassName() {
        return EmployePhoneDTO.class.getSimpleName();
    }
}
