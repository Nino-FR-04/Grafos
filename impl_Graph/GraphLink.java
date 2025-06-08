package impl_Graph;

import Excepciones.*;
import impl_Graph.components.*;
import impl_List.ListLinked;

public class GraphLink <E extends Comparable<E>> implements TADGraph<E> {
    
    //Atributos
    protected ListLinked<Vertex<E>> listVertex;
    protected final boolean graphType; //Si es false es no dirigido caso contrario es dirigido

    //-> Constructores
    public GraphLink() {
        this.listVertex = new ListLinked<Vertex<E>>();
        this.graphType = false;
    }

    public GraphLink(boolean graphType) {
        this.listVertex = new ListLinked<Vertex<E>>();
        this.graphType = graphType;
    }

    //Metodos
    @Override
    public void insertVertex(E data) throws ExceptionItemDuplicated {
        if(data == null) throw new ExceptionElementIsNull("El elemento es null");

        //-> Vertice
        Vertex<E> newVertex = new Vertex<E>(data);

        if(this.listVertex.contains(newVertex)) {
            throw new ExceptionItemDuplicated("Valor Duplicado");
        }

        this.listVertex.insertLast(newVertex);
    }

    @Override
    public void insertEdge(E vertOri, E vertDest) throws ExceptionElementNotFound, ExceptionElementIsNull {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertEdge'");
    }

    //toString
    public String toString() {
        return this.listVertex.toString();
    }

}
