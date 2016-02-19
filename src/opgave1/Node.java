package opgave1;

/**
 * Created by Laurens on 15-2-2016.
 */
public class Node<T extends Element> {
    private Node<T> left,right;
    private T element;

    public Node(T element) {
        this.element = element;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public String toString(){
        return element.toString();
    }



}
