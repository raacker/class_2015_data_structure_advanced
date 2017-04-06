package winnertree;

public class Test {
	public static void main(String args[])
	{
		int[][] source = 
				{{1,9,11,19,21,31,41,51,61,71,84,86,88,92,99,100},
				{7,17,27,37,47,50,57,60,67,77,110},
				{2,12,22,29,32,39,42,52,62,72},
				{4,14,24,34,44,54,64,69,74,79},
				{8,18,28,38,48,58,68,70,78,80},
				{5,10,15,20,25,35,45,55,65,75},
				{3,13,23,33,43,49,53,59,63,73},
				{6,16,26,30,36,40,46,56,66,76}};
		
		int[] sorted = new int[source.length * source[0].length];
		WinnerTree tree = new WinnerTree(source);
		for(int i = 0; i < source.length*source[0].length; i++)
		{
			sorted[i] = tree.remove();
		}
		
		int counter = 0;
		for(int i = 0; i < sorted.length; i++)
		{
			if(sorted[i] == Integer.MAX_VALUE)
				continue;
			System.out.print(sorted[i] + " ");
			counter++;
			if(counter == 10)
			{
				System.out.println("");
				counter = 0;
			}
		}
	}
}
