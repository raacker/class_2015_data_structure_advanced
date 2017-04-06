package ds02_1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SearchForGraph {
	
	public String DFS(AdjacencyMatrix g, int i)
	{
		if(g.isEmpty())
			return null;
		boolean[][] map = g.getMap();
		String[] vertices = g.getVertex();
		boolean[] visit = new boolean[g.getSize()];
		StringBuffer buf = new StringBuffer("{");
		int listFullChecker = 1;
		Stack<String> s = new Stack<String>();

		visit[i] = true;		//mark vertex(i) as true
		s.push(vertices[i]);	//push vertex(i)
		int offset = i;
		buf.append(s.pop());
		do{
			for(int j = 0; j<g.getSize(); j++)
			{
				if((!visit[j]) && map[offset][j])  //adjacency and not visited yet
				{
					s.push(vertices[j]);	//push
					visit[j] = true;
				}
			}
			
			offset = 0;
			String temp = s.peek();
			for(int k = 0; k <g.getSize(); k++)	//find offset of next push element
			{
				if(temp.equals(vertices[k]))	//if there's equal vertex
				{
					offset = k;
					break;
				}
			}
			buf.append("->"+s.pop());
			listFullChecker++;
		}while(!s.isEmpty() || (listFullChecker != g.getSize()));	//till the stack is not empty
		
		return buf+"}";
	}
	
	public String BFS(AdjacencyMatrix g, int i)
	{
		if(g.isEmpty())
			return null;
		boolean[][] map = g.getMap();
		String[] vertices = g.getVertex();
		boolean[] visit = new boolean[g.getSize()];
		StringBuffer buf = new StringBuffer("{");
		int listFullChecker = 1;
		Queue<String> q = new LinkedList<String>();

		visit[i] = true;		//mark vertex(i) as true
		q.add(vertices[i]);	//push vertex(i)
		int offset = i;
		buf.append(q.remove());
		do{
			for(int j = 0; j<g.getSize(); j++)
			{
				if((!visit[j]) && map[offset][j])  //adjacency and not visited yet
				{
					q.add(vertices[j]);	//push
					visit[j] = true;
				}
			}
			
			offset = 0;
			String temp = q.peek();
			for(int k = 0; k <g.getSize(); k++)	//find offset of next push element
			{
				if(temp.equals(vertices[k]))	//if there's equal vertex
				{
					offset = k;
					break;
				}
			}
			buf.append("->"+q.remove());
			listFullChecker++;
		}while(!q.isEmpty() || (listFullChecker != g.getSize()));	//till the stack is not empty
		
		return buf+"}";
	}
	
	public String DFS(AdjacencyList g, int i)
	{
		if(g.isEmpty())
			return null;
		String[] vertices = g.getVertex();
		Node[] list = g.getList();
		boolean[] visit = new boolean[g.getSize()];
		int listFullChecker = 1;
		StringBuffer buf = new StringBuffer("{");
		Stack<String> s = new Stack<String>();

		visit[i] = true;		//mark vertex(i) as true
		s.push(vertices[i]);	//push vertex(i)
		int offset = i;
		buf.append(s.pop());
		do{
			Node adjacencyNode = list[offset].getNext();
			int target;
			while(adjacencyNode != null)
			{
				target = adjacencyNode.getData();
				if((!visit[target]) && (adjacencyNode != null))  //adjacency and not visited yet
				{
					s.push(vertices[target]);	//push
					visit[target] = true;
				}
				adjacencyNode = adjacencyNode.getNext();
			}
			
			offset = 0;
			String temp = s.peek();
			for(int k = 0; k <g.getSize(); k++)	//find offset of next push element
			{
				if(temp.equals(vertices[k]))	//if there's equal vertex
				{
					offset = k;
					break;
				}
			}
			buf.append("->"+s.pop());
			listFullChecker++;
		}while(!s.isEmpty() || (listFullChecker != g.getSize()));	//till the stack is not empty
		
		return buf+"}";
	}
	
	public String BFS(AdjacencyList g, int i)
	{
		if(g.isEmpty())
			return null;
		String[] vertices = g.getVertex();
		Node[] list = g.getList();
		boolean[] visit = new boolean[g.getSize()];
		int listFullChecker = 1;
		StringBuffer buf = new StringBuffer("{");
		Queue<String> q = new LinkedList<String>();

		visit[i] = true;		//mark vertex(i) as true
		q.add(vertices[i]);	//push vertex(i)
		int offset = i;
		buf.append(q.remove());
		do{
			Node adjacencyNode = list[offset].getNext();
			int target;
			while(adjacencyNode != null)
			{
				target = adjacencyNode.getData();
				if((!visit[target]) && (adjacencyNode != null))  //adjacency and not visited yet
				{
					q.add(vertices[target]);	//push
					visit[target] = true;
				}
				adjacencyNode = adjacencyNode.getNext();
			}
			
			offset = 0;
			String temp = q.peek();
			for(int k = 0; k <g.getSize(); k++)	//find offset of next push element
			{
				if(temp.equals(vertices[k]))	//if there's equal vertex
				{
					offset = k;
					break;
				}
			}
			buf.append("->"+q.remove());
			listFullChecker++;
		}while(!q.isEmpty() || (listFullChecker != g.getSize()));	//till the stack is not empty
		
		return buf+"}";
	}
}
