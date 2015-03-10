package game.engine;

public class AreaGraph
{
	private boolean adjacencyMatrix [][];
	private int vertexCount;

	/**
	 * Creates an {@link AreaGraph}
	 * 
	 * @param vertexCnt vertex count
	 */
	public AreaGraph(int vertexCnt)
	{
		this.vertexCount = vertexCnt+1;
		adjacencyMatrix = new boolean[vertexCnt+1][vertexCnt+1];
	}
	
	public void addEdge(int i, int j)
	{
		if(i > 0 && i <= vertexCount && j > 0 && j <= vertexCount)
		{
			adjacencyMatrix[i][j] = true;
			adjacencyMatrix[j][i] = true;
		}
	}

	public void removeEdge(int i, int j)
	{
		if(i >= 0 && i <= vertexCount && j > 0 && j <= vertexCount)
		{
			adjacencyMatrix[i][j] = false;
			adjacencyMatrix[j][i] = false;
		}

	}
	
	public boolean isEdge(int i, int j)
	{
		if(i > 0 && i <= vertexCount && j > 0 && j <= vertexCount)
			return adjacencyMatrix[i][j];
		return false;
	}
}
