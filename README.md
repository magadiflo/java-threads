# Threads: Hilos y Concurrencia

---

## ¿Qué son los hilos?

- Objetos que dan la capacidad de hacer más de una tarea al mismo tiempo.
- Los `hilos` (o `threads`) en Java son una herramienta fundamental para ejecutar múltiples tareas de manera concurrente
  dentro de un programa.
- Un `hilo` es la unidad más pequeña de ejecución dentro de un programa. Los programas que usan hilos pueden realizar
  múltiples tareas al mismo tiempo, aprovechando mejor los recursos del sistema, especialmente en procesadores
  multinúcleo.

En Java, los hilos son representados por la clase `Thread` y se pueden crear de dos formas principales:

1. Extendiendo la `clase Thread`.
2. Implementando la `interfaz Runnable`.

## Características

- La Máquina Virtual de Java (JVM) es un sistema `multi-thread` capaz de ejecutar varias tareas (o subprocesos)
  simultáneamente.
- Java soporta Thread con algunas clases e interfaces y con métodos específicos en la clase object.
- La JVM gestiona los detalles, asignación de tiempos de ejecución, prioridades, de forma similar a cómo gestiona un
  Sistema Operativo.
- `Ejecución concurrente`, permite que diferentes partes del programa se ejecuten simultáneamente.
- `Comparación de recursos`, los hilos de un mismo proceso comparten memoria y recursos del sistema.
- `Ligereza`, comparados con los procesos, los hilos son más ligeros y rápidos.
- `Control`, java ofrece métodos para controlar los hilos, como `start()`, `sleep()`, `join()`, etc.

## Ciclo de vida de un Thread

Un hilo en java pasa por varios estados durante su vida.

### 1. NEW

Un hilo `NEW` es uno que se ha creado pero que aún no se ha iniciado con el método `start()`.

````java
public class Main {
    public static void main(String[] args) {
        Runnable runnable = new Tarea();
        Thread t = new Thread(runnable);
        System.out.println(t.getState()); //NEW
    }
}
````

### 2. RUNNABLE

Un hilo `RUNNABLE` es uno que se ha creado e iniciado con el `start()`. El sistema operativo decide cuándo asignarle
tiempo de CPU.

````java
public class Main {
    public static void main(String[] args) {
        Runnable runnable = new Tarea();
        Thread t = new Thread(runnable);
        t.start();
        System.out.println(t.getState()); //RUNNABLE
    }
}
````

### 3. BLOCKED

- Un hilo está en estado `BLOCKED` cuando actualmente no es elegible para ejecutarse.
- Entra en este estado cuando está esperando un bloqueo del monitor e intenta acceder a una sección de código que está
  bloqueada por algún otro hilo en un método sincronizado.

````java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> recurso();

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(t2.getState()); //BLOCKED
    }

    public static synchronized void recurso() {
        //realizando algún proceso compartido entre hilos
        while (true) {
        }
    }
}
````

### 4. WAITING

- Un hilo está en estado `WAITING` cuando está esperando que otro hilo realice una acción en particular.
- Un hilo puede entrar en este estado llamando a cualquiera de los dos métodos `wait()` y `join()`.
- `join()`, se utiliza para hacer que un hilo espere a que otro hilo termine su ejecución antes de continuar. Es como
  decir, *"No sigas adelante hasta que el hilo en el que llamé `join()` haya terminado"*.

````java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = Thread.currentThread();
        Thread t2 = new Thread(() -> {
            //realizando alguna tarea costosa
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(t1.getState()); //WAITING
            }
        });
        t2.start();
        t2.join();
    }
}
````

### 5. TERMINATED

- Este es el estado de un hilo muerto. Está en el estado `TERMINATED` cuando ha finalizado la ejecución o se terminó de
  forma anormal.
- También podemos usar el método `isAlive()` para determinar si el hilo está vivo o no.

````java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            //realizando alguna tarea rápida
        });

        t1.start();
        Thread.sleep(1000);

        System.out.println(t1.getState()); //TERMINATED
        System.out.println(t1.isAlive()); //false
    }
}
````

## Métodos wait(), notify() y notifyAll()

- La clase Object tiene tres métodos que permiten que los hilos se sincronicen y comuniquen sobre el estado bloqueado de
  un recurso.
- `wait()`, libera el bloqueo para que otros hilos tengan la oportunidad de acceder a un recurso compartido (métodos
  sincronizado) y queda esperando indefinidamente hasta que otro hilo invoca `notify()` o `notifyAll()`.
- `notify()` y `notifyAll()` se usa para despertar los hilos que están esperando un acceso a un recurso compartido
  (monitor).

## Método sleep()

- `Thread.sleep()`, envía el hilo actual al estado `TIMED_WAITING` durante algún tiempo.
- Permanece dormido hasta que el tiempo expire o se llame al método `interrupt()`.

````java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        Thread.sleep(1000);
        System.out.println(t1.getState()); //TIMED_WAITING
    }
}
````
