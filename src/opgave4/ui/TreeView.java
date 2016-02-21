package opgave4.ui;

import opgave4.classifier.DecisionTree;
import opgave4.classifier.Node;
import opgave4.test.TestFileReader;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by Laurens on 21-2-2016.
 */
public class TreeView extends JPanel{

    public static final int DRAW_Y_OFFSET = 45;
    public static final boolean SIMPLE_MODE = false;
    private Controller controller;

    public TreeView(Controller controller) {
        this.controller = controller;
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());//Makes it fullscreen
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawTree(g,0,getWidth(), DRAW_Y_OFFSET,controller.getTree().getRoot(),0,0,"");
    }

    public void drawTree(Graphics g, int xMin,int xMax, int y,Node node,int previousX, int previousY,String arc){
        if(node==null)return;
        int middle = ((int) Math.round(((double)xMax-xMin)/2)+xMin);
        int titleLen = g.getFontMetrics().stringWidth(node.label());
        int half = titleLen/2;
        if(previousX!=0&&previousY!=0){
            g.setColor(Color.RED);
            g.drawLine(previousX,previousY,middle,y);
            int xMid = Math.abs(previousX-middle)/2;
            xMid = previousX>middle?middle+xMid:middle-xMid;
            int yMid = previousY + DRAW_Y_OFFSET/2;
            xMid-= g.getFontMetrics().stringWidth(arc)/2;
            g.setColor(Color.BLUE);
            g.drawString(arc,xMid,yMid);
        }
        int xDrawPos = middle - half;
        g.setColor(Color.BLACK);
        g.drawString(node.label(),xDrawPos,y);
        drawTree(g,xMin,middle,y+DRAW_Y_OFFSET,node.follow("no"),middle,y,"no");
        drawTree(g,middle,xMax,y+DRAW_Y_OFFSET,node.follow("yes"),middle,y,"yes");
    }

    public static void main(String[] args) {
        Controller controller = new Controller(SIMPLE_MODE?DecisionTree.buildTree():TestFileReader.get());
        JFrame jFrame = new JFrame("Tree");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(new TreeView(controller));
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
