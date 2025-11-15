package frdmplayer.Strategies;

import frdmplayer.Interfaces.ReentrantLockStrategy;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class PhoneTableLockStrategy implements ReentrantLockStrategy {

    private final ReentrantLock phonelock = new ReentrantLock();

    @Override
    public void lock(String tableName) {
        if(!"phone".equalsIgnoreCase(tableName)){
            throw new IllegalArgumentException("Table name must be either \"phone\"");

        }
        System.out.println("Locking phone table " + tableName);
        phonelock.lock();
    }

    @Override
    public void unlock(String tableName) {
        if(!"phone".equalsIgnoreCase(tableName)) {
            throw new IllegalArgumentException("Table name must be either \"phone\"");
        }
        System.out.println("Unlocking phone table " + tableName);
        phonelock.unlock();

    }

    @Override
    public boolean supports(String tableName) {
        return "phone".equalsIgnoreCase(tableName);
    }
}
