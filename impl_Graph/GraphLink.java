package impl_Graph;

import Excepciones.*;
import impl_Graph.components.*;
import impl_List.ListLinked;
import impl_Queue.QueueLink;
import impl_Graph.components.Edge.EdgeState;
import impl_Stack.StackLink;

public class GraphLink <E extends Comparable<E>> implements TADGraph<E> {
    
    /**
     * Representa el tipo del grafo - Dirigido o No Dirigido
     */
    public enum GraphType {
        DIRECTED,
        DIRECTED_WEIGHTED,
        UNDIRECTED,
        UNDIRECTED_WEIGHTED
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
    //---------------------------------->Insercion
    /**
     * Inserta un nuevo vértice en el grafo.
     *
     * @param data Información del vértice.
     * @throws ExceptionItemDuplicated Si el vértice ya existe.
     * @throws ExceptionElementIsNull Si el dato es nulo.
     */
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

    /**
     * Inserta una arista entre dos vértices.
     *
     * @param vertOri Vértice de origen.
     * @param vertDest Vértice de destino.
     * @throws ExceptionElementNotFound Si alguno de los vértices no existe.
     * @throws ExceptionElementIsNull Si alguno de los parámetros es nulo.
     * @throws ExceptionUnsupportedGraphTypeOperation Si la insercion es para un grafo no permitido {@code GraphType}
     */
    @Override
    public void insertEdge(E vertOri, E vertDest) throws ExceptionElementNotFound, ExceptionElementIsNull, ExceptionUnsupportedGraphTypeOperation {
        if(this.graphType != GraphType.DIRECTED && 
                this.graphType != GraphType.UNDIRECTED) {
            throw new ExceptionUnsupportedGraphTypeOperation("Operacion no permitida para este tipo de grafo: " + this.graphType);
        }
        
        if(vertOri == null || vertDest == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        if(!this.containsVertex(vertOri, vertDest)) throw new ExceptionElementNotFound("Un elemento(Vertice) no fue encontrado");

        if(this.graphType == GraphType.UNDIRECTED) {
            this.insertEdgeUndirected(vertOri, vertDest,-1);
            return;
        }

        this.insertEdgeDirected(vertOri, vertDest,-1);
    }

    /**
     * Inserta una arista con peso entre dos vértices.
     *
     * @param vertOri Vértice de origen.
     * @param vertDest Vértice de destino.
     * @throws ExceptionElementNotFound Si alguno de los vértices no existe.
     * @throws ExceptionElementIsNull Si alguno de los parámetros es nulo.
     * @throws ExceptionUnsupportedGraphTypeOperation Si la insercion es para un grafo no permitido {@code GraphType}
     */
    public void insertEdgeWeighted(E vertOri, E vertDest, int weight) throws ExceptionElementNotFound, ExceptionElementIsNull, ExceptionUnsupportedGraphTypeOperation {
        if(this.graphType != GraphType.DIRECTED_WEIGHTED && 
                this.graphType != GraphType.UNDIRECTED_WEIGHTED) {
            throw new ExceptionUnsupportedGraphTypeOperation("Operacion no permitida para este tipo de grafo: " + this.graphType);
        }
        
        if(vertOri == null || vertDest == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        if(!this.containsVertex(vertOri, vertDest)) throw new ExceptionElementNotFound("Un elemento(Vertice) no fue encontrado");

        if(this.graphType == GraphType.UNDIRECTED_WEIGHTED) {
            this.insertEdgeUndirected(vertOri, vertDest,weight);
            return;
        }
        this.insertEdgeDirected(vertOri, vertDest,weight);
    }

    /** Inserta una arista bidireccional (grafo no dirigido). */
    private void insertEdgeUndirected(E vertOri, E vertDest, int weight) {

        Vertex<E>[] vertices = getVertex(vertOri, vertDest);
        Vertex<E> refOri = vertices[0];
        Vertex<E> refDest = vertices[1];

        if (!this.edgeExists(refOri, refDest)) {
            refOri.getListAdj().insertLast(new Edge<>(refDest,weight));
            refDest.getListAdj().insertLast(new Edge<>(refOri,weight));
            return;
        }
        //System.out.println("Si existe");
    }

    /** Inserta una arista unidireccional (grafo dirigido). */
    private void insertEdgeDirected(E vertOri, E vertDest, int weight) {

        Vertex<E>[] vertices = getVertex(vertOri, vertDest);
        Vertex<E> refOri = vertices[0];
        Vertex<E> refDest = vertices[1];

        if (!this.edgeExists(refOri, refDest)) {
            refOri.getListAdj().insertLast(new Edge<>(refDest,weight));
            return;
        }
        //System.out.println("Si existe");
    }

    //->Metodos de verificacion
    /**
     * Verifica si ambos vértices existen en el grafo.
     *
     * @param vertOri Vértice de origen.
     * @param vertDest Vértice de destino.
     * @return true si ambos existen.
     */
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

    /** Verifica si existe una arista entre dos vértices. */
    private boolean edgeExists(Vertex<E> origin, Vertex<E> destination) {
        for (Edge<E> edge : origin.getListAdj()) {
            if (edge.getRefDest().equals(destination)) return true;
        }
        return false;
    }

    //-> Metodos de obtencion de datos
    /** 
     * Retorna en un arreglo las referencias del vertice de Origen y Destiono
     * @param vertOri vertice de origen
     * @param vertDest vertice de destino
     * @return Arreglo de vertices
     */
    @SuppressWarnings("unchecked")
    private Vertex<E>[] getVertex(E vertOri, E vertDest) {
        Vertex<E> refOri = null;
        Vertex<E> refDest = null;

        for (Vertex<E> vertex : this.listVertex) {
            if (vertex.getData().equals(vertOri)) refOri = vertex;
            if (vertex.getData().equals(vertDest)) refDest = vertex;
            if (refOri != null && refDest != null) break;
        }

        return (Vertex<E>[]) new Vertex[] { refOri, refDest };
    }

    /**
     * Busca si un vértice existe en el grafo.
     *
     * @param data Dato del vértice.
     * @return true si existe.
     * @throws ExceptionElementIsNull Si el dato es nulo.
     */
    @Override
    public boolean searchVertex(E data) throws ExceptionElementIsNull {
        if(data == null) throw new ExceptionElementIsNull("El elemento es nulo");

        Vertex<E> vertAux = new Vertex<>(data);
        return this.listVertex.contains(vertAux);
    }

    /**
     * Busca si una arista existe entre dos vértices.
     *
     * @param vert1 Vértice 1.
     * @param vert2 Vértice 2.
     * @return true si existe la arista.
     * @throws ExceptionElementIsNull Si algún parámetro es nulo.
     */
    @Override
    public boolean searchEdge(E vert1, E vert2) throws ExceptionElementIsNull {
        if(vert1 == null || vert2 == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        Vertex<E>[] vertices = getVertex(vert1, vert2);
        Vertex<E> refVert1 = vertices[0];
        Vertex<E> refVert2 = vertices[1];

        if(refVert1 == null || refVert2 == null) return false;

        return this.edgeExists(refVert1, refVert2);
    }

    //-> Metodos de Eliminacion
    /**
     * Elimina un vértice del grafo y todas sus conexiones.
     *
     * @param vertex Vértice a eliminar.
     * @throws ExceptionIsEmpty Si el grafo está vacío.
     * @throws ExceptionElementIsNull Si el vértice es nulo.
     * @throws ExceptionElementNotFound Si el vértice no existe.
     */
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

    /**
     * Elimina un vértice en un grafo no dirigido.
     *
     * @param refVertex Referencia al vértice.
     */
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
            if (removeRef != null) vertAux.getListAdj().remove(removeRef);
        }
        // Eliminar el vértice de la lista principal
        this.listVertex.remove(refVertex);
    }

    /**
     * Elimina un vértice en un grafo dirigido.
     *
     * @param refVertex Referencia al vértice.
     */
    private void removeVertexDirected(Vertex<E> refVertex) {
        
        for (Vertex<E> vertex : this.listVertex) {
            Edge<E> edgeRemove = null;

            for (Edge<E> edge : vertex.getListAdj()) {
                if(edge.getRefDest().equals(refVertex)){
                    edgeRemove = edge;
                    break;
                }
            }
            if(edgeRemove != null) vertex.getListAdj().remove(edgeRemove);
        }
        this.listVertex.remove(refVertex);
    }

    /**
     * Elimina una arista entre dos vértices.
     *
     * @param vertOri Vértice origen.
     * @param vertDest Vértice destino.
     * @throws ExceptionIsEmpty Si el grafo está vacío.
     * @throws ExceptionElementIsNull Si algún valor es nulo.
     * @throws ExceptionElementNotFound Si los vértices no existen.
     */
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

    /**
     * Elimina una arista en un grafo no dirigido.
     */
    private void removeEdgeUndirected(E vertOri, E vertDest) {

        Vertex<E>[] vertices = getVertex(vertOri, vertDest);
        Vertex<E> refOri = vertices[0];
        Vertex<E> refDest = vertices[1];

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

    /**
     * Elimina una arista en un grafo dirigido.
     */
    private void removeEdgeDirected(E vertOri, E vertDest) {

        Vertex<E>[] vertices = getVertex(vertOri, vertDest);
        Vertex<E> refOri = vertices[0];
        Vertex<E> refDest = vertices[1];

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

    //-----> Metodos de recorridos
    /**
     * Realiza un recorrido en profundidad (DFS) desde un vértice.
     *
     * @param vertex Vértice inicial.
     * @throws ExceptionElementIsNull Si el vértice es nulo.
     * @throws ExceptionElementNotFound Si el vértice no se encuentra.
     */
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
        refVertActual.setVertexState(true);
        System.out.print("\n" + refVertActual.getData());
        stack.push(refVertActual);

        while (!stack.isEmpty()) {
            Vertex<E> current = stack.top(); 

            //-> Flag: Indicador si tiene al menos un vecino no visitado
            boolean foundUnvisited = false; 

            for (Edge<E> edge : current.getListAdj()) {
                Vertex<E> next = edge.getRefDest();

                if (edge.getEdgeState() == EdgeState.UNEXPLORED) {
                    if (next.getVertexState() == false) {
                        // Descubrimiento
                        edge.setEdgeState(EdgeState.DISCOVERY);
                        next.setVertexState(true);
                        System.out.print(", " + next.getData());
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
                // ya no hay vertices por visitar
                stack.pop();
            }
        }
        this.resetStates();
    }

    /**
     * Realiza un recorrido en anchura (BFS) desde un vértice.
     *
     * @param vertex Vértice inicial.
     * @throws ExceptionElementIsNull Si el vértice es nulo.
     * @throws ExceptionElementNotFound Si el vértice no se encuentra.
     */
    @Override
    public void bfs(E vertex) throws ExceptionElementIsNull, ExceptionElementNotFound {
        if(vertex == null) throw new ExceptionElementIsNull("El elemento es nulo");

        Vertex<E> refVertActual = null;

        for (Vertex<E> vert : this.listVertex) {
            if(vert.getData().equals(vertex)) {
                refVertActual = vert;
                break;
            }
        }

        if(refVertActual == null) throw new ExceptionElementNotFound("Elemento no encontrado");

        QueueLink<Vertex<E>> queue = new QueueLink<>();
        queue.enqueue(refVertActual);
        refVertActual.setVertexState(true);
        System.out.print("\n" + refVertActual.getData());

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.dequeue();

            for (Edge<E> edge : current.getListAdj()) {
                Vertex<E> vecino = edge.getRefDest();

                if (edge.getEdgeState() == EdgeState.UNEXPLORED) {
                    if (vecino.getVertexState() == false) {
                        edge.setEdgeState(EdgeState.DISCOVERY);
                        vecino.setVertexState(true);
                        System.out.print(", " + vecino.getData());
                        queue.enqueue(vecino);
                    } else {
                        edge.setEdgeState(EdgeState.CROSS);
                    }
                }
            }
        }
        this.resetStates();
    }

    /**
     * Restablece los estados de todos los vértices y aristas del grafo.
     */
    private void resetStates() {
        for (Vertex<E> v : this.listVertex) {
            v.setVertexState(false);
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
