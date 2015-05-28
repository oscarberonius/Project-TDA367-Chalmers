package view.gameStates.playfieldGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import view.gameStates.playfieldGUI.GUI_Foundation;
import model.entity.players.Player;


/*
 * Gör att man har ett skal som updateras hela tiden och sedan ritar
 * bara denna klassen över specfik ruta som annars har en "grå ram"
 * till en "markerad ram"
 */

/**
 * Candy_Bar
 * 
 * @author  Kim Berger
 * @version 1.0
 */
public class Candy_Bar extends GUI_Super {
	
	// Original box-sizes for our menu.
	private float squareSizeX;
	private float squareSizeY;
	
	// number box
	private float nbrBoxPosX;
	private float nbrBoxPosY;
	private float nbrBoxSize;
	
	// Candy icon position
	private float iconPosX;
	private float iconPosY;
	
	// numberPos
	private float nbrPosX;
	private float nbrPosY;
	
	// used to get how many unlocked candies the user has.
	private Player player;
	
	// Using this for getting the cam-position.
	private OrthographicCamera cam;

	// Used for having strings on-screen.
	private SpriteBatch batch;
	private BitmapFont textFont;

	@SuppressWarnings("deprecation")
	public Candy_Bar(OrthographicCamera cam, GUI_Foundation gui) {
		
		this.cam = cam;
		
		// Startpoint lower-left corner on the "candy-part" of the bar.
		x = gui.getCandyBarStartPointX();
		y = gui.getCandyBarStartPointY();
		
		// size of the square we will draw (same as GUI_Foundation).
		squareSizeX = gui.getSquareSizeX();
		squareSizeY = gui.getSquareSizeY();

		iconPosX = x + (squareSizeX / 3) + 5;
		iconPosY = y + (squareSizeY / 2);
		
		// The small box with corresponding number to candy.
		nbrBoxSize = 20;
		nbrBoxPosX = x + squareSizeX - nbrBoxSize;
		nbrBoxPosY = y + squareSizeY - nbrBoxSize;
		
		nbrPosX = nbrBoxPosX + (nbrBoxSize / 3);
		nbrPosY = nbrBoxPosY + ((nbrBoxSize / 5) * 4);
		
		batch = new SpriteBatch();

		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("src/main/resources/fonts/OpenSans-CondLight.ttf"));
		
		textFont = gen.generateFont(16);
		textFont.setColor(Color.YELLOW);
	}

	/*
	 * So candyBar first paints all unlocked candies, and give colors to the
	 * chosen candy (rest will be gray), and also shows a corresponding number
	 * to unlocked candies.
	 */
	public void render(ShapeRenderer sr) {

		// Start to draw strings.
		batch.setProjectionMatrix(cam.combined);

		sr.begin(ShapeType.Line);
		sr.setAutoShapeType(true);
		
		// Unlocked one candy, so paintorder: outerBox, number-box, number
//		if(player.getCandyData().size() == 1){
		if(true) {
			
			sr.setColor(Color.GRAY);
			sr.set(ShapeType.Filled);
			sr.circle(iconPosX, iconPosY, 11);
			sr.triangle(iconPosX, iconPosY, iconPosX-25, iconPosY+5, iconPosX-5, iconPosY+25);
			sr.triangle(iconPosX, iconPosY, iconPosX+25, iconPosY-5, iconPosX+5, iconPosY-25);
			
			sr.setColor(Color.GREEN);
			sr.set(ShapeType.Line);
			sr.rect(x, y, squareSizeX, squareSizeY);
			sr.rect(nbrBoxPosX, nbrBoxPosY, nbrBoxSize, nbrBoxSize);
			
			batch.begin();
			textFont.draw(batch, "1", nbrPosX, nbrPosY);
			batch.end();
		}
		
		// unlocked two candies
//		if(player.getCandyData().size() == 2){	
		if(true) {

			sr.setColor(Color.GRAY);
			sr.set(ShapeType.Filled);
			sr.circle(iconPosX + squareSizeX, iconPosY, 21);

			sr.setColor(Color.GREEN);
			sr.set(ShapeType.Line);
			
			sr.rect(x + squareSizeX, 
					y, 
					squareSizeX, 
					squareSizeY);
			
			sr.rect(nbrBoxPosX + squareSizeX, 
					nbrBoxPosY, 
					nbrBoxSize, 
					nbrBoxSize);
			
			batch.begin();
			textFont.draw(batch, "2", 
					nbrPosX + squareSizeX, 
					nbrPosY);
			batch.end();
		}
		
		// unlocked three candies
//		if (player.getCandyData().size() == 3) {
		if(true) {

			sr.rect(x + squareSizeX * 2, y, squareSizeX, squareSizeY);
			
			sr.rect(nbrBoxPosX + squareSizeX*2, 
					nbrBoxPosY, 
					nbrBoxSize, 
					nbrBoxSize);
			
			batch.begin();
			textFont.draw(batch, "3", 
					nbrPosX + squareSizeX*2, 
					nbrPosY);
			batch.end();
		}
		
		// determine which candy is selected, and show that.
//		switch(player.getSelectedCandy()){
		int one = 2;
		switch(one){
		
		// First candy
		case 1:

			sr.setColor(Color.CYAN);
			sr.set(ShapeType.Filled);
			
			sr.circle(iconPosX, iconPosY, 11);
			sr.triangle(iconPosX, iconPosY, iconPosX-25, iconPosY+5, iconPosX-5, iconPosY+25);
			sr.triangle(iconPosX, iconPosY, iconPosX+25, iconPosY-5, iconPosX+5, iconPosY-25);

			sr.setColor(Color.ORANGE);
			sr.set(ShapeType.Line);			
			
			sr.rect(x, y, squareSizeX, squareSizeY);
			sr.rect(nbrBoxPosX, nbrBoxPosY, nbrBoxSize, nbrBoxSize);
			break;
			
		// Second candy is selected
		case (2):
			
			// brown color
			sr.setColor(0.40f,0.20f,0,1);
			sr.set(ShapeType.Filled);
			sr.circle(iconPosX + squareSizeX, iconPosY, 21);

			sr.setColor(Color.WHITE);
			sr.set(ShapeType.Line);
			sr.circle(iconPosX + squareSizeX, iconPosY, 21);
			sr.circle(iconPosX + squareSizeX, iconPosY, 14);

			sr.set(ShapeType.Filled);
			sr.circle(iconPosX + squareSizeX, iconPosY, 5);

			sr.setColor(Color.ORANGE);
			sr.set(ShapeType.Line);
			sr.rect(x + squareSizeX, y, squareSizeX, squareSizeY);
			sr.rect(nbrBoxPosX + squareSizeX, 
					nbrBoxPosY, 
					nbrBoxSize, 
					nbrBoxSize);

			break;
			
		// Third candy is selected
		case 3:

			sr.setColor(Color.ORANGE);
			sr.set(ShapeType.Line);	
			
			sr.rect(x + squareSizeX * 2, y, squareSizeX, squareSizeY);
			
			sr.rect(nbrBoxPosX + squareSizeX*2, 
					nbrBoxPosY, 
					nbrBoxSize, 
					nbrBoxSize);

			break;
			
		default:
//			System.out.println("Candy_Bar.render: "
//					+ "tried to select a candy which don't exists, number: " 
//					+ player.getSelectedCandy());
			break;
		}
		
//		batch.end();
		sr.end();
	}

}