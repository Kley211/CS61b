package deque;
import java.util.Iterator;

import afu.org.checkerframework.checker.oigj.qual.O;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    private TNode sentinel;
    private int size;


    private class TNode
     {
         private T item;
         private TNode next;
         private TNode pre;

     }

    public LinkedListDeque() {
        sentinel = new TNode(); // 初始化sentinel
        sentinel.next = sentinel; // 循环链表，指向自己
        sentinel.pre = sentinel;
        sentinel.item = null;
        size = 0;
    }


    @Override
    public void addFirst(T item) {
        TNode t  = new TNode();
        t.item = item;
        t.next = sentinel.next;
        t.pre = sentinel;
        t.next.pre = t;
        sentinel.next = t;
        size++;
    }

    @Override
    public void addLast(T item)
    {
        TNode t = new TNode();
        t.item = item;
        t.next = sentinel;
        sentinel.pre.next = t;
        t.pre = sentinel.pre;
        sentinel.pre = t;
        size++;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void printDeque()
    {
        if(isEmpty())
        {
            System.out.println();
        }
        TNode t = sentinel;
        for(int i = 0; i < size;i++)
        {
            T x = t.next.item;
            System.out.print(x);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        TNode t = sentinel.next;
        T x = t.item;
        t.next.pre = sentinel;
        sentinel.next = t.next;
        size--;
        return x;
    }

    @Override
    public T removeLast()
    {
        if(isEmpty())
        {
            return null;
        }
        TNode t = sentinel.pre;
        T x = t.item;
        t.pre.next = sentinel;
        sentinel.pre = t.pre;
        size--;
        return x;
    }

    @Override
    public T get(int index)
    {
        if(index + 1 > size)
        {
            return null;
        }

        TNode t = sentinel;
        for(int i = 0; i < index;i++)
        {
            t = t.next;
        }
        return t.next.item;
    }

    @Override
    public boolean equals(Object o)
    {

        if (o == this) {
            return true;
        }


        if(!(o instanceof Deque) || ((Deque<?>)o).size() != this.size())
        {
            return false;
        }

        for(int i =0; i < this.size();i++)
        {
            Object item = ((Deque<?>)o).get(i);
            if(!(this.get(i).equals(item)))
            {
                return false;
            }
        }
        return true;
    }


    public Iterator<T> iterator()
    {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements  Iterator<T>
    {
        private TNode node;

        LinkedListIterator()
        {
            this.node = sentinel.next;
        }

        public boolean hasNext()
        {
            return this.node != sentinel;
        }

        public T next()
        {
            T item = this.node.item;
            this.node = this.node.next;
            return item;
        }
    }
}
