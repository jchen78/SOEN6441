package game.engine;

import game.core.enums.*;
import game.core.interfaces.*;
import game.error.InvalidOperationException;

public class ConcreteCreator {
	public IPlayer createPlayer(int index, String initialState) throws NumberFormatException, InvalidOperationException {
		IPlayer player = new Player(index);
		player.setCurrentState(initialState);
		return player;
	}
	
	public ICityArea createCity(String initialState) {
		return new MapArea(initialState);
	}

	public IPersonalityCard create(PersonalityCardName name) {
		return new PersonalityCard(name);
	}
	
	public IPlayerCard create(PlayerCardName name) {
		return new PlayerCard(name);
	}
	
	public IRandomEventCard create(RandomEventCardName name) {
		return new RandomEventCard(name);
	}
}
