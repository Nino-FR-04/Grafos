package impl_Graph.components;

public class EdgeObj<V extends Comparable<V>,E> implements Comparable<EdgeObj<V,E>> {
    //Atributos
    protected E info;
    protected VertexObj<V> vertex1;
    protected VertexObj<V> vertex2;
    protected int position;
    protected boolean state;

    //Constructor
    public EdgeObj(VertexObj<V> vert1, VertexObj<V> vert2, E info, int pos) {
        this.vertex1 = vert1;
        this.vertex2 = vert2;
        this.info = info;
        this.position = pos;
        this.state = false;
    }

    //Setters y getters
    public VertexObj<V> getVert1() {return this.vertex1;}
    public VertexObj<V> getVert2() {return this.vertex2;}
    public E getInfo() {return this.info;}
    public int getPosition() {return this.position;}
    public boolean getState() {return this.state;}
    public void setInfo(E info) {this.info = info;}
    public void setState(boolean state) {this.state = state;}

    //->Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        EdgeObj<?, ?> edge = (EdgeObj<?, ?>) obj;

        boolean sameVertex = 
            (this.vertex1.equals(edge.vertex1) && this.vertex2.equals(edge.vertex2)) ||
            (this.vertex1.equals(edge.vertex2) && this.vertex2.equals(edge.vertex1));

        boolean samePos = this.position == edge.position;

        return samePos && sameVertex &&
            (this.info == null ? edge.info == null : this.info.equals(edge.info));
    }

    //-> compareTo
    @Override
    public int compareTo(EdgeObj<V, E> o) {
        return Integer.compare(this.position, o.position);
    }

    //toString
    @Override
    public String toString() {
        return "(" + this.info + " :" + this.vertex1 
        + "-" + this.vertex2 + ")";
    }
}
