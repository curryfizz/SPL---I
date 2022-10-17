package shelved_classes;

import src.RandomGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class Dummy {
    public static void main(String[] args) {

        RandomGenerator randomGenerator =  new RandomGenerator(23);

        randomGenerator.createUnique();

        for(int i=0; i<randomGenerator.RandObjIndices.size(); i++){
            System.out.println(randomGenerator.RandObjIndices.get(i));
        }
    }
}
