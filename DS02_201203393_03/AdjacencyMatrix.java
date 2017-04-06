package  kruskal;

import java.util.Arrays;
import java.util.Stack;

public class AdjacencyMatrix {
	private int _size;
	private String[] _vertices;
	private int[][] _map;
	private int _edges;
	private int[] _ufArr;
	
	public AdjacencyMatrix(String[] args)
	{
		this._edges = 0;
		this._size = args.length;
		this._vertices = new String[this._size];
		System.arraycopy(args,0,this._vertices, 0, this._size);	
		this._map = new int[this._size][this._size];
		this._ufArr = new int[this._size];
		for(int i = 0; i < this._size; i++)
		{
			for(int j = 0; j < this._size; j++)
			{
				this._map[i][j] = Integer.MAX_VALUE;
			}
			this._map[i][i] = 0;
			this._ufArr[i] = -1;
		}
	}
	
	public int getSize()
	{
		return this._size;
	}
	public int[][] getMap()
	{
		return this._map;
	}
	public String[] getVertex()
	{
		return this._vertices;
	}
	public boolean isEmpty()
	{
		return (this._size == 0);
	}
	public void add(String v, String w, int weight)
	{
		int i = index(v), j = index(w);
		if(i < 0 || j < 0)
			return;
		this._map[i][j] = this._map[j][i] = weight;
		this._edges++;
	}
	
	public String toString()
	{
		if(this._size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{"+vertex(0));
		for(int i = 1; i < this._size; i++)
		{
			buf.append("," + vertex(i));
		}
		return buf+"}";
	}

	
	private int index(String v)
	{
		for(int i = 0; i<this._size; i++)
		{
			if(this._vertices[i].equals(v))return i;
		}
		return -1;
	}
	
	private String vertex(int i)
	{
		StringBuffer buf = new StringBuffer(this._vertices[i] + ":");
		for(int j = 0; j< this._size; j++)
			if((this._map[i][j] != 0) && (this._map[i][j] != Integer.MAX_VALUE)) buf.append(this._vertices[j]+" ");
		return buf+"";
	}
	
	public String DFS(int i)
	{
		if(this.isEmpty())
			return null;
		
		boolean[] visit = new boolean[this.getSize()];  //Check vertex visited state
		StringBuffer buf = new StringBuffer("{");		//output list
		int listFullChecker = 1;						//check if graph is not searched all
		Stack<String> s = new Stack<String>();			//Stack

		visit[i] = true;			//mark vertex(i) as true
		s.push(this._vertices[i]);	//push vertex(i)
		int offset = i;				//start offset
		buf.append(s.pop());
		do{
			for(int j = 0; j<this.getSize(); j++)
			{
				if((!visit[j]) && ((this._map[i][j] != 0) && (this._map[i][j] != Integer.MAX_VALUE)))  //adjacency and not visited yet
				{
					s.push(this._vertices[j]);	//push
					visit[j] = true;			//mark as true
				}
			}
			
			offset = 0;
			String temp = s.peek();
			for(int k = 0; k <this.getSize(); k++)	//find offset of next push element
			{
				if(temp.equals(this._vertices[k]))	//if there's equal vertex
				{
					offset = k;
					break;
				}
			}
			buf.append("->"+s.pop());
			listFullChecker++;
		}while(!s.isEmpty() || (listFullChecker != this.getSize()));	//till the stack is not empty
		
		return buf+"}";
	}
	
	public void kruskal()
	{
		Edge[] E = new Edge[this._edges];
		Edge[] T = new Edge[this._edges];
		int edgeCount = 0;
		int tCount = 0;
		int minimalWeight = 0;
		for(int i = 0; i < this._size; i++)
		{
			for(int j = i; j< this._size; j++)
			{
				if((this._map[i][j] != 0) && (this._map[i][j] != Integer.MAX_VALUE))
					E[edgeCount++] = new Edge(i,j,this._map[i][j]);
			}
		}
		
		Arrays.sort(E);
		Edge temp;
		
		while((edgeCount > 0) && (tCount < this._size-1))
		{
			temp = E[edgeCount-1];
			E[--edgeCount] = null;
			
			if(collapsingFind(temp.getV()) != collapsingFind(temp.getW()))
			{
				T[tCount++] = temp;
				weightedUnion(temp.getV(), temp.getW());
				minimalWeight += temp.getWeight();
			}
		}
		if(tCount < this._size-1)
		{
			System.out.println("ERROR : It can't be Spanning Tree");
		}
		else
		{
			System.out.println("Kruskal Algorithm result");
			System.out.println("Minimum Cost : " + minimalWeight);
			System.out.println("Used Edges : ");
			for(int i = 0; i < tCount; i++)
			{
				temp = T[i];
				System.out.println(this._vertices[temp.getV()]
						+ "-" + this._vertices[temp.getW()] 
						+ ", Weight : "+temp.getWeight());
			}
		}
		
		
	}
	private void weightedUnion(int v, int w)
	{
		int vCount = this._ufArr[collapsingFind(v)];
		int wCount = this._ufArr[collapsingFind(w)];
		
		if(vCount > wCount)
		{
			this._ufArr[w] = vCount + wCount;
 			this._ufArr[v] = w;
		}
		else
		{
			this._ufArr[v] = vCount + wCount;
 			this._ufArr[w] = v;
		}
	}
	private int collapsingFind(int v)	//find v's root index
	{
		int startPoint = v;
		for(;this._ufArr[v] >= 0;v=this._ufArr[v]);
		for(;this._ufArr[startPoint] >= 0; startPoint = this._ufArr[startPoint])
			this._ufArr[startPoint] = v;
		
		return v;
	}
	private final class Edge implements Comparable<Edge>
	{
		private int v,w;
		private int weight;
		
		Edge(int v, int w, int weight)
		{
			this.v = v;
			this.w = w;
			this.weight = weight;
		}
		
		final int getV()
		{
			return this.v;
		}
		final int getW()
		{
			return this.w;
		}
		final int getWeight()
		{
			return this.weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			if(this.weight > e.getWeight())
				return -1;
			else if(this.weight < e.getWeight())
				return 1;
			else
				return 0;
		}
	}
}

