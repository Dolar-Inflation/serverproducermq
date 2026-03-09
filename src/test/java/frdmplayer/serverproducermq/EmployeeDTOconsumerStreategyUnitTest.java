package frdmplayer.serverproducermq;

import frdmplayer.CrudTemplate.EmployeeCrudTemplate;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.Strategies.Consumer.EmployeeDTOConsumerStategy;
import frdmplayer.services.LockTableService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class EmployeeDTOconsumerStreategyUnitTest {

    @Mock
    private EmployeeCrudTemplate employeeCrudTemplate;

    @Mock
    private LockTableService lockTableService;

    @InjectMocks
    private EmployeeDTOConsumerStategy employeeDTOConsumerStategy;



    @Test
    public void testEmployeeDTOConsumerUnit() {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        Mockito.when(employeeCrudTemplate.create(Mockito.any())).thenReturn(employeeDTO);

        employeeDTOConsumerStategy.handle(employeeDTO, MethodsKafka.CREATE);

        verify(employeeCrudTemplate).create(employeeDTO);

    }

}
