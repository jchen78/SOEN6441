package game.engine;


/*
    This Class tries to makes a graph of the city areas. We have 12 areas, so 
    here we have 12 nodes. Edges in this graph show the neighbourhood of two areas.
    If there is an edge between two nodes of the graph, that means the corresponding areas have 
    common border or a bridge between them.
*/
public class AreaGraph
{
    // as the Adjacency Matrix of the Graph
	private boolean adjacencyMatrix [][];
    
    // number of nodes in the Graph, in our example we have 12 nodes (areas)
	private int vertexCount;

    /* 
        Constructor of the Graph
        @input: number of nodes of the Graph
        We initialize the Adjacency Matrix
    */
	public AreaGraph(int vertexCnt)
	{
		this.vertexCount = vertexCnt+1;
		adjacencyMatrix = new boolean[vertexCnt+1][vertexCnt+1];
	}
	
    /*
        Adds an edge between node i and node j
    */ 
	public void addEdge(int i, int j)
	{
		if(i > 0 && i <= vertexCount && j > 0 && j <= vertexCount)
		{
			adjacencyMatrix[i][j] = true;
			adjacencyMatrix[j][i] = true;
		}
	}

    /*
        Removes the edge between node i and node j
    */ 
	public void removeEdge(int i, int j)
	{
		if(i >= 0 && i <= vertexCount && j > 0 && j <= vertexCount)
		{
			adjacencyMatrix[i][j] = false;
			adjacencyMatrix[j][i] = false;
		}

	}
	
    /*
        Tests whether there is a graph between node i and node j
    */ 
	public boolean isEdge(int i, int j)
	{
		if(i > 0 && i <= vertexCount && j > 0 && j <= vertexCount)
			return adjacencyMatrix[i][j];
		return false;
	}
}