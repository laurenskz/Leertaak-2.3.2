package opgave4.ui;

import opgave4.classifier.DecisionTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Jules on 21/02/2016.
 */
public class View extends JFrame {

	public static final String TITLE = "Insurance classifier";

	private QuestionPanel questionPanel;
	private ButtonPanel buttonPanel;

	public View(ActionListener listener) {
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		Container contentPane = getContentPane();

		questionPanel = new QuestionPanel(listener);
		contentPane.add(questionPanel, BorderLayout.CENTER);

		buttonPanel = new ButtonPanel(listener);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public void setQuestion(String s, boolean isRoot) {
		questionPanel.setQuestion(s);
		buttonPanel.lastVisible(!isRoot);
	}

	public boolean isSelected() {
		return questionPanel.isSelected();
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		DecisionTree tree = DecisionTree.buildTree();
		Controller controller = new Controller(tree);
		View view = new View(controller);
	}
}
