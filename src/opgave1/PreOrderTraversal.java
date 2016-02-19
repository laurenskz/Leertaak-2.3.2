package opgave1;

import java.util.Stack;

/**
 * Created by Laurens on 15-2-2016.
 */
public class PreOrderTraversal {


    Stack<Node<?>> nodeStack = new Stack<>();

    public void traverse(Node<?> tree){
        nodeStack.clear();
        Node<?> current = tree;
        while(current!=null||nodeStack.size()>0){
            if(current!=null){
                System.out.println(current);
                nodeStack.push(current);
                current = current.getLeft();
            }else{
                current = nodeStack.pop().getRight();
            }
        }
    }
}
