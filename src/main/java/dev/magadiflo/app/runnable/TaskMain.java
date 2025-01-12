package dev.magadiflo.app.runnable;

public class TaskMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(new TaskRunnable("Spring"), "Spring");
        t1.start();

        Thread t2 = new Thread(new TaskRunnable("Docker"), "Docker");
        t2.start();

        Thread t3 = new Thread(new TaskRunnable("Angulr"), "Angulr");
        t3.start();
    }
}
