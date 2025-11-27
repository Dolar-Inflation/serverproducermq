package frdmplayer.serverproducermq;

import frdmplayer.CrudTemplate.EmployeeCrudTemplate;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Interfaces.DtoMappinStrategy;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.services.LockTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@SpringBootTest
@ActiveProfiles("test")
public class EmployeeCrudTemplateUnitTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DtoMappinStrategy<Employee, EmployeeDTO> employeeDtoMapper;

    @Autowired
    private EmployeeCrudTemplate employeeCrudTemplate;


    @Mock
    private LockTableService lockTableService;

    @Test
    void FindById() {
        Employee employee = new Employee();

        employee.setFio("name");
        employee.setAddress("address");
        employee.setPosition("position");

        employeeRepository.save(employee);




        EmployeeDTO result = employeeCrudTemplate.readById(employee.getId());

        assertEquals("name", result.getFio());

    }
    @Test
    void Create() {


        EmployeeDTO dto = new EmployeeDTO();
        dto.setFio("name");
        dto.setAddress("address");
        dto.setPosition("position");


        EmployeeDTO result = employeeCrudTemplate.create(dto);

        assertEquals("name", result.getFio());
        assertTrue(employeeRepository.existsById(result.getId()));
    }
    @Test
    void DeleteById() {
        Employee employee = new Employee();

        employee.setFio("name");
        employee.setAddress("address");
        employee.setPosition("position");

        employeeRepository.save(employee);



        employeeCrudTemplate.deleteById(employee.getId());

        assertFalse(employeeRepository.existsById(employee.getId()));
    }
    @Test
    void Patch(){
        Employee employee = new Employee();
        employee.setFio("name");
        employee.setAddress("address");
        employee.setPosition("position");
        employeeRepository.save(employee);

        EmployeeDTO dto = new EmployeeDTO();
        dto.setFio("name2");
        dto.setAddress("address2");
        dto.setPosition("position2");

        employeeCrudTemplate.patch(employee.getId(),dto);
        employee = employeeRepository.findById(employee.getId()).get();
        assertFalse("name".equals(employee.getFio()));
    }
}