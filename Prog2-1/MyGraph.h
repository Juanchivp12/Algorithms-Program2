#include <iostream>
#include <vector>

using namespace std;

struct Link {

public:

   int v1, v2;
   float w;
};

ostream& operator<<(ostream&, const Link&);

class MyGraph {

public:
   MyGraph(int n);
   MyGraph(const MyGraph&);

   bool addEdge(int a, int b, float w);
   void output(ostream& os);
   vector<Link> MST();
};


bool* Prog2(int n, vector<Link>& road, int q, vector<Link>& test);
