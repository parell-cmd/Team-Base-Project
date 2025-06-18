import java.util.*;
import java.util.function.Predicate;

public class LinkedList<T> {
    private class Node {
        T data;
        Node next;
        Node(T data) { this.data = data; }
    }

    private Node head;

    public void add(T data) {
        Node node = new Node(data);
        if (head == null) head = node;
        else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = node;
        }
    }

    public boolean remove(Predicate<T> predicate) {
        Node curr = head, prev = null;
        while (curr != null) {
            if (predicate.test(curr.data)) {
                if (prev == null) head = curr.next;
                else prev.next = curr.next;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public T find(Predicate<T> predicate) {
        Node curr = head;
        while (curr != null) {
            if (predicate.test(curr.data)) return curr.data;
            curr = curr.next;
        }
        return null;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr.data);
            curr = curr.next;
        }
        return list;
    }

    public void clear() {
        head = null;
    }
}