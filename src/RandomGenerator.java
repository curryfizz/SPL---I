package src;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class RandomGenerator {
    public ArrayList<Integer> RandObjIndices;
    int TotalNumberOfObjects;
    RandomGenerator(int TotalNumberOfObjects){
        Random random = new Random();
        int num;
        this.TotalNumberOfObjects = TotalNumberOfObjects;
        num = random.nextInt(TotalNumberOfObjects);
        RandObjIndices.add(num);
        num = random.nextInt(TotalNumberOfObjects-1);
        RandObjIndices.add(num);
        num = random.nextInt(TotalNumberOfObjects-2);
        RandObjIndices.add(num);
        num = random.nextInt(TotalNumberOfObjects-3);
        RandObjIndices.add(num);
        num = random.nextInt(TotalNumberOfObjects-4);
        RandObjIndices.add(num);

        Collections.sort(RandObjIndices);
        make1Unique();
        make2Unique();
        make3Unique();
        make4Unique();
    }

    void make1Unique(){
        if(Objects.equals(RandObjIndices.get(0), RandObjIndices.get(1))) {  // unique checker for 1
            if(RandObjIndices.get(1) == TotalNumberOfObjects){ //if last element, occupy 1st index
                RandObjIndices.set(1,0);
            }
            else { //ocupy the next index
                int temp;
                temp = RandObjIndices.get(1);
                RandObjIndices.set(1,temp++);
            }
        }
    }
    void make2Unique(){
        if(Objects.equals(RandObjIndices.get(0), RandObjIndices.get(2))) { // unique checker for 2
            if(RandObjIndices.get(1) == TotalNumberOfObjects){ //if last element, occupy 1st index
                RandObjIndices.set(1,0);
            }
            else { //ocupy the next index
                int temp;
                temp = RandObjIndices.get(1);
                RandObjIndices.set(1,temp++);
            }
        }

    }
    void make3Unique(){

    }
    void make4Unique(){

    }
}
