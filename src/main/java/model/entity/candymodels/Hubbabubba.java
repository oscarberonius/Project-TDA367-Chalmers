package model.entity.candymodels;

/**
 * Created by Matilda on 2015-05-30.
 */
public class Hubbabubba extends Candy {

    private boolean expired;
    private double maxTravelLength;
    private double travelledLength;

    public Hubbabubba(float x, float y, int[] candyStatus){
        super(x, y);
        type = "Hubbabubba";
        expired = false;

        switch(candyStatus[0]){
            case 0:
                damage = 10;
                break;
            case 1:
                damage = 15;
                break;
            case 2:
                damage = 20;
                break;
            case 3:
                damage = 25;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid upgrade");
        }

        switch(candyStatus[1]){
            case 0:
                maxTravelLength = 400;
                break;
            case 1:
                maxTravelLength = 450;
                break;
            case 2:
                maxTravelLength = 500;
                break;
            case 3:
                maxTravelLength = 600;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid upgrade");
        }

        switch(candyStatus[2]){
            case 0:
                slowRate = 0.8;
                break;
            case 1:
                slowRate = 0.6;
                break;
            case 2:
                slowRate = 0.4;
                break;
            case 3:
                slowRate = 0.2;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid upgrade");
        }

        switch(candyStatus[3]){
            case 0:
                radius = 4;
                break;
            case 1:
                radius = 7;
                break;
            case 2:
                radius = 10;
                break;
            case 3:
                radius = 13;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid upgrade");
        }
    }


    @Override
    public void update(double deltaTime) {
        double delta = deltaTime * 150;
        if(travelledLength < maxTravelLength){
            xPos += 4 * delta;
            travelledLength += 4 * delta;
        }else{
            expired = true;
        }

    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    public int getDamage(){
        return damage;
    }
}
