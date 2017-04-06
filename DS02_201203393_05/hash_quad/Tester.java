package hash_quad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		HashTable<String, Country> normalHash = new HashTable<String, Country>(17);
		HashTableQuad<String, Country> quadHash = new HashTableQuad<String, Country>(17);
		
		int totalSize = sc.nextInt();
		for(int i = 0; i < totalSize; i++)
		{
			String key = sc.next();
			String name = sc.next();
			String language = sc.next();
			int people = sc.nextInt();
			int area = sc.nextInt();
			
			normalHash.put(key, new Country(name, language, people, area));
			quadHash.put(key, new Country(name, language, people, area));
		}

		String[] normalTestLog = normalHash.getTestLog();
		String[] quadTestLog = quadHash.getTestLog();

		System.out.println("<Linear Probing>");
		for(int i = 0; i < totalSize; i++){
			System.out.print(i+1 + ". ");
			System.out.println(normalTestLog[i]);
		}
		System.out.println("Collision : "+normalHash.getCollision());
		System.out.println("<Quad Probing>");
		for(int i = 0; i < totalSize; i++){
			System.out.print(i+1 + ". ");
			System.out.println(quadTestLog[i]);
		}
		System.out.println("Collision : "+quadHash.getCollision());
		sc.close();
	}
}
