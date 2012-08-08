package pat.game.Tetris;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MPConnectFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MPConnectFrame(final TetrisGUI mainFrame) {

		super("Connect to Remote Game");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int frameLocationX = (int) ((dim.getWidth() - 300) / 2);
		int frameLocationY = (int) ((dim.getHeight() - 100) / 2);

		setBounds(frameLocationX, frameLocationY, 300, 100);

		final JTextField ipField = new JTextField();
		ipField.setBounds(10, 10, 100, 20);

		JButton connectButton = new JButton("Connect");

		connectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MPConnection.send(mainFrame, ipField.getText());
			}
		});

		connectButton.setBounds(150, 20, 80, 20);
		
		add(connectButton);
		add(ipField);
		

		setVisible(true);

	}



}
