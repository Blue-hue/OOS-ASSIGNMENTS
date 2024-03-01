class SharedResource {
    private int count = 1;

    synchronized void printOdd() {
        while (count % 2 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("printOdd interrupted");
            }
        }
        System.out.println(count++);
        notify();
    }

    public synchronized void printEven() {
        while (count % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("printEven interrupted");
            }
        }
        System.out.println(count++);
        notify();
    }
}

class OddThread extends Thread {
    private SharedResource sharedResource;

    public OddThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run() {
        for(int i=0;i<100;i++) {
            sharedResource.printOdd();
        }
    }
}

class EvenThread extends Thread {
    private SharedResource sharedResource;

    public EvenThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run() {
        for(int i=0;i<100;i++) {
            sharedResource.printEven();
        }
    }
}

public class SequencePrint {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        OddThread oddThread = new OddThread(sharedResource);
        EvenThread evenThread = new EvenThread(sharedResource);

        oddThread.start();
        evenThread.start();
    }
}
