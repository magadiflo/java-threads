package dev.magadiflo.app.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        log.info("Inicia el hilo \"{}\"", this.getName());

        IntStream.range(0, 10)
                .forEach(index -> log.info("{}, {}", index, this.getName()));

        log.info("Finaliza el hilo \"{}\"", this.getName());
    }
}
