package game.engine;

import game.error.BankException;
import game.error.EntityNotSetException;
import game.error.InvalidEntityNameException;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	public String playerName;
	public String playerColor;
	public int numofPlayerBuildings;
	int buildingCost;
	public int numofPlayerMinions;
	public String playerPersonality;
	public int playerMoney;
	public int playerId;
	public ArrayList<String> playerCards;
	private static Bank bankAccsess = new Bank ();
	//Map mapA= new Map();
	PlayerCard cardSet = new PlayerCard();
	static Player[] players= new Player[4];

	
	
   public Player(String name, String color, int minions, int buildings, String personality, int money, int Id, ArrayList<String> initialCards) {
		this.playerName = name;
		this.playerColor = color;
		this.numofPlayerMinions = minions;
		this.numofPlayerBuildings = buildings;
		this.playerPersonality = personality;
		this.playerMoney = money;
		this.playerId=Id;
		this.playerCards= initialCards;
		
		}
   
   public static void main(String [] args){
	   int i;
		//players[0]=new Player("...." );
		//players[1]=new Player( ".....");
		//players[2]=new Player( ".....");
		//players[3]=new Player("......");
		
	   // print the list of players
	   System.out.println(" The players of the game are:");
		for (i=0; i<4; i++){
	     System.out.println("Player number "+ i + ": " + players[i].getName()); }
   }
   
   // Minion
   void addMinion( int minionCount,int areaIndex){
		if (minionCount <= numofPlayerMinions)
				{
			//mapA.area[areaIndex].addMinion(playerId, minionCount);	
			 numofPlayerMinions-= minionCount;
				}
		else
		{
			System.out.println("No enough available minions!!!");
		}
   }
	 
	   void removeMinion(int minionCount, int areaIndex){
		   //mapA.area[areaIndex].removeMinion(playerId, minionCount);
		  numofPlayerMinions+= minionCount;
     }
   
   // Trouble
   void addTroubleMarker(int areaIndex){
//	 
//	   if (mapA.area[areaIndex].isTroubleMarker() == true){
//		   System.out.println("Unable to add TroubleMarker, there is already a troubleMarker in area []: "+ areaIndex);
//	   }
//	   else
//	   {
//		   mapA.area[areaIndex].setTroubleMarker(true);
//	   }
   }
		  
   void removeTroubleMarker(int areaIndex){
//	   mapA.area[areaIndex].setTroubleMarker(false);
	   }
   
   // Building
   void removeBuilding(int areaIndex){
	 
//	   mapA.area[areaIndex].removeBuilding(playerId);
	   numofPlayerBuildings+= 1;
   }
      
   void addBuilding(int areaIndex){
	   int NoBuilding;
	   int buildingCost = 0;
	   boolean withdrawResult=false;
//	   buildingCost= mapA.area[areaIndex].getBuildingCost();
//	   NoBuilding=mapA.area[areaIndex].getNoOfBuildings();
	   
	   if (buildingCost < playerMoney){
		   
	       System.out.println("There is Enough Money to add building");  
//		   if (!((NoBuilding==1) || (mapA.area[areaIndex].isTroubleMarker()==true)))
//		   {
//			    mapA.area[areaIndex].addBuilding(playerId);
//				playerMoney-=mapA.area[areaIndex].getBuildingCost();
//				System.out.println("Bulding added to Area ["+ areaIndex+ " ]"); 
//		   }
//		   else{
//			    System.out.println("Already a bulding or a trouble marker exists in this area , new bulding can not be added");
//		   }
	   }  
	   else { 
		    withdrawResult= takeMoney(buildingCost);
		     if (withdrawResult=true){
		    	System.out.println("Money is withdrawn successfully for adding building");
//			          if (!((NoBuilding==1) || (mapA.area[areaIndex].isTroubleMarker()==true))){
//			               mapA.area[areaIndex].addBuilding(playerId);
//					       playerMoney-=mapA.area[areaIndex].getBuildingCost();
//					       System.out.println("Bulding added to Area ["+ areaIndex+ " ]");  
//			          }else {
//			        	  System.out.println("Already a bulding or a trouble marker exists in this area , new bulding can not be added");
//			          }
			} 
		    else {
					System.out.println("Money could'nt get withdrawn");
			}
			   
		}
		    	   
	
	}
	       
   // Money
   public boolean takeMoney(int buildingCost){
	   boolean withdrawResult=false;
//	   try {
//		withdrawResult =bankAccsess.withdraw(buildingCost);
//	} catch (BankException e) {
//		e.printStackTrace();
//	}
	   return withdrawResult;
   }
   
        public int getPlayerId() {
		  return playerId;
	    }

	    public void setPlayerId(int id) {
		   this.playerId = id;
	    }
	    
    	public String getName() {
			return playerName;
		}

		public void setName(String name) {
			this.playerName = name;
		}
		
		public String getplayercolor() {
			return playerColor;
		}
		
		public void setplayercolor(String playercolor) {
			this.playerColor = playercolor;
		}
		public int getnumofminions() {
			return numofPlayerMinions;
		}

		public void setnumofminions(int numofminions) {
			this.numofPlayerMinions = numofminions;
		}

		public int getnumofBuildings() {
			return numofPlayerBuildings;
		}

		public void setnumofbuildings(int numofbuildings) {
			this.numofPlayerBuildings = numofbuildings;
		}
		
		public String getPersonality() {
			return playerPersonality;
		}

		public void setpersonality(String personality) {
			this.playerPersonality = personality;
		}
		public int getPlayerMoney() {
			return playerMoney;
		}

		public void setPlayerMoney(int amountofmoney) {
			this.playerMoney = amountofmoney;
		}
		
		public ArrayList<String> getPlayerCards() {
			return playerCards;
		}

		public void setPlayerCards(ArrayList<String> cardNames) {
			this.playerCards = cardNames;
		}
		
		
		// prints the name, color, personality and the cards of all players 		
		void GetCurrentState(){
			int i,j;
			
			String cardColor="NO COLOR";
			int cardIndex=-1;
			String nameP;
			String colorP, personalityP;
			
			// assuming there are 4 players (j is number of total players)
			for (j=0; j<4; j++)
			{
				System.out.println("Player " +j +" : ");
				System.out.println("---------------------------------------");
				nameP=players[j].getName();
				System.out.println("Player name is: "+ nameP );
				colorP=players[j].getplayercolor();
				System.out.println("Player color is:  "  + colorP);
				personalityP=players[j].getPersonality();
				System.out.println("Player personality is:  "  + personalityP);
				
				// is the number of cards the player has
			    for (i=0; i<playerCards.size(); i++){
				  try {
					players[j].cardSet.setEntity(playerCards.get(i));
					System.out.println("Player cards are:");
					try {
						cardColor=cardSet.getBorderColor();
						System.out.print("Card ["+ i+ " ] : " + cardColor +",  ");
					} catch (EntityNotSetException e) {
						e.printStackTrace();
					}
//					 try {
//						cardIndex=cardSet.getCardIndex();
//						System.out.println(cardIndex);
//					} catch (EntityNotSetException e) {
//						
//						e.printStackTrace();
//					}
				} catch (InvalidEntityNameException e) {
						e.printStackTrace();
				}
					    
		     }// END FOR 2
	}//end for 1
     

		
}
				
}
