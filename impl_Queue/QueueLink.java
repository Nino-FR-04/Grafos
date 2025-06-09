package impl_Queue;

import Excepciones.ExceptionIsEmpty;
import Nodes.Node;

/**
 * Implementación de una cola genérica utilizando una lista enlazada.
 * @param E el tipo de elementos
 */

public class QueueLink <E> implements QueueTAD <E> {

    //Atributos
    private Node<E> first;
    private Node<E> last;

    //Constructor
    public QueueLink() {
        this.first = null;
        this.last = null;
    }

    //-----------Implementacion de los metodos de la Interfaz

    /**
     * Agrega un elemento al final de la cola.
     * Si la cola está vacía, el primer y el último nodo serán el mismo.
     * 
     * @param obj el dato a agregar a la cola.
     */
    @Override
    public void enqueue(E obj) {

        Node<E> nodeAux = new Node<> (obj);

        if(this.isEmpty()) {
            this.first = nodeAux;
            this.last = nodeAux;
        }else {
            this.last.setNext(nodeAux);
            this.last = nodeAux;
        }
    }
    
    /**
     * Elimina y retorna el primer elemento de la cola.
     * Si la cola está vacía, se lanza una excepción.
     * 
     * @return el primer elemento de la cola.
     * @throws ExceptionIsEmpty si la cola está vacía.
     */
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if(this.isEmpty())
            throw new ExceptionIsEmpty("Cola vacia");
        
        E data = this.first.getData();
        this.first = this.first.getNext();

        if(this.first == null) {
            this.last = null;
        }

        return data;
    }

    /**
     * Vacía la cola, eliminando todos sus elementos.
     * Después de este método, tanto first como last serán null.
     * 
     */
    @Override
    public void destroyQueue() {
        this.first = null;
        this.last = null;
    }

    /**
     * Verifica si la cola está vacía.
     * La cola está vacía si el puntero first es null.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * Retorna el primer elemento de la cola sin eliminarlo.
     * Si la cola está vacía, se lanza una excepción.
     * 
     * @return el primer elemento de la cola.
     * @throws ExceptionIsEmpty si la cola está vacía.
     */
    @Override
    public E front() throws ExceptionIsEmpty {
        if(this.isEmpty())
            throw new ExceptionIsEmpty("Cola vacia");
        
        return this.first.getData();
    }

    /**
     * Retorna el último elemento de la cola sin eliminarlo.
     * Si la cola está vacía, se lanza una excepción.
     * 
     * @return el último elemento de la cola.
     * @throws ExceptionIsEmpty si la cola está vacía.
     */
    @Override
    public E back() throws ExceptionIsEmpty {
        if(this.isEmpty())
            throw new ExceptionIsEmpty("Cola vacia");
        
        return this.last.getData();
    }


    //Representacion de la cola en forma de cadena de texto
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");

        Node<E> actual = this.first;

        while (actual != this.last) {
            sb.append(actual.getData() + ", ");
            actual = actual.getNext();
        }

        sb.append(actual.getData() + "]");
        return sb.toString();
    }
    
}
