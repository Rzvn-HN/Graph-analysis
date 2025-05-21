import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    static class Vertex {
        int vertexId;
        double toEdgeWeight; // weight

        public Vertex(int vertexId, double w) {
            this.vertexId = vertexId;
            this.toEdgeWeight = w;
        }

        public double getToEdgeWeight() {
            return toEdgeWeight;
        }

        public void setToEdgeWeight(double toEdgeWeight) {
            this.toEdgeWeight = toEdgeWeight;
        }

        public int getVertexId() {
            return vertexId;
        }

        public void setVertexId(int vertexId) {
            this.vertexId = vertexId;
        }
    }

    static class Graph {
        int id;
        int e = 0; // number of edges
        int v = 0; // number of vertices
        Map<Integer, List<Vertex>> adjacencyList = new HashMap<>();
        List<Vertex> v_w = new ArrayList<>();

        public Graph(int id) {
            this.id = id;
        }

        public void addVertex(int vertexId, double weight) {
            Vertex newV = new Vertex(vertexId, weight);
            v_w.add(newV);
            adjacencyList.put(vertexId, new ArrayList<>());
            v++;
        }

        public void addEdge(int startVertexId, int endVertexId, double weight) {
            if (adjacencyList.containsKey(startVertexId) && adjacencyList.containsKey(endVertexId)) {
                Vertex startVertex = new Vertex(endVertexId, weight);
                adjacencyList.get(startVertexId).add(startVertex);
                e++;
            }
        }

        public void deleteVertex(int vertexId) {
            int k =0;
            List<Integer> neibors=new ArrayList<>();
            for(int i =0; i<this.adjacencyList.get(vertexId).size();i++){
                neibors.add(adjacencyList.get(vertexId).get(i).getVertexId());
            }
            for (int s : adjacencyList.keySet()){
                int o=adjacencyList.get(s).size();
                adjacencyList.get(s).removeIf(vertex -> vertex.getVertexId()==vertexId);
                if (o != adjacencyList.get(s).size()){
                    k++;
                }
            }
            adjacencyList.remove(vertexId);
            v_w.removeIf(e -> e.vertexId == vertexId);
            v--;
            e = e - neibors.size() - k  ;
        }

        public void deleteEdge(int startVertexId, int endVertexId) {
            int n = 0;
            for (int i = 0; i < this.adjacencyList.get(startVertexId).size(); i++) {
                if (this.adjacencyList.get(startVertexId).get(i).vertexId == endVertexId) {
                    n = i;
                    break;
                }
            }
            this.adjacencyList.get(startVertexId).remove(n);
            e--;
        }

        public void editVertex(int vertexId, double newWeight) {
            for (Vertex v : v_w){
                if (v.getVertexId()== vertexId){
                    v.setToEdgeWeight(newWeight);
                    break;
                }
            }
        }

        public void editEdge(int startVertexId, int endVertexId, double newWeight) {
            for (Vertex v : this.adjacencyList.get(startVertexId)) {
                if (v.getVertexId()==endVertexId) {
                    v.setToEdgeWeight(newWeight);
                    break;
                }
            }


        }

        public void showGraph() {
            DecimalFormat ft = new DecimalFormat("0.000000");

            System.out.println(id + " " + this.v + " " + this.e);

            v_w.sort(Comparator.comparing(Vertex::getVertexId));
            for (Vertex vertex : v_w) {
                System.out.println(id + " " + vertex.getVertexId() + " " + ft.format(vertex.getToEdgeWeight()));
            }

            for (Vertex ed : v_w) {
                adjacencyList.get(ed.getVertexId()).sort(Comparator.comparing(Vertex::getVertexId));
                adjacencyList.get(ed.getVertexId()).forEach(e ->
                        System.out.println(id + " " + ed.getVertexId() + " " + e.getVertexId() + " " + ft.format(e.getToEdgeWeight())));
            }
        }

        public boolean vertexExisted(Graph g, int v) {
            return g.adjacencyList.containsKey(v);
        }

        public boolean edgeExisted(Graph g, int f, int e) {
            for (Vertex vertex : g.adjacencyList.get(f)) {
                if (vertex.vertexId == e) {
                    return true;
                }
            }
            return false;
        }
    }

    private final static LinkedHashMap<Integer, Graph> graphs = new LinkedHashMap<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            int q = Integer.parseInt(reader.readLine().trim());


            for (int i = 0; i < q; i++) {
                String command = reader.readLine().trim();
                String[] parts = command.split(" ");
                int graphId;
                int vertexId, startVertexId, endVertexId;
                double vertexWeight, edgeWeight;
                try {
                    switch (parts[0]) {
                        case "NEW_GRAPH":
                            graphId = Integer.parseInt(parts[1]);
                            Graph graphToAdd = graphs.get(graphId);
                            if (graphToAdd != null) {
                                System.out.println("INVALID COMMAND");
                            } else {
                                graphs.put(graphId, new Graph(graphId));
                            }
                            break;
                        case "ADD_VERTEX":
                            graphId = Integer.parseInt(parts[1]);
                            vertexId = Integer.parseInt(parts[2]);
                            vertexWeight = Double.parseDouble(parts[3]);
                            Graph graphToAddVertex = graphs.get(graphId);
                            if (graphToAddVertex != null && !graphToAddVertex.vertexExisted(graphToAddVertex, vertexId)) {
                                graphToAddVertex.addVertex(vertexId, vertexWeight);
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        case "ADD_EDGE":
                            graphId = Integer.parseInt(parts[1]);
                            startVertexId = Integer.parseInt(parts[2]);
                            endVertexId = Integer.parseInt(parts[3]);
                            edgeWeight = Double.parseDouble(parts[4]);
                            Graph graphToAddEdge = graphs.get(graphId);
                            if (graphToAddEdge != null && !graphToAddEdge.edgeExisted(graphToAddEdge, startVertexId, endVertexId)) {
                                graphToAddEdge.addEdge(startVertexId, endVertexId, edgeWeight);
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        case "DEL_VERTEX":
                            graphId = Integer.parseInt(parts[1]);
                            vertexId = Integer.parseInt(parts[2]);
                            Graph graphToDeleteVertex = graphs.get(graphId);
                            if (graphToDeleteVertex != null && graphToDeleteVertex.vertexExisted(graphToDeleteVertex, vertexId)) {
                                graphToDeleteVertex.deleteVertex(vertexId);
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        case "DEL_EDGE":
                            graphId = Integer.parseInt(parts[1]);
                            startVertexId = Integer.parseInt(parts[2]);
                            endVertexId = Integer.parseInt(parts[3]);
                            Graph graphToDeleteEdge = graphs.get(graphId);
                            if (graphToDeleteEdge != null && graphToDeleteEdge.edgeExisted(graphToDeleteEdge, startVertexId, endVertexId)) {
                                graphToDeleteEdge.deleteEdge(startVertexId, endVertexId);
                                graphs.replace(graphId, graphs.get(graphId), graphToDeleteEdge);
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        case "EDIT_VERTEX":
                            graphId = Integer.parseInt(parts[1]);
                            vertexId = Integer.parseInt(parts[2]);
                            vertexWeight = Double.parseDouble(parts[3]);
                            Graph graphToEditVertex = graphs.get(graphId);
                            if (graphToEditVertex != null && graphToEditVertex.vertexExisted(graphToEditVertex, vertexId)) {
                                graphToEditVertex.editVertex(vertexId, vertexWeight);
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        case "EDIT_EDGE":
                            graphId = Integer.parseInt(parts[1]);
                            startVertexId = Integer.parseInt(parts[2]);
                            endVertexId = Integer.parseInt(parts[3]);
                            edgeWeight = Double.parseDouble(parts[4]);
                            Graph graphToEditEdge = graphs.get(graphId);
                            if (graphToEditEdge != null && graphToEditEdge.edgeExisted(graphToEditEdge, startVertexId, endVertexId)) {
                                graphToEditEdge.editEdge(startVertexId, endVertexId, edgeWeight);
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        case "SHOW_GRAPH":
                            graphId = Integer.parseInt(parts[1]);
                            Graph graphToShow = graphs.get(graphId);
                            if (graphToShow != null) {
                                graphToShow.showGraph();
                            } else {
                                System.out.println("INVALID COMMAND");
                            }
                            break;
                        default:
                           System.out.println("INVALID COMMAND");
                    }
                } catch (Exception e) {
                  System.out.println("INVALID COMMAND");
                }
            }
        } catch (IOException e) {
            System.out.println("INVALID COMMAND");
        }
    }
}
