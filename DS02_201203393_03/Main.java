package kruskal;

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
		for(int i = 0; i < size; i++)
		{
			s[i] = sc.nextLine();
			System.out.print(s[i]+" ");
		}
		AdjacencyMatrix g = new AdjacencyMatrix(s);
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
		System.out.println(g);
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
			g.kruskal();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		sc.close();	
	}

}
