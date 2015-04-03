<<<<<<< HEAD
<<<<<<< HEAD
package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.PlayerCardName;
import game.core.interfaces.IPersonalityCard;
import game.core.interfaces.IPlayer;
import game.error.EntityNotSetException;

/**
 * This visitee will keep on iterating until winning conditions are met
 * (whether through personality cards, random events or emptying the player
 * card deck).
 * @author Jason
 */
public class TurnIterator implements IVisitee {
	private static final int MINIMUM_NUMBER_CARDS = 5;
	IPlayer _currentPlayer;
	IVisitor _visitor;
	
	int i = 0;

	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException {
		initializeVisit(visitor);
		visitor.visit(_currentPlayer);
		iterateTurn();
	}

	private void initializeVisit(IVisitor visitor) throws GameOverException, EntityNotSetException {
		_currentPlayer = visitor.getCurrentPlayer();
		_visitor = visitor;
		
		IPersonalityCard currentPersonality = _visitor.getPersonalityCard(_currentPlayer.getPersonality());
		currentPersonality.accept(_visitor); // This verifies whether the card has met the winning conditions.
		if (++i == 2)
			throw new GameOverException();
	}

	private void iterateTurn() throws GameOverException, EntityNotSetException {
		cleanUpCurrentPlayer();
		_visitor.iteratePlayer();
		accept(_visitor);
	}

	private void cleanUpCurrentPlayer() throws GameOverException {
		for (int currentNumberCards = getNumberOfCards(); currentNumberCards < MINIMUM_NUMBER_CARDS; currentNumberCards++) {
			PlayerCardName drawnCard = _visitor.drawPlayerCard();
			_currentPlayer.addPlayerCard(drawnCard);
		}
	}

	private int getNumberOfCards() {
		return _currentPlayer.getPlayerCards().length;
	}

	@Override
	public String getDescription() {
		return "Play";
	}
=======
package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.PlayerCardName;
import game.core.interfaces.IPersonalityCard;
import game.core.interfaces.IPlayer;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

/**
 * This visitee will keep on iterating until winning conditions are met
 * (whether through personality cards, random events or emptying the player
 * card deck).
 * @author Jason
 */
public class TurnIterator implements IVisitee {
	private static final int MINIMUM_NUMBER_CARDS = 5;
	IPlayer _currentPlayer;
	IVisitor _visitor;
	
	int i = 0;

	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		initializeVisit(visitor);
		visitor.visit(_currentPlayer);
		iterateTurn();
	}

	private void initializeVisit(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		_currentPlayer = visitor.getCurrentPlayer();
		_visitor = visitor;
		
		IPersonalityCard currentPersonality = _visitor.getPersonalityCard(_currentPlayer.getPersonality());
		currentPersonality.accept(_visitor); // This verifies whether the card has met the winning conditions.
		if (++i == 2)
			throw new GameOverException();
	}

	private void iterateTurn() throws GameOverException, EntityNotSetException, InvalidOperationException {
		cleanUpCurrentPlayer();
		_visitor.iteratePlayer();
		accept(_visitor);
	}

	private void cleanUpCurrentPlayer() throws GameOverException {
		for (int currentNumberCards = getNumberOfCards(); currentNumberCards < MINIMUM_NUMBER_CARDS; currentNumberCards++) {
			PlayerCardName drawnCard = _visitor.drawPlayerCard();
			_currentPlayer.addPlayerCard(drawnCard);
		}
	}

	private int getNumberOfCards() {
		return _currentPlayer.getPlayerCards().length;
	}

	@Override
	public String getDescription() {
		return "Play";
	}
>>>>>>> origin/master
=======
package game.action.sequence.visitee;

import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
import game.core.enums.PlayerCardName;
import game.core.interfaces.IPersonalityCard;
import game.core.interfaces.IPlayer;
import game.core.interfaces.IPlayerCard;
import game.error.EntityNotSetException;
import game.error.InvalidOperationException;

/**
 * This visitee will keep on iterating until winning conditions are met
 * (whether through personality cards, random events or emptying the player
 * card deck).
 * @author Jason
 */
public class TurnIterator implements IVisitee {
	private static final int MINIMUM_NUMBER_CARDS = 5;
	IPlayer _currentPlayer;
	IVisitor _visitor;
	
	int i = 0;

	@Override
	public void accept(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		initializeVisit(visitor);
		visitor.visit(_currentPlayer);
		iterateTurn();
	}

	private void initializeVisit(IVisitor visitor) throws GameOverException, EntityNotSetException, InvalidOperationException {
		_currentPlayer = visitor.getCurrentPlayer();
		_visitor = visitor;
		
		IPersonalityCard currentPersonality = _visitor.getPersonalityCard(_currentPlayer.getPersonality());
		currentPersonality.accept(_visitor); // This verifies whether the card has met the winning conditions.
		System.out.println("Current player: " + _currentPlayer.getName() + " (" + _currentPlayer.getplayercolor() + ")");
		System.out.println("\t" + "Number of minions in hand: " + _currentPlayer.getNumberOfMinionsInHand());
		System.out.println("\t" + "Number of buildings in hand: " + _currentPlayer.getNumberOfBuildingsInHand());
		System.out.println("\t" + "Personality card: " + _currentPlayer.getPersonality().getDescriptiveName());
	}

	private void iterateTurn() throws GameOverException, EntityNotSetException, InvalidOperationException {
		cleanUpCurrentPlayer();
		_visitor.iteratePlayer();
		accept(_visitor);
	}

	private void cleanUpCurrentPlayer() throws GameOverException {
		for (int currentNumberCards = getNumberOfCards(); currentNumberCards < MINIMUM_NUMBER_CARDS; currentNumberCards++) {
			PlayerCardName drawnCard = _visitor.drawPlayerCard();
			_currentPlayer.addPlayerCard(drawnCard);
		}
	}

	private int getNumberOfCards() {
		int numberOfCards = _currentPlayer.getPlayerCards().length;
		for (PlayerCardName nameOfCardInDisplay : _currentPlayer.getPlayerCardsInDisplay()) {
			IPlayerCard cardInDisplay = _visitor.getPlayerCard(nameOfCardInDisplay);
			if (cardInDisplay.countsInHandSize())
				numberOfCards++;
		}
		
		return numberOfCards;
	}

	@Override
	public String getDescription() {
		return "Play";
	}
>>>>>>> 251b19f80ddb9a8ad8713f1b97db0dce37eba0fc
}