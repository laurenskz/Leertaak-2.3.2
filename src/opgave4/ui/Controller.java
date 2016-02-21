package opgave4.ui;

import opgave4.classifier.DecisionTree;
import opgave4.classifier.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jules on 21/02/2016.
 */
public class Controller implements ActionListener {

	DecisionTree tree;

	Node root;
	Node current;

	public Controller(DecisionTree tree) {
		this.tree = tree;

		root = tree.getRoot();
		current = root;
	}

	private void next(Component c) {
		if (c instanceof View) {
			View view = (View) c;

			current = current.follow(getArcLabel(view.isSelected()));

//			view.setQuestion();
 		}
	}

	private String getArcLabel(boolean b) {
		if (b) {
			return "YES";
		} else {
			return "NO";
		}
	}

	private void previous(Component c) {
		if (c instanceof View) {
			System.out.println("Previous");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o instanceof JButton) {
			// Check each button possibility
			JButton button = (JButton) o;

			switch (button.getText()) {
				case ButtonPanel.NEXT_TXT:
					next(SwingUtilities.getRoot(button));
					break;
				case ButtonPanel.LAST_TXT:
					previous(SwingUtilities.getRoot(button));
					break;
			}

		} else if (true) {

		}
	}

	public DecisionTree getTree() {
		return tree;
	}
}
