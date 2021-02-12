package ru.geekbrains.java3.racing;

import java.util.concurrent.*;

public class Car implements Runnable {
  private static int CARS_COUNT;

  static {
    CARS_COUNT = 0;
  }

  private Race race;
  private int speed;
  private String name;
  public static CyclicBarrier cb;

  public String getName() {
    return name;
  }

  public int getSpeed() {
    return speed;
  }

  public Car(Race race, int speed) {
    this.race = race;
    this.speed = speed;
    CARS_COUNT++;
    this.name = "Участник #" + CARS_COUNT;
  }

  @Override
  public void run() {
    try {
      System.out.println(this.name + " готовится.");
      Thread.sleep(500 + (int) (Math.random() * 800));
      cb.await();
      System.out.println(this.name + " готов ");
      MainClass.cdlStart.countDown(); // Счетчик готовности-1
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      cb.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < race.getStages().size(); i++) {
      race.getStages().get(i).go(this);
    }
  }

}
