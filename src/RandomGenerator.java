package src;
import java.util.*;

public class RandomGenerator {
    public ArrayList<Integer> RandObjIndices;

    ArrayList<Integer> indexes;
    int TotalNumberOfObjects;
    public RandomGenerator(int TotalNumberOfObjects){
        this.TotalNumberOfObjects = TotalNumberOfObjects;
        indexes = new ArrayList<>();
        RandObjIndices = new ArrayList<>();
        for(int i=0; i<TotalNumberOfObjects; i++){
            indexes.add(i);
        }
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

    public void createUnique(){
        Random random = new Random();
        while (RandObjIndices.size()!=6){
            int randomIndex = random.nextInt(TotalNumberOfObjects);
            TotalNumberOfObjects--;
            RandObjIndices.add(indexes.get(randomIndex));
            indexes.remove(randomIndex);
        }
    }
}
