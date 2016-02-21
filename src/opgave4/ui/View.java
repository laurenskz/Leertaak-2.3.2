package opgave4.ui;

import opgave4.classifier.DecisionTree;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Created by Jules on 21/02/2016.
 */
public class View extends JFrame {

	public static final String TITLE = "Insurance classifier";
	public static final String RESULT_TITLE = "Result";

	private QuestionPanel questionPanel;
	private ButtonPanel buttonPanel;

	public View(Controller controller) {
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		Container contentPane = getContentPane();

		questionPanel = new QuestionPanel(controller);
		contentPane.add(questionPanel, BorderLayout.CENTER);

		buttonPanel = new ButtonPanel(controller);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		controller.reset(this);

		pack();
		setVisible(true);
	}

	public void displayResult(String content) {
		JOptionPane.showMessageDialog(this, content, RESULT_TITLE, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Sets the current question
	 * @param s
	 * @param options
	 * @param isRoot
	 */
	public void setQuestion(String s, Set options, boolean isRoot) {
		questionPanel.setQuestion(s, options);
		buttonPanel.resetVisible(!isRoot);
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

	public void pleaseSelectAnOption() {
		JOptionPane.showMessageDialog(this, "Please select an option before clicking next", "Please select an option", JOptionPane.WARNING_MESSAGE);
	}
}
