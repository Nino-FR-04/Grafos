package impl_Stack;

import Excepciones.ExceptionIsEmpty;
import Nodes.Node;

/**
 * Implementación de una pila (Stack) usando una estructura de lista enlazada.
 * Los elementos se apilan/desapilan desde el tope.
 *
 * @param E Tipo de elementos almacenados en la pila.
 */
public class StackLink <E> implements StackTAD <E> {

    //Atributos
    private Node<E> tope;
    private int size;

    //Constructor
    public StackLink() {
        this.tope = null;
        this.size = 0;
    }

    // -> getter -> size
    /**
     * 
     * @return tamaño de la pila
     * 
     */
    public int getSize() {return this.size;}

    //---------Implementacion de metodos de la interfaz
    /**
     * Inserta un elemento en el tope de la pila.
     * 
     * @param obj Elemento a insertar.
     */
    @Override
    public void push(E obj) {
        
        Node<E> nodo = new Node<>(obj);
        nodo.setNext(this.tope);
        this.tope = nodo;
        this.size++;
    }

    /**
     * Elimina y retorna el elemento en el tope de la pila.
     * 
     * @return Elemento en el tope.
     * @throws ExceptionIsEmpty Si la pila está vacía.
     */
    @Override
    public E pop() throws ExceptionIsEmpty {
        
        if(this.isEmpty())
            throw new ExceptionIsEmpty("La pila esta vacia");
        
        E data = this.tope.getData();
        this.tope = this.tope.getNext();
        this.size--;
        return data;

    }

    /**
     * Retorna el elemento en el tope sin eliminarlo.
     * 
     * @return Elemento en el tope.
     * @throws ExceptionIsEmpty Si la pila está vacía.
     */
    @Override
    public E top() throws ExceptionIsEmpty {
        
        if(this.isEmpty())
            throw new ExceptionIsEmpty("La pila esta vacia");
        
        return this.tope.getData();
    }

    /**
     * Elimina todos los elementos de la pila.
     */
    @Override
    public void destroyStack() throws ExceptionIsEmpty {
        this.tope = null;
        this.size = 0;
    }

    /**
     * Verifica si la pila está vacía.
     * 
     * @return true si está vacía, false en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return this.tope == null;
    }
    
    /**
     * Devuelve una representación en texto de los elementos de la pila.
     * El primer elemento mostrado es el tope.
     * 
     * @return Cadena con los elementos de la pila.
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        Node<E> actual = this.tope;

        while (actual.getNext() != null) {
            sb.append(actual.getData() + ", ");
            actual = actual.getNext();
        }

        sb.append(actual.getData() + "]");
        return sb.toString();
    }

}
