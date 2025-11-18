package frdmplayer.Strategies.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.CrudTemplate.RelationCrudTemplate;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;

import frdmplayer.services.UpdateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelationDTOConsumerStrategy implements KafkaConsumerStrategy {


    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;
    private final RelationCrudTemplate relationCrudTemplate;

    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case CREATE -> relationCrudTemplate.create((EmployePhoneDTO)obj);
            case DELETE -> relationCrudTemplate.deleteById(((EmployePhoneDTO)obj).getId());
            case READALL -> {
                List<EmployePhoneDTO> employePhoneDTOS = relationCrudTemplate.readAll();
                employePhoneDTOS.forEach(System.out::println);
            }
            case READ -> { EmployePhoneDTO employePhoneDTO = relationCrudTemplate.readById(((EmployePhoneDTO)obj).getId());
                System.out.println(employePhoneDTO);

            }
            case PATCH -> {
                EmployePhoneDTO employePhoneDTO = (EmployePhoneDTO)obj;
                relationCrudTemplate.patch(employePhoneDTO.getId(), employePhoneDTO);
            }

        }
    }

    @Override
    public String getClassName() {
        return EmployePhoneDTO.class.getSimpleName();
    }
}
