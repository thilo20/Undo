package thilo20.undo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** App using ComplexApi with undo support - interactive GUI. */
public class AppGui extends JDialog {

	private static final long serialVersionUID = 1L;

	/** complex api under test */
	ComplexApiWithUndo cawu = new ComplexApiWithUndo();

	/** GUI elements */
	private final JPanel operationsPanel = new JPanel();
	private JTextField op2_val;
	private JTextField n_undo;
	private JTextField n_redo;
	private JList<Command> listUndo;
	private JList<Command> listRedo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AppGui dialog = new AppGui();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AppGui() {
		setTitle("undo GUI demo");
		setBounds(100, 100, 335, 402);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		operationsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(operationsPanel);
		operationsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JButton btnOp1 = new JButton("operation 1");
			btnOp1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					cawu.op1();
					cawu.printState();
					updateLists();
				}
			});
			operationsPanel.add(btnOp1);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			operationsPanel.add(horizontalStrut);
		}
		{
			JButton btnOp2 = new JButton("operation 2");
			btnOp2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int val = Integer.parseInt(op2_val.getText());
					cawu.op2(val);
					cawu.printState();
					updateLists();
				}
			});
			operationsPanel.add(btnOp2);
		}
		{
			op2_val = new JTextField();
			op2_val.setHorizontalAlignment(SwingConstants.CENTER);
			op2_val.setText("0");
			op2_val.setToolTipText("op2 argument val");
			operationsPanel.add(op2_val);
			op2_val.setColumns(2);
		}
		{
			JPanel undoPanel = new JPanel();
			getContentPane().add(undoPanel);
			{
				listUndo = new JList<Command>();
				undoPanel.add(listUndo);
				listUndo.setPreferredSize(new Dimension(100, 100));
			}
			JButton btnUndo = new JButton("undo");
			undoPanel.add(btnUndo);
			btnUndo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int comm = Integer.parseInt(n_undo.getText());
					cawu.undo(comm);
					cawu.printState();
					updateLists();
				}
			});
			{
				n_undo = new JTextField();
				undoPanel.add(n_undo);
				n_undo.setHorizontalAlignment(SwingConstants.CENTER);
				n_undo.setText("1");
				n_undo.setToolTipText("number of undo steps");
				n_undo.setColumns(2);
			}
			{
				n_undo.setToolTipText("number of redo steps");
			}
		}
		{
			JPanel redoPanel = new JPanel();
			getContentPane().add(redoPanel);
			{
				listRedo = new JList<Command>();
				redoPanel.add(listRedo);
				listRedo.setPreferredSize(new Dimension(100, 100));
			}
			{
				JButton btnRedo = new JButton("redo");
				redoPanel.add(btnRedo);
				n_redo = new JTextField();
				redoPanel.add(n_redo);
				n_redo.setHorizontalAlignment(SwingConstants.CENTER);
				n_redo.setText("1");
				n_redo.setColumns(2);
				btnRedo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int comm = Integer.parseInt(n_redo.getText());
						cawu.redo(comm);
						cawu.printState();
						updateLists();
					}
				});
			}
		}

		updateLists();
	}

	private void updateLists() {
		getListUndo().setListData(cawu.getUndoCommands().toArray(new Command[0]));
		getListRedo().setListData(cawu.getRedoCommands().toArray(new Command[0]));
	}

	protected JList<Command> getListUndo() {
		return listUndo;
	}

	protected JList<Command> getListRedo() {
		return listRedo;
	}
}
