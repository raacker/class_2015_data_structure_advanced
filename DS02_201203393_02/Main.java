package ds02_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new FileInputStream("input2.txt"));
		int size = sc.nextInt();
		sc.nextLine();
		String[] s = new String[size];
		for(int i = 0; i < size; i++)
		{
			s[i] = sc.nextLine();
			System.out.print(s[i]+" ");
		}
		AdjacencyList g = new AdjacencyList(s);
		System.out.print(" => Total Count : "+size+"\n");
		while(sc.hasNextLine())
		{
			String left = sc.next();
			String right = sc.next();
			int weight = sc.nextInt();
			g.add(left, right, weight);
		}
		sc.close();
		sc = new Scanner(System.in);
		System.out.print("Type Start index of Vertex : ");
		try{
			String input = sc.nextLine();
			int start = size;
			for(int i = 0; i < size; i++)
			{
				if(input.equals(s[i]))
				{
					start = i;
					break;
				}	
			}
			if(start > size-1)
			{
				sc.close();
				throw new OutOfBoundException();
			}
			g.dijkstra(start);
			g.printPath();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		sc.close();	
	}

}
