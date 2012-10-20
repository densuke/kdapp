package ${package}.ve;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DemoFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel Text = null;
	private JLabel Message = null;

	/**
	 * @param owner
	 */
	public DemoFrame() {
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("VisualEditorによるフレームデモ");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
				System.exit(0);

			}
		});
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Message = new JLabel();
			Message.setText("ウィンドウ上右上の×の字ボタンで閉じられます");
			Text = new JLabel();
			Text.setText("中央に文字を出してみてます");
			Text.setHorizontalTextPosition(SwingConstants.CENTER);
			Text.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(Text, BorderLayout.CENTER);
			jContentPane.add(Message, BorderLayout.SOUTH);
		}
		return jContentPane;
	}

}
