package com.adotcode.caslockdemo.lock;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

/**
 * Adc锁
 *
 * @author risfeng
 * @date 2019/12/06
 */
public class ADotCodeLock {

  private volatile int state = 0;
  private static final Unsafe unsafe = UnsafeUtils.getUnsafe();
  private static long valueOffset;
  private AtomicInteger atomicInteger = new AtomicInteger(0);

  static {
    try {
      valueOffset = unsafe.objectFieldOffset(ADotCodeLock.class.getDeclaredField("state"));
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  public void lock() throws NoSuchFieldException {
    System.out.println("原始值：state=" + state + " valueOffset=" + valueOffset);

    System.out.println(unsafe.compareAndSwapInt(this, 24, 0, 1));
    System.out.println("修改后的值：state=" + state + " valueOffset=" + valueOffset);

    System.out.println(unsafe.compareAndSwapInt(this, 48, 1, 2));
    valueOffset = unsafe.objectFieldOffset(ADotCodeLock.class.getDeclaredField("state"));

    System.out.println("再修改后的值：state=" + state + " valueOffset=" + valueOffset);

  }

  private void atomicTest() {
    System.out.println("原始值：atomicInteger=" + atomicInteger.get());

    System.out.println(atomicInteger.compareAndSet(1, 2));
    System.out.println("没有更新成功的值：atomicInteger=" + atomicInteger.get());

    System.out.println(atomicInteger.compareAndSet(0, 2));
    System.out.println("更新成功的值：atomicInteger=" + atomicInteger.get());
  }

  public static void main(String[] args) {
    ADotCodeLock aDotCodeLock = new ADotCodeLock();
    try {
      aDotCodeLock.atomicTest();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
