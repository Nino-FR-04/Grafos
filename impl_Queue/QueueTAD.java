package impl_Queue;

import Excepciones.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones básicas de una cola genérica.
 * 
 * @param E el tipo de elementos que almacena la pila.
 */

public interface QueueTAD <E> {
    public void enqueue(E obj);
    public E dequeue() throws ExceptionIsEmpty;
    public void destroyQueue();
    public boolean isEmpty();
    public E front() throws ExceptionIsEmpty;
    public E back() throws ExceptionIsEmpty;
}

/**
 * El metodo isFull() solo es aplicable en una Cola basada en un arreglo
 * estatico.
 */
