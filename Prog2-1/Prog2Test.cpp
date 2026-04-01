#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <functional>
#include "MyGraph.h"


struct {

  bool operator()(Link l1, Link l2) const {return (l1.v1 < l2.v1) || ((l1.v1 == l2.v1) && (l1.v2 < l2.v2) );}
} myless;

main(int argc, char **argv)
{
   ifstream ifile(argv[1]);
   int n, e, q;

   ifile >> n >> e;

   vector<Link> l;
   for (int i = 0; i < e; i++)
	{
	   Link s;
	   ifile >> s.v1 >> s.v2 >> s.w;
	   l.push_back(s);
	}

   vector<Link> l2;
   ifile >> q;
   for (int i = 0; i < q; i++)
   {
	   Link s;
	   ifile >> s.v1 >> s.v2;
	   s.w= 0;
	   l2.push_back(s);

   }

   bool* res = Prog2(n, l, q, l2);

   for (int i =0; i < q; i++)
   {
	   cout << i << " ";
	   if (res[i])
	      cout << "T";
	   else 
	      cout << "F";
	   cout << endl;
   }
   cout << "\n";

}
