package impl_Graph.components;

import impl_List.ListLinked;

/**
 * Representa un vertice (vertex) en un grafo.
 *
 * @param E el tipo de dato almacenado en los vértices del grafo.
 */
public class Vertex <E extends Comparable<E>> implements Comparable<Vertex<E>> {

    //Atributos
    protected E data;
    protected ListLinked<Edge<E>> listadj;
    // -> true si esta visitado, false si no lo esta
    protected boolean state;
    protected int distance;

    //Constructor
    public Vertex(E data) {
        this.data = data;
        this.listadj = new ListLinked<Edge<E>>();
        this.state = false;
        this.distance = -1;
    }

    //Metodos -> Setters y Getters
    public E getData() {return this.data;}
    public void setData(E data) {this.data = data;}
    public ListLinked<Edge<E>> getListAdj() {return this.listadj;}
    public boolean getVertexState() {return this.state;}
    public void setVertexState(boolean state) {this.state = state;}
    public void setDistance(int distance) {this.distance = distance;}
    public int getDistance() {return this.distance;}

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
