package com.mijecu25.meme.utils.gc;

public final class GC {

  private GC() {}

  public static void gc(int waitTime) throws InterruptedException {
    System.gc();
    Thread.sleep(waitTime);
  }
}
