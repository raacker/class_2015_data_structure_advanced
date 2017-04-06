package ds02_2;

import java.util.Stack;

public class AdjacencyMatrix {
	private int _size;
	private String[] _vertices;
	private int[][] _map;
	
	public AdjacencyMatrix(String[] args)
	{
		this._size = args.length;
		this._vertices = new String[this._size];
		System.arraycopy(args,0,this._vertices, 0, this._size);	
		this._map = new int[this._size][this._size];
		for(int i = 0; i < this._size; i++)
		{
			for(int j = 0; j < this._size; j++)
			{
				this._map[i][j] = Integer.MAX_VALUE;
			}
			this._map[i][i] = 0;
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
}
