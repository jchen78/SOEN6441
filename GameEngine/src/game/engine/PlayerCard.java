package game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import game.core.enums.PlayerCardName;
import game.core.interfaces.IPlayerCard;
import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.visitee.DiscardCard;
import game.action.sequence.visitee.DrawCardsFromDiscardPile;
import game.action.sequence.visitee.DrawCardsFromDrawDeck;
import game.action.sequence.visitee.LookAtUnusedPersonalityCard;
import game.action.sequence.visitee.OptionalActionVisitee;
import game.action.sequence.visitee.PlaceBuilding;
import game.action.sequence.visitee.PlaceMinionVisitee;
import game.action.sequence.visitee.PlayCardVisitee;
import game.action.sequence.visitee.RemoveMinionVisitee;
import game.action.sequence.visitee.RemoveTroubleMarker;
import game.action.sequence.visitee.TakeCardsFromDrawDeck;
import game.action.sequence.visitee.TakeCardsFromOtherPlayer;
import game.action.sequence.visitee.TakeLoanFromBankVisitee;
import game.action.sequence.visitee.TakeMoneyForEachTroubleMarker;
import game.action.sequence.visitee.TakeMoneyFromBank;
import game.action.sequence.visitee.TakeMoneyFromOthersVisitee;
import game.action.sequence.visitee.TakeMoneyOrCardFromEachPlayer;
import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

/**
 * This class represents the green- or brown-bordered player cards. It is yet incomplete: only arbitrary (temporary) names and border color are stored.
 */
public class PlayerCard extends Card implements IPlayerCard {
	private ArrayList<String> symbols = new ArrayList<String>();
	private String explanation;
	private PlayerCardName _playerCardName;
	private CardType _cardType;
	
	
	public PlayerCard(String cardName) {
		this(PlayerCardName.valueOf(cardName));
	}

