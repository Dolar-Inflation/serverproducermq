package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Consumer;


import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployeeDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.KafkaConsumerStrategy;
import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.CrudTemplate.EmployeeCrudTemplate;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.LockTable.LockTableService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeDTOConsumerStategy implements KafkaConsumerStrategy {


    private static final Logger log = LoggerFactory.getLogger(EmployeeDTOConsumerStategy.class);
    private final LockTableService lockTableService;
    private final EmployeeCrudTemplate employeeCrudTemplate;



    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){

            case CREATE ->{
                lockTableService.lockTable("employee");
                try
            {
                employeeCrudTemplate.create((EmployeeDTO) obj);

            } finally {
                    lockTableService.unlockTable("employee");
                }
            }


            case DELETE ->{
                lockTableService.lockTable("employee");
                try {


                    employeeCrudTemplate.deleteById(((EmployeeDTO) obj).getId());
                    lockTableService.unlockTable("employee");
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
            case READALL -> {
                List<EmployeeDTO> employeeDTOList= employeeCrudTemplate.readAll();
                employeeDTOList.forEach(System.out::println);
            }
            case READ -> {
                try {


                    EmployeeDTO employeeDTO = employeeCrudTemplate.readById(((EmployeeDTO) obj).getId());
                    System.out.println(employeeDTO);
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            case PATCH -> {
                lockTableService.lockTable("employee");
                try {

                    EmployeeDTO employeeDTO = (EmployeeDTO) obj;
                    employeeCrudTemplate.patch(employeeDTO.getId(), employeeDTO);
                    lockTableService.unlockTable("employee");
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public String getClassName() {
        return EmployeeDTO.class.getSimpleName();
    }


}




