package impl_Graph;

import Excepciones.*;
import impl_Graph.components.*;
import impl_List.ListLinked;

public class GraphLink <E extends Comparable<E>> implements TADGraph<E> {
    
    /**
     * Representa el tipo del grafo - Dirigido o No Dirigido
     */
    public enum GraphType {
        DIRECTED,
        UNDIRECTED,
    }

    //Atributos
    protected ListLinked<Vertex<E>> listVertex;
    protected final GraphType graphType;

    //-> Constructores
    //Por defecto el grafo es dirigido
    public GraphLink() {
        this.listVertex = new ListLinked<Vertex<E>>();
        this.graphType = GraphType.DIRECTED;
    }

    public GraphLink(GraphType graphType) {
        this.listVertex = new ListLinked<Vertex<E>>();
        this.graphType = graphType;
    }

    //-->Metodos
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
        if(vertOri == null || vertDest == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        if(!this.containsVertex(vertOri, vertDest)) {
            throw new ExceptionElementNotFound("Un elemento(Vertice) no fue encontrado");
        }

        if(this.graphType == GraphType.UNDIRECTED) {
            this.insertEdgeUndirected(vertOri, vertDest);
            return;
        }

        this.insertEdgeDirected(vertOri, vertDest);

    }

    private boolean containsVertex(E vertOri, E vertDest) {
        
        boolean origen = false;
        boolean destino = false;
        
        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vertOri)) origen = true;
            if(vertex.getData().equals(vertDest)) destino = true;
        }

        return (origen && destino);
    }

    private void insertEdgeUndirected(E vertOri, E vertDest) {
        Vertex<E> refOri = null;
        Vertex<E> refDest = null;

        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vertOri)) refOri = vertex;
            if(vertex.getData().equals(vertDest)) refDest = vertex;
        }

        if (!this.edgeExists(refOri, refDest)) {
            refOri.getListAdj().insertLast(new Edge<>(refDest));
            refDest.getListAdj().insertLast(new Edge<>(refOri));
            return;
        }

        //System.out.println("Si existe");
    }

    private void insertEdgeDirected(E vertOri, E vertDest) {
        Vertex<E> refOri = null;
        Vertex<E> refDest = null;

        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vertOri)) refOri = vertex;
            if(vertex.getData().equals(vertDest)) refDest = vertex;
        }

        if (!this.edgeExists(refOri, refDest)) {
            refOri.getListAdj().insertLast(new Edge<>(refDest));
            return;
        }
        //System.out.println("Si existe");
    }

    private boolean edgeExists(Vertex<E> origin, Vertex<E> destination) {
        for (Edge<E> edge : origin.getListAdj()) {
            if (edge.getRefDest().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    //toString
    public String toString() {
        return this.listVertex.toString();
    }


}
