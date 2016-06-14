package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import java.awt.*;

public class TextMessage extends JPanel {
	public static JTextArea text = new JTextArea();// TODO: observer model
	private Font font = new Font("Courier", Font.PLAIN, 10);

	public TextMessage() {
		setBorder(new TitledBorder("请看这里"));
		setLayout(new BorderLayout());
		text.setFont(font);
		text.setLineWrap(true);// the line is automatically wrapped when the text cannot fit in one line.
		text.setEditable(false);// user can only read
		text.setWrapStyleWord(true);// wrap on words rather than characters
		// create a scroll pane to hold the text area
		JScrollPane scrollPane = new JScrollPane(text);
		((DefaultCaret)text.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		text.setText("欢迎来到大富翁~" + "\n您将在此看到各类信息");
		add(scrollPane, BorderLayout.CENTER);
	}
}
