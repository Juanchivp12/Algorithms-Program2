#include <iostream>
#include "MyGraph.h"


ostream& operator<<(ostream& os, const Link& l)
{
   os << l.v1 << " " << l.v2 << " " << l.w;
   return os;
}

MyGraph::MyGraph(int n)
{

}

MyGraph::MyGraph(const MyGraph&)
{

}

bool MyGraph::addEdge(int a, int b, float w)
{
   return true;
}

void MyGraph::output(ostream& os)
{
}

vector<Link> MyGraph::MST()
{
   Link l1;
   l1.v1 = 1;
   l1.v2 = 2;
   l1.w = 0.1;
   vector<Link> res;
   res.push_back(l1);
   return res;

}

