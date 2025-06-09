package impl_Graph;

import Excepciones.*;

public class GraphListEdge <E extends Comparable<E>> implements TADGraph<E> {

    @Override
    public boolean searchVertex(E data) throws ExceptionElementIsNull {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchVertex'");
    }

    @Override
    public boolean searchEdge(E vert1, E vert2) throws ExceptionElementIsNull {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchEdge'");
    }

    @Override
    public void insertVertex(E data) throws ExceptionItemDuplicated, ExceptionElementIsNull {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertVertex'");
    }

    @Override
    public void insertEdge(E vertOri, E vertDest) throws ExceptionElementNotFound, ExceptionElementIsNull {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertEdge'");
    }

    @Override
    public void removeVertex(E vertex) throws ExceptionIsEmpty, ExceptionElementIsNull, ExceptionElementNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
    }

    @Override
    public void removeEdge(E vertOri, E vertDest)
            throws ExceptionIsEmpty, ExceptionElementIsNull, ExceptionElementNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
    }

    @Override
    public void dfs(E vertex) throws ExceptionElementIsNull, ExceptionElementNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dfs'");
    }

    @Override
    public void bfs(E vertex) throws ExceptionElementIsNull, ExceptionElementNotFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bfs'");
    }
    
}
