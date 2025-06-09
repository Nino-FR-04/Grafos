package impl_List;

import Excepciones.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones básicas para una lista genérica - Dequeue.
 * Proporciona métodos para verificar si la lista está vacía, obtener su tamaño,
 * destruir la lista, buscar, insertar, eliminar, entre otras.
 *
 * @param E el tipo de elementos que almacena la lista, que debe ser comparable.
 */
public interface TADList <E> extends Iterable<E> {
    public boolean isEmptyList();
    public int length();
    public void destroyList();
    public int search(E data);
    public void insertFirst(E data);
    public void insertLast(E data);
    public boolean remove(E data);
    public void reverse(); 
    public boolean contains(E data);
    public E getMax() throws ExceptionIsEmpty;
    public E getMin() throws ExceptionIsEmpty;
    public E getFirst() throws ExceptionIsEmpty;
    public E getLast() throws ExceptionIsEmpty;
}
