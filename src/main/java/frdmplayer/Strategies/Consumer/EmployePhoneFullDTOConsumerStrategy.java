package frdmplayer.Strategies.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.CrudTemplate.FullTableCrudTemplate;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployePhoneFullDTOConsumerStrategy implements KafkaConsumerStrategy {


    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;

    private final FullTableCrudTemplate fullTableCrudTemplate;



    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
//            case READALL -> getDbInfo.getAllRelations();
            case READALL -> {
                List<EmployePhoneFullDTO> employePhoneFullDTOList = fullTableCrudTemplate.readAll();
                employePhoneFullDTOList.forEach(System.out::println);
            }



                        case READ -> {
//                EmployePhoneFullDTO dto = (EmployePhoneFullDTO) obj;
//                getDbInfo.GetById(dto.getId());
                            try {


                                EmployePhoneFullDTO employePhoneFullDTO = (EmployePhoneFullDTO) obj;
                                EmployePhoneFullDTO found = fullTableCrudTemplate.readById(employePhoneFullDTO.getId());
                                System.out.println("Из базы: " + found);
                            }
                            catch (Exception e) {
                                System.err.println("<UNK> <UNK> <UNK> <UNK> <UNK> <UNK>");
                            }
            }//этот метод ищет по таблице relation
            case PATCH ->{
                try {


//                updateDataService.updateData((EmployePhoneFullDTO) obj);
                    EmployePhoneFullDTO employePhoneFullDTO = (EmployePhoneFullDTO) obj;
                    fullTableCrudTemplate.patch(employePhoneFullDTO.getId(), employePhoneFullDTO);
                    System.out.println("patched: " + employePhoneFullDTO);
                }catch (Exception e) {
                    System.err.println("<UNK> <UNK> <UNK> <UNK>");
                }
            }

        }
    }

    @Override
    public String getClassName() {
        return EmployePhoneFullDTO.class.getSimpleName();
    }
}
