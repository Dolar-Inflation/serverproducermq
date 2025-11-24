package frdmplayer.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.*;
import frdmplayer.KafkaMethods.MethodsKafka;

import frdmplayer.ObjToJSON.ObjToJSON;
import frdmplayer.Repository.EmployesphoneRepository;

import frdmplayer.services.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class KafkaController {
    @Autowired




    private Producer producer;


    public KafkaController(  Producer producer) {

        this.producer = producer;
    }
        //маппинги для работы с таблицей employee
    @PostMapping("/employee/create")
    public EmployeeDTO sendName(@RequestBody EmployeeDTO message) throws JsonProcessingException {


        producer.send(message,MethodsKafka.CREATE);
        return message;
    }
    @GetMapping("/employee/data")
    public EmployeeDTO employeeData() throws JsonProcessingException {
        EmployeeDTO dull=new EmployeeDTO();
        producer.send(dull,MethodsKafka.READALL);
        return dull;

    }
    @GetMapping("/employee/data/{id}")
    public EmployeeDTO employeeDataById(@PathVariable int id) throws JsonProcessingException {
        EmployeeDTO dull=new EmployeeDTO();
        dull.setId(id);
        producer.send(dull,MethodsKafka.READ);
        return dull;
    }
    @DeleteMapping("/employee/delete")
    public EmployeeDTO deleteEmployee(@RequestBody EmployeeDTO employeeDTO ) throws JsonProcessingException {

        producer.send(employeeDTO,MethodsKafka.DELETE);

        System.out.println("employee data deleted " +  employeeDTO+"method sent"+MethodsKafka.values());
        return employeeDTO;
    }
    @PatchMapping("/employee/patch")
    public EmployeeDTO patchEmployee(@RequestBody EmployeeDTO employeeDTO ) throws JsonProcessingException {
        producer.send(employeeDTO,MethodsKafka.PATCH);
        return employeeDTO;
    }


//маппинги для работы с таблицей phone
    @GetMapping("/phone/data")
    public PhoneNumberDTO getPhoneData() throws JsonProcessingException {
        PhoneNumberDTO dull = new PhoneNumberDTO();
        producer.send(dull,MethodsKafka.READALL);
        return dull;
    }
    @GetMapping("/phone/data/{id}")
    public PhoneNumberDTO getPhoneById(@PathVariable Integer id) {

        PhoneNumberDTO dull = new PhoneNumberDTO();
        dull.setId(id);
        producer.send(dull,MethodsKafka.READ);
        return dull;

    }
    @PatchMapping("/phone/patch")
    public PhoneNumberDTO patchPhone(@RequestBody PhoneNumberDTO message ) throws JsonProcessingException {
        producer.send(message,MethodsKafka.PATCH);
        return message;
    }
    @DeleteMapping("/phone/delete")
    public PhoneNumberDTO deletePhone(@RequestBody PhoneNumberDTO phoneNumberDTO ) throws JsonProcessingException {
        producer.send(phoneNumberDTO,MethodsKafka.DELETE);
        return phoneNumberDTO;
    }
    @PostMapping("/phone/create")
    public String sendPhone(@RequestBody PhoneNumberDTO message) throws JsonProcessingException {
//        producerService.sendPhoneNumber(message);
        producer.send(message,MethodsKafka.CREATE);
        return "phone added: " + message+"method sent"+MethodsKafka.values();
    }


//методы для работы с таблицей employeephonerelation
    @PostMapping("/relation/addrelation")
    public String sendRelation(@RequestBody EmployePhoneDTO message) throws JsonProcessingException {

        producer.send(message,MethodsKafka.CREATE);
        return "relation created " + message;
    }


    @DeleteMapping("/relation/{id}")
    public EmployePhoneDTO deleteEmployeeRelation(@PathVariable Integer id) throws JsonProcessingException {
        EmployePhoneDTO employePhoneDTO = new EmployePhoneDTO();
        employePhoneDTO.setId(id);
        producer.send(employePhoneDTO,MethodsKafka.DELETE);

        System.out.println("employee Relation data for deleting sent to kafka " + employePhoneDTO);
        return employePhoneDTO;
    }
@GetMapping("/relation/data")
public EmployePhoneDTO employeeRelationData() throws JsonProcessingException {
        EmployePhoneDTO dull = new EmployePhoneDTO();
        producer.send(dull,MethodsKafka.READALL);
        return dull;

}
@GetMapping("/relation/data/{id}")
public EmployePhoneDTO employeeRelationDataById(@PathVariable Integer id) throws JsonProcessingException {
        EmployePhoneDTO dull = new EmployePhoneDTO();
        dull.setId(id);
        producer.send(dull,MethodsKafka.READ);
        return dull;
}
@PatchMapping("/relation/patch")
public EmployePhoneDTO patchRelation(@RequestBody EmployePhoneDTO message) throws JsonProcessingException {
        producer.send(message,MethodsKafka.PATCH);
        return message;
}



//EmployePhoneFULLDTO
    @GetMapping("/")
    public ResponseEntity<String> getAllEmployees() {
        EmployePhoneFullDTO dull = new EmployePhoneFullDTO();
        producer.send(dull,MethodsKafka.READALL);


        return ResponseEntity.ok("READALL");
    }

    @GetMapping("/{id}")
    public EmployePhoneFullDTO getEmployeeById(@PathVariable Integer id) {

        EmployePhoneFullDTO dull = new EmployePhoneFullDTO();
        dull.setId(id);
        producer.send(dull,MethodsKafka.READ);
        return dull;

    }


    @PatchMapping("/")
    public EmployePhoneFullDTO updateAllRecords(@RequestBody EmployePhoneFullDTO message) throws JsonProcessingException {

        producer.send(message,MethodsKafka.PATCH);
        System.out.println("employee data sent " + message);
        return message;

    }





   }


