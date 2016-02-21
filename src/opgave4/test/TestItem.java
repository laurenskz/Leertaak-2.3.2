package opgave4.test;
import junit.framework.TestCase;

import opgave4.classifier.Feature;
import opgave4.classifier.FeatureType;
import opgave4.classifier.Item;

public class TestItem extends TestCase {

	public TestItem(String arg0) {
		super(arg0);
	}

	public void testFeatureType(){
		String featureTypeName="YesNo";
		String[] allowedValues = new String[]
							{"yes","no"};
		FeatureType type = new FeatureType(
				featureTypeName,allowedValues);
		
		for(int i=0; i<allowedValues.length; ++i){
			assertTrue(type.isAllowed(allowedValues[i]));
		}
	}
	
	public void testFeature(){
		String featureTypeName="YesNo";
		String[] allowedValues = new String[]
							{"yes","no"};
		FeatureType type = new FeatureType(
				featureTypeName,allowedValues);

		String value="yes";
		Feature feature = new Feature("nome","yes",type);
		
		assertEquals(value,feature.value());
		
		try{
			feature.value("no");
			assertTrue(true);
		}catch(IllegalArgumentException e){
			fail("cannot set valid value");
		}
		
		try{
			feature.value("invalid");
			fail("could set invalid value");
		}catch(IllegalArgumentException e){
			assertTrue(true);
		}
	}
	
	public void testItem(){
		FeatureType yn = new FeatureType("YesNo"
						,new String[]{"yes","no"});

		Feature[] features = new Feature[]
		{ new Feature("f1","yes",yn),
		  new Feature("f2","no",yn)
		};
		
		Item item = new Item("name",features);
		
		assertEquals("yes",item.value("f1"));
		assertEquals("no",item.value("f2"));
		
		item.setValue("f1","no");
	}
}
