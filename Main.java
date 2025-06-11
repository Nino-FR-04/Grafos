import impl_Graph.*;
import impl_Graph.GraphLink.GraphType;
import impl_Graph.components.EdgeObj;
import impl_Graph.components.Vertex;
import impl_Graph.components.VertexObj;
import impl_List.ListLinked;

public class Main {
    public static void main(String[] args) {
        
        GraphListEdge<Integer,Integer> g = new GraphListEdge<>();
        g.insertVertex(1);
        g.insertVertex(2);
        g.insertVertex(3);
        g.insertVertex(4);

        g.insertEdge(1, 2);
        g.insertEdge(1, 3);

        //System.out.println(g.getListVert());
        //System.out.println(g.getListEdge());
        //System.out.println(g.searchVertex(1));

        System.out.println(g);
        System.out.println(g.searchEdge(2,1));
        
        g.removeVertex(1);
        //g.removeEdge(2,4);
        System.out.println(g);

        



        //ListLinked<EdgeObj<Integer,Integer>> l = g.getListEdge();

        //System.out.println(l.getFirst().equals(new EdgeObj<>(new VertexObj<Integer>(2), new VertexObj<Integer>(1))));




        //GraphLink<Integer> g = new GraphLink<>(GraphType.DIRECTED);

        //g.insertVertex(1);
        //g.insertVertex(2);
        //g.insertVertex(3);

        //g.insertEdge(1, 2);
        //g.insertEdge(2, 3);
        //g.insertEdge(1, 2);
        //g.insertEdgeWeighted(1, 2, 1);

        //System.out.println(g.isConexo());

        //g.removeEdge(1, 2);

        //System.out.println(g);
        //g.dfs(1);
        //g.bfs(1);


        /*  Test N° 1
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

        g.dfs(2);
        g.removeEdge(1,3);
        System.out.println(g);
        */
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
