package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] t;
    private int size;    // 数组容量
    private int length;  // 元素个数
    private int first;
    private int last;

    public ArrayDeque() {
        this.size = 8;
        this.t = (T[]) new Object[size];
        this.length = 0;
        this.first = 0;
        this.last = 0;
    }

    private void reSize(int s) {
        T[] temp = (T[]) new Object[s];
        for (int i = 0; i < length; i++) {
            temp[i] = this.t[(first + i) % size];
        }
        this.t = temp;
        this.size = s;
        this.first = 0;
        this.last = (length == 0) ? 0 : length - 1;
    }


    @Override
    public void addFirst(T item) {
        if (length == size) {
            reSize(size * 2);
        }
        if (!isEmpty()) {
            this.first = (this.first - 1 + size) % size;
        }
        this.t[this.first] = item;
        this.length++;
        if (length == 1) {
            this.last = this.first;
        }
    }

    @Override
    public void addLast(T item) {
        if (length == size) {
            reSize(size * 2);
        }
        if (!isEmpty()) {
            this.last = (this.last + 1) % size;
        }
        this.t[this.last] = item;
        this.length++;
        if (length == 1) {
            this.first = this.last;
        }
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public void printDeque() {
        int i = this.first;
        for (int cnt = 0; cnt < length; cnt++) {
            System.out.print(t[i] + " ");
            i = (i + 1) % size;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = t[first];
        t[first] = null; // 防止 loitering
        if (length == 1) {
            first = last = 0;
        } else {
            first = (first + 1) % size;
        }
        length--;
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = t[last];
        t[last] = null; // 防止 loitering
        if (length == 1) {
            first = last = 0;
        } else {
            last = (last - 1 + size) % size;
        }
        length--;
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        int pos = (first + index) % size;
        return t[pos];
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (other.size() != this.length) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            Object item = other.get(i);
            if (!this.get(i).equals(item)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new Iterable();
    }

    private class Iterable implements Iterator<T> {
        private int pos;

        Iterable() {
            this.pos = 0;
        }

        public boolean hasNext() {
            return this.pos < length;
        }

        public T next() {
            T x = get(this.pos);
            this.pos++;
            return x;
        }
    }
}