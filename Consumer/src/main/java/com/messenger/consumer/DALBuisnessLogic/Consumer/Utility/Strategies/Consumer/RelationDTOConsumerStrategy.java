package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Consumer;


import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.KafkaConsumerStrategy;
import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.CrudTemplate.RelationCrudTemplate;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.LockTable.LockTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelationDTOConsumerStrategy implements KafkaConsumerStrategy {




    private final RelationCrudTemplate relationCrudTemplate;
    private final LockTableService lockTableService;

    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){

            case CREATE -> {
                lockTableService.lockTable("employeephonerelation");
                try {


                    relationCrudTemplate.create((EmployePhoneDTO) obj);
                } finally {
                    lockTableService.unlockTable("employeephonerelation");
                }

            }

            case DELETE -> {
                lockTableService.lockTable("employeephonerelation");
                try {


                    relationCrudTemplate.deleteById(((EmployePhoneDTO) obj).getId());
                    lockTableService.unlockTable("employeephonerelation");
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
                lockTableService.lockTable("employeephonerelation");
                try {

                    EmployePhoneDTO employePhoneDTO = (EmployePhoneDTO) obj;
                    relationCrudTemplate.patch(employePhoneDTO.getId(), employePhoneDTO);
                    lockTableService.unlockTable("employeephonerelation");
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
