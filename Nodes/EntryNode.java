package Nodes;

/**
 * Clase que representa una entrada con un valor de datos y una prioridad asociada.
 * @param E el tipo del dato almacenado.
 * @param P el tipo de la prioridad asociada al dato.
 */

public class EntryNode <E,P> {
    
    //Atributos
    private E data;
    private P priority;

    //Constructor
    public EntryNode(E data, P priority) {
        this.data = data;
        this.priority = priority;
    }

    //Metodos
    public E getData() {return this.data;}
    public P getPriority() {return this.priority;}
    public void setPriority(P priority) {this.priority = priority;}

    //toString
    @Override
    public String toString() {
        return "{" + this.data.toString() + ":" 
                + this.priority.toString() + "}";
    }
}
