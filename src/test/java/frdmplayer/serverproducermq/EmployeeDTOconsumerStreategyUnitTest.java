package frdmplayer.serverproducermq;

import frdmplayer.CrudTemplate.EmployeeCrudTemplate;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.Strategies.Consumer.EmployeeDTOConsumerStategy;
import frdmplayer.services.LockTableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class EmployeeDTOconsumerStreategyUnitTest {

    @Mock
    private EmployeeCrudTemplate employeeCrudTemplate;

    @Mock
    private LockTableService lockTableService;



    @Test
    public void testEmployeeDTOConsumerUnit() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        EmployeeDTOConsumerStategy employeeDTOConsumerStategy = new EmployeeDTOConsumerStategy(lockTableService,employeeCrudTemplate);

        employeeDTOConsumerStategy.handle(employeeDTO, MethodsKafka.CREATE);

        verify(employeeCrudTemplate).create(employeeDTO);

    }

}
