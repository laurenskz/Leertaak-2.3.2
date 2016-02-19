package opgave1;

import java.util.Stack;

/**
 * Created by Laurens on 15-2-2016.
 * The main part of this traversal to remember is left child -> parent -> right child
 */
public class InorderTraversal {

    Stack<Node<?>> nodeStack = new Stack<>();

    public static void main(String[] args) {
        Node<Simple> tree = new Node<>(new Simple(1));
        Node<Simple>
                two =   new Node<>(new Simple(2)),
                three = new Node<>(new Simple(3)),
                four =  new Node<>(new Simple(4)),
                five =  new Node<>(new Simple(5)),
                six =   new Node<>(new Simple(6)),
                seven = new Node<>(new Simple(7));

        tree.setLeft(two);
        tree.setRight(three);
        two.setLeft(four);
        two.setRight(five);
        five.setRight(seven);
        five.setLeft(six);

        new PostOrderTraversal().traverse(tree);
    }

    static class Simple implements Element{
        int x;

        public Simple(int x) {
            this.x = x;
        }

        @Override
        public int getValue() {
            return x;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "x=" + x +
                    '}';
        }
    }

    public void traverse(Node<?> tree){
        nodeStack.clear();
        Node<?> current = tree;
        while(current!=null||nodeStack.size()>0){
            if(current!=null){
                nodeStack.push(current);
                current = current.getLeft();
            }else{
                Node<?> node = nodeStack.pop();
                System.out.println(node);
                current = node.getRight();
            }
        }
    }
}
