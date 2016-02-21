package opgave4.test;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import opgave4.classifier.*;

public class TestTraining extends TestCase {

	public TestTraining(String arg0) {
		super(arg0);
	}

	private Item createItem(String ac, String abs){
		Feature[] features = new Feature[]
		{ new Feature("AC",ac,yn),
		new Feature("ABS",abs,yn)
		};
		
		return new Item("car",features);
	}
	
	private FeatureType yn = new FeatureType("YesNo"
						,new String[]{"yes","no"});
 
	public void testExample(){
		Map items = new HashMap();
		Map features = new HashMap();
		
		features.put("AC",yn);
		features.put("ABS",yn);
		
		Item item1 = createItem("yes","yes");
		items.put(item1,"high");

		Item item2 = createItem("yes","no");
		items.put(item2,"medium");

		Item item3 = createItem("no","yes");
		items.put(item3,"medium");

		Item item4 = createItem("no","no");
		items.put(item4,"low");
		
		DecisionTree dc=new DecisionTree(items,features);
		
		assertEquals("high",dc.assignCategory(item1));
		assertEquals("medium",dc.assignCategory(item2));
		assertEquals("medium",dc.assignCategory(item3));
		assertEquals("low",dc.assignCategory(item4));
	}

}
