/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package opgave4.classifier;

import java.util.*;

/**
 * Represents a decision tree.
 */
public class DecisionTree
        implements Classifier {

    private Node root;

    public DecisionTree(Node tree) {
        root = tree;
    }

    /**
     * Assign a category to an item.
     * The algorithm takes as input an item and a decision tree.
     * The output is the name of the category.
     */
    public String assignCategory(Item item) {
        Node current;

//1)	start at the root node
        current = root;
//2)	repeat while the current node is not a leaf
        while (!current.isLeaf()) {
//    a)	follow the arc corresponding to the item's
//              value of the current node split attribute
            //Arc arc = current.findArc(item.findAttribute(current.label()).value());

//    b)	the node reached becomes the current node
            //current = arc.destination();
            current = current.follow(item.value(current.label()));
        }
//3)	the label of the leaf node is the class of the item
        return current.label();
    }

    public String toString() {
        return "Decision tree:\n" + root.toString();
    }


    public DecisionTree(Map items, Map features) {
        root = buildDecisionTree(items, features);
    }


    //Input: a training set of items T, a set of attributes A
    //Output: a decision tree (the root of it)
    public static Node buildDecisionTree(Map items, Map features) {
        //0) if the attribute set is empty or the items belong to a single class
        if (features.size() == 0 || information(items) == 0.0) {
            //  a) create a leaf node labeled according to the class of the items
            return new Node(findCategory(items));
        }
        //1)  select the "best" split attribute s in A
        String splitFeature = selectSplit(items, features);
        //2)  create a node n with label s.name
        Node n = new Node(splitFeature);
        //3)  for each possible value vi of s
        int i;
        FeatureType splitType = (FeatureType) features.get(splitFeature);
        Map partitions = performSplit(items, splitFeature, splitType.allowedValues());
        for (Iterator iter = partitions.keySet().iterator(); iter.hasNext(); ) {
            String value = (String) iter.next();
            //    a)  be ni the result of a recursive execution of this algorithm where
            //        the fist input is: Ti = { item in T | item.s == vi }
            //        the second input is: A - { s }
            Map partition = (Map) partitions.get(value);
            Map remainingFeatures = new HashMap(features);
            remainingFeatures.remove(splitFeature);
            Node child = buildDecisionTree(partition, remainingFeatures);
            //    b)  set ni as child node of n and label the connecting arc vi
            n.addChild(value, child);
        }
        //4)  n is the resulting root node of the decision tree
        return n;
    }

    /**
     * determines the class of a set of items.
     */
    private static String findCategory(Map items) {
        // no category if the set is empty
        if (items.size() == 0)
            return "?";
        // computes the frequency of each category
        Map catFreq = new HashMap();
        Iterator it = items.keySet().iterator();
        String category = "";
        while (it.hasNext()) {
            Item item = (Item) it.next();
            category = (String) items.get(item);
            Integer count = (Integer) catFreq.get(category);
            if (count == null) {
                catFreq.put(category, new Integer(1));
            } else {
                catFreq.put(category, new Integer(1 + count.intValue()));
            }
        }
        // if only one category is present,
        // it is the category of the set
        if (catFreq.keySet().size() == 1) {
            return category;
        }
        // otherwise it is not possible to assign
        // a category
        return "?";
    }

    /**
     * Determines the best split attribute for a set of items,
     * it is based on the information gain criterion.
     */
    private static String selectSplit(Map items, Map features) {
        Iterator attr = features.keySet().iterator();
        String split = null;
        double maxGain = 0.0;
        while (attr.hasNext()) {
            String candidate = (String) attr.next();
            FeatureType type = (FeatureType) features.get(candidate);
            double gain = evaluateSplitGain(items, candidate, type.allowedValues());
            if (gain > maxGain) {
                maxGain = gain;
                split = candidate;
            }
        }
        return split;
    }

    /**
     * Executes the split of a set of items according to a split attribute.
     * It returns the resulting sets of items, each of them being labeled
     * with the relative attribute value.
     */
    private static Map performSplit(Map items, String split, Collection possibleValues) {
        Map partitions = new HashMap();
        int i;
        for (Iterator iter = possibleValues.iterator(); iter.hasNext(); ) {
            String value = (String) iter.next();
            partitions.put(value, new HashMap());
        }
        Iterator it = items.keySet().iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            String splitValue = item.value(split);
            Map partition = (Map) partitions.get(splitValue);
            partition.put(item, items.get(item));
        }
        return partitions;
    }

    /**
     * Evaluates the information gain that would derive from splitting
     * a set of items according to a given split attribute.
     */
    private static double evaluateSplitGain(Map items, String split, Collection possibleValues) {
//        System.out.println("Split gain");
        double origInfo = information(items);
//        System.out.println(origInfo);
        double splitInfo = 0;
        Map partitions = performSplit(items, split, possibleValues);
//        System.out.println(partitions);
        int i;
        double size = items.size();
        for (Iterator iter = possibleValues.iterator(); iter.hasNext(); ) {
            String value = (String) iter.next();
//            System.out.println(value);
            Map partition = (Map) partitions.get(value);
            double partitionSize = partition.size();
            double partitionInfo = information(partition);
//            System.out.println(partitionInfo);
            splitInfo += partitionSize / size * partitionInfo;
        }
//        System.out.println(splitInfo);
        return origInfo - splitInfo;
    }

    /**
     * Computes the information content of a set of items.
     * The classes represent the symbols of the alphabet
     */
    private static double information(Map items) {
//        System.out.print("-(");
        Map frequencies = new HashMap();

        // compute number of occurrencies of classes
        Iterator it = items.keySet().iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            String category = (String) items.get(item);
            Long num_occur = (Long) frequencies.get(category);
            if (num_occur == null) {
                frequencies.put(category, new Long(1));
            } else {
                frequencies.put(category, new Long(num_occur.longValue() + 1));
            }
        }

        // compute information
        double info = 0;
        double numItems = items.size();
        it = frequencies.values().iterator();
        while (it.hasNext()) {
            Long num_occurr = (Long) it.next();
            double freq = num_occurr.doubleValue() / numItems;
//            System.out.print(freq+"*log2("+freq+")"+(it.hasNext()?"+":""));
            info += freq * Math.log(freq) / Math.log(2.0);
        }
