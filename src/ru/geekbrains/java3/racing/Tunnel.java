package ru.geekbrains.java3.racing;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
  private static Semaphore sm = new Semaphore(MainClass.CARS_COUNT / 2);

  public Tunnel() {
    this.length = 80;
    this.description = "Тоннель " + length + " метров";
  }

  @Override
  public void go(Car c) {
    try {
      try {
        System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
        sm.acquire();
        System.out.println(c.getName() + " въехал в: " + description + "в тоннеле> ");
        Thread.sleep(length / c.getSpeed() * 1000);
        super.givMeTicket();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        sm.release();
        System.out.println(c.getName() + " выехал из: " + description);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
