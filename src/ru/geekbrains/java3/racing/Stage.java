package ru.geekbrains.java3.racing;

public abstract class Stage {
  protected int length;
  protected String description;
  public String getDescription() {
    return description;
  }
  public void givMeTicket() {
    MainClass.cdlFinish.countDown();
  }
  public abstract void go(Car c);
}

