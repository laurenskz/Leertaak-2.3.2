package opgave4.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Jules on 21/02/2016.
 */
public class ButtonPanel extends JPanel{
	public static final String NEXT_TXT = "Next";
	public static final String LAST_TXT = "Previous";

	JButton next;
	JButton last;

	public ButtonPanel(ActionListener listener) {
		super();

		last = new JButton(LAST_TXT);
		last.addActionListener(listener);
		add(last);

		next = new JButton(NEXT_TXT);
		next.addActionListener(listener);
		add(next);
	}

	public void lastVisible(boolean b) {
		last.setVisible(b);
	}
}
