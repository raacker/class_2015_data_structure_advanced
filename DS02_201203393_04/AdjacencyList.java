package topological;

import java.util.PriorityQueue;

public class AdjacencyList {
	private int _size;
	private String[] _vertices;
	private Node[] _list;
	private int[] _prev;
	private int[] _dist;
	
	public AdjacencyList(String args[], int args2[])
	{
		this._size = args.length;
		this._list = new Node[this._size];
		this._vertices = new String[this._size];
		this._prev = new int[this._size];
		this._dist = new int[this._size];
		for(int i = 0; i < this._size; i++)
		{
			this._vertices[i] = new String(args[i]);
			this._list[i] = new Node(0, args2[i]);
		}
	}
	public boolean isEmpty()
	{
		return (this._size == 0);
	}
	public String toString()
	{
		if(this._size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{"+vertex(0)+" "+this._list[0].getData());
		for(int i = 1 ; i<this._size; i++)
			buf.append(","+vertex(i)+" "+this._list[i].getData());
		return buf+"}";
	}
	
	public void add(String v, String w, int weight)
	{
		int indexV = index(v);
		int indexW = index(w);
		if(indexV < 0 || indexW < 0)
			return;
		
		linkVertex(this._list[indexV],indexW, weight);
		this._list[indexW].setData((this._list[indexW].getData())+1);
	}
	private void linkVertex(Node left, int data, int weight)
	{
		Node newNode = new Node(data, weight);
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
		boolean[] visit = new boolean[this._size];	//Check vertex visited state
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
			for(int k = 0; k <this._size; k++)	//find offset of next push element
			{
				if(temp.equals(this._vertices[k]))	//if there's equal vertex
				{
					offset = k;
					break;
				}
			}
			buf.append("->"+s.pop());
			listFullChecker++;
		}while(!s.isEmpty() || (listFullChecker != this._size));	//till the stack is not empty
		
		return buf+"}";
	}
	
	public void dijkstra(int start)
	{
		// INITIALIZE-------------------------------------------------
		if(this.isEmpty())
			return;
		boolean[] visit = new boolean[this._size];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Stack<Node> s = new Stack<Node>();
		int listFullChecker = -1;
		Node offsetNode = null;
		Node searchNode;
		Node tempNode;
		for(int i = 0; i < this._size; i++)
		{
			this._dist[i] = Integer.MAX_VALUE;	//Make all dist in Infinity Value
			this._prev[i] = -1;					//not yet ( mark as -1)
		}
	
		this._prev[start] = 0;
		this._dist[start] = 0;
		pq.add(this._dist[start]);
		// INITIALIZE--------------------------------------------------
		
		while(listFullChecker != this._size)		//loop for graph size
		{
			int minimalDist = pq.poll();			//dequeue from P.Q
			for(int i = 0; i < this._size; i++)
			{
				if((this._dist[i] == minimalDist) && (!visit[i]))
				{
					offsetNode = this._list[i];		//target vertex of this loop 
					break;
				}
			}
			visit[offsetNode.getData()] = true;		//mark as visited
			
			int stackSize = 0;
			searchNode = offsetNode.getNext();		//find adjacency vertices of offsetNode
			while(searchNode != null)
			{
				if(!visit[searchNode.getData()])
				{
					s.push(searchNode);				//push all adjacency vertices of offsetNode
					stackSize++;
				}
				searchNode = searchNode.getNext();
			}
			
			int prevDist;
			int currentDist;
			for(int i = 0; i < stackSize; i++)
			{
				tempNode = s.pop();	
				currentDist = tempNode.getWeight() + this._dist[offsetNode.getData()];	//before dist + next dist
				prevDist = this._dist[tempNode.getData()];								//other way to get next vertex and its dist
			
				if(prevDist > currentDist)	//if currentDist is less than previousDist
				{
					this._dist[tempNode.getData()] = currentDist;
					this._prev[tempNode.getData()] = offsetNode.getData();
					pq.add(currentDist);	//enqueue in P.Q
				}
			}
			listFullChecker++;				//for graph size
		}
	}
	public void printPath()
	{
		int start = 0;
		for(int findFirstVertex = 0; findFirstVertex < this._size; findFirstVertex++)
		{
			if(this._dist[findFirstVertex] == 0)
			{
				start = findFirstVertex;
				break;
			}
		}
		for(int leafCount = 0; leafCount < this._size; leafCount++)
		{
			System.out.print(this._vertices[leafCount] + " : Distance");
			System.out.printf("%3d / ",this._dist[leafCount]);
			
			int index = leafCount;
			StringBuffer buf = new StringBuffer();
			
			if(index == start)
				buf.append("<Starting Point>");
			else
				buf.append(this._vertices[index]);
			
			while(index != start)
			{
				buf.append("<-" + this._vertices[this._prev[index]]);
				index = this._prev[index];
			}
			
			System.out.println(buf);
		}
	}
	
	public String topologicalSort()
	{
		StringBuffer buf = new StringBuffer();
		Stack<Integer> zeroIndegreeStack = new Stack<Integer>();
		int[] numberOfCourseForAppropriateGrade = checkCourseCountForAppropriateGrade();
		int checkedVertex = 0;
		int currentGrade = 1;
		
		while(checkedVertex < this._size)
		{
			for(int findZeroIndegree = 0; findZeroIndegree < this._size; findZeroIndegree++)
			{
				if((this._list[findZeroIndegree].getData() == 0) &&
						(numberOfCourseForAppropriateGrade[currentGrade-1] == 0) &&
					(this._list[findZeroIndegree].getWeight() == currentGrade))
				{
					zeroIndegreeStack.push(findZeroIndegree);
					checkedVertex++;
				}
			}
			
			
			if(zeroIndegreeStack.isEmpty())
				return "Network has a cycle";
			else
			{
				while(zeroIndegreeStack.hasNextValue())
				{
					int removeVertexOffset = zeroIndegreeStack.pop();
					Node searchForAdjacencyVertices = this._list[removeVertexOffset].getNext();
					
					while(searchForAdjacencyVertices != null)
					{
						this._list[searchForAdjacencyVertices.getData()].
							setData(this._list[searchForAdjacencyVertices.getData()].getData()-1);
						searchForAdjacencyVertices = searchForAdjacencyVertices.getNext();
					}
					buf.append(this._vertices[removeVertexOffset] + "->");
					this._list[removeVertexOffset].setData(-1);		//Mark as -1 if it is popped from graph
					numberOfCourseForAppropriateGrade[currentGrade]--;
				}
			}
			if(numberOfCourseForAppropriateGrade[currentGrade] <= 0)
				currentGrade++;
		}
		
		buf.deleteCharAt(buf.length()-1);
		buf.deleteCharAt(buf.length()-1);
		return buf.toString();
	}
	private int[] checkCourseCountForAppropriateGrade()
	{
		final int allGrade = 4;
		int[] numberOfCourseForAppropriateGrade = new int[allGrade+1];
		for(int leafCounterOfList = 0; leafCounterOfList < this._size; leafCounterOfList++)
		{
			numberOfCourseForAppropriateGrade[this._list[leafCounterOfList].getWeight()]++;
		}
		return numberOfCourseForAppropriateGrade;
	}
	private class Node {

		private int _data;
		private Node _next;
		private int _weight;
		
		private Node() {
			this._data = 0;
			this._next = null;
			this._weight = 0;
		}

		private Node(int data) {
			this._data = data;
			this._weight = 0;
			this._next = null;
		}
		
		private Node(int data, int weight) {
			this._data = data;
			this._weight = weight;
			this._next = null;
		}

		public int getData() {
			return this._data;
		}

		public void setData(int data) {
			this._data = data;
		}

		public Node getNext() {
			return this._next;
		}

		public void setNext(Node next) {
			this._next = next;
		}
		
		public int getWeight()
		{
			return this._weight;
		}
		
		public void setWeight(int weight)
		{
			this._weight = weight;
		}
	}
}
