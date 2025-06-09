package impl_Graph;

import Excepciones.*;
import impl_Graph.components.*;
import impl_Graph.components.Edge.EdgeState;
import impl_Graph.components.Vertex.VertexState;
import impl_List.ListLinked;
import impl_Stack.StackLink;

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
        if(data == null) throw new ExceptionElementIsNull("El elemento es nulo");

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

    @Override
    public void removeVertex(E vertex) 
            throws ExceptionIsEmpty, ExceptionElementIsNull, ExceptionElementNotFound {
        
        if(this.listVertex.isEmptyList()) throw new ExceptionIsEmpty("Grafo vacio");
        if(vertex == null) throw new ExceptionElementIsNull("El elemento es nulo");

        Vertex<E> refVertexDelete = null;

        for (Vertex<E> ver : this.listVertex) {
            if(ver.getData().equals(vertex)) {
                refVertexDelete = ver;
                break;
            }
        }

        if(refVertexDelete == null) throw new ExceptionElementNotFound("Elemento no encontrado");

        if(this.graphType == GraphType.UNDIRECTED) {
            this.removeVertexUndirected(refVertexDelete);
            return;
        }

        this.removeVertexDirected(refVertexDelete);
    }

    private void removeVertexUndirected(Vertex<E> refVertex) {
        
        // Eliminar referencias a refVertex
        for (Edge<E> edge : refVertex.getListAdj()) {
            Vertex<E> vertAux = edge.getRefDest();

            Edge<E> removeRef = null;

            for (Edge<E> edge2 : vertAux.getListAdj()) {
                if (edge2.getRefDest().equals(refVertex)) {
                    removeRef = edge2;
                    break;
                }
            }

            if (removeRef != null) {
                vertAux.getListAdj().remove(removeRef);
            }
        }

        // Eliminar el vértice de la lista principal
        this.listVertex.remove(refVertex);
    }

    private void removeVertexDirected(Vertex<E> refVertex) {
        
        for (Vertex<E> vertex : this.listVertex) {
            Edge<E> edgeRemove = null;

            for (Edge<E> edge : vertex.getListAdj()) {
                if(edge.getRefDest().equals(refVertex)){
                    edgeRemove = edge;
                    break;
                }
            }

            if(edgeRemove != null) {
                vertex.getListAdj().remove(edgeRemove);
            }
        }

        this.listVertex.remove(refVertex);
    }

    @Override
    public void removeEdge(E vertOri, E vertDest)
            throws ExceptionIsEmpty, ExceptionElementIsNull, ExceptionElementNotFound {
        
        if(this.listVertex.isEmptyList()) throw new ExceptionIsEmpty("Grafo vacio");

        if(vertOri == null || vertDest == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        if(!this.containsVertex(vertOri, vertDest)) {
            throw new ExceptionElementNotFound("Un elemento(Vertice) no fue encontrado");
        }

        if(this.graphType == GraphType.UNDIRECTED) {
            this.removeEdgeUndirected(vertOri, vertDest);
            return;
        }

        this.removeEdgeDirected(vertOri, vertDest);

    }

    private void removeEdgeUndirected(E vertOri, E vertDest) {
        Vertex<E> refOri = null;
        Vertex<E> refDest = null;

        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vertOri)) refOri = vertex;
            if(vertex.getData().equals(vertDest)) refDest = vertex;
            if(refOri != null && refDest != null) break;
        }

        // Eliminar arista refOri -> refDest
        Edge<E> edgeRemove = null;
        for (Edge<E> edge : refOri.getListAdj()) {
            if (edge.getRefDest().equals(refDest)) {
                edgeRemove = edge;
                break;
            }
        }
        if (edgeRemove != null) refOri.getListAdj().remove(edgeRemove);

        // Eliminar arista refDest -> refOri
        edgeRemove = null;
        for (Edge<E> edge : refDest.getListAdj()) {
            if (edge.getRefDest().equals(refOri)) {
                edgeRemove = edge;
                break;
            }
        }
        if (edgeRemove != null) refDest.getListAdj().remove(edgeRemove);
    }

    private void removeEdgeDirected(E vertOri, E vertDest) {
        Vertex<E> refOri = null;
        Vertex<E> refDest = null;

        for (Vertex<E> vertex : this.listVertex) {
            if(vertex.getData().equals(vertOri)) refOri = vertex;
            if(vertex.getData().equals(vertDest)) refDest = vertex;
            if(refOri != null && refDest != null) break;
        }

        // Eliminar arista refOri -> refDest (solo una dirección)
        Edge<E> edgeRemove = null;
        for (Edge<E> edge : refOri.getListAdj()) {
            if (edge.getRefDest().equals(refDest)) {
                edgeRemove = edge;
                break;
            }
        }
        if (edgeRemove != null) refOri.getListAdj().remove(edgeRemove);
    }

    @Override
    public void dfs(E vertex) throws ExceptionElementIsNull,ExceptionElementNotFound {
        if(vertex == null) throw new ExceptionElementIsNull("El elemento es nulo");

        Vertex<E> refVertActual = null;

        for (Vertex<E> vert : this.listVertex) {
            if(vert.getData().equals(vertex)) {
                refVertActual = vert;
                break;
            }
        }

        if(refVertActual == null) throw new ExceptionElementNotFound("Elemento no encontrado");

        StackLink<Vertex<E>> stack = new StackLink<>();
        refVertActual.setVertexState(VertexState.VISITED);
        System.out.println(refVertActual.getData());
        stack.push(refVertActual);

        while (!stack.isEmpty()) {
            Vertex<E> current = stack.top(); 

            boolean foundUnvisited = false;

            for (Edge<E> edge : current.getListAdj()) {
                Vertex<E> next = edge.getRefDest();

                if (edge.getEdgeState() == EdgeState.UNEXPLORED) {
                    if (next.getVertexState() == VertexState.UNEXPLORED) {
                        // Descubrimiento
                        edge.setEdgeState(EdgeState.DISCOVERY);
                        next.setVertexState(VertexState.VISITED);
                        System.out.println(next.getData());
                        stack.push(next);
                        foundUnvisited = true;
                        break; // seguimos por este nuevo vértice
                    } else {
                        // Arista de retroceso
                        edge.setEdgeState(EdgeState.BACK);
                    }
                }
            }

            if (!foundUnvisited) {
                // ya no hay vecinos por visitar
                stack.pop();
            }
        }

        this.resetStates();
    }

    private void resetStates() {
        for (Vertex<E> v : this.listVertex) {
            v.setVertexState(VertexState.UNEXPLORED);
            for (Edge<E> e : v.getListAdj()) {
                e.setEdgeState(EdgeState.UNEXPLORED);
            }
        }
    }

    //toString
    public String toString() {
        return this.listVertex.toString();
    }
}
