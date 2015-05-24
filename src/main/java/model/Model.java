package model;

import model.candymodels.Candy;
import model.candymodels.JellyBean;
import model.kids.KidFactory;
import model.kids.KidTypes;
import model.kids.Kid;
import model.levelmodels.Level;
import model.levelmodels.LevelOne;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Created by Oscar on 24/04/15.
 */
public class Model implements ObservedSubject {


    private ArrayList<Observer> observers;
    private static ArrayList<Entity> objects;
    public static float width;
    public static float height;
    public static float leftBoundary;
    public static float rightBoundary;

    private Player player1;
    private Player player2;

    private Level level;
    private int currentLevel;


    public Model(int width, int height) {
        observers = new ArrayList<Observer>();
        objects = new ArrayList<Entity>();
        this.width = (float) width;
        this.height = (float) height - 62;
        leftBoundary = 45;
        rightBoundary = 200;
        currentLevel = 1;
    }

    /**
     * Creates game with one player
     *
     * @param playerName
     */

    public Model(String playerName, int width, int height) {
        this(width, height);
        player1 = new Player(100, 250, playerName);
        objects.add(player1);
        startLevel(currentLevel);
    }

    /**
     * Creates game with two players
     *
     * @param player1Name
     * @param player2Name
     */
    public Model(String player1Name, String player2Name, int width, int height) {
        this(width, height);
        player1 = new Player(400, 500, player1Name);
        player2 = new Player(400, 800, player2Name);
        objects.add(player1);
        objects.add(player2);
    }

    /**
     * Updates the player's directions
     *
     * @param player     what player to update
     * @param directions the new directions
     */
    public void movePlayer(int player, boolean[] directions) {

        if (player == 1) {
            player1.updateDir(directions);
        } else {
            player2.updateDir(directions);
        }
    }

    /**
     * Creates the currently selected candy for the specified player.
     *
     * @param player which player to throw the candy
     */
    public void throwCandy(int player) {

        Candy candy;

        if (player == 1) {
            switch (player1.getSelectedCandy()) {
                case 0:
                    ArrayList<int[]> tmpData1 = player1.getCandyData();
                    candy = new JellyBean(player1.getX(), player1.getY(), tmpData1.get(0));
                    objects.add(candy);
                    break;
                case 1:
//                    ArrayList<int[]> tmpData2 = player1.getCandyData();
//                    candy = new Skittle(player1.getX(), player1.getY(), tmpData2.get(1));
//                    break;
                case 2:
//                    ArrayList<int[]> tmpData3 = player1.getCandyData();
//                    candy = new HubbaBubba(player1.getX(), player1.getY(), tmpData3.get(2));
//                    break;
                case 3:
//                    ArrayList<int[]> tmpData4 = player1.getCandyData();
//                    candy = new FerroRoscher(player1.getX(), player1.getY(), tmpData4.get(3));
//                    break;
            }
        } else {
            switch (player2.getSelectedCandy()) {
                case 0:
                    ArrayList<int[]> tmpData1 = player2.getCandyData();
                    candy = new JellyBean(player2.getX(), player2.getY(), tmpData1.get(0));
                    objects.add(candy);
                    break;
                case 1:
//                    ArrayList<int[]> tmpData2 = player2.getCandyData();
//                    candy = new Skittle(player2.getX(), player2.getY(), tmpData2.get(1));
//                    break;
                case 2:
//                    ArrayList<int[]> tmpData3 = player2.getCandyData();
//                    candy = new HubbaBubba(player2.getX(), player2.getY(), tmpData3.get(2));
//                    break;
                case 3:
//                    ArrayList<int[]> tmpData4 = player2.getCandyData();
//                    candy = new FerroRoscher(player2.getX(), player2.getY(), tmpData4.get(3));
//                    break;
            }

        }

    }

    public static void spawnKid(KidTypes type, float xPos, float yPos) {
        objects.add(KidFactory.createKid(type, xPos, yPos));
    }

    /**
     * Starts a new level
     *
     * @param levelNbr
     */
    public void startLevel(int levelNbr) {
        //    kids.clear();
        switch (levelNbr) {
            case 1:
                level = new LevelOne();
                break;
            //         case 2:
            //             level = new LevelTwo();
            //             break;
        }
    }

    /**
     * Updates the list of active objects and notifies view. Removes objects that have expired.
     */
    public void updateGame(double delta) {
        level.update(delta);
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) != null) {
                Entity entity = objects.get(i);
                String id = entity.getId();
                if (!entity.isExpired()) {
                    entity.update(delta);
                    if (id.substring(0, 1).equals("c")) {
                        for(int j = 0; j< objects.size();j++){
                            if (objects.get(j) != null) {
                            if(objects.get(j).getId().substring(0,1).equals("k")) {
                                Entity kid = objects.get(j);
                                float deltaX = kid.getX() - entity.getX();
                                float deltaY = kid.getY() - entity.getY();
                                float combinedR = kid.getRadius() + entity.getRadius();
                                if (Math.pow(deltaX, 2) + Math.pow(deltaY, 2) <= Math.pow(combinedR, 2)) {
                                    ((Kid) kid).hitByCandy((Candy) entity);
                                    objects.remove(entity);
                                    for (Observer observer : observers) {
                                        observer.removeEntity(entity);
                                    }
                                }
                            }
                            }
                        }

                    }
                } else {
                    objects.remove(entity);
                    /**
                     * if entity is a kid then we need to tell level how the kid expired: if it entered the store
                     * or if it got shot down by candies.
                     */
                    if (entity.getId().substring(0, 1).equals("k")) {

                        if (((Kid) entity).enteredStore()) {
                            level.enteredStore();
                        } else {
                            level.killedByCandy();
                        }
                    }
                    for (Observer observer : observers) {
                        observer.removeEntity(entity);
                    }
                }
            }
        }
    }

    /**
     * Adds a new observer
     * @param newObserver
     */
    @Override
    public void register(Observer newObserver) {

        if(newObserver == null){
            throw new IllegalArgumentException("Tries to add null object as observer");
        }

       if(!observers.contains(newObserver)){
           observers.add(newObserver);
       }
    }

    /**
     * Removes an observer
     * @param observer
     */
    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all the observers that a change has occurred
     */
    @Override
    public void notifyObserver() {


        for(Observer observer: observers){
            for(int i = 0; i < objects.size();i++){
                if(objects.get(i) != null) {
                    Entity entity = objects.get(i);
                    observer.update(entity, entity.getX(), entity.getY());
                }
            }

        }

    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }


}
