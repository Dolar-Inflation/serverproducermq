package frdmplayer.Strategies.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.CrudTemplate.RelationCrudTemplate;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelationDTOConsumerStrategy implements KafkaConsumerStrategy {


    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;

    private final RelationCrudTemplate relationCrudTemplate;

    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case CREATE -> relationCrudTemplate.create((EmployePhoneDTO)obj);

            case DELETE -> {
                try {


                    relationCrudTemplate.deleteById(((EmployePhoneDTO) obj).getId());
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            case READALL -> {
                List<EmployePhoneDTO> employePhoneDTOS = relationCrudTemplate.readAll();
                employePhoneDTOS.forEach(System.out::println);
            }
            case READ -> {
                try {


                    EmployePhoneDTO employePhoneDTO = relationCrudTemplate.readById(((EmployePhoneDTO) obj).getId());
                    System.out.println(employePhoneDTO);
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            case PATCH -> {
                try {

                    EmployePhoneDTO employePhoneDTO = (EmployePhoneDTO) obj;
                    relationCrudTemplate.patch(employePhoneDTO.getId(), employePhoneDTO);
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

        }
    }

    @Override
    public String getClassName() {
        return EmployePhoneDTO.class.getSimpleName();
    }
}
