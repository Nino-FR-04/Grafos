package impl_List;
import java.util.Iterator;
import Excepciones.ExceptionIsEmpty;
import Nodes.Node;

/**
 * Implementación de una lista enlazada basada en nodos.
 * Admite inserción al inicio y al final, así como búsqueda, eliminación, reversa y obtención
 * del valor máximo o mínimo según el de los elementos.
 *
 * @param E el tipo de elementos almacenados en la lista, que deben ser comparables.
 */
public class ListLinked <E extends Comparable<E>> implements TADList<E> {
    
    //Atributos
    protected Node<E> first; 
    protected Node<E> last;
    private int size;


    //Constructor
    public ListLinked(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    //---------Metodos
    /**
     * Verifica si la lista está vacía.
     * Si la referencia al primer nodo es nula, significa que no hay elementos en la lista.
     *
     * @return true si la lista no tiene ningún elemento, false en caso contrario.
     */
    @Override
    public boolean isEmptyList(){
        return this.first == null;
    }

    /**
     * Retorna la cantidad de elementos almacenados en la lista.
     *
     * @return el tamaño actual de la lista.
     */
    @Override
    public int length(){
        /*
        int size = 0;
        Node<E> nodoActual = this.first;
        
        while(nodoActual != null){
            nodoActual = nodoActual.next;
            size++;
        }

        return size;
        */
        return this.size;
    }

    /**
     * Elimina todos los elementos de la lista,
     * estableciendo la referencia al primer y ultimo nodo como nula.
     * El tamaño se establece en 0 - Size == 0
     */
    @Override
    public void destroyList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Busca un elemento en la lista y devuelve su posición.
     *
     * @param data el elemento que se desea buscar.
     * @return el índice donde se encuentra el elemento, o -1 si no se encuentra en la lista.
     */
    @Override
    public int search(E data) {
        if (this.isEmptyList()) return -1;
    
        int index = 0;
        Node<E> actual = this.first;
    
        while (actual != null) {
            if (actual.getData().equals(data)) {
                return index;
            }
            actual = actual.getNext();
            index++;
        }
    
        return -1; // Si no se encuentra el elemento
    }

    /**
     * Inserta un nuevo elemento al inicio de la lista.
     * Si la lista está vacía, el nuevo nodo se convierte en el primer nodo.
     * Si no, el nuevo nodo apunta al primer nodo actual, y luego se actualiza la referencia
     * para que 'first' apunte al nuevo nodo.
     *
     * @param data el elemento a insertar al inicio de la lista.
     */
    @Override
    public void insertFirst(E data){

        Node<E> newNode = new Node<E>(data);

        if(this.isEmptyList()){
            this.first = newNode;
            this.last = newNode;
        }else{
            newNode.setNext(this.first);
            this.first = newNode;
        }

        this.size++;
    }

    /**
     * Inserta un nuevo elemento al final de la lista.
     * Si la lista está vacía, el nuevo nodo se convierte en el primer nodo.
     * De lo contrario, se recorre la lista hasta el último nodo y se enlaza el nuevo nodo
     * como siguiente, convirtiéndolo en el nuevo último nodo.
     *
     * @param data el elemento a insertar al final de la lista.
     */
    @Override
    public void insertLast(E data){
        
        Node<E> newNode = new Node<>(data);

        if(this.isEmptyList()) {
            this.first = newNode;
            this.last = newNode;
        }else{
            this.last.setNext(newNode);
            this.last = newNode;
        }

        this.size++;
        /*
        if(this.isEmptyList()){
            this.first = nodo;
        }else{
            Node<E> actual = this.first;

            while(actual.next != null){
                actual = actual.next;
            }

            actual.next = nodo;
        }
        */
    }

    /**
     * Elimina el primer nodo que contiene el elemento especificado.
     * Si el elemento no existe o la lista está vacía, retorna false.
     * Si el elemento es encontrado y eliminado, reorganiza los enlaces
     * para mantener la continuidad de la lista.
     *
     * @param data el elemento a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean remove(E data) {
        
        // Caso especial: lista vacía
        if (this.isEmptyList()) return false;

        // Caso el nodo a eliminar es el primero
        if (this.first.getData().equals(data)) {
            this.first = this.first.getNext();
            this.size--;
            
            if (this.first == null) this.last = null;
            return true;
        }

        //Buscar el nodo a remover
        Node<E> currentNode = this.first; //Nodo Actual

        while(currentNode.getNext() != null && currentNode.getNext() != this.last) {
            
            if(currentNode.getNext().getData().equals(data)) {
                currentNode.setNext(currentNode.getNext().getNext());
                this.size--;
                return true;
            }

            currentNode = currentNode.getNext();
        }
        
        //Ultimo caso - El nodo last es el que se quiere remover
        if(this.last.getData().equals(data)) {
            currentNode.setNext(null);
            this.last = currentNode;
            this.size--;
            return true;
        }

        return false;
    }

    /**
     * Verifica si un elemento específico existe en la lista.
     *
     * @param obj el elemento a buscar en la lista.
     * @return true si el elemento está presente en la lista; false en caso contrario.
     */
    @Override
    public boolean contains(E data) {
        /* ----Otra implementación
        Node actual = this.first;

        while (actual != null) {
            if (actual.data.compareTo(obj) == 0) {
                return true;
            }
            actual = actual.next;
        }

        return false;
        */

        return (this.search(data) != -1);
    }

    /**
     * Retorna el elemento máximo de la lista, según el orden natural definido por {@code compareTo}.
     *
     * @return el elemento de mayor valor en la lista, o {@code null} si la lista está vacía.
     */
    @Override
    public E getMax() throws ExceptionIsEmpty {

        if(this.isEmptyList()) throw new ExceptionIsEmpty("Lista Vacia") ;

        Node<E> current = this.first;
        E max = this.first.getData();

        while(current != null) {
            
            if(current.getData().compareTo(max) > 0) {
                max = current.getData();
            }

            current = current.getNext();
        }

        return max;
    }

    /**
     * Obtiene el valor mínimo de la lista según el orden.
     *
     * @return el valor más bajo encontrado en la lista.
     * @throws ExceptionIsEmpty si la lista está vacía.
     */
    @Override
    public E getMin() {
        
        if(this.isEmptyList()) throw new ExceptionIsEmpty("Lista Vacia") ;

        Node<E> current = this.first;
        E min = this.first.getData();

        while(current != null) {
            
            if(current.getData().compareTo(min) < 0) {
                min = current.getData();
            }

            current = current.getNext();
        }

        return min;
    }

    /**
     * Invierte el orden de los elementos en la lista.
     * Inserta cada nodo al inicio de una nueva lista auxiliar
     */
    @Override
    public void reverse() {
        if (this.isEmptyList()) return;

        ListLinked<E> auxList = new ListLinked<>();
        Node<E> current = this.first;

        while(current != null) {
            
            auxList.insertFirst(current.getData());
            current = current.getNext();
        
        }

        this.first = auxList.first;
        this.last = auxList.last;
    }
    
    /**
     * Recupera el elemento almacenado en el primer nodo.
     * @return el valor almacenado {@code E} en el primer nodo.
     * @throws ExceptionIsEmpty si la lista esta vacia.
     */
    @Override
    public E getFirst() throws ExceptionIsEmpty {
        if(this.isEmptyList()) {
            throw new ExceptionIsEmpty("Lista vacia");
        }

        return this.first.getData();
    }

    /**
     * Recupera el elemento almacenado en el ultimo nodo nodo.
     * @return el valor almacenado {@code E} en el ultimo nodo.
     * @throws ExceptionIsEmpty si la lista esta vacia.
     */
    @Override
    public E getLast() throws ExceptionIsEmpty {
        if(this.isEmptyList()) {
            throw new ExceptionIsEmpty("Lista vacia");
        }

        return this.last.getData();
    }

    //-> ToString
    @Override
    public String toString() {
        if (this.isEmptyList()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> current = this.first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Retorna un iterador para recorrer esta lista usando un bucle for-each.
     *
     * Este método es requerido por la interfaz Iterable, lo cual permite
     * recorrer los elementos de la lista.
     *
     * @return un objeto Iterator que recorre los elementos de la lista.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteradorList();
    }

    // Clase interna que actúa como iterador
    private class IteradorList implements Iterator<E> {

        private Node<E> actual;

        //Constructor
        public IteradorList() {this.actual = first;}


        /**
         * Indica si hay un siguiente elemento en la lista.
         *
         * @return true si hay otro nodo por recorrer, false si se llegó al final.
         */
        @Override
        public boolean hasNext() {
            return actual != null;
        }

        /**
         * Retorna el siguiente elemento de la lista y avanza al siguiente nodo.
         *
         * @return El dato almacenado en el nodo actual.
         */
        @Override
        public E next() {
            E dato = actual.getData();
            actual = actual.getNext();
            return dato;
        }
    }
}
