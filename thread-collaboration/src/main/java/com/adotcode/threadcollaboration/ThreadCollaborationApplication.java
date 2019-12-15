package com.adotcode.threadcollaboration;

import com.adotcode.threadcollaboration.thread.LetterRunnable;
import com.adotcode.threadcollaboration.thread.NumberRunnable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author risfeng
 */
public class ThreadCollaborationApplication {

  private static Object object = new Object();

  public static void main(String[] args) {
    ExecutorService executorService = new ThreadPoolExecutor(
        2,
        2,
        1000,
        TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<>(5),
        Executors.defaultThreadFactory(),
        (r, executor) -> System.out.println(r.toString() + "执行了拒绝策略"));
    NumberRunnable numberRunnable = new NumberRunnable(object);
    LetterRunnable letterRunnable = new LetterRunnable(object);
    executorService.execute(numberRunnable);
    executorService.execute(letterRunnable);
  }
}
