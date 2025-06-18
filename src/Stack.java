import java.util.*;

public class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        List<T> items = list.toList();
        if (items.isEmpty()) return null;
        T last = items.get(items.size() - 1);
        list.remove(x -> x.equals(last));
        return last;
    }

    public T peek() {
        List<T> items = list.toList();
        if (items.isEmpty()) return null;
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() {
        return list.toList().isEmpty();
    }
}