package ds02_1;

import java.util.Stack;

public class AdjacencyList {
	private int _size;
	private String[] _vertices;
	private Node[] _list;
	
	public AdjacencyList(String args[])
	{
		this._size = args.length;
		this._list = new Node[this._size];
		this._vertices = new String[this._size];
		for(int i = 0; i < this._size; i++)
		{
			this._vertices[i] = new String(args[i]);
			this._list[i] = new Node(i);
		}
	}
	public int getSize()
	{
		return this._size;
	}
	public String[] getVertex()
	{
		return this._vertices;
	}
	public Node[] getList()
	{
		return this._list;
	}
	public boolean isEmpty()
	{
		return (this._size == 0);
	}
	public String toString()
	{
		if(this._size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{"+vertex(0));
		for(int i = 1 ; i<this._size; i++)
			buf.append(","+vertex(i));
		return buf+"}";
	}
	
	public void add(String v, String w)
	{
		int indexV = index(v);
		int indexW = index(w);
		if(indexV < 0 || indexW < 0)
			return;
		
		linkVertex(this._list[indexV],indexW);
		linkVertex(this._list[indexW],indexV);
	}
	private void linkVertex(Node left, int data)
	{
		Node newNode = new Node(data);
		newNode.setNext(left.getNext());
		left.setNext(newNode);
	}
	
	private int index(String v)
	{
		for(int i = 0; i< this._size; i++)
			if(this._vertices[i].equals(v)) return i;
		return -1;
	}
	
	private String vertex(int i)
	{
		StringBuffer buf = new StringBuffer(this._vertices[i] + ":");
		Node search = this._list[i].getNext();
		while(search != null)
		{
			buf.append(this._vertices[search.getData()]+" ");
			search = search.getNext();
		}
		return buf+"";
	}
	
	public String DFS(int i)
	{
		if(this.isEmpty())
			return null;
		boolean[] visit = new boolean[this.getSize()];	//Check vertex visited state
		int listFullChecker = 1;					 	//check if graph is not searched all
		StringBuffer buf = new StringBuffer("{");		//output list
		Stack<String> s = new Stack<String>();			//Stack

		visit[i] = true;			//mark vertex(i) as true
		s.push(this._vertices[i]);	//push vertex(i)
		int offset = i;				//start offset
		buf.append(s.pop());
		do{
			Node adjacencyNode = this._list[offset].getNext();
			int target;
			while(adjacencyNode != null)	//until last adjacency vertex
			{
				target = adjacencyNode.getData();
				if((!visit[target]) && (adjacencyNode != null))  //adjacency and not visited yet
				{
					s.push(this._vertices[target]);	//push
					visit[target] = true;			//mark as true
				}
				adjacencyNode = adjacencyNode.getNext();	//move on to next vertex
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
