package dev.magadiflo.app.runnable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
@Getter
@RequiredArgsConstructor
public class TaskRunnable implements Runnable {

    private final String name;

    @Override
    public void run() {
        log.info("Inicia el hilo \"{}\"", this.name);

        IntStream.range(0, 10)
                .forEach(index -> {
                    log.info("{}, {}", index, this.name);
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        log.info("Finaliza el hilo \"{}\"", this.name);
    }
}
