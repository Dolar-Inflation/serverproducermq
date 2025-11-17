package frdmplayer.Strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import frdmplayer.services.DeleteDataById;
import frdmplayer.services.GetDbInfo;
import frdmplayer.services.SaveDataService;
import frdmplayer.services.UpdateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.kafka.common.requests.FetchMetadata.log;

@Component
@RequiredArgsConstructor
public class EmployePhoneFullDTOConsumerStrategy implements KafkaConsumerStrategy {
    private final SaveDataService saveDataService;
    private final DeleteDataById deleteDataById;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;
    private final GetDbInfo getDbInfo;



    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case READALL -> getDbInfo.getAllRelations();



                        case READ -> {
                EmployePhoneFullDTO dto = (EmployePhoneFullDTO) obj;
                getDbInfo.GetById(dto.getId());
            }//этот метод ищет по таблице relation
            case PATCH -> updateDataService.updateData((EmployePhoneFullDTO) obj);
        }
    }

    @Override
    public String getClassName() {
        return EmployePhoneFullDTO.class.getSimpleName();
    }
}
