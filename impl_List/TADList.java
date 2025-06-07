package impl_List;

import Excepciones.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones básicas para una lista genérica.
 * Proporciona métodos para verificar si la lista está vacía, obtener su tamaño,
 * destruir la lista, buscar, insertar, eliminar, entre otras.
 *
 * @param E el tipo de elementos que almacena la lista, que debe ser comparable.
 */
public interface TADList <E> {
    public boolean isEmptyList();
    public int length();
    public void destroyList();
    public int search(E data);
    public void insertFirst(E data);
    public void insertLast(E data);
    public boolean remove(E data) throws ExceptionIsEmpty;
    public void reverse() throws ExceptionIsEmpty; 
    public boolean contains(E data) throws ExceptionIsEmpty;
    public E getMax() throws ExceptionIsEmpty;
    public E getMin() throws ExceptionIsEmpty;
}
