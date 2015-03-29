package game.engine;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.visitee.GetRandomAreaVisitee;
import game.action.sequence.visitee.RemoveAllBuildingsFromArea;
import game.action.sequence.visitee.RemoveAllDemonsFromArea;
import game.action.sequence.visitee.RemoveAllMinionsFromArea;
import game.action.sequence.visitee.RemoveTroubleMarkerFromArea;
import game.core.enums.RandomEventCardName;
import game.core.interfaces.IRandomEventCard;

import java.util.LinkedList;

/**
 * This class represents entities from the Random Event card deck.
 */
public class RandomEventCard implements IRandomEventCard {
	private RandomEventCardName _cardName;
	private LinkedList<IVisitee> _actions;
	
	public RandomEventCard(RandomEventCardName cardName) {
		_cardName = cardName;
		_actions = new LinkedList<IVisitee>();
		
		switch (_cardName) {
			case TheDragon:
				_actions.add(new GetRandomAreaVisitee());
				_actions.add(new RemoveAllMinionsFromArea());
				_actions.add(new RemoveAllDemonsFromArea());
				_actions.add(new RemoveTroubleMarkerFromArea());
				_actions.add(new RemoveAllBuildingsFromArea());
				break;
			default:
				// TODO : fill in all other cards
				break;
		}
	}
	
	@Override
	public RandomEventCardName getCardName() {
		return _cardName;
	}
}
