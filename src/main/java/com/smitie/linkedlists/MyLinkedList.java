package com.smitie.linkedlists;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class MyLinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    protected transient int modCount = 0;

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(e, first, null);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, null, last);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;

        size++;
        modCount++;
    }

    private void linkBefore(E e, Node<E> succ) {
        final Node<E> currentPrev = succ.prev;
        final Node<E> newNode = new Node<>(e, succ, currentPrev);
        succ.prev = newNode;
        if (currentPrev == null)
            first = newNode;
        else
            currentPrev.next = newNode;
        size++;
        modCount++;
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.value;
        Node<E> next = f.next;
        f.value = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        E element = l.value;
        Node<E> prev = l.prev;
        l.value = null;
        l.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;

        size--;
        modCount++;
        return element;
    }

    private E unlink(Node<E> n) {
        Node<E> pred = n.prev;
        Node<E> nexd = n.next;

        E element = n.value;

        if (pred == null) {
            first = nexd;
        } else {
            pred.next = nexd;
            n.prev = null;
        }

        if (nexd == null) {
            last = pred;
        } else {
            nexd.prev = pred;
            n.next = null;
        }

        n.value = null;
        size--;
        modCount++;
        return element;
    }

    public E getFirst() {
        Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();

        return f.value;
    }

    public E getLast() {
        Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();

        return l.value;
    }

    public E removeFirst() {
        Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();

        return unlinkFirst(f);
    }

    public E removeLast() {
        Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }


    public void addFirst(E e) {
        linkFirst(e);
    }

    public void addLast(E e) {
        linkLast(e);
    }

    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.value == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.value)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;

        if (numNew == 0)
            return false;

        Node<E> pred, succ;

        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(e, succ, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = null;
        }

        size += numNew;
        modCount++;
        return true;
    }

    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.value = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).value;
    }

    public E set(int index, E e) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldValue = x.value;
        x.value = e;
        return oldValue;
    }

    public void add(int index, E e) {
        checkElementIndex(index);

        if (index == size) {
            linkLast(e);
        } else {
            linkBefore(e, node(index));
        }
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.value == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.value))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException();
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException();
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    class ListItr implements MyListIterator<E> {

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;

            return lastReturned.value;

        }

        @Override
        public E previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.value;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastNext);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }

            lastReturned = null;
            expectedModCount++;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();

            checkForComodification();
            lastReturned.value = e;
        }

        @Override
        public void add(E e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.value);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class DescendingIterator implements MyIterator<E> {
        private final ListItr itr = new ListItr(size());

        public boolean hasNext() {
            return itr.hasPrevious();
        }

        public E next() {
            return itr.previous();
        }

        public void remove() {
            itr.remove();
        }
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
