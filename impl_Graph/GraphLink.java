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

            if(origen && destino) break;
        }

        return (origen && destino);
    }

    private void insertEdgeUndirected(E vertOri, E vertDest) {
        Vertex<E> refOri = null;
        Vertex<E> refDest = null;

        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vertOri)) refOri = vertex;
            if(vertex.getData().equals(vertDest)) refDest = vertex;

            if(refOri != null && refDest != null) break;

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
            if(refOri != null && refDest != null) break;
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

    @Override
    public boolean searchVertex(E data) throws ExceptionElementIsNull {
        if(data == null) {
            throw new ExceptionElementIsNull("El elemento es nulo");
        }

        Vertex<E> vertAux = new Vertex<>(data);
        return this.listVertex.contains(vertAux);
    }

    @Override
    public boolean searchEdge(E vert1, E vert2) throws ExceptionElementIsNull {
        if(vert1 == null || vert2 == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        Vertex<E> refVert1 = null;
        Vertex<E> refVert2 = null;

        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vert1)) refVert1 = vertex;
            if(vertex.getData().equals(vert2)) refVert2 = vertex;
            if(refVert1 != null && refVert2 != null) break;
        }

        if(refVert1 == null || refVert2 == null) return false;

        return this.edgeExists(refVert1, refVert2);
    }

    //toString
    public String toString() {
        return this.listVertex.toString();
    }
}
