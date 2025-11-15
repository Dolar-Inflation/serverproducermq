package frdmplayer.Interfaces;

public interface ReentrantLockStrategy {
    void lock(String tableName);
    void unlock(String tableName);
    boolean supports(String tableName);
}
