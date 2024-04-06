import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class TBS extends Thread {
    private int start;
    private int end;
    private List<Integer> list;
    private int key;
    private int index;

    TBS(List<Integer> list, int start, int end, int key, String name) {
        super(name);
        this.start = start;
        this.end = end;
        this.list = list;
        this.key = key;
    }

    @Override
    public void run() {
        index = Arrays.binarySearch(list.toArray(new Integer[0]), start, end, key);
    }

    int getIndex() {
        return index >= 0 ? index : -1;
    }
}

public class q6 {
    static private List<Integer> list;
    static private Random random = new Random();

    public static void main(String[] args) {
        list = random
                .ints(0, 50)
                .limit(1000)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        int key = 41;

        List<TBS> tGroup = new ArrayList<>();

        for (int i = 0, len = list.size() / 10; i < 10; i++) {
            tGroup.add(
                    new TBS(list, i * len,
                            Math.min(i * len + len, list.size()),
                            key, String.valueOf(i)));
        }

        tGroup.stream().forEach((thread) -> thread.start());

        tGroup.stream().forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        });

        System.out.println(list);
        tGroup.stream()
                .dropWhile((thread) -> thread.getIndex() < 0)
                .findFirst()
                .ifPresentOrElse(
                        (thread) -> System.out.println("Key at index: " + thread.getIndex()
                                + " Thread index: " + thread.getName()),
                        () -> System.out.println("Key not present"));
    }
}