//        System.out.print(")=" + -info+"\n");
        return -info;
    }

    public static void main(String[] args) {
        String answer = "-(0.6666666666666666*log2(0.6666666666666666)+0.3333333333333333*log2(0.3333333333333333))=0.9182958340544896";
        answer = answer.replaceAll("0.6666666666666666","(2/3)");
        answer = answer.replaceAll("0.3333333333333333","(1/3)");
//        System.out.println(answer);
        Map<Item, String> items = new HashMap<>();
        Map<String,FeatureType> features = new HashMap<>();
        String[] yesNo= new String[]{"Ja","Nee"};
        FeatureType abs = new FeatureType("ABS",yesNo);
        FeatureType airco = new FeatureType("AC",yesNo);
        Feature noAirco = new Feature("AC","Nee",airco);
        Feature yesAirco = new Feature("AC","Ja",airco);
        Feature noAbs = new Feature("ABS","Nee",abs);
        Feature yesAbs = new Feature("ABS","Ja",abs);
        Item bmw = new Item("Mercedes",new Feature[]{yesAbs,yesAirco});
        Item smart = new Item("Porsche",new Feature[]{yesAbs,noAirco});
        Item bab = new Item("Renault",new Feature[]{noAirco,yesAbs});
        Item hlaas = new Item("Saab",new Feature[]{noAirco,noAbs});
        features.put("AC",airco);
        features.put("ABS",abs);
        items.put(bmw,"Hoog");
        items.put(smart,"Hoog");
        items.put(bab, "Midden");
        items.put(hlaas, "Laag");
        System.out.println(buildDecisionTree(items,features));
//        System.out.println(evaluateSplitGain(items,"ABS",airco.allowedValues()));
//        Map letsContinue = (Map) performSplit(items,"ABS",abs.allowedValues()).get("Ja");
//        System.out.println(letsContinue);
//        System.out.println(information(letsContinue));
//        System.out.println(performSplit(letsContinue,"AC",airco.allowedValues()));
        //System.out.println(information(items));
//        information((Map) performSplit(items, "AC", abs.allowedValues()).get("Nee"));
//        information(items);
//        System.out.println(items);
//        Map splitted = performSplit(items, "Abs", airco.allowedValues());
//        System.out.println(splitted);
//        System.out.println(information(items));
//        System.out.println(information((Map)splitted.get("yes")));
//        System.out.println(information((Map) splitted.get("no")));
//        System.out.println(evaluateSplitGain(items, "Abs", abs.allowedValues()));
//        System.out.println(selectSplit(items,features));
    }

}
