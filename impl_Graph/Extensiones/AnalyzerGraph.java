package impl_Graph.Extensiones;

import Excepciones.ExceptionElementIsNull;
import Excepciones.ExceptionUnsupportedGraphTypeOperation;
import impl_Graph.GraphLink;
import impl_Graph.GraphLink.GraphType;
import impl_Graph.components.Vertex;

/**
 * Clase que extiende GraphLink para proporcionar análisis estructurales
 * de grafos no dirigidos.
 *
 * @param <E> el tipo de dato que representan los vértices, debe ser comparable
 */
@SuppressWarnings("unused")
public class AnalyzerGraph<E extends Comparable<E>> extends GraphLink<E> {

    /**
     * Constructor que recibe el tipo de grafo.
     * 
     * @param type Tipo del grafo (debe ser no dirigido o no dirigido con peso)
     */
    public AnalyzerGraph(GraphType type) {
        super(type);
    }

    /**
     * Calcula el grado de un vértice en el grafo.
     * Solo es válido para grafos no dirigidos.
     *
     * @param vertex el vértice a evaluar
     * @return el número de aristas conectadas al vértice, o -1 si no se encuentra
     * @throws ExceptionElementIsNull si el vértice es nulo
     * @throws ExceptionUnsupportedGraphTypeOperation si el grafo es dirigido o dirigido ponderado
     */
    public int vertexGrade(E vertex) throws ExceptionElementIsNull, ExceptionUnsupportedGraphTypeOperation {
        if (vertex == null) throw new ExceptionElementIsNull("Vertice nulo");

        if(this.graphType != GraphType.UNDIRECTED && 
                this.graphType != GraphType.UNDIRECTED_WEIGHTED) {
            throw new ExceptionUnsupportedGraphTypeOperation("Operacion no permitida para este tipo de grafo: " 
                                                                + this.graphType);
        }

        for (Vertex<E> v : this.listVertex) {
            if (v.getData().equals(vertex)) {
                return v.getListAdj().length();
            }
        }
        return -1; // No encontrado
    }

    /**
     * Verifica si el grafo es un camino es decir:
     * - Dos vértices tienen grado 1 (extremos)
     * - Los demás vértices tienen grado 2
     * 
     * @return true si el grafo es un camino, false en caso contrario
     */
    public boolean isPath() {
        if (this.graphType != GraphType.UNDIRECTED && 
                this.graphType != GraphType.UNDIRECTED_WEIGHTED){
            return false;
        }
        
        int count = 0;

        for (Vertex<E> v : this.listVertex) {
            int degree = v.getListAdj().length();
            if (degree == 1) count++;
            else if (degree != 2) return false;
        }

        return count == 2;
    }

    /**
     * Verifica si el grafo forma un ciclo es decir:
     * - Todos los vértices tienen grado 2
     *
     * @return true si el grafo es un ciclo simple, false en caso contrario
     */
    public boolean isCycle() {
        if (this.graphType != GraphType.UNDIRECTED && 
                this.graphType != GraphType.UNDIRECTED_WEIGHTED){
            return false;
        }

        for (Vertex<E> v : this.listVertex) {
            if (v.getListAdj().length() != 2) return false;
        }

        return true;
    }

    /**
     * Verifica si el grafo forma una rueda, es decir:
     * - Existe un vértice con grado n-1 (el centro) - n es el numero de vertices
     * - Los demás vértices tienen grado 3
     *
     * @return true si el grafo es una rueda, false en caso contrario
     */
    public boolean isWheel() {
        if (this.graphType != GraphType.UNDIRECTED && 
                this.graphType != GraphType.UNDIRECTED_WEIGHTED){
            return false;
        }

        int centerCount = 0;
        int n = this.listVertex.length();

        for (Vertex<E> v : this.listVertex) {
            int degree = v.getListAdj().length();
            if (degree == n - 1) centerCount++;
            else if (degree != 3) return false;
        }

        return centerCount == 1;
    }

    /**
     * Verifica si el grafo es completo, es decir:
     * - Cada vértice está conectado con todos los demás
     * 
     * @return true si el grafo es completo, false en caso contrario
     */
    public boolean isComplete() {
        if (this.graphType != GraphType.UNDIRECTED && 
                this.graphType != GraphType.UNDIRECTED_WEIGHTED){
            return false;
        }

        int n = this.listVertex.length(); // Cantidad de vertices

        for (Vertex<E> v : this.listVertex) {
            if (v.getListAdj().length() != n - 1) return false;
        }

        return true;
    }
}