import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;

public class Prog2Test {
    
    public static void main(String[] args) {

	String filename = args[0];
	int n = 0, e = 0, q = 0;
	ArrayList<Link> l1 = new ArrayList<Link>();
	ArrayList<Link> l2 = new ArrayList<Link>();

        try (Scanner fin = new Scanner(new File(filename))) {

		n = fin.nextInt();
		e = fin.nextInt();
		for (int i = 0; i < e; i++)
		{
		    int v1 = fin.nextInt();
		    int v2 = fin.nextInt();
		    float w = fin.nextFloat();
		    Link l = new Link(v1, v2, w);
		    l1.add(l); 
		}
		q = fin.nextInt();
		for (int i = 0; i < q; i++)
		{
		   int v1 = fin.nextInt();
		   int v2 = fin.nextInt();
		   Link l = new Link(v1, v2, 0);
		   l2.add(l);

		}


	} catch (FileNotFoundException ex) {
		System.out.println("File not found");

	}

	boolean [] res1 = Prog2.Prog2(n, l1, q, l2);
 	for (int i = 0; i <  q;  i++) {
		System.out.print(i);
		System.out.print(" ");
	        if (res1[i]) 
			{
		 	   System.out.println("T");
			}
		else
			{
		 	   System.out.println("F");
			}
	}
     }
}

