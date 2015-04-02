package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import.game.engine.city.CityAreaCard

public class TakeMoneyFromBank implements IVisitee {
        private int _Amount;
	private IPlayerCard _card;
	private CITY_AREA_CARD_NAMES;
	ICityArea[] cityArea= visitor.getAllCityAreas();
	
	
	public TakeMoneyFromBank(int amount) {
        _amount=amount;
	_card = currentCard;
	
	if (( CITY_AREA_CARD_NAMES=="TheHippo")|| (CITY_AREA_CARD_NAMES=="TheScours")|| CITY_AREA_CARD_NAMES=="DragonsLanding"))
	{
	_amount=2;
	}
        else if (( CITY_AREA_CARD_NAMES=="NapHill")|| (CITY_AREA_CARD_NAMES=="Longwall"))
	{
	_amount=1;
	}
	else if(( CITY_AREA_CARD_NAMES=="SevenSleepers")
	{
	_amount=3;
	}
	
	@Override
	public void accept(IVisitor visitor) throws GameOverException {
	        int amountRetrieved = visitor.withdrawMoneyFromBank(_Amount);
		IPlayer currentPlayer = visitor.getCurrentPlayer();
		currentPlayer.addMoney(amountRetrieved);
		currentPlayer.putPlayerCardInDisplay(_card);
	}

	}

	@Override
	public String getDescription() {
	              return "Take money from the bank";
	}

}
