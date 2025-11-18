package frdmplayer.Strategies.TableThreadLock;

import frdmplayer.Interfaces.ReentrantLockStrategy;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;
@Service
public class EmployeeTableLockStrategy implements ReentrantLockStrategy {

private final ReentrantLock employeelock = new ReentrantLock();

    @Override
    public void lock(String tableName) {
        if(!"employee".equalsIgnoreCase(tableName)){
            throw new IllegalArgumentException("Table name must be either \"employee\"");

        }
        System.out.println("Locking employee table " + tableName);
        employeelock.lock();
    }

    @Override
    public void unlock(String tableName) {
        if(!"employee".equalsIgnoreCase(tableName)) {
            throw new IllegalArgumentException("Table name must be either \"employee\"");
        }
        System.out.println("Unlocking employee table " + tableName);
        employeelock.unlock();

    }

    @Override
    public boolean supports(String tableName) {
        return "employee".equalsIgnoreCase(tableName);
    }
}
