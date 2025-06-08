package impl_Graph.components;

import impl_List.ListLinked;

/**
 * Representa un vertice (vertex) en un grafo.
 *
 * @param E el tipo de dato almacenado en los vértices del grafo.
 */
public class Vertex <E extends Comparable<E>> implements Comparable<Vertex<E>> {
    
    /**
     * Estado que puede tomar un vertice durante un recorrido.
     */
    public enum VertexState {
        UNEXPLORED,
        VISITED
    }

    //Atributos
    protected E data;
    protected ListLinked<Edge<E>> listadj;
    protected VertexState state;

    //Constructor
    public Vertex(E data) {
        this.data = data;
        this.listadj = new ListLinked<Edge<E>>();
        this.state = VertexState.UNEXPLORED;
    }

    //Metodos -> Setters y Getters
    public E getData() {return this.data;}
    public void setData(E data) {this.data = data;}
    public ListLinked<Edge<E>> getListAdj() {return this.listadj;}
    public VertexState getVertexState() {return this.state;}
    public void setVertexState(VertexState state) {this.state = state;}
    

    //Equals
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) obj;
        return this.data.equals(vertex.getData());
    }

    //compareTo
    @Override
    public int compareTo(Vertex<E> o) {
        return this.data.compareTo(o.data);
    }

    //toString
    @Override
    public String toString() {
        return this.data + " -> " + this.listadj.toString() + "\n";
    }
}
