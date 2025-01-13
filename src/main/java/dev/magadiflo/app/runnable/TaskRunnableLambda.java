package dev.magadiflo.app.runnable;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class TaskRunnableLambda {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        Thread t1 = new Thread(runnable(), "Spring");
        t1.start();

        Thread t2 = new Thread(runnable(), "Docker");
        t2.start();

        Thread t3 = new Thread(runnable(), "Angulr");
        t3.start();

        t1.join(); //El hilo principal espera a que t1 termine
        t2.join(); //El hilo principal espera a que t2 termine
        t3.join(); //El hilo principal espera a que t3 termine

        log.info("Continúa con la ejecución del método main: " + main.getName());
    }

    private static Runnable runnable() {
        return () -> {
            log.info("Inicia el hilo \"{}\"", Thread.currentThread().getName());

            IntStream.range(0, 10)
                    .forEach(index -> {
                        log.info("{}, {}", index, Thread.currentThread().getName());
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

            log.info("Finaliza el hilo \"{}\"", Thread.currentThread().getName());
        };
    }
}
