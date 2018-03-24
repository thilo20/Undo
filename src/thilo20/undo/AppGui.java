package thilo20.undo;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** App using ComplexApi with undo support - interactive GUI. */
public class AppGui extends JDialog {

	private static final long serialVersionUID = 1L;

	/** under test: complex api with undo */
	// ComplexApiWithUndo cawu = new ComplexApiWithUndo();
	ComplexApiWithUndo cawu = new ComplexApiWithUndoGeneric();

	/** GUI elements */
	private JTextField op2_val;
	private JTextField n_undo;
	private JTextField n_redo;
	private JList<Command> listUndo;
	private JList<Command> listRedo;
	private JScrollPane scrollPaneUndo;
	private JScrollPane scrollPaneRedo;
	private JTextField op3_name;
	private JTextField op4_val;
	private JTextField op4_val2;

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
		setBounds(100, 100, 506, 394);
		getContentPane().setLayout(null);
		{
			JPanel operationsPanel = new JPanel();
			operationsPanel.setBounds(0, 0, 490, 152);
			operationsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(operationsPanel);
			{
				JButton btnOp1 = new JButton("op1()");
				btnOp1.setBounds(129, 10, 87, 23);
				btnOp1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						cawu.op1();
						cawu.printState();
						updateLists();
					}
				});
				operationsPanel.setLayout(null);
				operationsPanel.add(btnOp1);
			}
			{
				JButton btnOp2 = new JButton("op2(int)");
				btnOp2.setBounds(129, 36, 87, 23);
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
				op2_val.setBounds(221, 37, 22, 20);
				op2_val.setHorizontalAlignment(SwingConstants.CENTER);
				op2_val.setText("0");
				op2_val.setToolTipText("op2 int param");
				operationsPanel.add(op2_val);
				op2_val.setColumns(2);
			}

			JButton btnOp3 = new JButton("op3(String)");
			btnOp3.setBounds(129, 64, 102, 23);
			btnOp3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cawu.op3(op3_name.getText());
					cawu.printState();
					updateLists();
				}
			});
			operationsPanel.add(btnOp3);

			op3_name = new JTextField();
			op3_name.setBounds(245, 65, 86, 20);
			operationsPanel.add(op3_name);
			op3_name.setText("abcdef");
			op3_name.setToolTipText("op3 String param");
			op3_name.setColumns(10);

			JButton btnOp4 = new JButton("op4(int,Double)");
			btnOp4.setBounds(129, 89, 126, 23);
			btnOp4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int val = Integer.parseInt(op4_val.getText());
					Double val2 = Double.parseDouble(op4_val2.getText());
					cawu.op4(val, val2);
					cawu.printState();
					updateLists();
				}
			});
			operationsPanel.add(btnOp4);

			op4_val = new JTextField();
			op4_val.setBounds(260, 90, 22, 20);
			operationsPanel.add(op4_val);
			op4_val.setText("2");
			op4_val.setToolTipText("op4 int param");
			op4_val.setColumns(10);

			op4_val2 = new JTextField();
			op4_val2.setBounds(287, 91, 44, 20);
			operationsPanel.add(op4_val2);
			op4_val2.setText("2.5");
			op4_val2.setToolTipText("op4 Double param");
			op4_val2.setColumns(10);

			JLabel lblComplexApi = new JLabel("Complex API");
			lblComplexApi.setBounds(10, 11, 96, 20);
			operationsPanel.add(lblComplexApi);

			JLabel lblUndoApi = new JLabel("Undo API");
			lblUndoApi.setBounds(10, 127, 96, 14);
			operationsPanel.add(lblUndoApi);
		}
		{
			JPanel undoPanel = new JPanel();
			undoPanel.setBounds(0, 153, 490, 94);
			getContentPane().add(undoPanel);
			undoPanel.setLayout(null);
			{
				listUndo = new JList<Command>();
				listUndo.setVisibleRowCount(5);
				listUndo.setMinimumSize(new Dimension(100, 100));
				listUndo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPaneUndo = new JScrollPane(listUndo);
				scrollPaneUndo.setBounds(60, 5, 258, 82);
				scrollPaneUndo.setMinimumSize(new Dimension(100, 100));
				undoPanel.add(scrollPaneUndo);
			}

			JButton btnUndoAll = new JButton("undoAll");
			btnUndoAll.setBounds(328, 5, 84, 23);
			undoPanel.add(btnUndoAll);
			btnUndoAll.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cawu.getUndoApi().undoAll();
					cawu.printState();
					updateLists();
				}
			});

			JButton btnUndo = new JButton("undo");
			btnUndo.setBounds(328, 36, 69, 23);
			undoPanel.add(btnUndo);
			btnUndo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int comm = Integer.parseInt(n_undo.getText());
					cawu.getUndoApi().undo(comm);
					cawu.printState();
					updateLists();
				}
			});

			{
				n_undo = new JTextField();
				n_undo.setBounds(402, 38, 22, 20);
				undoPanel.add(n_undo);
				n_undo.setHorizontalAlignment(SwingConstants.CENTER);
				n_undo.setText("1");
				n_undo.setToolTipText("number of undo steps");
				n_undo.setColumns(2);
			}
		}
		{
			JPanel redoPanel = new JPanel();
			redoPanel.setBounds(0, 248, 490, 105);
			getContentPane().add(redoPanel);
			{
				listRedo = new JList<Command>();
				listRedo.setVisibleRowCount(5);
				listRedo.setMinimumSize(new Dimension(100, 100));
				listRedo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPaneRedo = new JScrollPane(listRedo);
				scrollPaneRedo.setBounds(62, 11, 258, 82);
				scrollPaneUndo.setMinimumSize(new Dimension(100, 100));
				redoPanel.setLayout(null);
				redoPanel.add(scrollPaneRedo);
			}

			JButton btnRedo = new JButton("redo");
			btnRedo.setBounds(330, 11, 69, 23);
			redoPanel.add(btnRedo);
			btnRedo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int comm = Integer.parseInt(n_redo.getText());
					cawu.getUndoApi().redo(comm);
					cawu.printState();
					updateLists();
				}
			});

			{
				n_redo = new JTextField();
				n_redo.setBounds(404, 13, 22, 20);
				redoPanel.add(n_redo);
				n_redo.setHorizontalAlignment(SwingConstants.CENTER);
				n_redo.setText("1");
				n_redo.setToolTipText("number of redo steps");
				n_redo.setColumns(2);
			}
		}

		updateLists();
		repaint();
	}

	private void updateLists() {
		// update list content
		listUndo.setListData(cawu.getUndoApi().getUndoCommands().toArray(new Command[0]));
		listRedo.setListData(cawu.getUndoApi().getRedoCommands().toArray(new Command[0]));

		// scroll to end
		scrollPaneUndo.validate();
		JScrollBar vertical = scrollPaneUndo.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		scrollPaneRedo.validate();
		vertical = scrollPaneRedo.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}
}
