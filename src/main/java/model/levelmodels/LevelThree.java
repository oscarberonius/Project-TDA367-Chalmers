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
        nextSpawnTime = 2;
    }

    @Override
    protected void updateLevel(double delta) {
        switch (currentWave){
            case 1:
                if(spawnedKids < 15){
                    int spawnKidType = random.nextInt(99);
                    if(spawnKidType < 45){
                        activeKids.add(createKid(KidTypes.SIMPLE_SAM, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }else if(spawnKidType < 90){
                        activeKids.add(createKid(KidTypes.SINESTER, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }else{
                        activeKids.add(createKid(KidTypes.DIZZY_DORIZ, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }
                    spawnedKids++;
                    nextSpawnTime += random.nextDouble()*5;
                }else{
                    changeWave();
                }
                break;
            case 2:
                if(spawnedKids < 20){
                    int spawnKidType = random.nextInt(99);
                    if(spawnKidType < 30){
                        activeKids.add(createKid(KidTypes.SIMPLE_SAM, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }else if(spawnKidType < 80){
                        activeKids.add(createKid(KidTypes.SINESTER, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }else{
                        activeKids.add(createKid(KidTypes.DIZZY_DORIZ, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }
                    spawnedKids++;
                    nextSpawnTime += random.nextDouble()*4;
                }else{
                    changeWave();
                }
                break;
            case 3:
                if(spawnedKids < 15){
                    int spawnKidType = random.nextInt(99);
                    if(spawnKidType < 30){
                        activeKids.add(createKid(KidTypes.SIMPLE_SAM, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }else if(spawnKidType < 70){
                        activeKids.add(createKid(KidTypes.SINESTER, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }else{
                        activeKids.add(createKid(KidTypes.DIZZY_DORIZ, Entity.getRightBoundary(), random.nextFloat()*Entity.getUpperBoundary()));
                    }
                }
                spawnedKids++;
                nextSpawnTime += random.nextDouble()*4;
                break;
        }
    }

    @Override
    protected void changeWave() {
        currentWave++;
        spawnedKids = 0;
        nextSpawnTime = random.nextDouble()*2;
    }

    @Override
    public boolean levelDone() {
        return kidsRemoved == 50;
    }

    @Override
    public boolean levelFailed() {
        return kidsInStore == 8;
    }
}