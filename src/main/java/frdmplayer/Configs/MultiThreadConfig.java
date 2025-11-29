package frdmplayer.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class MultiThreadConfig {
    @Bean
    public ExecutorService executorService() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(20);

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,10000, TimeUnit.MILLISECONDS, workQueue,new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                10,
                0L, TimeUnit.MILLISECONDS,
                workQueue,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        System.out.println(Thread.currentThread().getName());
        return threadPoolExecutor;

    }
    @Bean
    public Semaphore semaphore() {
        return new Semaphore(2);
    }
}
