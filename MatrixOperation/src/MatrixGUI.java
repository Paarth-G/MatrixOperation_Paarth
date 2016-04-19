import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import java.awt.*;

/**
 * This class has most of the methods used to create a table and an array with the values inserted into the table. 
 * 
 * @author Paarth Garg
 * @version 1.0
 * 
 * */

public class MatrixGUI 
{
	public JTable matrixTable;
	private int row, col;
	private boolean isDataSaved = false;
	
	/**
	 * Default Constructor 
	 * @param r : number of rows
	 * @param c : number of columns
	 * */
	public MatrixGUI(int r, int c)
	{
		row = r;
		col = c;
		
		 //set up matrixTable
		matrixTable = new JTable(row, col);
		matrixTable.setFont(new Font("Arial Black", Font.BOLD, 20));
		matrixTable.setRowHeight(30);
		matrixTable.setGridColor(Color.black);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		for(int i=0; i<col; i++)
		{
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			matrixTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
	     }	
	}
	
	/**
	 * Constructor to build the object from 2D Array
	 * @param p : a 2 Dimensional Array of integers
	 * */
	public MatrixGUI(Integer p[][])
	{
		//set up matrixTable
		String a[] = new String[p[0].length];
		for(int i = 0; i < a.length; i++)
		{
			a[i] = "data";
		}
		matrixTable = new JTable(p, a);
		matrixTable.setFont(new Font("Arial Black", Font.BOLD, 20));
		matrixTable.setRowHeight(30);
		matrixTable.setGridColor(Color.black);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		for(int i=0; i<col; i++)
		{
			matrixTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
	     }
	}
	
	/**
	 * Save Data for the matrix
	 * */
	public boolean saveData()
	{
		isDataSaved = true;
		for(int i = 0; i< row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(matrixTable.getModel().getValueAt(i, j) == null)
				{
					JOptionPane.showMessageDialog(null, "Enter and center your values by selecting a row.",
							"ERROR",JOptionPane.ERROR_MESSAGE);
					   isDataSaved = false;
					   return isDataSaved;
				}
			}
		}
		return isDataSaved;
	}
         
	/**
	 * Get Data from Table to then perform the equations on. 
	 * */
	public int[][] getTableData () 
	{  
	    DefaultTableModel dtm = (DefaultTableModel) matrixTable.getModel();
	 
	    int[][] tableData = new int[row][col];
	    for (int i = 0 ; i < row ; i++)
	    {
	        for (int j = 0 ; j < col ; j++)
	        {       	   
	        	    String s = (String)dtm.getValueAt(i, j);
	        	    if(s == null)
	        	    {
	        	    	   JOptionPane.showMessageDialog(null, "Please make sure"
	        	    	   		+ "all of your matrix values are centered before performing"
	        	    	   		+ "any operation.",
	        	    			   "ERROR", JOptionPane.ERROR_MESSAGE);
	        	    }
	        	    int value = Integer.parseInt(s);
	        	    tableData[i][j] = value;  
	        }
	    }
	    return tableData;
	}
	
	public JTable getMatrixAsTable()
	{		
		return matrixTable;
	}
	
	/**
	 * Set the bounds of the tables in a certain format.  
	 * 
	 * @param xStart : x coordinate of table
	 * @param yStart : y coordinate of table
	 * @param rows : number of rows on the table.
	 * */
	public void setBound(int xStart, int yStart, int rows)
	{
		matrixTable.setBounds(xStart, yStart, matrixTable.getColumnCount() * 75, rows * matrixTable.getRowHeight());
	}
	
	/**
	 * Center the Numbers on the table. 
	 * */
	public void centerSolution(int iArray[][]) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < iArray[0].length; i++) {
			matrixTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}
}
