package topological;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		int size = sc.nextInt();
		sc.nextLine();
		String[] s = new String[size];
		int[] arr = new int[size];
		for(int i = 0; i < size; i++)
		{
			s[i] = sc.next();
			arr[i] = sc.nextInt();
			System.out.print(s[i]+" ");
		}
		AdjacencyList g = new AdjacencyList(s,arr);
		System.out.print(" => Total Count : "+size+"\n");
		while(sc.hasNextLine())
		{
			String left = sc.next();
			String right = sc.next();
			g.add(left, right, 0);
		}
		
		System.out.println(g);System.out.println();
		System.out.println(g.topologicalSort());
		sc.close();	
	}

}
