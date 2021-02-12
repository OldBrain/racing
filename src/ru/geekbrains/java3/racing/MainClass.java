package ru.geekbrains.java3.racing;

import java.util.concurrent.*;

public class MainClass {
  public static final int CARS_COUNT = 4;
  public static final int ROAD_COUNT = 3;
  public static final CountDownLatch cdlStart = new CountDownLatch(CARS_COUNT);
  public static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT * ROAD_COUNT);

  public static void main(String[] args) {
    ExecutorService ex = Executors.newFixedThreadPool(CARS_COUNT);
    Car.cb = new CyclicBarrier(4);
    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
    Race race = new Race(new Road(60), new Tunnel(), new Road(40));
    Car[] cars = new Car[CARS_COUNT];

    for (int i = 0; i < cars.length; i++) {
      cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
    }

    for (int i = 0; i < cars.length; i++) {
      ex.execute(cars[i]);
    }
    ex.shutdown();

    try {
      cdlStart.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

    try {
      cdlFinish.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
  }
}
