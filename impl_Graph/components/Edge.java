package impl_Graph.components;

/**
 * Representa una arista (edge) en un grafo, que conecta un vértice origen
 * con un vértice destino y puede tener un peso y un estado asociado.
 *
 * @param E el tipo de dato almacenado en los vértices del grafo.
 */
public class Edge <E extends Comparable<E>> implements Comparable<Edge<E>> {
    
    /**
     * Estado que puede tomar una arista durante un recorrido.
     */
    public enum EdgeState {
        UNEXPLORED,
        DISCOVERY,
        BACK,
        CROSS
    }

    //Atributos
    protected Vertex<E> refDest;
    protected int weight;
    protected EdgeState state;

    //Constructores
    /**
     * Constructor que crea una arista con vértice destino y peso por defecto (-1).
     * El estado se inicializa en UNEXPLORED.
     * 
     * @param refDest vértice destino al que apunta esta arista.
     */
    public Edge(Vertex<E> refDest) {
        this.refDest = refDest;
        this.weight = -1;
        this.state = EdgeState.UNEXPLORED;
    }

    /**
     * Constructor que crea una arista con vértice destino y peso especificados.
     * El estado se inicializa en UNEXPLORED.
     * 
     * @param refDest vértice destino al que apunta esta arista.
     * @param weight peso o costo asociado a esta arista.
     */
    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
        this.state = EdgeState.UNEXPLORED;
    }

    //Metodos -> Setters y Getters
    public Vertex<E> getRefDest() {return this.refDest;}
    public int getWeight() {return this.weight;}
    public void setWeight(int weight) {this.weight = weight;}
    public EdgeState getEdgeState() {return this.state;}
    public void setEdgeState(EdgeState state) {this.state = state;}

    //Equals
    /**
     * Compara esta arista con otro objeto para determinar si son iguales.
     * Dos aristas son iguales si apuntan al mismo vértice destino.
     * 
     * @param obj el objeto a comparar con esta arista.
     * @return {@code true} si son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false;

        Edge<?> edge = (Edge<?>) obj;
        return this.refDest.equals(edge.refDest);
    }

    //Implementacion del metodo compareTo
    @Override
    public int compareTo(Edge<E> o) {
        if(this.weight == o.weight) return 0;
        return (this.weight < o.weight) ? -1 : 1;
    }

    //ToString
    @Override
    public String toString() {
        if(this.weight > -1) {
            return "(" + this.refDest.getData() + "-" + this.weight + ")";
        }
        return "(" + this.refDest.getData() + ", -)";
    }
}
