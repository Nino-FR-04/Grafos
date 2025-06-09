import impl_Graph.GraphLink;
import impl_Graph.GraphLink.GraphType;
import impl_List.ListLinked;

public class Main {
    public static void main(String[] args) {
        
        GraphLink<Integer> g = new GraphLink<>(GraphType.DIRECTED);

        // -> Test N° 1
        g.insertVertex(1);
        g.insertVertex(2);
        g.insertVertex(3);
        g.insertVertex(4);
        g.insertVertex(5);
        g.insertVertex(6);
        

        g.insertEdge(2,4);
        g.insertEdge(5,6);
        g.insertEdge(1,3);
        g.insertEdge(3,4);
        g.insertEdge(4,6);
        g.insertEdge(2,3);
        g.insertEdge(3,5);
        g.insertEdge(1,2);
        g.insertEdge(4,5);

        System.out.println(g);

        g.dfs(1);
        g.removeEdge(1,3);
        System.out.println(g);
        /*
        g.removeVertex(4);
        System.out.println(g);
        
        System.out.println(g.searchVertex(0));
        System.out.println("Existe: " + g.searchEdge(1, 3));
        System.out.println("Existe: " + g.searchEdge(3,1));
        g.removeVertex(6);
        System.out.println(g);
        */
    }
}
