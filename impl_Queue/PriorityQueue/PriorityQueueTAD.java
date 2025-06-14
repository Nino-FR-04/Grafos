package impl_Queue.PriorityQueue;

import Excepciones.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones básicas de una cola de prioridad genérica.
 *
 * @param E el tipo de elementos almacenados en la cola.
 * @param P el tipo de prioridad asociada a cada elemento.
 */
public interface PriorityQueueTAD<E, P> {

    /**
     * Inserta un elemento en la cola con una prioridad determinada.
     * 
     * @param data el elemento a insertar
     * @param pri la prioridad del elemento
     */
    public void enqueue(E data, P pri);

    /**
     * Elimina y retorna el elemento con mayor prioridad.
     * 
     * @return el elemento con mayor prioridad
     * @throws ExceptionIsEmpty si la cola está vacía
     */
    public E dequeue() throws ExceptionIsEmpty;

    /**
     * Elimina todos los elementos de la cola.
     */
    public void clear();

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty();

    /**
     * Retorna el elemento con mayor prioridad sin eliminarlo.
     * 
     * @return el elemento con mayor prioridad
     * @throws ExceptionIsEmpty si la cola está vacía
     */
    public E front() throws ExceptionIsEmpty;

    /**
     * Retorna el elemento con menor prioridad sin eliminarlo.
     * 
     * @return el elemento con menor prioridad
     * @throws ExceptionIsEmpty si la cola está vacía
     */
    public E back() throws ExceptionIsEmpty;

    /**
     * Actualiza la prioridad de un elemento en la Cola.
     * 
     * @param data elemento en la cola al cual se le quiere actualizar su prioridad.
     * @param pri nuevo valor de la prioridad del elemento.
     * 
     * @return {@code true} si el elemento fue actualizado o {@code false}
     * si no lo encontro o tiene la misma prioridad.
     */
    public boolean updatePriority(E data, P pri);
}
