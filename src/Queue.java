import java.util.*;
import java.util.function.Predicate;

public class Queue<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T item) {
        list.add(item);
    }

    public T dequeue() {
        List<T> items = list.toList();
        if (items.isEmpty()) return null;
        T first = items.get(0);
        list.remove(x -> x.equals(first));
        return first;
    }

    public List<T> toList() {
        return list.toList();
    }

    public boolean isEmpty() {
        return list.toList().isEmpty();
    }

    public void removeIf(Predicate<T> predicate) {
        List<T> items = list.toList();
        for (T item : items) {
            if (predicate.test(item)) {
                list.remove(x -> x.equals(item));
            }
        }
    }
}