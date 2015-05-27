package view;

import model.Model;
import view.gameStates.HowToPlayView;
import view.gameStates.MainMenu;
import view.gameStates.PlayfieldView;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;


/**
 * GameManager
 * This class is responsible for managing which screen to show to the user,
 * which uses an StatePattern to tell which paint-responsible-class to work.
 * It's also responsible to handle input from user to change which object(string)
 * is chosen in mainMenu and its sub-categories like HowToPlay.
 * 
 * @author  Kim Berger
 * @version 1.0
 */

public class GameManager extends Game {

	private int width;
	private int height;
	private boolean gmIsStarted = false;
    private Model model;

	// We need this camera to see our game.
	private OrthographicCamera cam;
	
	// All types of "Game-States" we will see.
	private MainMenu mainMenuView;
	private PlayfieldView playfieldView;
	private HowToPlayView howToPlayView;
	
	// used for change state within mainMenu & HowToPlay
	private int currentItem;

	
	public GameManager(Model model, int width, int height){
        this.model = model;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void create() {
		
		/*
		 * default cam look at origo with a box: (-1, -1) to (1, 1) so we make
		 * it our resolution-size with parameters, and then move camera to
		 * center of our window with translate, and lastly we have to update
		 * camera to see the change.
		 */
		cam = new OrthographicCamera(width, height);
		cam.translate((float)width / 2, (float)height / 2);
		cam.update();
		
		// create an object for each "Game-State".
		mainMenuView = new MainMenu(cam, width);
		playfieldView = new PlayfieldView(cam, width, height);
		howToPlayView = new HowToPlayView(this);
        model.register(playfieldView);
        
        currentItem = mainMenuView.getCurrentItem();
		
		// Sets our mainMenu to be first screen we will see.
		setScreen(mainMenuView);
		
		// used for Controller who needs to wait for this libGDX initiater to be done.
		gmIsStarted = true;
		
	}
	
	@Override
	public void render () {
		if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
	}
	
	// Called by a controller when user press specific key in MainMenu.
	public String handleInput(String key) {
		
		// Inside mainMenu
		if(screen.equals(mainMenuView)) {
			
			String[] menuItemsList = mainMenuView.getMenuItems();
			
			switch(key) {
			
			case "Up":
				if(currentItem > 0)	{ currentItem--; }
				return "Up";
			
			case "Down":
				if(currentItem < mainMenuView.getMenuItems().length - 1) { currentItem++; }
				return "Down";
				
			case "Enter":
				select();
				return menuItemsList[currentItem];
//				return mainMenuView.getMenuItems()[currentItem];
				
			default: 
				return "Error, Controller called GameManager.handleInput with unknown parameter";
			}
		}
		
		// inside howToPlay
		else if(screen.equals(howToPlayView)){
			if(key.equals("Enter")){
				dispose();
				setScreen(getMainMenu());
			}
		}
		return "Error, Controller called GameManager.handleInput with unknown parameter";
		

	}
	
	/**
	 * This method is used by the controllers who are responsible for the 
	 * mainMenu and its sub-categories like howToPlay, to  
	 */
	private void select() {
		
		if(screen.equals(mainMenuView)){
			
			int currentItem = mainMenuView.getCurrentItem();
			switch(currentItem){

			// Play
			case 0:
				dispose();
				setScreen(getPlayfieldView());
				break;
				
			// Settings
			case 1:
//				gm.setScreen(gm.MENU_SETTINGS);
				break;
				
			// HighScore
			case 2:
//				gm.setState(gm.HIGHSCORE);
				break;
				
			// HowToPlay
			case 3:
				dispose();
				setScreen(getHowToPlayView());
				break;
				
			// Quit
			case 4:
				Gdx.app.exit();
				break;
				
			default: 
				System.out.println("Error, MainMenu.select had selected an unkown string.");
				break;
			}
		}
		
	}
	
	public OrthographicCamera getCam(){	return cam;	}
	
	public int getWidth(){ return width; }
	public int getHeight(){	return height; }
	
	public MainMenu getMainMenu() { return mainMenuView; }
	public PlayfieldView getPlayfieldView() { return playfieldView; }
	public HowToPlayView getHowToPlayView() {return howToPlayView; }
	public boolean getgmStatus() { return gmIsStarted; }
}