package game.engine;

/**
 *This class contains the function returning array list of all the adjacent areas for a particular area
 */
public class Map {
	public MapArea[] createMap() {
		MapArea[] map = new MapArea[12];
		
		for (int i = 0; i < map.length; ++i) {
			map[i] = new MapArea();
		}
		
		map[0].addAdjacentArea(map[2]);
		map[0].addAdjacentArea(map[1]);
		map[0].addAdjacentArea(map[11]);
		
		map[1].addAdjacentArea(map[0]);
		map[1].addAdjacentArea(map[2]);
		map[1].addAdjacentArea(map[9]);
		map[1].addAdjacentArea(map[10]);
		map[1].addAdjacentArea(map[11]);
		
		map[2].addAdjacentArea(map[0]);
		map[2].addAdjacentArea(map[1]);
		map[2].addAdjacentArea(map[3]);
		map[2].addAdjacentArea(map[4]);
		map[2].addAdjacentArea(map[9]);
		
		map[3].addAdjacentArea(map[2]);
		map[3].addAdjacentArea(map[4]);
		map[3].addAdjacentArea(map[5]);
		
		map[4].addAdjacentArea(map[2]);
		map[4].addAdjacentArea(map[3]);
		map[4].addAdjacentArea(map[5]);
		map[4].addAdjacentArea(map[6]);
		map[4].addAdjacentArea(map[9]);
		
		map[5].addAdjacentArea(map[3]);
		map[5].addAdjacentArea(map[4]);
		map[5].addAdjacentArea(map[6]);
				
		map[6].addAdjacentArea(map[4]);
		map[6].addAdjacentArea(map[5]);
		map[6].addAdjacentArea(map[7]);
		
		map[7].addAdjacentArea(map[4]);
		map[7].addAdjacentArea(map[6]);
		map[7].addAdjacentArea(map[8]);
		
		map[8].addAdjacentArea(map[7]);
		map[8].addAdjacentArea(map[9]);
		map[8].addAdjacentArea(map[10]);
		
		map[9].addAdjacentArea(map[1]);
		map[9].addAdjacentArea(map[2]);
		map[9].addAdjacentArea(map[4]);
		map[9].addAdjacentArea(map[8]);
		
		map[10].addAdjacentArea(map[1]);
		map[10].addAdjacentArea(map[8]);
		map[10].addAdjacentArea(map[11]);
		
		map[11].addAdjacentArea(map[0]);
		map[11].addAdjacentArea(map[1]);
		map[11].addAdjacentArea(map[10]);
		
		return map;
	}
}
