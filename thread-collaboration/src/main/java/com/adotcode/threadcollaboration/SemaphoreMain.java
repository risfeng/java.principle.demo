package com.adotcode.threadcollaboration;

import java.util.concurrent.Semaphore;

/**
 * Semaphore案例演示
 *
 * @author risfeng
 * @date 2020/03/01
 */
public class SemaphoreMain {

  /**
   * <p>一个厕所只有3个坑位，但是有10个人来上厕所，那怎么办？
   * 假设10的人的编号分别为1-10，并且1号先到厕所，10号最后到厕所。
   * <p>那么1-3号来的时候必然有可用坑位，顺利如厕，
   * <p>4号来的时候需要看看前面3人是否有人出来了，如果有人出来，进去，否则等待。
   * <p> 同样的道理，4-10号也需要等待正在上厕所的人出来后才能进去， 并且谁先进去这得看等待的人是否有素质，是否能遵守先来先上的规则。
   */
  public static void main(String[] args) {
    //需要上厕所的人数
    int peopleTotal = 50;
    //厕所总坑数
    int toiletCount = 5;
    Semaphore semaphore = new Semaphore(toiletCount);
    for (int i = 0; i < peopleTotal; i++) {
      new Thread(new ToiletRunnable("第" + (i + 1) + "号", semaphore)).start();

    }
  }

  static class ToiletRunnable implements Runnable {

    /**
     * 名称 who
     */
    public String name;
    /**
     * 总厕所数
     */
    private final Semaphore toiletCount;

    public ToiletRunnable(String name, Semaphore semaphore) {
      this.name = name;
      toiletCount = semaphore;
    }

    @Override
    public void run() {
      //获取可用的厕所量
      int availablePermits = toiletCount.availablePermits();
      if (availablePermits > 0) {
        System.out.println("厕所还有坑！");
      } else {
        System.out.println("[" + name + "]等位中。。。。");
      }

      try {
        //请求资源
        toiletCount.acquire();
        System.out.println("[" + name + "]上厕所中。。。。");
        //随机一个数 代表上厕耗时
        Thread.sleep((long) (Math.random() * 10000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("[" + name + "]已上完厕所。");
        //释放资源
        toiletCount.release();
      }
    }
  }
}
