package frdmplayer.Strategies;

import frdmplayer.Interfaces.ReentrantLockStrategy;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;
@Service
public class EmployeephonerelationTableLockStrategy implements ReentrantLockStrategy {
    private final ReentrantLock employeephonereelationlock = new ReentrantLock();

    @Override
    public void lock(String tableName) {
        if(!"employeephonerelation".equalsIgnoreCase(tableName)){
            throw new IllegalArgumentException("Table name must be  \"employeephonerelation\"");

        }
        System.out.println("Locking employeephonerelation table " + tableName);
        employeephonereelationlock.lock();

    }

    @Override
    public void unlock(String tableName) {
        if(!"employeephonerelation".equalsIgnoreCase(tableName)){
            throw new IllegalArgumentException("Table name must be  \"employeephonerelation\"");

        }
        System.out.println("Unlocking employeephonerelation table " + tableName);
        employeephonereelationlock.unlock();

    }

    @Override
    public boolean supports(String tableName) {
        return "employeephonerelation".equalsIgnoreCase(tableName);
    }
}
