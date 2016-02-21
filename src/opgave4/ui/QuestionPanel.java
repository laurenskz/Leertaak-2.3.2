package opgave4.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jules on 21/02/2016.
 */
public class QuestionPanel extends JPanel {

	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel question = new JLabel("question");

	private JRadioButton[] buttons = { new JRadioButton("1") };

	private ActionListener listener;

	public QuestionPanel(ActionListener listener) {
		super();

		this.listener = listener;

		setLayout(new FlowLayout());

		add(question);

		validate();
	}

	public void setQuestion(String s, Map options) {
		question.setText(s);

		for (JRadioButton button : buttons) {
			remove(button);
		}

		Object[] obs = options.keySet().toArray();

		if (obs[1] instanceof String) {
			buttons = new JRadioButton[obs.length];

			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = new JRadioButton((String) obs[i]);
				buttons[i].addActionListener(listener);
				buttonGroup.add(buttons[i]);
				add(buttons[i]);
			}
		}
	}
}
