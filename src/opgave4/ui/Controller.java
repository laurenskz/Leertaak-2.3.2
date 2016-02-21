package opgave4.ui;

import opgave4.classifier.Arc;
import opgave4.classifier.DecisionTree;
import opgave4.classifier.Node;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jules on 21/02/2016.
 */
public class Controller implements ActionListener {

	private String arc = null;

	private DecisionTree tree;

	private Node root;
	private Node current;

	// HashMap of Node and ArcLabels
	HashMap<Node, String> history = new HashMap<>();

	public Controller(DecisionTree tree) {
		this.tree = tree;

		root = tree.getRoot();
		current = root;
	}

	/**
	 * Resets the controller and the view and clears the history
	 * @param view
	 */
	public void reset(View view) {
		history.clear();
		arc = null;
		current = root;
		view.setQuestion(makeQuestion(current.label()), current.getArcs(), current == root);
		view.pack();
	}

	/**
	 * Makes the view display the next question
	 * @param view
	 */
	private void next(View view) {
		if (arc != null) {
			// Add the decision to the history HashMap
			history.put(current, arc);

			// Goes to the next question
			current = current.follow(arc);
			arc = null;

			if (!current.isLeaf()) {
				view.setQuestion(makeQuestion(current.label()), current.getArcs(), current == root);
			} else {
				view.displayResult(makeResult(current));
				reset(view);
			}

			view.pack();
		} else {
			view.pleaseSelectAnOption();
		}
	}

	/**
	 * Generates the String for the resultmessage
	 * @param leaf
	 * @return
	 */
	private String makeResult(Node leaf) {
		StringBuilder sb = new StringBuilder("The information that you have entered:\r\n");
		sb.append("\r\n");

		Iterator it = history.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();

			sb.append(((Node) pair.getKey()).label());
			sb.append(": ");
			sb.append(pair.getValue());
			sb.append("\r\n");

			it.remove();
		}

		sb.append("\r\nAccording to this information we get the following category: ");
		sb.append(leaf.label());
		return sb.toString();
	}

	/**
	 * Generates the question String
	 * @param label
	 * @return
	 */
	private String makeQuestion(String label) {
		StringBuilder sb = new StringBuilder("Do you have ");
		sb.append(label);
		sb.append("?");
		return sb.toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o instanceof JButton) {
			// Check each button possibility and do the corresponding action
			JButton button = (JButton) o;

			switch (button.getText()) {
				case ButtonPanel.NEXT_TXT:
					next((View) SwingUtilities.getRoot(button));
					break;
				case ButtonPanel.RESET_TXT:
					reset((View) SwingUtilities.getRoot(button));
					break;
			}
		} else if (o instanceof JRadioButton) {
			JRadioButton button = (JRadioButton) o;

			arc = button.getText();
		}
	}
}