	public PlayerCard(PlayerCardName name) {
		// TODO: manage cardType
		allActions = new LinkedList<IVisitee>();
		_playerCardName = name;
		_cardName = name.getValue();
		switch (name) {
		case MrBoggis:
			_cardType = CardType.Playable;
			allActions.add(new OptionalActionVisitee(new TakeMoneyFromOthersVisitee(2, ActionType.PlayerCardText, ActionName.TakeMoney)));
			allActions.add(new OptionalActionVisitee(new PlaceMinionVisitee(ActionType.PlayerCardIcon)));
			break;
		case MrBent:
			allActions.add(new TakeLoanFromBankVisitee());
			allActions.add(new PlayCardVisitee());
		//	this.allActions.add(new Scroll(ActionType.PlayerCardIcon));
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place this card in front of you and take a loan of $10 from the bank and at the end of the game you must payback $12 or lose 15 points";
			break;
		case TheBeggarsGuild:
			allActions.add(new TakeCardsFromOtherPlayer());
			allActions.add(new PlaceMinionVisitee(ActionType.PlayerCardIcon));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select one player, they must give you two cards of their choice";
			break;
		case TheBankOfAnkhMorpork:
			allActions.add(new TakeLoanFromBankVisitee());
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place this card infront of you and take a loan of $10 from the bank and at the end of the game you must payback $12 or lose 15 points";
			break;
		case TheAnkhMorporkSunshineDragonSanctuary:
			allActions.add(new TakeMoneyOrCardFromEachPlayer());
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Each player must give you either $1 or one of their cards";
			break;
		case SergeantAngua:
			allActions.add(new RemoveTroubleMarker());
			allActions.add(new PlayCardVisitee());
			symbols.add("Remove one Trouble marker");
			symbols.add("Play another card");
			break;
		case TheAgonyAunts:
			allActions.add(new RemoveMinionVisitee(_cardName, _cardName, null));
			allActions.add(new TakeMoneyFromBank());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Assassination");
			symbols.add("Take money $2");
			symbols.add("Place a minion");
			break;
		case TheDysk:
			allActions.add(new PlaceBuilding());
		//	allActions.add(new TakeMoneyForEachMinion());
			symbols.add("Place a Building");
			symbols.add("Scroll");
			explanation = "Each $1 for each minion in The Isle of Gods";
			break;
		case TheDuckman:
		//	allActions.add(new MoveMinionToAdjacent());
			symbols.add("Scroll");
			explanation = "Move a minion belonging to another player from one area to adjacent area";
			break;
		case Drumknott:
			//allActions.add(new PlayAnyTwoCardVisitee());
			symbols.add("Scroll");
			explanation = "Play any two other cards from your hand";
			break;
		case CMOTDibbler:
			//allActions.add(new RollDice());
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll a die. On a roll of '7' or more you take $4 from the band and On the roll of '1' give '$1' to the bank or remove one of your minion from the board. All other results have no effects";
			break;
		case DrCruces:
			allActions.add(new RemoveMinionVisitee(_cardName, _cardName, null));
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Assassination");
			symbols.add("Take money $3");
			break;
		case CaptainCarrot:
			allActions.add(new PlaceMinionVisitee(null));
			allActions.add(new RemoveTroubleMarker());
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Place a minion");
			symbols.add("Remove one trouble marker");
			symbols.add("Take money $1");
			break;
		case MrsCake:
			allActions.add(new LookAtUnusedPersonalityCard());
			allActions.add(new TakeMoneyFromBank());
			allActions.add(new PlaceBuilding());
			symbols.add("Scroll");
			symbols.add("Take money $2");
			symbols.add("Place a building");
			explanation = "Look at all but one of the unused personality card";
			break;
		case Groat:
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Place a minion");
			break;
		case GimletsDwarfDelicatessen:
			allActions.add(new TakeMoneyFromBank());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Take money $3");
			symbols.add("Place a minion");
			break;
		case Gaspode:
			//allActions.add(new Interrupt());
			symbols.add("Interrupt");
			explanation = "Stop a player from moving or removing one of your minion";
			break;
		case FreshStartClub:
			//allActions.add(new Interrupt());
			symbols.add("Interrupt");
			explanation = "If you have a minion removed you can place him in a different area";
			break;
		case FourOleRon:
			//allActions.add(new MoveOtherMiniontoAdjacent());
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Move a minion belonging to another player from one area to an adjacent area";
			break;
		case TheFoolsGuild:
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select another player. If they donot give you $5 then place this card infront of them. This card now counts towards their hand size of five cards when they come to refill their hand. They cannot get rid of this card";
			break;
		case TheFireBrigade:
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Choose a player. If they donot give you $5 then you can remove one of his building from the board";
			break;
		case InigoSkimmer:
			allActions.add(new RemoveMinionVisitee(_cardName, _cardName, null));
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Assassination");
			symbols.add("Take money $2");
			break;
		case HistoryMonks:
			allActions.add(new DrawCardsFromDiscardPile());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Shuffle the discard pile and draw four cards randomly and place the remaining cards back as the discard pile";
			break;
		case Hex:
			allActions.add(new PlaceBuilding());
			allActions.add(new DrawCardsFromDrawDeck());
			symbols.add("Scroll");
			symbols.add("Place a Building");
			explanation = "Take 3 cards from the draw deck";
			break;
		case HereNNow:
		//	allActions.add(new RollDice());
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll a die. On a roll of '7' or more you take $3 from the band and On the roll of '1' remove one of your minion from the board. All other results have no effects";
			break;
		case HarryKing:
			allActions.add(new PlaceMinionVisitee(null));
		//	allActions.add(new TakeMoneyFromDiscardCard());
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Discard as many card as you wish and take $2 for each discarded";
			break;
		case HargasHouseOfRibs:
			allActions.add(new TakeMoneyFromBank());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Take money $3");
			symbols.add("Place a minion");
			break;
		case MrGryle:
			allActions.add(new RemoveMinionVisitee());
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Assassination");
			symbols.add("Take money $1");
			break;
		case ThePeeledNuts:
			// TODO
			break;
		case TheOperaHouse:
			allActions.add(new PlaceBuilding());
		//	allActions.add(new TakeMoneyForEachMinion());
			symbols.add("Place a building");
			symbols.add("Scroll");
			explanation = "Earn $1 for each minion in The Isle of Gods";
			break;
		case NorryNobbs:
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			allActions.add(new PlayCardVisitee());
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Take $3 from the player of your choice";
			break;
		case Modo:
			allActions.add(new DiscardCard());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Discard one card";
			break;
		case TheMendedDrum:
			allActions.add(new PlaceBuilding());
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Place a building");
			symbols.add("Take money $2");
			break;
		case Librarian:
			allActions.add(new TakeCardsFromDrawDeck());
			symbols.add("Scroll");
			explanation = "Take four cards from the draw deck";
			break;
		case LeonardOfQuirm:
			allActions.add(new TakeCardsFromDrawDeck());
			symbols.add("Scroll");
			explanation = "Take four cards from the draw deck";
			break;
		case ShonkyShop:
			allActions.add(new DiscardCard());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Discard as many card as you wish and take $1 for each one discarded";
			break;
		case SacharissaCrisplock:
			allActions.add(new TakeMoneyForEachTroubleMarker());
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Earn $1 for each trouble marker on the board";
			break;
		case RosiePalm:
			allActions.add(new PlaceMinionVisitee(null));
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Choose one Player. Give them one of of your cards. Thwy must give you $2 in return";
			break;
		case Rincewind:
		//	allActions.add(new RandomEvent());
		//	allActions.add(new MoveMinionToAdjacent());
			allActions.add(new PlayCardVisitee());
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Place another card");
			explanation = "Move one of your minion from an area containing a trouble marker to an adjacent area";
			break;
		case TheRoyalMint:
			allActions.add(new PlaceBuilding());
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Place a building");
			symbols.add("Take money $5");
			break;
		case QueenMolly:
			allActions.add(new PlaceMinionVisitee(null));
		//	allActions.add(new SelectPlayerToGetCards());
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Select one player. They must give you two cards of their choice";
			break;
		case PinkPussycatClub:
			allActions.add(new TakeMoneyFromBank());
			allActions.add(new PlayCardVisitee());
			symbols.add("Take money $3");
			symbols.add("Play another card");
			break;
		case ZorgoTheRetrophrenologist:
		//	allActions.add(new ExchangePensonality());
			allActions.add(new PlaceBuilding());
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "You may exchange your Personality card with one drawn randomly from those not in use";
			break;
		case DrWhiteface:
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select another player. If they donot give you $5 then place this card infront of them. This card now counts towards their hand size of five cards when they come to refill their hand. They cannot get rid of this card";
			break;
		case WallaceSponky:
		//	allActions.add(new Interrupt());
			symbols.add("Interrupt");
			explanation = "You cannot be affected by the text on a card played by another player";
			break;
		case TheSeamstressesGuild:
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Choose one player. Give them one of your cards. They must give you $2 in return";
			break;
		case MrPinAndMrTulip:
			allActions.add(new RemoveMinionVisitee());
			allActions.add(new TakeMoneyFromBank());
			symbols.add("Assassination");
			symbols.add("Take money $1");
			explanation = "The New Firm";
			break;
		case TheThievesGuild:
			allActions.add(new TakeMoneyFromOthersVisitee(0, null, null));
			allActions.add(new PlaceMinionVisitee(null));
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Take $2, if possible from every other player";
			break;
		case SergeantCheeryLittlebottom:
			symbols.add("Scroll");
			symbols.add("Remove one trouble marker");
			explanation = "Take two cards from the draw deck";
			break;
		case OttoChriek:
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Earn $1 for each trouble marker on the board";
			break;
		case TheClacks:
			symbols.add("Scroll");
			symbols.add("Take money $2");
			symbols.add("Place another card");
			explanation = "Take two cards from the draw deck";
			break;
		case SergeantColon:
			symbols.add("Remove one trouble marker");
			symbols.add("Place a minion");
			break;
		case CosmoLavish:
			symbols.add("Scroll");
			symbols.add("Place another card");
			explanation = "Pay another player $2. They must then remove one minion of their choice(not one of yours) with an area trouble marker in it";
			break;
		case TheDean:
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Place another card");
			explanation = "Remove one minion from unreal estate";
			break;
		case HELLO:
			symbols.add("Assassination");
			symbols.add("Assassination");
			symbols.add("Place a Building");
			break;
		case BurleighAndStronginthearm:
			symbols.add("Scroll");
			symbols.add("Place a Building");
			explanation = "Pay a player of your choice $2. Then choose a minion to assassinate";
			break;
		case TheBursar:
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Exchange the positions of any two minion on the board";
			break;
		case CableStreetParticulars:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select one player. Look at his cards and choose one of them to be discarded";
			break;
		case CantingCrew:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Move a minion belonging to another player from one area to an adjacent area";
			break;
		case Carcer:
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll the die twice and remove one minion of your choice from those areas even if there is no trouble there";
			break;
		case TheChairOfIndefiniteStudies:
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Exchange your hand with that of another player";
			break;
		case SirCharlesLavatory:
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Earn $1 for each building on the board(any color)";
			break;
		case Dorfl:
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Move one of your minion from one area to any other area on the board";
			break;
		case SergeantDetritus:
			symbols.add("Remove one trouble marker");
			symbols.add("Remove one trouble marker");
			break;
		case DeepDwarves:
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place a minion in any area and donot place a trouble marker";
			break;
		case AdoraBelleDearheart:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			symbols.add("Place a building");
			explanation = "Move one of your minion from one area to any other area on the board";
			break;
		case TheAlchemistsGuild:
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Discard upto three cards and refill your hand to five cards";
			break;
		case TheAuditors:
			symbols.add("Scroll");
			explanation = "Every other player, in player order, must remove one of their minion from the board";
			break;
		case BaggySwipes:
			symbols.add("Remove a trouble marker");
			break;
		case Susan:
			symbols.add("Interrupt");
			explanation = "Stop one of your minions from being removed from the board";
			break;
		case SybilVimes:
			symbols.add("Take money $3");
			symbols.add("Scroll");
			explanation = "Replace another player's building with one of your own. Pay the cost of the building to the original owner. It must be an area that does not have a trouble marker ";
			break;
		case MrTeatime:
			symbols.add("Take Money $3");
			symbols.add("Assassination");
			symbols.add("Play another card");
			break;
		case TheWatch:
			symbols.add("Place a building");
			symbols.add("Remove one trouble marker");
			break;
		case WeeMadArthur:
			symbols.add("Scroll");
			explanation = "You may build a building for a half price";
			break;
		case WilliamDeWorde:
			symbols.add("Place a minion");
			symbols.add("Scroll");
			explanation = "Earn $1 for each trouble marker on the board";
			break;
		case Willikins:
			symbols.add("Scroll");
			explanation = "Place one minion in an area you have a building";
			break;
		case ArchchancellorRidcully:
			symbols.add("Random Event");
			symbols.add("Scroll");
			explanation = "Place one or two minions in or adjacent to Unreal Estate ";
			break;
		case Ruby:
			symbols.add("Place a minion");
			symbols.add("Place a building");
			break;
		case TheSeniorWrangler:
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place one minion in or adjacent to Unreal Estate";
			break;
		case MrShine:
			symbols.add("Scroll");
			explanation = "Place a minion in any area and donot place a trouble marker";
			break;
		case MrSlant:
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Choose a area containing a trouble marker and receive $2 for each minion there";
			break;
		case TheSmokingGnu:
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place one minion in an area containing a trouble marker";
			break;
		case Stanley:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Select two cards randomly from one player and choose one to keep. Return the another card";
			break;
		case MoistVonLipwig:
			symbols.add("Place a minion");
			symbols.add("Take Money $3");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Take two cards from the draw deck";
			break;
		case DoctorMossyLawn:
			symbols.add("Interrupt");
			explanation = "Interrupt your own turn. Your turn finishes but donot discard the card you played before this one";
			break;
		case PatriciansPalace:
			symbols.add("Place a building");
			symbols.add("Take money $4");
			symbols.add("Place a minion");
			break;
		case PonderStibbons:
			symbols.add("Random Event");
			symbols.add("Scroll");
			explanation = "Play any two cards from your hand";
			break;
		case ThePostOffice:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Take $1 for each building on the board";
			break;
		case ReacherGilt:
			symbols.add("Scroll");
			explanation = "You can replace other player's building with one of your own. Pay the cost of the building to the original owner. It must be an area that has trouble marker in it";
			break;
		case ProfessorOfRecentRunes:
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Take two cards from the draw deck";
			break;
		case DoctorHix:
			symbols.add("Random Event");
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Place a trouble marker in an area of your choice";
			break;
		case HobsonsLiveryStable:
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Pay $2 to a player of your choice. Then move one of your minions to any area you wish";
			break;
		case Hubert:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			explanation = "Force one player to give another player $3(you cannot choose yourself)";
			break;
		case Igor:
			symbols.add("Interrupt");
			explanation = "If you have a minion removed you can place him in a different area";
			break;
		case TheLuggage:
			symbols.add("Assassination");
			symbols.add("Scroll");
			explanation = "Discard one card";
			break;
		case TheMob:
			symbols.add("Scroll");
			symbols.add("Place a minion");
			symbols.add("Play another card");
			explanation = "Place one trouble marker in an area adjacent to one already containing a trouble marker";
			break;
		case LordDowney:
			symbols.add("Assassination");
			symbols.add("Take money $3");
			symbols.add("Place a building");
			break;
		case Dwarves:
			symbols.add("Place a minion");
			symbols.add("Place a minion");
			break;
		case EdwardDeath:
			symbols.add("Assassination");
			symbols.add("Take money $3");
			symbols.add("Place a building");
			break;
		case Errol:
			symbols.add("Scroll");
			symbols.add("Play another card");
			explanation = "Roll a die. On the roll of '7' or more you remove a minion of your choice from an area containing a trouble marker. On a roll of a '1' you must remove one of your own minions";
			break;
		case Gargoyles:
			symbols.add("Scroll");
			symbols.add("Place a building");
			explanation = "Draw one card for each building you have on the board";
			break;
		}
	}
	private LinkedList<IVisitee> allActions;

	@Override
	public void setEntity(String entityName) throws InvalidEntityNameException {
		if (entityName == null)
			throw new InvalidEntityNameException();
		
		_playerCardName = PlayerCardName.valueOf(entityName);
	}

	/**
	 * Gets the border color, as loaded when the <code>setEntity</code> method was called.
	 * @see setEntity
	 * @return Border color ("Green" or "Brown").
	 * @throws EntityNotSetException Thrown when this method is called before the <code>setEntity</code> method.
	 */
	public String getBorderColor() throws EntityNotSetException {
		if (_playerCardName == null)
			throw new EntityNotSetException();

		return _playerCardName.getColor();
	}

	@Override
	public String getDescription() {
		return _playerCardName.getDescriptiveName();
	}

	@Override
	public CardType getCardType() throws EntityNotSetException {
		return _cardType;
	}

	@Override
	public IVisitee[] getActions() {
		return allActions.toArray(new IVisitee[allActions.size()]);
	}

	@Override
	public PlayerCardName getName() {
		return _playerCardName;
	}
}