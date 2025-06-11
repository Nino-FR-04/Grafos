package impl_Graph;

import Excepciones.*;
import impl_Graph.components.*;
import impl_List.ListLinked;

/**
 * Grafo no dirigido - Otra implementacion de un grafo
 * @param V valor del vertice
 * @param E tipo de valor de la arista
 */
public class GraphListEdge <V extends Comparable<V>,E> implements TADGraph<V> {
    
    //Atributos
    private ListLinked<VertexObj<V>> secVertex;
    private ListLinked<EdgeObj<V,E>> secEdge;

    //Constructor
    public GraphListEdge() {
        this.secVertex = new ListLinked<>();
        this.secEdge = new ListLinked<>();
    }

    /**
     * Verifica si existe un vértice en el grafo
     * @param data dato del vértice a buscar
     * @return true si existe, false en caso contrario
     * @throws ExceptionElementIsNull si el dato es null
     */
    @Override
    public boolean searchVertex(V data) throws ExceptionElementIsNull {
        if(data == null) throw new ExceptionElementIsNull("El elemento es nulo");

        VertexObj<V> verSearch = new VertexObj<>(data, 0);
        return this.secVertex.search(verSearch) != -1;
    }

    /**
     * Verifica si existe una arista entre dos vértices
     * @param vert1 vértice origen - Vert1
     * @param vert2 vértice destino - Vert2
     * @return true si la arista existe, false en caso contrario
     * @throws ExceptionElementIsNull si alguno de los vértices es null
     */
    @Override
    public boolean searchEdge(V vert1, V vert2) throws ExceptionElementIsNull {
        if(vert1 == null || vert2 == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        VertexObj<V>[] vertices = getVertex(vert1, vert2);
        VertexObj<V> refVert1 = vertices[0];
        VertexObj<V> refVert2 = vertices[1];

        if(refVert1 == null || refVert2 == null) return false;

        return this.edgeExists(refVert1, refVert2);
    }

    /**
     * Inserta un nuevo vértice en el grafo
     * @param data valor del vértice
     * @throws ExceptionItemDuplicated si el vértice ya existe
     * @throws ExceptionElementIsNull si el dato es null
     */
    @Override
    public void insertVertex(V data) throws ExceptionItemDuplicated, ExceptionElementIsNull {
        if(data == null) throw new ExceptionElementIsNull("El elemento es nulo");

        if(this.secVertex.contains(new VertexObj<>(data, 0))){
            throw new ExceptionItemDuplicated("valor Duplicado"); 
        }

        this.secVertex.insertLast(new VertexObj<>(data, 0));
    }

    /** 
     * Retorna en un arreglo las referencias de los vertices
     * @param vertOri vertice de origen - Vert1
     * @param vertDest vertice de destino - Vert2
     * @return Arreglo de vertices
     */
    @SuppressWarnings("unchecked")
    private VertexObj<V>[] getVertex(V vertOri, V vertDest) {
        VertexObj<V> refOri = null;
        VertexObj<V> refDest = null;

        for (VertexObj<V> vertex : this.secVertex) {
            if (vertex.getInfo().equals(vertOri)) refOri = vertex;
            if (vertex.getInfo().equals(vertDest)) refDest = vertex;
            if (refOri != null && refDest != null) break;
        }

        return (VertexObj<V>[]) new VertexObj[] { refOri, refDest };
    }

    //DEBUG
    /*
    public ListLinked<VertexObj<V>> getListVert() {
        return this.secVertex;
    }

    public ListLinked<EdgeObj<V,E>> getListEdge() {
        return this.secEdge;
    }
    */

    /**
     * Inserta una arista no dirigida entre dos vértices existentes
     * @param vertOri vértice de origen - Destino
     * @param vertDest vértice de destino - Origen
     * @throws ExceptionElementNotFound si alguno de los vértices no existe
     * @throws ExceptionElementIsNull si alguno de los valores es null
     */
    @Override
    public void insertEdge(V vertOri, V vertDest)
            throws ExceptionElementNotFound, ExceptionElementIsNull, ExceptionUnsupportedGraphTypeOperation {
        
        if(vertOri == null || vertDest == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        VertexObj<V>[] vertices = this.getVertex(vertOri, vertDest);
        VertexObj<V> vert1 = vertices[0];
        VertexObj<V> vert2 = vertices[1];

        if (vert1 == null || vert2 == null) {
            throw new ExceptionElementNotFound("Uno o ambos vértices no existen");
        }

        if(!edgeExists(vert1, vert2)) {
            this.secEdge.insertLast(new EdgeObj<>(vert1, vert2));
            return;
        }

        //System.out.println("Existe");
    }

    /** Verifica si existe una arista entre dos vértices. */
    private boolean edgeExists(VertexObj<V> v1, VertexObj<V> v2) {
        for (EdgeObj<V,E> edge : this.secEdge) {
            boolean connects = 
            (edge.getVert1().equals(v1) && edge.getVert2().equals(v2)) ||
            (edge.getVert1().equals(v2) && edge.getVert2().equals(v1));
            
            if(connects) return true;
        }
        return false;
    }

    /**
     * Elimina un vértice del grafo y todas sus aristas asociadas
     * @param vertex valor del vértice a eliminar
     * @throws ExceptionIsEmpty si el grafo está vacío
     * @throws ExceptionElementIsNull si el parámetro es null
     * @throws ExceptionElementNotFound si el vértice no existe
     */
    @Override
    public void removeVertex(V vertex) throws ExceptionIsEmpty, ExceptionElementIsNull, ExceptionElementNotFound {
        if(this.secVertex.isEmptyList()) throw new ExceptionIsEmpty("Grafo vacio");
        if(vertex == null) throw new ExceptionElementIsNull("El elemento es nulo");

        VertexObj<V> refVertexDelete = null;
        for (VertexObj<V> v : this.secVertex) {
            if (v.getInfo().equals(vertex)) {
                refVertexDelete = v;
                break;
            }
        }

        if (refVertexDelete == null) {
            throw new ExceptionElementNotFound("El vértice no existe");
        }

        //Aristas a remover almacenadas en una lista auxiliar
        ListLinked<EdgeObj<V,E>> removeEdgeList = new ListLinked<>();

        for (EdgeObj<V,E> edgeObj : this.secEdge) {
            if(edgeObj.getVert1().equals(refVertexDelete) || 
                edgeObj.getVert2().equals(refVertexDelete)) {

                removeEdgeList.insertLast(edgeObj);
            }
        }

        //Remover aristas de el contenedor
        for (EdgeObj<V,E> edgeObj : removeEdgeList) {
            this.secEdge.remove(edgeObj);
        }

        //Se elimina el vertice
        this.secVertex.remove(refVertexDelete);
    }

    /**
     * Elimina una arista entre dos vértices
     * @param vertOri vértice origen
     * @param vertDest vértice destino
     * @throws ExceptionIsEmpty si el grafo está vacío
     * @throws ExceptionElementIsNull si alguno de los valores es null
     * @throws ExceptionElementNotFound si alguno de los vértices no existe
     */
    @Override
    public void removeEdge(V vertOri, V vertDest)
            throws ExceptionIsEmpty, ExceptionElementIsNull, ExceptionElementNotFound {
        
        if(this.secVertex.isEmptyList()) throw new ExceptionIsEmpty("Grafo vacio");

        if(vertOri == null || vertDest == null) {
            throw new ExceptionElementIsNull("Un elemento(Vertice) es nulo");
        }

        VertexObj<V>[] vertices = this.getVertex(vertOri, vertDest);
        VertexObj<V> vert1 = vertices[0];
        VertexObj<V> vert2 = vertices[1];

        if (vert1 == null || vert2 == null) {
            throw new ExceptionElementNotFound("Uno o ambos vértices no existen");
        }

        if(edgeExists(vert1, vert2)) {
            this.secEdge.remove(new EdgeObj<>(vert1, vert2));
            return;
        }

        //System.out.println("No existe");
    }

    @Override
    public void dfs(V vertex) throws ExceptionElementIsNull, ExceptionElementNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dfs'");
    }

    @Override
    public void bfs(V vertex) throws ExceptionElementIsNull, ExceptionElementNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bfs'");
    }    

    //toString
    /**
     * Retorna una representación en texto del grafo (Solo las aristas)
     */
    @Override
    public String toString() {
        return this.secEdge.toString();
    }
}
