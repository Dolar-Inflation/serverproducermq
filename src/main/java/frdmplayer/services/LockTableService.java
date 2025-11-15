package frdmplayer.services;

import frdmplayer.Interfaces.ReentrantLockStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockTableService  {
    private final List<ReentrantLockStrategy> strategies;

    public LockTableService(List<ReentrantLockStrategy> strategies) {
        this.strategies = strategies;
    }

    public void lockTable(String tableName) {
        for (ReentrantLockStrategy strategy : strategies) {
            if(strategy.supports(tableName)){
                strategy.lock(tableName);
                return;
            }
        }
        throw new IllegalArgumentException("НЕ РАБОТАЕТ LockTableService метод lock");
    }

    public void unlockTable(String tableName) {
        for (ReentrantLockStrategy strategy : strategies) {
            if(strategy.supports(tableName)){
                strategy.unlock(tableName);
            return;
            }
        }
        throw new IllegalArgumentException("НЕ РАБОТАЕТ LockTableService метод unclock");
    }
}
