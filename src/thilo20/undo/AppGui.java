package thilo20.undo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
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
			operationsPanel.setBounds(0, 0, 490, 85);
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
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setBounds(221, 21, 20, 12);
				operationsPanel.add(horizontalStrut);
			}
			{
				JButton btnOp2 = new JButton("op2(int)");
				btnOp2.setBounds(246, 10, 87, 23);
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
				op2_val.setBounds(338, 11, 22, 20);
				op2_val.setHorizontalAlignment(SwingConstants.CENTER);
				op2_val.setText("0");
				op2_val.setToolTipText("op2 argument val");
				operationsPanel.add(op2_val);
				op2_val.setColumns(2);
			}

			JLabel lblComplexApi = new JLabel("Complex API");
			lblComplexApi.setBounds(10, 11, 96, 20);
			operationsPanel.add(lblComplexApi);

			JLabel lblUndoApi = new JLabel("Undo API");
			lblUndoApi.setBounds(10, 71, 96, 14);
			operationsPanel.add(lblUndoApi);
		}
		{
			JPanel undoPanel = new JPanel();
			undoPanel.setBounds(0, 100, 490, 119);
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
					cawu.undoAll();
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
					cawu.undo(comm);
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
			redoPanel.setBounds(0, 219, 490, 134);
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
					cawu.redo(comm);
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
		listUndo.setListData(cawu.getUndoCommands().toArray(new Command[0]));
		listRedo.setListData(cawu.getRedoCommands().toArray(new Command[0]));

		// scroll to end
		scrollPaneUndo.validate();
		JScrollBar vertical = scrollPaneUndo.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		scrollPaneRedo.validate();
		vertical = scrollPaneRedo.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}
}
