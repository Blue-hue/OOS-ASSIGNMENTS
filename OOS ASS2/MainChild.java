class Child implements Runnable{
    public void run(){
        System.out.println("Within child thread");
        System.out.println("Exiting from child thread");
    }
}

public class MainChild {
    public static void main(String[] args) {
        Thread t = new Thread(new Child());
        t.start();
        System.out.println("Within main thread");
        System.out.println("Exiting from main thread");
    }
}
