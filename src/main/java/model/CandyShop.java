package model;

import model.entity.players.Player;

public class CandyShop {

	/*
	 * No data in view, so info about what item in the shop that is marked (currentRow/Col) must be here.
	 * Use the following rows/cols in view:
	 * 				JellyBean						row = -1
	 * Speed	Damage	Spread	Penetration			row = 0
	 *   1		  2		  3			4				row = 1
	 *   1		  2		  3			4				row = 2
	 *   1		  2		  3			4				row = 3
	 *   1		  2		  3			4				row = 4
	 *   		  StartNewLevel 					row = 5
	 * col=1   col=2    col=3    col=4
	 */
	
	/*
	 * Wishlist on methods
	 * 
	 * getCandyShop()		in Model, that returns reference to this object
	 * 
	 * int getCurrentRow()
	 * int getCurrentCol()
	 * String getSelectedCandy()
	 * 
	 * String getStatus(int row, int col)	is only called for row=1-4 (i.e. upgrades), returns a String 
	 * 										("have"/"buy"/"not") depending on the status of the upgrade
	 * String getUpgradeName(int row, int col)	returns the name of the row:th upgrade for property col
	 * 
	 * int getMoney()		returns the amount of money that the player has
	 * 
	 * String getInfo()		returns info (e.g. info about a candy). Should return a welcome message (see hard coded 
	 * 						info in view) before user chooses to view info about something else.
	 * 
	 * move(int)			that takes 0-3 representing LURD, and changes currentRow/Col (if row = -1, it changes candy)
	 * choose()				that buys the upgrade if an upgrade is marked, and sets info if property is marked				
	 * 
	 */
    private static CandyShop cs;

    private Player player;

    private int currentRow;
    private int currentCol;
    private String selectedCandyInShop;

	private CandyShop(){
        currentRow = 1;
        currentCol = 1;
        selectedCandyInShop = "Jellybean";
    }
    public void changePlayer(Player p){
        player = p;
    }

    public static CandyShop getInstance(){
        if(cs== null)
            cs = new CandyShop();
        return cs;
    }

    public Player getBrowsingPlayer(){
        return player;
    }

    public void move(int step){
        switch(step) {
            case 0:
                if (currentCol > 1)
                    currentCol--;
                break;
            case 1:
                if (currentRow > -2)
                    currentRow--;
                break;
            case 2:
                if (currentCol < 4)
                    currentCol++;
                break;
            case 3:
                if (currentRow < 5)
                    currentRow++;
                break;

        }
    }

    public int getCurrentRow(){
        return currentRow;
    }

    public int getCurrentCol(){
        return currentCol;
    }

    public String getSelectedCandyInShop(){
        return selectedCandyInShop;
    }

    public String getStatus(){
        return "0";
    }

    public int getMoney(){
        return player.getMoney();
    }

}
