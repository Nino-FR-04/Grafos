package impl_Queue.PriorityQueue;

import Excepciones.ExceptionIsEmpty;
import Nodes.EntryNode;
import Nodes.Node;


/**
 * Implementación de una cola de prioridad usando una lista enlazada ordenada.
 * Los elementos se insertan de acuerdo con su prioridad, manteniendo el orden FIFO para elementos con igual prioridad.
 *
 * @param E Tipo de dato almacenado.
 * @param P Tipo de prioridad.
 */
public class PriorityQueue <E,P extends Comparable<P>> implements PriorityQueueTAD<E,P> {

    //Atributos
    protected Node<EntryNode<E,P>> first;
    protected Node<EntryNode<E,P>> last;
    protected int size;

    //Constructor
    public PriorityQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * @return tamaño de la cola de prioridad
     */
    public int getSize() {return this.size;}

    //---->Insercion ordenada de manera ascendente
    @Override
    public void enqueue(E data, P pri) {
        
        Node<EntryNode<E,P>> newNode = new Node<>(new EntryNode<>(data, pri));

        //Caso 1: Cola Vacia
        if(this.isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            this.size++;
            return;
        }

        /*
        Caso 2: La prioridad de mi elemento es menor que la de mi ultimo elemento
        */
        if(pri.compareTo(this.last.getData().getPriority()) >= 0) {
            this.last.setNext(newNode);
            this.last = newNode;
            this.size++;
            return;
        }

        /*
        Caso 3: La prioridad de mi elemento es mayor que la de mi primer elemento
        */
        if(pri.compareTo(this.first.getData().getPriority()) < 0) {
            newNode.setNext(this.first);
            this.first = newNode;
            this.size++;
            return;
        }

        /*
        Caso 4: Inserción en una posición intermedia, manteniendo el orden por prioridad.
        Si hay varios elementos con la misma prioridad, se insertará después de ellos (respeta FIFO entre iguales).
        */
        Node<EntryNode<E,P>> currentNode = this.first;
        
        while(currentNode.getNext() != null && 
            pri.compareTo(currentNode.getNext().getData().getPriority()) >= 0) {

            currentNode = currentNode.getNext();
        }

        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        this.size++;
    }
    
    //-> Se desencola el primer elemento (mayor prioridad)
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if(this.isEmpty()) throw new ExceptionIsEmpty("La cola de prioridad esta vacía");

        E data = this.first.getData().getData();
        this.first = this.first.getNext();

        if(this.first == null) {this.last = null;}

        this.size--;
        return data;
    }

    //Se encarga de limpiar la cola y reiniciar la cantidad de elementos en 0
    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // -> Verificacion si la cola esta vacia mediante el atributo size
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //Retorna el primer elemento sin eliminarlo
    @Override
    public E front() throws ExceptionIsEmpty {
        if(this.isEmpty()) throw new ExceptionIsEmpty("La cola de prioridad esta vacía");
        return this.first.getData().getData();
    }

    //Retorna el ultimo elemento sin eliminarlo
    @Override
    public E back() throws ExceptionIsEmpty {
        if(this.isEmpty()) throw new ExceptionIsEmpty("La cola de prioridad esta vacía");
        return this.last.getData().getData();
    }

    //La actualizacion la realiza . . .
    @Override
    public boolean updatePriority(E data, P pri) {
        
        if(this.isEmpty()) return false;

        //----->Busqueda del valor
        Node<EntryNode<E,P>> currentNode = this.first;

        while(currentNode != null) {
            if(currentNode.getData().getData().equals(data)) {
                break;
            }
            currentNode = currentNode.getNext();
        }

        //El nodo no se encontro o la prioridad es la misma
        if(currentNode == null || 
            currentNode.getData().getPriority().compareTo(pri) == 0) 
            return false;

        //Actualiza la prioridad y se llama a un metodo auxiliar para actualizar la posicion
        currentNode.getData().setPriority(pri);
        this.updatePosition(currentNode);
        return true;
    }

    protected void updatePosition(Node<EntryNode<E,P>> node) {
        
        // Si el nodo es el único en la cola, no necesita ser movido
        if (this.first == this.last) return;

        // 1. Eliminar el nodo de su posición actual
        if (node == this.first) {
            this.first = this.first.getNext();
        } else {
            Node<EntryNode<E, P>> prev = this.first;
            while (prev.getNext() != node) {
                prev = prev.getNext();
            }
            prev.setNext(node.getNext());
            if (node == this.last) {
                this.last = prev;
            }
        }
        this.size--;

        node.setNext(null); // Limpiar el enlace

        // 2. Reinsertar en la posición correcta según su prioridad
        if (this.isEmpty() || 
            node.getData().getPriority().compareTo(this.first.getData().getPriority()) < 0) {
            // Insertar al inicio
            node.setNext(this.first);
            this.first = node;
            this.size++;
            return;
        }

        // Buscar la posición donde insertarlo
        Node<EntryNode<E, P>> current = this.first;
        while (current.getNext() != null && 
            node.getData().getPriority().compareTo(current.getNext().getData().getPriority()) >= 0) {
            current = current.getNext();
        }

        // Insertar en la posición encontrada
        node.setNext(current.getNext());
        current.setNext(node);

        // Actualizar this.last si se insertó al final
        if (node.getNext() == null) {
            this.last = node;
        }
        this.size++;
    } 

    //-> toString
    @Override
    public String toString() {

        if(this.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");        
        Node<EntryNode<E,P>> currentNode = this.first;

        while (currentNode != this.last) {
            sb.append(currentNode).append(", ");
            currentNode = currentNode.getNext();
        }

        sb.append(this.last).append("]");
        return sb.toString();
    }
}
