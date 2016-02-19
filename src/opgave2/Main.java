package opgave2;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

/**
 * Created by Laurens on 16-2-2016.
 */
public class Main {

    public static void main(String[] args) {
        DefaultMutableTreeNode tree = getTree();
        printEnumeration(tree.preorderEnumeration(), "Preordered:");
        printEnumeration(tree.postorderEnumeration(),"Postordered:");
        printEnumeration(tree.breadthFirstEnumeration(),"Breadth first:");
    }

    private static DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode
                tree = new DefaultMutableTreeNode("person", true),
                employee = new DefaultMutableTreeNode("employee", true),
                customer = new DefaultMutableTreeNode("customer", true),
                sales_rep = new DefaultMutableTreeNode("sales_rep"),
                us_customer = new DefaultMutableTreeNode("us_customer", true),
                non_us_customer = new DefaultMutableTreeNode("non_us_customer"),
                local_customers = new DefaultMutableTreeNode("local_customers"),
                regional_customers = new DefaultMutableTreeNode("regional_customers"),
                engineer = new DefaultMutableTreeNode("engineer");
        tree.add(employee);
        tree.add(customer);
        employee.add(sales_rep);
        employee.add(engineer);
        customer.add(us_customer);
        customer.add(non_us_customer);
        us_customer.add(local_customers);
        us_customer.add(regional_customers);
        return tree;
    }

    private static void printEnumeration(Enumeration enumeration,String message) {
        System.out.println(message);
        while (enumeration.hasMoreElements()){
            System.out.println("\t-"+enumeration.nextElement());
        }
    }
}
