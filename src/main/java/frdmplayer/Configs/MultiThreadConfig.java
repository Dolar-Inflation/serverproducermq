package frdmplayer.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class MultiThreadConfig {
    @Bean
    public ExecutorService executorService() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(4);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,8,10000, TimeUnit.MILLISECONDS, workQueue,new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);
        System.out.println(Thread.currentThread().getName());
        return threadPoolExecutor;

    }
    @Bean
    public Semaphore semaphore() {
        return new Semaphore(2);
    }
}
