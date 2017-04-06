package ds02_1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdjacencyMatrix g = new AdjacencyMatrix(new String[]{"SE","UK","DE","FR","CZ","CH","AT","IT"});
		//AdjacencyList g = new AdjacencyList(new String[]{"SE","UK","DE","FR","CZ","CH","AT","IT"});
		SearchForGraph search = new SearchForGraph();
		
		System.out.println(g);
		System.out.println();
		g.add("SE", "UK"); 
		g.add("SE", "DE"); 
		g.add("UK", "FR");
		g.add("DE", "FR");
		g.add("DE", "IT");
		g.add("DE", "CZ");		
		g.add("CH", "FR");
		g.add("CH", "IT");
		g.add("CH", "AT");
		
		System.out.println(g);
		System.out.println();
		System.out.println(search.BFS(g,3));
	}

}
