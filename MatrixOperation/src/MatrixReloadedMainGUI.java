import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class MatrixReloadedMainGUI extends JFrame {
	private MatrixGUI matrixA, matrixB, matrixC;
	private JButton saveMatrixData;

	private JTextField rowJTextField, colJTextField;
	private JLabel label, labelA, labelB, labelC, instructionsLabel;
	private JButton buttonA, buttonB, addButton, subtractButton, multiplyButton;

	private Container contentPane;
	private TheMatrix calculator = new TheMatrix();

	MatrixReloadedMainGUI() {
		createMainInterface();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void createMainInterface() {
		contentPane = getContentPane();
		contentPane.setLayout(null);

		// set up instructions
		instructionsLabel = new JLabel("<html>NOTE: Press the tab key after" + "<br>entering the matrix values</html>");
		instructionsLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		instructionsLabel.setBounds(15, 58, 530, 40);
		instructionsLabel.setForeground(Color.RED);
		contentPane.add(instructionsLabel);

		// set up rowJTextField
		rowJTextField = new JTextField();
		rowJTextField.setFont(new Font("Arial Black", Font.BOLD, 25));
		rowJTextField.setBounds(10, 30, 100, 30);
		rowJTextField.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(rowJTextField);
		rowJTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//contentPane.remove(matrixC.matrixTable);
				repaint();
			}
		});

		// set up colJTextField
		colJTextField = new JTextField();
		colJTextField.setFont(new Font("Arial Black", Font.BOLD, 25));
		colJTextField.setBounds(110, 30, 100, 30);
		colJTextField.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(colJTextField);
		colJTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// contentPane.remove(matrixC.matrixTable);
				repaint();
			}
		});

		// set up label
		label = new JLabel("         Row             Col");
		label.setFont(new Font("Arial Black", Font.BOLD, 14));
		label.setBounds(0, 0, 200, 30);
		contentPane.add(label);

		// set up label A
		labelA = new JLabel("MATRIX A ");
		labelA.setFont(new Font("Arial Black", Font.BOLD, 20));
		labelA.setBounds(10, 100, 200, 30);
		contentPane.add(labelA);

		// set up label B
		labelB = new JLabel("MATRIX B ");
		labelB.setFont(new Font("Arial Black", Font.BOLD, 20));
		labelB.setBounds(10, 400, 200, 30);
		contentPane.add(labelB);

		// set up label C
		labelC = new JLabel("SOLUTION: MATRIX C ");
		labelC.setFont(new Font("Arial Black", Font.BOLD, 20));
		labelC.setBounds(700, 100, 300, 30);
		contentPane.add(labelC);

		// set up button - create Matrix A
		buttonA = new JButton("Create Matrix A");
		buttonA.setFont(new Font("Arial Black", Font.BOLD, 14));
		buttonA.setBounds(220, 30, 150, 30);
		contentPane.add(buttonA);
		buttonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(matrixA != null)
					contentPane.remove(matrixA.matrixTable);
				
				addButton.setEnabled(false);
				subtractButton.setEnabled(false);
				multiplyButton.setEnabled(false);
				labelC.setText("SOLUTION: MATRIX C ");
				try {
					int row = Integer.parseInt(rowJTextField.getText());
					int col = Integer.parseInt(colJTextField.getText());

					if (row <= 9 && col <= 9) {
						if (row == 1 && col == 1) {
							JOptionPane.showMessageDialog(null, "1X1 matrices not allowed!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							matrixA = new MatrixGUI(row, col);
							matrixA.setBound(10, 130, row);
							contentPane.add(matrixA.matrixTable);
							repaint();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Rows and Columns must be integers greater than 1 and" + " less than 10", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Please enter a row and a col as integers from 1 to 9", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// set up button - Create Matrix B
		buttonB = new JButton("Create Matrix B");
		buttonB.setFont(new Font("Arial Black", Font.BOLD, 14));
		buttonB.setBounds(360, 30, 150, 30);
		contentPane.add(buttonB);
		buttonB.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(matrixB != null)
							contentPane.remove(matrixB.matrixTable);
					
						addButton.setEnabled(false);
						subtractButton.setEnabled(false);
						multiplyButton.setEnabled(false);
						labelC.setText("SOLUTION: MATRIX C ");
						try {
							int row = Integer.parseInt(rowJTextField.getText());
							int col = Integer.parseInt(colJTextField.getText());
							if (row <= 9 && col <= 9) {
								if (row == 1 && col == 1) {
									JOptionPane.showMessageDialog(null, "1X1 matrices not allowed!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									matrixB = new MatrixGUI(row, col);
									matrixB.setBound(10, 430, row);
									contentPane.add(matrixB.matrixTable);
									repaint();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Rows and Columns must be integers greater than 1 and " + " less than 10",
										"ERROR", JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception error) {
							JOptionPane.showMessageDialog(null, "Please enter a row and a col as integers from 1 to 9",
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});

		// set up button - Save data
		saveMatrixData = new JButton("SAVE DATA");
		saveMatrixData.setFont(new Font("Arial Black", Font.BOLD, 16));
		saveMatrixData.setForeground(Color.RED);
		saveMatrixData.setBounds(305, 60, 120, 40);
		contentPane.add(saveMatrixData);
		saveMatrixData.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (matrixA != null && matrixB != null && matrixA.saveData() && matrixB.saveData()) {
							JOptionPane.showMessageDialog(null, "DATA SAVED");
							addButton.setEnabled(true);
							subtractButton.setEnabled(true);
							multiplyButton.setEnabled(true);
						} else {
							// do nothing
						}
					}
				});

		// set up button - add
		addButton = new JButton("ADD");
		addButton.setEnabled(false);
		addButton.setBounds(600, 30, 120, 30);
		addButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		contentPane.add(addButton);
		addButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int A[][] = getMatrixAData();
						int B[][] = getMatrixBData();
						int C[][] = calculator.add(A, B);
						
						//checking that dimensions of both matrices are same
						if(A.length != B.length || A[0].length != B[0].length)
						{	
							JOptionPane.showMessageDialog(null,calculator.ErrMessage,"No Solution",JOptionPane.ERROR_MESSAGE);
							return;
						}						

						// NO SOLUTION
						if (C.length == 1 && C[0].length == 1)
						{
							labelC.setText("A+B = NO SOLUTION");
							JOptionPane.showMessageDialog(null, "NO SOLUTION", "ERROR", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						//contentPane.remove(matrixC.matrixTable);
						if (matrixC != null)
							contentPane.remove(matrixC.matrixTable);
						matrixC = new MatrixGUI(convertFromIntToInteger(C));
						matrixC.setBound(700, 130, C.length);
						matrixC.centerSolution(C);
						matrixC.matrixTable.setEnabled(false);
						contentPane.add(matrixC.matrixTable);
						labelC.setText("SOLUTION: A + B = C");
						repaint();
						
					}
				});

		//set up button - subtract 
		subtractButton = new JButton("SUBTRACT");
		subtractButton.setEnabled(false);
		subtractButton.setBounds(750, 30, 120, 30);
		subtractButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		contentPane.add(subtractButton);
		subtractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int A[][] = getMatrixAData();
				int B[][] = getMatrixBData();
				int C[][] = calculator.subtract(A, B);
				
				//checking that dimensions of both matrices are same
				if(A.length != B.length || A[0].length != B[0].length)
				{	
					JOptionPane.showMessageDialog(null,calculator.ErrMessage,"No Solution",JOptionPane.ERROR_MESSAGE);
					return;
				}
				// NO SOLUTION
				if (C.length == 1 && C[0].length == 1)
				{
					labelC.setText("A+B = NO SOLUTION");
					JOptionPane.showMessageDialog(null, "NO SOLUTION", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (matrixC != null)
					contentPane.remove(matrixC.matrixTable);
				matrixC = new MatrixGUI(convertFromIntToInteger(C));
				matrixC.setBound(700, 130, C.length);
				matrixC.centerSolution(C);
				contentPane.add(matrixC.matrixTable);
				labelC.setText("SOLUTION: A - B = C");
				repaint();
			}
		});

		//set up button - mulitiply
		multiplyButton = new JButton("MULTIPLY");
		multiplyButton.setEnabled(false);
		multiplyButton.setBounds(900, 30, 120, 30);
		multiplyButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		contentPane.add(multiplyButton);
		multiplyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int A[][] = getMatrixAData();
				int B[][] = getMatrixBData();
				int C[][] = calculator.multiply(A, B);
				
				//checking that dimensions of both matrices are compatible
				if(A[0].length != B.length)
				{	
					JOptionPane.showMessageDialog(null, calculator.ErrMessage,"No Solution!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//checking that C has atleast one row and one column
				if (C.length == 1 && C[0].length == 1)
				{
					labelC.setText("A+B = NO SOLUTION");
					JOptionPane.showMessageDialog(
							null, 
							"NO SOLUTION", 
							"ERROR", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (matrixC != null)
					contentPane.remove(matrixC.matrixTable);
				
				matrixC = new MatrixGUI(convertFromIntToInteger(C));
				matrixC.setBound(700, 130, C.length);
				matrixC.centerSolution(C);
				contentPane.add(matrixC.matrixTable);
				labelC.setText("SOLUTION: A * B = C");
				repaint();
			}
		});

		setSize(1200, 1200);
		setTitle("Matrix Reloaded");
		setVisible(true);
	}

	public int[][] getMatrixAData() {
		return matrixA.getTableData();
	}

	public int[][] getMatrixBData() {
		return matrixB.getTableData();
	}
	
	public Integer[][] convertFromIntToInteger(int array[][])
	{
		Integer a[][] = new Integer[array.length][array[0].length];
		
			for(int row = 0; row < array.length; row++)
			{
				for(int col = 0; col < array[0].length; col++)
				{
					Integer x = new Integer(array[row][col]);
					a[row][col] = x;
				}
			}
		return a;
	}	

}
