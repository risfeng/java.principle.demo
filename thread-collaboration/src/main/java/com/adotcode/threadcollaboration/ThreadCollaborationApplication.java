package com.adotcode.threadcollaboration;

import com.adotcode.threadcollaboration.thread.LetterRunnable;
import com.adotcode.threadcollaboration.thread.NumberRunnable;

/**
 * @author risfeng
 */
public class ThreadCollaborationApplication {

  private static Object object = new Object();

  public static void main(String[] args) {
    NumberRunnable numberRunnable = new NumberRunnable(object);
    LetterRunnable letterRunnable = new LetterRunnable(object);
    new Thread(numberRunnable).start();
    new Thread(letterRunnable).start();
  }
}
