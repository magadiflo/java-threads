package dev.magadiflo.app.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Thread t1 = new MyThread("uns");
        t1.start();

        Thread t2 = new MyThread("ucv");
        t2.start();

        log.info(String.valueOf(t1.getState()));
        log.info(String.valueOf(t2.getState()));
    }
}
