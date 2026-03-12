//package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Consumer;
//
//import com.messenger.consumer.DALBuisnessLogic.Consumer.Abstracts.CrudAbstractMethods;
//import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneFullDTO;
//import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.KafkaConsumerStrategy;
//import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;
//import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.CrudTemplate.FullTableCrudTemplate;
//import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.LockTable.LockTableService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class EmployePhoneFullDTOConsumerStrategy implements KafkaConsumerStrategy {
//
//    private final FullTableCrudTemplate fullTableCrudTemplate;
//    private final LockTableService lockTableService;
//
//
//
//
//    @Override
//    public void handle(Object obj, MethodsKafka methodsKafka) {
//        switch (methodsKafka){
//
//            case READALL -> {
//                List<EmployePhoneFullDTO> employePhoneFullDTOList = fullTableCrudTemplate.readAll();
//                employePhoneFullDTOList.forEach(System.out::println);
//            }
//                        case READ -> {
//
//                            try {
//
//
//                                EmployePhoneFullDTO employePhoneFullDTO = (EmployePhoneFullDTO) obj;
//                                EmployePhoneFullDTO found = fullTableCrudTemplate.readById(employePhoneFullDTO.getId());
//                                System.out.println("Из базы: " + found);
//                            }
//                            catch (Exception e) {
//                                System.err.println("<UNK> <UNK> <UNK> <UNK> <UNK> <UNK>");
//                            }
//            }//этот метод ищет по таблице relation
//            case PATCH ->{
//                lockTableService.lockTable("employeephonerelation");
//                lockTableService.lockTable("employee");
//                lockTableService.lockTable("phone");
//                try {
//
//
//
//                    EmployePhoneFullDTO employePhoneFullDTO = (EmployePhoneFullDTO) obj;
//                    fullTableCrudTemplate.patch(employePhoneFullDTO.getId(), employePhoneFullDTO);
//                    System.out.println("patched: " + employePhoneFullDTO);
//                    lockTableService.unlockTable("employeephonerelation");
//                    lockTableService.unlockTable("employee");
//                    lockTableService.unlockTable("phone");
//                }catch (Exception e) {
//                    System.err.println("<UNK> <UNK> <UNK> <UNK>");
//                }
//            }
//
//        }
//    }
//
//    @Override
//    public String getClassName() {
//        return EmployePhoneFullDTO.class.getSimpleName();
//    }
//}
