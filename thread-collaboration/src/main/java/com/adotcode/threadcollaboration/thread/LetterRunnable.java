package com.adotcode.threadcollaboration.thread;

/**
 * 输出字母线程
 *
 * @author risfeng
 * @date 2019/12/12
 */
public class LetterRunnable implements Runnable {

  private final Object lockObject;

  public LetterRunnable(Object lockObject) {
    this.lockObject = lockObject;
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread,
   * starting the thread causes the object's <code>run</code> method to be called in that separately
   * executing thread.
   *
   * <p>The general contract of the method <code>run</code> is that it may take any action
   * whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    char maxValue = 'Z';
    for (char i = 'A'; i <= maxValue; i++) {
      synchronized (lockObject) {
        System.out.println(Thread.currentThread().getName() + ": " + i);
        lockObject.notifyAll();
        try {
          lockObject.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
