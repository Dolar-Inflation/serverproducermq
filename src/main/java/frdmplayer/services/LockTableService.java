package frdmplayer.services;

import frdmplayer.Interfaces.ReentrantLockStrategy;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class LockTableService  {
    private final List<ReentrantLockStrategy> strategies;
    private static final Logger log = LoggerFactory.getLogger(LockTableService.class);
    public LockTableService(List<ReentrantLockStrategy> strategies) {
        this.strategies = strategies;
    }

    public void lockTable(String tableName) {
        for (ReentrantLockStrategy strategy : strategies) {
            if(strategy.supports(tableName)){
                strategy.lock(tableName);
                log.info("Заблокирована таблица {}", tableName);
                return;
            }
        }
        throw new IllegalArgumentException("НЕ РАБОТАЕТ LockTableService метод lock");
    }

    public void unlockTable(String tableName) {
        for (ReentrantLockStrategy strategy : strategies) {
            if(strategy.supports(tableName)){
                strategy.unlock(tableName);
                log.info("Разблокирована таблица {}", tableName);
            return;
            }
        }
        throw new IllegalArgumentException("НЕ РАБОТАЕТ LockTableService метод unclock");
    }
}
