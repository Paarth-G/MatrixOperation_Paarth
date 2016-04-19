import javax.swing.JFrame;

public class Main 
{
	private MatrixReloadedMainGUI mainGUI;
	
	
	public Main()
	{
		mainGUI = new MatrixReloadedMainGUI();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
			Main app = new  Main();
			
	}

}
