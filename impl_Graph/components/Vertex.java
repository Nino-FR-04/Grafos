package impl_Graph.components;

import impl_List.ListLinked;

public class Vertex <E extends Comparable<E>> implements Comparable<Vertex<E>> {
    
    public enum VertexState {
        UNEXPLORED,
        VISITED
    }

    //Atributos
    protected E data;
    protected ListLinked<Edge<E>> listadj;

    //Constructor
    public Vertex(E data) {
        this.data = data;
        this.listadj = new ListLinked<Edge<E>>();
    }

    //Setters y Getters
    public E getData() {return this.data;}
    public ListLinked<Edge<E>> getListAdj() {return this.listadj;}

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
}
