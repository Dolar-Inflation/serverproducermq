package frdmplayer.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.*;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.MapEntity.EntityToDTO;
import frdmplayer.ObjToJSON.ObjToJSON;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.services.GetDbInfo;
import frdmplayer.services.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class KafkaController {
    @Autowired

    private GetDbInfo getDbInfo;
    private EmployesphoneRepository empPhoneRepository;
    private EntityToDTO entityToDTO;
    private UpdateDto updateDto;
    private Producer producer;
    private ObjToJSON objToJSON;

    public KafkaController( GetDbInfo getDbInfo, EmployesphoneRepository empPhoneRepository, EntityToDTO entityToDTO,Producer producer,ObjToJSON objToJSON) {

        this.objToJSON = objToJSON;
        this.getDbInfo = getDbInfo;
        this.empPhoneRepository = empPhoneRepository;
        this.entityToDTO = entityToDTO;
        this.producer = producer;
    }

    @PostMapping("/api/messages/addemploye")
    public EmployeeDTO sendName(@RequestBody EmployeeDTO message) throws JsonProcessingException {
//        producerService.sendMessage(message);
//        producer.sendCreateEmployee(message);
//        producer.send(message);

        producer.send(message,MethodsKafka.CREATE);
        return message;
    }

    @PostMapping("/api/messages/addphone")
    public String sendPhone(@RequestBody PhoneNumberDTO message) throws JsonProcessingException {
//        producerService.sendPhoneNumber(message);
        producer.send(message,MethodsKafka.CREATE);
        return "phone added: " + message+"method sent"+MethodsKafka.values();
    }

    @PostMapping("/api/messages/addrelation")
    public String sendRelation(@RequestBody EmployePhoneDTO message) throws JsonProcessingException {
//        producerService.sendRelation(message);
        producer.send(message,MethodsKafka.CREATE);
        return "relation created " + message;
    }

//        @GetMapping("/")
//    public String DbInfo(EmployePhoneDTO message) throws JsonProcessingException {
//        getDbInfo.getDbInfo(message);
//        return "db info " + message;
//    }
    //TODO я еблан забыл что с сообщением передаётся метод
    // а нахуй это надо в теории же можно будет просто контроллер на стороне консьюмера написать и просто получать данные т.к консьюмер имеет связь с бд
    // а вдруг надо если продюсер отправляет сообщение о создании?
    // не не надо удаление да а на чтение данных не думаю
//    @GetMapping("/")
//    public List<EmployePhoneFullDTO> getAllEmployees() {
//        List<EmployePhoneFullDTO> employeesData = getDbInfo.getAllRelations();
//        System.out.println(employeesData);
//        return employeesData;
//    }
    //TODO я еблан забыл что с сообщением передаётся метод нахуй я это сделал?)
//    @GetMapping("/{id}")
//    public List<EmployePhoneFullDTO> getEmployeeById(@PathVariable int id) {
//        List<EmployePhoneFullDTO> employeeData = getDbInfo.getEmployeeById(id);
//        System.out.println(employeeData);
//        return employeeData;
//
//    }

    @DeleteMapping("/relation/{id}")
    public EmployePhoneDTO deleteEmployeeRelation(@PathVariable Integer id) throws JsonProcessingException {
        EmployePhoneDTO employePhoneDTO = new EmployePhoneDTO();
        employePhoneDTO.setId(id);
        producer.send(employePhoneDTO,MethodsKafka.DELETE);
//        producerService.sendFullEmployePhoneDeleteByID(employePhoneFullDTO);
        System.out.println("employee Relation data deleted " + employePhoneDTO);
        return employePhoneDTO;
    }
//    }
    @DeleteMapping("/employee/delete")
    public EmployeeDTO deleteEmployee(@RequestBody EmployeeDTO employeeDTO ) throws JsonProcessingException {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        employeeDTO.setId(id);
//        producer.send(employeeDTO, MethodsKafka.DELETE);
//        producerService.SendEmployeeDelete(employeeDTO);
//        producer.sendDeleteEmployeeByID(employeeDTO);
        producer.send(employeeDTO,MethodsKafka.DELETE);

        System.out.println("employee data deleted " +  employeeDTO+"method sent"+MethodsKafka.values());
        return employeeDTO;
    }
    @DeleteMapping("/phone/delete")
    public PhoneNumberDTO deletePhone(@RequestBody PhoneNumberDTO phoneNumberDTO ) throws JsonProcessingException {
        producer.send(phoneNumberDTO,MethodsKafka.DELETE);
        return phoneNumberDTO;
    }

@PatchMapping("/")
    public EmployePhoneFullDTO updateAllRecords(@RequestBody EmployePhoneFullDTO message) throws JsonProcessingException {
//        producerService.sendUpdateDto(message);
    producer.send(message,MethodsKafka.PATCH);
        System.out.println("employee data sent " + message);
        return message;

}

   }


