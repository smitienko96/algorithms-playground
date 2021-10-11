package com.smitie.linkedlists;

public interface MyListIterator<E> extends MyIterator<E> {

    boolean hasNext();

    boolean hasPrevious();

    E next();

    E previous();

    int nextIndex();

    int previousIndex();

    void remove();

    void set(E e);

    void add(E E);

}
