import java.sql.Array;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;


public class MyGraph {
    int numVertices;
    ArrayList<Link>[] cityRoads;
    int[] parent;
    int[] rank;

    /**
     * Create an empty graph with n vertices and no edges
     * @param n number of vertices
     */
    public MyGraph(int n) {
        numVertices = n;
        cityRoads = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            cityRoads[i] = new ArrayList<Link>();
        }
    }

    /**
     * Copy g into the new graph
     * @param g graph passed
     */
    public MyGraph(MyGraph g) {
        numVertices = g.numVertices;
        cityRoads = new ArrayList[numVertices + 1];

        for (int i = 1; i <= numVertices; i++) {
            cityRoads[i] = new ArrayList<Link>();

            for (Link l : g.cityRoads[i]) {
                cityRoads[i].add(new Link(l.v1(), l.v2(), l.w()));
            }
        }
    }


    /**
     * add an edge a->b with weight w to the graph
     * @param a 1st city
     * @param b 2nd city
     * @param w cost of the road
     * @return if successfully added an edge between the 2 cities
     */
    public boolean addEdge(int a, int b, float w) {
        if (a < 1 || a > numVertices || b < 1 || b > numVertices) return false;

        // Check if road already exists
        for (Link l : cityRoads[a]) {
            if (l.v2() == b) return false;
        }

        cityRoads[a].add(new Link(a, b, w));
        cityRoads[b].add(new Link(b, a, w));

        return true;
    }

    /**
     * Return MST of a graph
     * @return MST of a graph
     */
    public ArrayList<Link>  MST() {
        ArrayList<Link> res = new ArrayList<Link>();
        height = new int[numVertices + 1];
        parent = new int[numVertices + 1];

        // Make an arrayList with all the roads with no repetitions, e.g. contains (1, 3) but not (3, 1)
        ArrayList<Link> allRoads = new ArrayList<>();
        for (int i = 1; i <= numVertices; i++) {
            for (Link l : cityRoads[i]) {
                if (i < l.v2()) {
                    allRoads.add(l);
                }
            }
        }
        // Sort the list
        Collections.sort(allRoads, (x, y) -> Float.compare(x.w(), y.w()));

        // Initialize every city as its own parent
        for (int i = 1; i <= numVertices; i++) {
            parent[i] = i;
        }

        // Build the MST
        for (Link l : allRoads) {
            if (!sameSet(l.v1(), l.v2())) {
                res.add(l);
                union(l.v1(), l.v2());
            }

            // MST complete
            if (res.size() == numVertices - 1) break;
        }
        return res;
    }



    public void output() {
        ArrayList<Link> MST = MST();
        System.out.println(numVertices);
        for (Link l : MST) {
            System.out.print(l.v1 < l.v2() ? l.v1 : l.v2);
            System.out.println(l.w());
        }
    }

    /**
     * Returns the root of the set
     * @param i city to find the root of
     * @return the root of the set
     */
    private int findSet(int i) {
        int root = i;
        while (root != parent[root]) {
            root = parent[root];
        }

        int j = parent[i];
        while (j != root) {
            parent[i] = root;
            i = j;
            j = parent[i];
        }
        return root;
    }

    /**
     * Determines if the two cities are in the same set
     * @param i first city
     * @param j second city
     * @return true if same set, false otherwise
     */
    private boolean sameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    /**
     * Merges two sets
     * @param i first set
     * @param j second set
     */
    private void union(int i, int j) {
        int rootI = findSet(i);
        int rootJ = findSet(j);

        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else { // same rank
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
    }
}

