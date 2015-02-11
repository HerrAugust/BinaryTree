package org.altervista.geheimerschatz.Queue_Stack;

import java.util.LinkedList;

/**
 * A trivial implementation of the simple queue interface, built as
 * a wrapper around the LinkedList class from java.util.
 */
public class Queue<E> {
    private LinkedList<E> data = new LinkedList<E>();
    
    public void enqueue(E item) {data.addLast(item);}
    public Object dequeue() {return data.removeFirst();}
    public Object peek() {return data.getFirst();}
    public int size() {return data.size();}
    public boolean isEmpty() {return data.isEmpty();}
    public String toString() {
    	int i = 1;
    	String str = "";
    	for(E cur = data.getFirst(); cur != null; cur = data.get(i), i++)
    		str += cur.toString();
    	return str;
    }
    
}