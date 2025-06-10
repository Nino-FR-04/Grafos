package impl_Graph;

import Excepciones.*;

/**
 * Interfaz que define el comportamiento basico de un grafo.
 * @param E tipo de dato que almacena el grafo 
 */
public interface TADGraph <E extends Comparable<E>> {
    //---> Metodos de acceso
    public boolean searchVertex(E data) throws ExceptionElementIsNull;
    public boolean searchEdge(E vert1, E vert2) throws ExceptionElementIsNull;
    //---> Metodos de actualizacion
    public void insertVertex(E data) throws ExceptionItemDuplicated, ExceptionElementIsNull;
    public void insertEdge(E vertOri, E vertDest) throws ExceptionElementNotFound, ExceptionElementIsNull, ExceptionUnsupportedGraphTypeOperation;
    public void removeVertex(E vertex) throws ExceptionIsEmpty,ExceptionElementIsNull,ExceptionElementNotFound;
    public void removeEdge(E vertOri, E vertDest) throws ExceptionIsEmpty,ExceptionElementIsNull,ExceptionElementNotFound;
    public void dfs(E vertex) throws ExceptionElementIsNull, ExceptionElementNotFound;
    public void bfs(E vertex) throws ExceptionElementIsNull, ExceptionElementNotFound;
}
