#include "MyGraph.h"
#include <vector>

bool* Prog2(int n, vector<Link>& road, int q, vector<Link>& test)
{
   bool *res = new bool[q];
   for (int i = 0; i < q; i++)
	   res[i] = false;
   res[0] = true;
   return res;
}
