package opgave4.test;
import opgave4.classifier.*;
import junit.framework.TestCase;
import opgave4.classifier.Node;


public class TestTree extends TestCase {

	public TestTree(String arg0) {
		super(arg0);
	}

	public void testLeaf(){
		String label = "leaf label";
		Node leaf = new Node(label);
		
		assertTrue("Should be a leaf",leaf.isLeaf());
		assertEquals(label,leaf.label());
	}
	
	public void testOneLevel(){
		Node leftLeaf = new Node("left");
		Node rightLeaf = new Node("right");
		
		String toLeft="to the left";
		String toRight="to the right";
		Node root = new Node("root");
		root.addChild(toLeft,leftLeaf);
		root.addChild(toRight,rightLeaf);
		
		assertFalse("root shouldn't be a leaf",
					root.isLeaf());
		
		assertSame(leftLeaf,root.follow(toLeft));
		assertSame(rightLeaf,root.follow(toRight));
	}
	
}
