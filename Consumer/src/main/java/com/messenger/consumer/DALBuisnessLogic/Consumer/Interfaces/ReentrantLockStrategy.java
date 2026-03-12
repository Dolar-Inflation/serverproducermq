package com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces;

public interface ReentrantLockStrategy {
    void lock(String tableName);
    void unlock(String tableName);
    boolean supports(String tableName);
}
