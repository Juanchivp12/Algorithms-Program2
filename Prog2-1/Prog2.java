import java.util.ArrayList;

public class Prog2 {

    /**
     * Determines if a road appears for each queried city pair in AT LEAST ONE valid MST
     * @param n The number of cities
     * @param roads list of links such that each entry contains Link representing a possible road between two cities and the cost of building a road between them
     * @param q Number of city pairs to be considered
     * @param test List of all city pairs. Each entry stores a pair of cities
     * @return true if the road appears
     */
    public static boolean[] Prog2(int n, ArrayList<Link> roads, int q, ArrayList<Link> test) {
        boolean[] res = new boolean[q];
        for (int i = 0; i < q; i++)
            res[i] = false;

        MyGraph graph = new MyGraph(n);
        for (Link l : roads) {
            graph.addEdge(l.v1(), l.v2(), l.w());
        }

        ArrayList<Link> mst = graph.MST();
        for (int i = 0; i < q; i++) {
            Link query = test.get(i);

            if (findEdge(roads, query) == null) { // If road doesn't even exist, we don't have to keep checking for anything and res[i] is false
                res[i] = false;
            } else if (findEdge(mst, query) != null) { // If road is already in MST, then we set res[i] to true
                res[i] = true;
            } else { // find the max weight road, if it's equal to query.w, ture, false otherwise
                boolean[] visited = new boolean[n + 1];
                Link road = findEdge(roads, query);
                final double THRESHOLD = 0.0001;
                float maxW = dfs(query.v1(), query.v2(), mst, visited);
                res[i] = Math.abs(maxW - road.w()) < THRESHOLD; // Right way to compare floats to avoid precision error
            }
        }
        return res;
    }

    /**
     * Helper method to determine if provided list of edges contains a specific edge
     * @param list of edges to search through
     * @param query edge to search for
     * @return true if edge is found, false otherwise
     */
    private static Link findEdge(ArrayList<Link> list, Link query) {
        for (Link l : list) {
            if ((l.v1() == query.v1() && l.v2() == query.v2()) || (l.v1() == query.v2() && l.v2() == query.v1())) {
                return l;
            }
        }
        return null;
    }

    /**
     * Finds the most expensive edge on the path between two cities in MST
     * @param current current city we are looking at
     * @param end destination city we are trying to reach
     * @param mst the MST to traverse on
     * @param visited keeps track of visited cities
     * @return the maximum edge weight from current to end
     */
    private static float dfs(int current, int end, ArrayList<Link> mst, boolean[] visited) {
        if (current == end) return 0;

        visited[current] = true;
        for (Link l : mst) {
            int neighbor = -1; // Not visited yet
            if (l.v1() == current && !visited[l.v2()]) {
                neighbor = l.v2();
            } else if (l.v2() == current && !visited[l.v1()]) {
                neighbor = l.v1();
            }

            if (neighbor != -1) {
                float result = dfs(neighbor, end, mst, visited);
                if (result >= 0) {
                    if (l.w() > result) return l.w();
                    else return result;
                }
            }
        }
        return -1; // no path found
    }
}