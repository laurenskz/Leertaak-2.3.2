package opgave4.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Jules on 21/02/2016.
 */
public class QuestionPanel extends JPanel {

//	private JLabel questionLabel = new JLabel("Nieuwe vraag", JLabel.LEFT);
	private JCheckBox checkBox = new JCheckBox("Vraag");

	public QuestionPanel(ActionListener listener) {
		super();

		setLayout(new FlowLayout());

		checkBox.setVisible(true);
		add(checkBox);

		validate();
	}

	public void setQuestion(String s) {
		checkBox.setText(s);
	}

	public boolean isSelected() {
		return checkBox.isSelected();
	}
}
