import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Queue {
    Queue(int size) {
        list = new Integer[size];
        this.size = size;
    }

    void put(Integer elem) {
        if (index != size) {
            list[index++] = elem;
        }
    }

    Integer get() {
        if (index != 0) {
            return list[--index];
        }
        return null;
    }

    Integer peek() {
        return list[index];
    }

    boolean isFull() {
        return index == size;
    }

    boolean isEmpty() {
        return index == 0;
    }

    boolean condition = true;
    private int size;
    private int index = 0;
    private Integer[] list;
}

class Producer extends Thread {
    Producer(Queue store, File input) {
        this.store = store;
        try {
            this.scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.err.println("Given File not found.");
        }
    }

    @Override
    public void run() {
        synchronized (store) {
            while (store.condition) {
                while (!store.isFull()) {
                    if (!scanner.hasNextInt()) {
                        store.condition = false;
                        break;
                    }
                    store.put(scanner.nextInt());
                }
                store.notify();
                try {
                    store.wait();
                } catch (InterruptedException e) {

                }
            }
            store.notify();
        }
    }

    private Scanner scanner;
    private Queue store;
}

class Consumer extends Thread {
    Consumer(Queue store) {
        this.store = store;
    }

    @Override
    public void run() {
        synchronized (store) {
            while (store.condition) {
                while (!store.isEmpty()) {
                    result += store.get();
                }
                store.notify();
                try {
                    store.wait();
                } catch (InterruptedException e) {

                }
            }
            while (!store.isEmpty()) {
                result += store.get();
            }
            store.notify();
        }
    }

    int getResult() {
        return result;
    }

    private int result;
    private Queue store;
}

public class q4 {
    public static void main(String[] args) {
        Queue store = new Queue(5);

        File readFile = new File("input.txt");

        Producer producer = new Producer(store, readFile);
        Consumer consumer = new Consumer(store);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {

        }

        System.out.println("Answer : " + consumer.getResult());
    }
}