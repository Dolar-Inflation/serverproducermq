package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Consumer;


//import com.messenger.consumer.DALBuisnessLogic.Consumer.Abstracts.CrudAbstractMethods;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Repository.EmployeeRepository;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployeeDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.KafkaConsumerStrategy;
import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;
//import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.CrudTemplate.EmployeeCrudTemplate;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.LockTable.LockTableService;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeDTOConsumerStategy implements KafkaConsumerStrategy {


    private static final Logger log = LoggerFactory.getLogger(EmployeeDTOConsumerStategy.class);
    private final LockTableService lockTableService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;




    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){

            case CREATE ->{
                lockTableService.lockTable("employee");
                try
            {
               Employee employee = employeeMapper.mapToEmployee((EmployeeDTO)obj);
                employeeRepository.save(employee);
//                employeeCrudTemplate.create((EmployeeDTO) obj);

            } finally {
                    lockTableService.unlockTable("employee");
                }
            }


            case DELETE ->{
                lockTableService.lockTable("employee");
                try {


//                    employeeCrudTemplate.deleteById(((EmployeeDTO) obj).getId());
                    EmployeeDTO employeeDTO = (EmployeeDTO) obj;
                   Employee employee = employeeMapper.mapToEmployee(employeeDTO);
                    employeeRepository.deleteById(employee.getId());

                    lockTableService.unlockTable("employee");
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
            case READALL -> {
                List<Employee> employeeList = employeeRepository.findAll();
                employeeMapper.mapToEmployeeDTOList(employeeList);

                employeeList.forEach(System.out::println);
            }
            case READ -> {
                try {
                    EmployeeDTO employeeDTO = (EmployeeDTO) obj;
                    Employee employee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(RuntimeException::new);
                    employeeMapper.mapToEmployeeDTO(employee);

                    System.out.println(employee);



                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            case PATCH -> {
                lockTableService.lockTable("employee");
                try {
                    EmployeeDTO employeeDTO = (EmployeeDTO) obj;

                   Employee employee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(RuntimeException::new);
                   employeeMapper.updateEmployeeFromDto(employeeDTO, employee);
                   employeeRepository.save(employee);
//                    employeeCrudTemplate.patch(employeeDTO.getId(), employeeDTO);
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




