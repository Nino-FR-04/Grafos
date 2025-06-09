package impl_Stack;

import Excepciones.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones básicas de una pila genérica.
 * 
 * @param E el tipo de elementos que almacena la pila.
 */

public interface StackTAD <E> {
    public void push(E obj);
    public E pop() throws ExceptionIsEmpty;
    public E top() throws ExceptionIsEmpty;
    public void destroyStack() throws ExceptionIsEmpty;
    public boolean isEmpty();
}

/**
 * El metodo isFull() solo es aplicable en un Stack basado en un arreglo
 * estatico.
 */
