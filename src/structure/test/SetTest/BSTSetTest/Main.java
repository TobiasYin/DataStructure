package structure.test.SetTest.BSTSetTest;

import structure.kotlin.set.BSTSet;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("src/structure/test/BSTSetTest/pride-and-prejudice.txt", words1)) {
            info(words1);
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("src/structure/test/BSTSetTest/a-tale-of-two-cities.txt", words2)) {
            info(words2);
        }
    }

    private static void info(ArrayList<String> words) {
        System.out.println("Total words: " + words.size());

        BSTSet<String> set = new BSTSet<>();
        for (String word : words)
            set.add(word);
        System.out.println("Total different words: " + set.getSize());
    }
}
