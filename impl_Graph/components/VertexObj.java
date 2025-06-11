package impl_Graph.components;

public class VertexObj <V extends Comparable<V>> implements Comparable<VertexObj<V>> {
    //Atributos
    protected V info;
    protected boolean state; //Estado para DFS y BFS
    protected boolean origin; //Util para grafos dirigidos
    protected int position;

    //Constructor
    public VertexObj(V info, int pos) {
        this.info = info;
        this.position = pos;
        this.state = false;
        this.origin = false;
    }

    //Setters y getters
    public V getInfo() {return this.info;}
    public int getPosition() {return this.position;}
    public boolean getState() {return this.state;}
    public boolean isOrigin() {return this.origin;}
    public void setInfo(V info) {this.info = info;}
    public void setState(boolean state) {this.state = state;}
    public void setOrigin(boolean origin) {this.origin = origin;}

    //-> Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        VertexObj<?> vertex = (VertexObj<?>) obj;
        return this.position == vertex.position && this.info.equals(vertex.info);
    }

    //-> compareTo
    @Override
    public int compareTo(VertexObj<V> o) {
        return this.info.compareTo(o.info);
    }    

    //-> toString()
    @Override
    public String toString() {
        return "{" + this.info + "}";
    }

}
