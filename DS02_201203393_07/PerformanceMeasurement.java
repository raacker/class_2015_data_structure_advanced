package performance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PerformanceMeasurement {
	
	public PerformanceMeasurement()
	{
	}
	
	public ResultDataSet measure(MeasurementInterface<String> structure, String file)
	{
		int lineNumber = 0;
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while(line != null){
				++lineNumber;
				StringTokenizer parser = new StringTokenizer(line, " ,:;-.?!");
				while(parser.hasMoreTokens()){
					String word = parser.nextToken().toUpperCase();
					structure.putData(word);
				}
				System.out.println(lineNumber + ":\t"+line);
				line = in.readLine();
			}
			in.close();
		}catch(IOException e){System.out.println(e);}
		long startTime = System.nanoTime();
		structure.printData();
		long endTime = System.nanoTime();
		long travelTime = endTime - startTime;
		System.out.println("Arithmetic Counter: "+structure.performanceCounter());
		System.out.println("Search Timelaps: "+travelTime);
		System.out.println("lines: " + lineNumber);
		System.out.println("distinct words: " + structure.size());
		if(structure instanceof AVLTree)
			System.out.println("Tree Height : " + ((AVLTree)structure).getHeight());
		if(structure instanceof BinarySearchTree)
			System.out.println("Tree Height : " + ((BinarySearchTree)structure).getHeight());
			
		
		return new ResultDataSet(structure.performanceCounter(), travelTime);
	}
	public ResultDataSet LinkedListMeasurement(String args)
	{
		System.out.println("\n-------------------------LinkedList Test-------------------------\n");
		ResultDataSet result = measure(new LinkedList(), args);
		System.out.println("\n-------------------------LinkedList Test-------------------------\n");
		return result;
	}
	public ResultDataSet AVLTreeMeasurement(String args)
	{
		System.out.println("\n---------------------------AVLTree Test--------------------------\n");
		ResultDataSet result = measure(new AVLTree(new Entry<String, Integer>("null", 0)), args);
		System.out.println("\n---------------------------AVLTree Test--------------------------\n");
		return result;
	}
	public ResultDataSet BinarySearchTreeMeasurement(String args)
	{
		System.out.println("\n----------------------BinarySearchTree Test----------------------\n");
		ResultDataSet result = measure(new BinarySearchTree(new Entry<String, Integer>("null", 0)), args);
		System.out.println("\n----------------------BinarySearchTree Test----------------------\n");
		return result;
	}
	public ResultDataSet DoubleHashMeasurement(String args)
	{
		System.out.println("\n-------------------------DoubleHash Test-------------------------\n");
		ResultDataSet result = measure(new HashTableDouble(), args);
		System.out.println("\n-------------------------DoubleHash Test-------------------------\n");
		return result;
	}
}
