import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.io.*;

public class ScoringInterface extends JFrame 
{
	private static JTextField teamOneScoreText, teamTwoScoreText, winnerTextField;
	private static int teamOneScore = 0;
	private static int teamTwoScore = 0;
        private String output = "";
	private static Scanner fileScanner;
	
	/**
	 * Constructor for scoring interface
	 * sets all of the UI elements up and makes it visible.
	 */
	public ScoringInterface()
	{
		super("Scoring Interface");
		setLocation(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setupUI(this);
		pack();
		setVisible(true);
	}
	
	/**
	 * Setup method which initializes all of the UI elements.
	 * @param gui returns the created gui.
	 */
	public static void setupUI(ScoringInterface gui)
	{
		/*
		 * JPanel initialization statements
		 */
			JPanel mainJPanel = new JPanel();
			JPanel buttonPanel = new JPanel();
			JPanel teamOnePanel = new JPanel();
			JPanel teamTwoPanel = new JPanel();
			JPanel teamOneScore = new JPanel();
			JPanel teamTwoScore = new JPanel();
			JPanel winnerText = new JPanel();
		
			//Although the default layout manager for a JPanel is FlowLayout,
			//It can be set with the setLayout method:
			//ex : mainJPanel.setLayout(new BorderLayout());
			mainJPanel.setLayout(new BorderLayout());
			buttonPanel.setLayout(new FlowLayout());
			teamOnePanel.setLayout(new FlowLayout());
			teamTwoPanel.setLayout(new FlowLayout());
			teamOneScore.setLayout(new BorderLayout());
			teamTwoScore.setLayout(new BorderLayout());
			winnerText.setLayout(new FlowLayout());
			
			
			//Set up some JButton components to add to the mainJPanel
			JButton button1 = new JButton("Score 1");
			JButton button2 = new JButton("Score 2");
			JButton button3 = new JButton("Score 3");
			button1.setName("T1S1");
			button2.setName("T1S2");
			button3.setName("T1S3");
			button1.addActionListener(gui.new ButtonListener());
			button2.addActionListener(gui.new ButtonListener());
			button3.addActionListener(gui.new ButtonListener());
				
			JButton button4 = new JButton("Score 1");
			JButton button5 = new JButton("Score 2");
			JButton button6 = new JButton("Score 3");
			button4.setName("T2S1");
			button5.setName("T2S2");
			button6.setName("T2S3");
			button4.addActionListener(gui.new ButtonListener());
			button5.addActionListener(gui.new ButtonListener());
			button6.addActionListener(gui.new ButtonListener());
			
			JButton newGame = new JButton("New Game");
			newGame.setName("NewGame");
			newGame.addActionListener(gui.new FunctionListener());
				
			JButton gameOver = new JButton("Game Over");
			gameOver.setName("GameOver");
			gameOver.addActionListener(gui.new FunctionListener());
				
			JButton openGame = new JButton("Open Game");
			openGame.setName("Open");
			openGame.addActionListener(gui.new FunctionListener());
				
			JButton saveGame = new JButton("Save Game");
			saveGame.setName("Save");
			saveGame.addActionListener(gui.new FunctionListener());
				
			//JTextFields for score
			teamOneScoreText = new JTextField("0");
			teamTwoScoreText = new JTextField("0");
			teamOneScoreText.setHorizontalAlignment(SwingConstants.CENTER);
			teamTwoScoreText.setHorizontalAlignment(SwingConstants.CENTER);
			teamOneScoreText.setEditable(false);
			teamTwoScoreText.setEditable(false);
			
			//JLables for team labels
			JLabel teamOneName = new JLabel("Team One");
			JLabel teamTwoName = new JLabel("Team Two");
			teamOneName.setHorizontalAlignment(SwingConstants.CENTER);
			teamTwoName.setHorizontalAlignment(SwingConstants.CENTER);

			//JLabel for winner/tie text
			winnerTextField = new JTextField(10);
			winnerTextField.setHorizontalAlignment(SwingConstants.CENTER);
			winnerTextField.setEditable(false);
			winnerText.add(winnerTextField);
			
			//add all of the components to the mainJPanel
			//Note if we were using BorderLayout we would say:
			//mainJPanel.add(componentName,BorderLayout.NORTH)
			//to add the component to the North section. Likewise for EAST, WEST, CENTER and SOUTH
			//Adding the respective panels and buttons to the GUI
			mainJPanel.add(teamOneScore,BorderLayout.WEST);
			mainJPanel.add(teamTwoScore, BorderLayout.EAST);
			mainJPanel.add(winnerText,BorderLayout.CENTER);
			mainJPanel.add(buttonPanel, BorderLayout.NORTH);
			buttonPanel.add(newGame, BorderLayout.NORTH);
			buttonPanel.add(openGame, BorderLayout.NORTH);
			buttonPanel.add(gameOver, BorderLayout.NORTH);
			buttonPanel.add(saveGame, BorderLayout.NORTH);
			
			//Adding teamOne's respective elements to the panel
			teamOneScore.add(teamOnePanel, BorderLayout.SOUTH);
			teamOneScore.add(teamOneScoreText, BorderLayout.CENTER);
			teamOneScore.add(teamOneName, BorderLayout.NORTH);
			
			//Adding teamTwo's respective elements to the panel
			teamTwoScore.add(teamTwoPanel, BorderLayout.SOUTH);
			teamTwoScore.add(teamTwoScoreText, BorderLayout.CENTER);
			teamTwoScore.add(teamTwoName, BorderLayout.NORTH);
			//Adding the score buttons
			teamOnePanel.add(button1);
			teamOnePanel.add(button2);
			teamOnePanel.add(button3);
			teamTwoPanel.add(button4);
			teamTwoPanel.add(button5);
			teamTwoPanel.add(button6);
		
		//Finally, add the initialized mainJPanel to the frame's content pane:
		gui.getContentPane().add(mainJPanel);
	}
	
	/**
	 * ButtonListeners for all of the scoring buttons
	 */
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//This code adds the respective scores any time a button is pushed
			JButton button = (JButton)arg0.getSource();
			if(button.getName().equals("T1S1"))
			{
				teamOneScore = teamOneScore + 1;
				teamOneScoreText.setText(Integer.toString(teamOneScore));
			}
			if(button.getName().equals("T1S2"))
			{
				teamOneScore = teamOneScore + 2;
				teamOneScoreText.setText(Integer.toString(teamOneScore));
			}
			if(button.getName().equals("T1S3"))
			{
				teamOneScore = teamOneScore + 3;
				teamOneScoreText.setText(Integer.toString(teamOneScore));
			}
			if(button.getName().equals("T2S1"))
			{
				teamTwoScore = teamTwoScore + 1;
				teamTwoScoreText.setText(Integer.toString(teamTwoScore));
			}
			if(button.getName().equals("T2S2"))
			{
				teamTwoScore = teamTwoScore + 2;
				teamTwoScoreText.setText(Integer.toString(teamTwoScore));
			}
			if(button.getName().equals("T2S3"))
			{
				teamTwoScore = teamTwoScore + 3;
				teamTwoScoreText.setText(Integer.toString(teamTwoScore));
			}
		}	
	}
	
	/**
	 * FunctionListener implements the buttons that are not related to the score.
	 */
	private class FunctionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton function = (JButton)arg0.getSource();
			
			if(function.getName().equals("NewGame"))
			{
				winnerTextField.setText("");
				teamOneScore = 0;
				teamTwoScore = 0;
				teamOneScoreText.setText(Integer.toString(teamOneScore));
				teamTwoScoreText.setText(Integer.toString(teamTwoScore));
			
			}
			if(function.getName().equals("GameOver"))
			{
				//if TeamOne has the higher score they win
				if(teamOneScore > teamTwoScore)
				{
					winnerTextField.setText("<- WINNER");
				}
				//if TeamTwo has the higher score they win
				else if(teamOneScore < teamTwoScore)
				{
					winnerTextField.setText("WINNER ->");
				}
				//if the scores are equal then the game is a tie
				else if(teamOneScore == teamTwoScore)
				{
					winnerTextField.setText("<- TIE ->");
				}
			}
			if(function.getName().equals("Open"))
			{
				try {
					fileScanner = new Scanner(new File("gameData.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				teamOneScore = (int) fileScanner.nextDouble();
				teamTwoScore = (int) fileScanner.nextDouble();
				teamOneScoreText.setText(Integer.toString(teamOneScore));
				teamTwoScoreText.setText(Integer.toString(teamTwoScore));
				//if TeamOne has the higher score they win
				if(teamOneScore > teamTwoScore)
				{
					winnerTextField.setText("<- WINNER");
				}
				//if TeamTwo has the higher score they win
				else if(teamOneScore < teamTwoScore)
				{
					winnerTextField.setText("WINNER ->");
				}
				//if the scores are equal then the game is a tie
				else if(teamOneScore == teamTwoScore)
				{
					winnerTextField.setText("<- TIE ->");
				}
				fileScanner.close();
				
			}
			if(function.getName().equals("Save"))
			{
				try 
		        {
			        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			        
			        // Method 1: direct access to a microsoft access database file
		            String filename = "./demo.accdb";//use the name of your database!
		            String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
		            database+= filename.trim() + ";DriverID=22;READONLY=true}"; // add on to the end 
		            Connection con = DriverManager.getConnection( database ,"",""); 
		            
		         // try and create a java.sql.Statement so we can run queries
			        Statement s = con.createStatement();
                                
			        //Line used once to create database -- should comment out the create line after first run
			        s.execute("create table BBallScores( team1 number, team2 number, PRIMARY KEY(team1,team2))"); // create a table
			        s.execute("insert into BBallScores values(" + teamOneScore + "," + teamTwoScore + ")"); // insert some data into the table
			        s.execute("select team1 from BBallScores order by team1"); // select the data from the table
	
                                ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query
                                rs.next();
			        output = rs.getString(1); //copy the data to output
                                s.execute("select team2 from BBallScores order by team2");
                                rs = s.getResultSet();
                                rs.next();
			        output += " " + rs.getString(1);
                                
                                try{
                                FileWriter fstream = new FileWriter("gameData.txt");
                                BufferedWriter out = new BufferedWriter(fstream);
                                out.write(output + "\n"); //write the output to the file to open
                                output = "";
                                
                                out.close();
                                }catch (Exception e){//Catch exception if any
                                System.err.println("Error: " + e.getMessage());
                                }
                                
			        s.execute("drop table BBallScores");
			        s.close(); // close the Statement to let the database know we're done with it
			        con.close(); // close the Connection to let the database know we're done with it
			        
		        }
				catch (Exception e)
				{
					System.out.println("ERROR: " + e);
				}
				
			}
			
		}
		
	}
	
	public static void main(String [] args)
	{
		//All that needs to be done is instantiate a scoring interface object
		//The constructor and setupUI methods implement all of the functionality.
		ScoringInterface si = new ScoringInterface();
	}
}