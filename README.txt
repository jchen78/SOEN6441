SOEN 6441 Project Build 3
@ Welcome to DiscWorld AnkhMork Version 1.0

Group 9 Memebers:
Chen, Jun-Duo
Aggarwal, Deepak Kumar
Taheri, Fariba
Taqizadeh Ganji, Elnaz


FILES
-------------------------------------------

                          src/game.engine	                        ::	main java classes

			        game.engine				::	main package
				
                                ActionName.java                         ::      List of action names
                                ActionType.java                         ::      List of action types
                                Bank.java				::	Represents Bank object for the game
				Card.java				::	Interface for all game cards
				CityAreaCard.java			::	Represents area of game board				
				GameManager.java			::	Handles requests from player
				IEntity.java				::	Represents the Game Entity(defined as an object from the physical game that possesses some predefined characteristics).
				MapArea.java				::	Handles game area as per the GameManager instruction	
				GameTest.java				::	Main class
				PersonalityCardDeck.java                ::      Represents the personality card Deck
                                Personality.java		        ::      Represents the personality cards
                                Player.java				::	Represents each player of the game
				PlayerCard.java				::	Represents the games player card
				RandomEventCard.java		        ::	Represents Random Event game card
				CardType.java				::	Represent the type of Card(Playable, Interrupt...)
                                ConcreteCreator.java			::      Concrete Creator 	
                                Dice.java				::	Random selection of Player which should play first
				IInterruptCard.java			::	Interrupt actiontype and Interrupt type
				IMoneyHolder.java			::	Handling the particular player money account 
				Map.java				::	Providing the all adjacent areas to the particular area
                                PlayerCardDeck.java                     ::      Player card in Deck
                                RandomEventCardDeck.java	        ::      Represents the Random Event card Deck		


                       src/game.Core

                              Enums
 	                      CityAreaData.java                         ::      City Area Card Names
	                      ICardName.java 	                        ::      Card Names
	                      PersonalityCardName.java                  ::	Personality Card Names 	
	                      PlayerCardName.java 	                ::      Player Card Names 	
                              RandomEventCardName.java 	                ::      Random Event Card Names 	

                        Interfaces
                               ICityArea.java 	                        :: 	//the action of depositing minions on the city area instance
	                       IGameInstance.java                       ::      Player card board, personality and randomcards management 	
	                       IPersonalityCard.java 	                ::      Interface represents a personality card. 	
	                       IPlayer.java 	                        ::      Represent player Interface 	
	                       IPlayerCard.java 	                ::      Interface represents player cards  	
	                       IRandomEventCard.java 	                ::      Interface represents Random event cards   	
	                       ISelectable.java 	                ::      Represent interface for select 

                        Io

 		               MenuSelector.java 	                ::      Menu selector	
	                       MessageOutputter.java 	                ::      Output the message
	                       MessageReceiver.java  	                ::      Receive the message
	                       PersistanceManager.java 	                :: 	Manager


			src/game/action/Sequence		        ::     Package contain all cards action to be performed
				
	                interfaces
				ICardVisitee.java			::     Visitees that are cards
				IVisitee.java				::     Visitees for the Visitor pattern (these are actions)
				IVisitor.java				::     Visitor (moves across different visitees)


                                PlayerCardAction.java		        :: 	Perform the player card action 
				PlaceMinion.java			::	Placing the minion on the particular area by checking its area condition
				PlayCard.java				::	Selecting card to play
				PlayInterruptCardAction.java            ::	Perform the Action of Interrupt card
				TakeMoneyFromOthers.java	        ::	Condition to take money from other as instructed by some PlayerCard
				TransferMoneyDataGatherer.java	        ::      Transfering money data to its main class
				PlaceEntityData.java		        ::	Placing the entity of particular player
				RemoveEntityData.java		        ::	Removing the entity of a particular player
			
			
		
			visitee
                             DiscardCard.java 	                         ::     Discard a card
                             DrawCardsFromDiscardPile.java 	         ::     Draw card from discard pile
	                     DrawCardsFromDrawDeck.java 	         ::     Draw card from deck
	                     EvaluateEscapeActionVisitee.java 	         ::     Evaluate space action
	                     GameOverException.java 	                 ::     Thrown when the game ends in mid-turn
	                     GetRandomAreaVisitee.java                   ::     Get Random area 
                             IgnoreRandomEvent.java 	                 ::     Ignore random Event
 	                     InterruptibleVisitee.java                   ::     Allow interruptions 	
	                     LookAtUnusedPersonalityCard.java 	         ::     Look at unused personality card
	                     MultipleActionSelector.java 	         ::     Used to group several actions, all of which may be executed in arbitrary order
	                     NoActionVisitee.java 	                 ::     No Action
	                     OptionalActionVisitee.java 	         ::     Optional Action
	                     PayMoneyToBank.java 	                 ::     Action for Pay Money To Bank to PayMoneyToBank.java 
	                     PlaceBuilding.java 	                 ::    Action to place a building 
	                     PlaceMinionVisitee.java 	                 ::    Used to place a minion on the board
	                     PlaceTrollsInArea.java 	                 ::    Action to place a Troll on the area affected
	                     PlaceTroubleMarker.java 	                 ::    Action to place a Trouble marker on the area affected
	                     PlayCardVisitee.java 	                 ::    Action for play a card
	                     RemoveAllBuildingsFromArea.java           	 ::    Action to remove Building of area affected
	                     RemoveAllDemonsFromArea.java                ::    Action to remove Demons of area affected
	                     RemoveAllMinionsFromArea.java 	         ::    Action to remove Minions of area affected
	                     RemoveAllTrollsFromArea.java 	         ::    Action to remove Trolls of area affected
	                     RemoveMinionVisitee.java                    ::    Used to remove a minion from the board	
	                     RemoveMoneyVisitee.java 	                 ::    Action to remove money
	                     RemoveTroubleMarker.java 	                 ::    Action to remove Trouble marker from the board
	                     RemoveTroubleMarkerFromArea.java 	         ::    Action to remove Trouble marker from area affected
	                     SelectionVisitee.java 	                 ::    Used to select one option from many possible actions
	                     SingleActionSelector.java                   ::    Used to group several actions which are mutually exclusive
	                     TakeCardsFromDrawDeck.java 	         ::    To Take card from Deck
	                     TakeCardsFromOtherPlayer.java 	         ::    To take card from other players
	                     TakeLoanFromBankVisitee.java 	         ::    Take loan from bank
	                     TakeMoneyForEachTroubleMarker.java 	 ::    Take money for each trouble marker on board
	                     TakeMoneyFromBank.java                      ::    Take money from Bank
                             TakeMoneyFromOthersVisitee.java 	         ::    Take money from others
	                     TakeMoneyOrCardFromEachPlayer.java 	 ::    Take money or card from others
	                     TurnIterator.java                           ::    Represents the actions a player may take during a turn
	                     WinningConditionVisitor.java                ::    Traverses the different players to determine if there is a winner	

				
			visitor
				MainVisitor.java			::     Implementation of IVisitor
				Printer.java				::     Prints out the options from which to select
				Selector.java				::     Accepts input from the user, and resolves the input to a corresponding IVisitee
			
		       Exception Handling
			        src/game.error			        ::	Main Package for all Exceptions
				BankException.java		        ::	Handling exception for BankError class
				EntityNotSetException		        ::	Override in InvalidOperationException
				InvalidEntityNameException	        ::	Override in InvalidOperationException
				InvalidOperationException	        ::	Handling EntityNotSetException and InvalidEntityNameException
		
		Game Error
			src/game.error.code		                ::	Package for the bank error class
				BankError.java			        :: 	Handling the bank limit	
		
		Files
			input.txt
				
		Test_Cases
			src/Test_Cases			::	test java classes			
			Test_Cases			::	main package
			BankTests.java			::	Test bank class
			MapAreaTests.java		::	Test card (personality card)
			PlayerCardTests.java		::	Test card (city card)
                        TurnTests.java 	                ::      Test turns
                        ConcreteCreatorTest.java        ::      Test Concrete creator
                	CityAreaCardTest.java           ::      Test Area cards
			
		

INSTALL
-------------------------------------------
a. Clone the project at your machine
b. Make sure you have the junit-4.10 plugin installed, it's what Eclipse uses to integrate for jUnit test cases. 
If you don't have it search for it in the Eclipse Marketplace (Help -> Eclipse Marketplace, find it there and install it)
c. Import the project into Eclipse: File -> Import -> (General -> Existing Projects into Workspace) 
