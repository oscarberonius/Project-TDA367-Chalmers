package model.levelmodels;

import model.entity.Entity;
import model.entity.kids.KidTypes;
import static model.entity.kids.KidFactory.createKid;

/**
 * Created by Matilda on 2015-05-27.
 */
public class LevelThree extends Level {

    public LevelThree(){
        super();
        nextSpawnTime = 3;
        maxKidsInStore = 10;
    }

    @Override
    protected void updateLevel(double delta) {
        timePassed += delta;
        switch (currentWave){
            case 1:
                if(spawnedKids < 15){
                    if(timePassed >= nextSpawnTime) {
                        int spawnKidType = random.nextInt(99);
                        float spawnPos = generateSpawnPos(10);
                        if (spawnKidType < 45) {
                            activeKids.add(createKid(KidTypes.SIMPLE_SAM, Entity.getRightBoundary(), spawnPos));
                        } else if (spawnKidType < 90) {
                            activeKids.add(createKid(KidTypes.DIZZY_DORIZ, Entity.getRightBoundary(), spawnPos));
                        } else {
                            activeKids.add(createKid(KidTypes.SINESTER, Entity.getRightBoundary(), spawnPos));
                        }
                        spawnedKids++;
                        nextSpawnTime += random.nextDouble() * 3;
                    }
                }else if(kidsRemoved == 15){
                    changeWave();
                }
                break;
            case 2:
                if(spawnedKids < 20){
                    if(timePassed >= nextSpawnTime) {
                        int spawnKidType = random.nextInt(99);
                        float spawnPos = generateSpawnPos(10);
                        if (spawnKidType < 30) {
                            activeKids.add(createKid(KidTypes.SIMPLE_SAM, Entity.getRightBoundary(), spawnPos));
                        } else if (spawnKidType < 50) {
                            activeKids.add(createKid(KidTypes.SINESTER, Entity.getRightBoundary(), spawnPos));
                        } else {
                            activeKids.add(createKid(KidTypes.DIZZY_DORIZ, Entity.getRightBoundary(), spawnPos));
                        }
                        spawnedKids++;
                        nextSpawnTime += random.nextDouble() * 5;
                    }
                }else if(kidsRemoved == 35){
                    changeWave();
                }
                break;
            case 3:
                if(spawnedKids < 15){
                    if(timePassed >= nextSpawnTime) {
                        int spawnKidType = random.nextInt(99);
                        float spawnPos = generateSpawnPos(10);
                        if (spawnKidType < 30) {
                            activeKids.add(createKid(KidTypes.SIMPLE_SAM, Entity.getRightBoundary(), spawnPos));
                        } else if (spawnKidType < 60) {
                            activeKids.add(createKid(KidTypes.SINESTER, Entity.getRightBoundary(), spawnPos));
                        } else {
                            activeKids.add(createKid(KidTypes.DIZZY_DORIZ, Entity.getRightBoundary(), spawnPos));
                        }
                        spawnedKids++;
                        nextSpawnTime += random.nextDouble()*4;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Not an existing wave");
        }
    }

    @Override
    protected void changeWave() {
        currentWave++;
        spawnedKids = 0;
        nextSpawnTime = timePassed + 2;
    }

    @Override
    public boolean levelDone() {
        return kidsRemoved == 50 && kidsInStore < maxKidsInStore;
    }

    @Override
    public boolean levelFailed() {
        return kidsInStore == maxKidsInStore;
    }
}
