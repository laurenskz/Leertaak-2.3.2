package opgave4.test;

import opgave4.classifier.*;
import junit.framework.TestCase;


public class TestClassifier extends TestCase {

    public TestClassifier(String arg0) {
        super(arg0);
    }

    private DecisionTree buildTree() {
        Node root = new Node("AC");

        Node n1 = new Node("ABS");
        Node n2 = new Node("ABS");
        root.addChild("yes", n1);
        root.addChild("no", n2);

        // leaves
        Node l1 = new Node("cd player");
        Node l2 = new Node("cd player");
        Node l3 = new Node("cd player");
        Node l4 = new Node("cd player");

        Node high = new Node("high");
        Node medium = new Node("medium");
        Node low = new Node("low");

        n1.addChild("yes", l1);
        l1.addChild("yes", high);
        l1.addChild("no", medium);

        n1.addChild("no", l2);
        l2.addChild("yes", medium);
        l2.addChild("no", medium);

        n2.addChild("yes", l3);
        l3.addChild("yes", medium);
        l3.addChild("no", medium);

        n2.addChild("no", l4);
        l4.addChild("yes", medium);
        l4.addChild("no", low);

        return new DecisionTree(root);
    }

    public void testCategory() {
        DecisionTree dt = buildTree();

        FeatureType yn = new FeatureType("YesNo"
                , new String[]{"yes", "no"});

        Feature[] features = new Feature[]
                {new Feature("AC", "yes", yn),
                        new Feature("ABS", "yes", yn),
                        new Feature("cd player", "yes", yn)
                };

        Item item = new Item("car", features);

        String category = dt.assignCategory(item);
        assertEquals("high", category);


        item.setValue("AC", "no");
        category = dt.assignCategory(item);
        assertEquals("medium", category);

        item.setValue("ABS", "no");
        category = dt.assignCategory(item);
        assertEquals("medium", category);

        item.setValue("cd player", "no");
        category = dt.assignCategory(item);
        assertEquals("low", category);

    }
}
