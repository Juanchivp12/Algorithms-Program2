 nn import java.util.ArrayList;

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
	return res;
     }

}

