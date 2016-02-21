package opgave4.test;

import junit.framework.TestCase;
import opgave4.classifier.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Laurens on 21-2-2016.
 */
public class TestFileReader  extends TestCase {

    private static Map<Item,String> items;
    private static Map<String, FeatureType> features;
    public static final FeatureType YES_NO_FEATURE_TYPE = new FeatureType("YesNo", new String[]{"yes", "no"});
    private static int numberOfFeatures;
    private static int numberOfItems;

    public TestFileReader(String arg0) {
        super(arg0);
    }

    public void testDecisionTree(){
        String[] contents = readFile("testData.txt", Charset.defaultCharset());
        parseLines(contents);
        DecisionTree tree = new DecisionTree(DecisionTree.buildDecisionTree(items, features));
        for (Item item : items.keySet()) {
            String category = items.get(item);
            String assigned = tree.assignCategory(item);
            assertTrue(category.equals(assigned));
        }
    }

    public static DecisionTree get(){
        String[] contents = readFile("testData.txt", Charset.defaultCharset());
        parseLines(contents);
        DecisionTree tree = new DecisionTree(DecisionTree.buildDecisionTree(items, features));
        return tree;
    }

    private static void parseLines(String[] contents) {
        items = new HashMap<>();
        features = new HashMap<>();
        numberOfFeatures = 0;
        numberOfItems = 0;
        for(String line : contents){
            String[] words = line.split(";");
            if(words[0].equals("Features")){
                numberOfFeatures = Integer.parseInt(words[1]);
                continue;
            }
            if(words[0].equals("Items")){
                numberOfItems = Integer.parseInt(words[1]);
                continue;
            }
            Item item = parseItem(words);
            items.put(item, words[words.length - 1]);//The last word is the category
        }
        if(features.size()!=numberOfFeatures||items.size()!=numberOfItems) System.out.println("Something went wrong loading the file");
    }

    private static int get(String s){
        return Integer.parseInt(s.substring(4));
    }

    private static Item parseItem(String[] words){
        Feature[] features = new Feature[words.length-2];
        for (int i = 1; i < words.length-1; i++) {
            features[i-1] = new Feature("Feature"+i,words[i].equals("1")?"yes":"no",YES_NO_FEATURE_TYPE);
            if(TestFileReader.features.size()<i){
                TestFileReader.features.put("Feature"+i,YES_NO_FEATURE_TYPE);
            }
        }
        return new Item(words[0],features);
    }

    static String[] readFile(String path, Charset encoding)
    {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding).split("\r\n");
        } catch (IOException e) {
            return null;
        }
    }
}
