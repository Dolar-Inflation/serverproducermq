package frdmplayer.serverproducermq;

import frdmplayer.Strategies.TableThreadLock.EmployeeTableLockStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class TableLockTest {
    @Spy
    @InjectMocks
    EmployeeTableLockStrategy employeeTableLockStrategy;

    @Mock
    ReentrantLock reentrantLock;

   static final String tableName = "employee";
    @Test
    public void testTableLock() {
    employeeTableLockStrategy.lock(tableName);
    verify(employeeTableLockStrategy).lock(tableName);

    }

    @Test
    public void testTableUnlock() {
        employeeTableLockStrategy.lock(tableName);
       employeeTableLockStrategy.unlock(tableName);
       verify(employeeTableLockStrategy).unlock(tableName);

    }

    @Test
    public void testTableLockSupports(){

       Assertions.assertTrue(employeeTableLockStrategy.supports(tableName));

    }


}
