package opgave4.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Jules on 21/02/2016.
 */
public class ButtonPanel extends JPanel{
	public static final String NEXT_TXT = "Next";
	public static final String RESET_TXT = "Reset";

	JButton next;
	JButton reset;

	public ButtonPanel(ActionListener listener) {
		super();

		reset = new JButton(RESET_TXT);
		reset.addActionListener(listener);
		add(reset);

		next = new JButton(NEXT_TXT);
		next.addActionListener(listener);
		add(next);
	}

	public void resetVisible(boolean b) {
		reset.setVisible(b);
	}
}
