package opgave1;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Laurens on 15-2-2016.
 */
public class PostOrderTraversal {

    private HashSet<Node<?>> poppedNodes = new HashSet<>();

    Stack<Node<?>> nodeStack = new Stack<>();



    public void traverse(Node<?> tree){
        nodeStack.clear();
        poppedNodes.clear();
        Node<?> current = tree;
        while(current!=null||nodeStack.size()>0){
            if(current!=null){
                nodeStack.push(current);
                current = current.getLeft();
            }else{
                current = nodeStack.pop();
                if(poppedNodes.contains(current)){//second time popped
                    System.out.println(current);
                    current = null;//We want to go back to the else clause and then pop from the stack.
                }else{
                    poppedNodes.add(current);
                    nodeStack.push(current);
                    current = current.getRight();
                }
            }
        }
    }
}
