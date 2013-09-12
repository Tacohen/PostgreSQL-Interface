package database;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author jchause
 *
 */
public class econMain extends JFrame{

	static int queryType;
	static String finalResult ="";
	static String monthChosen;
	static String dayChosen;
	static String yearChosen;
	static String currencyChosen;
	static String avgMonthStart;
	static String avgDayStart;
	static String avgYearStart;
	static String avgMonthEnd;
	static String avgDayEnd;
	static String avgYearEnd;
	static String dateChosen;
	static String avgStartDate;
	static String avgEndDate;
	static JPanel startScreen = new JPanel();
	static JFrame f = new JFrame("Economic Comparator");
	static boolean nasdaqBool;
	static boolean nasdaqRadioBool;
	static boolean dowJonesBool;
	static boolean dowJonesRadioBool;
	static boolean snpBool;
	static boolean snpRadioBool;
	static boolean xChangeBool;
	static boolean xChangeRadioBool;
	static boolean goldBool;
	static boolean goldRadioBool;
	static boolean treasBool;
	static boolean treasRadioBool;
	static boolean intRateBool;
	static boolean intRateRadioBool;
	static boolean oilBool;
	static boolean oilRadioBool;
	static boolean eventsBool;
	static boolean gasBool;
	static boolean gasRadioBool;
	static String dataDesired;
	static JPanel queryOne = new JPanel();
	static JPanel queryTwo = new JPanel();
	static JPanel queryThree = new JPanel();

	static Dimension maximumSize = new Dimension(400,85);
	/**
	 * @param args
	 */
	public static void main(String[] args) {		

		f.setSize(800, 600);
		f.setLocation(300,100);
		f.getContentPane().add(BorderLayout.CENTER, new JTextArea(10, 40));

		JPanel panel = panelCreator();

		f.getContentPane().add(panel);
		f.setVisible(true);
	}
	
	public static void run(){
		EconDataComp.frame.setSize(800, 600);
		EconDataComp.frame.setLocation(300,100);
		EconDataComp.frame.getContentPane().add(BorderLayout.CENTER, new JTextArea(10, 40));
		JPanel mainPanel = new JPanel();

		JPanel panel = panelCreator();
		
		EconDataComp.frame.getContentPane().add(panel);
		EconDataComp.frame.setVisible(true);
		
	}


	public static JPanel panelCreator(){

		// Layout of the control panel
		startScreen.setLayout(new BoxLayout(startScreen, BoxLayout.Y_AXIS));
		//startScreen.setVisible(true);
		JButton home = new JButton("Back to Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EconDataComp.panel_1.setVisible(false);
				EconDataComp.panel.setVisible(true);
			}
		});
		startScreen.add(home);
		queryOne.setLayout(new BoxLayout(queryOne, BoxLayout.Y_AXIS));
		queryTwo.setLayout(new BoxLayout(queryTwo, BoxLayout.Y_AXIS));
		queryThree.setLayout(new BoxLayout(queryThree, BoxLayout.Y_AXIS));



		// Strings-------------------------
		String[] queries = {"Choose Query", "Enter a date, Find data", "Enter data, find a date", "Enter Date Range, find averages"};
		final String[] months = {"Choose Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] currency = {"Choose Currency", "AustralianDollar", "Euro", "NewZealandDollar", "BritishPound", "BrazilianReal", "CanadianDollar", "ChineseYuan", "DanishKroner", "HongKongDollar", "IndianRupee", "JapaneseYen", "KoreanWon", "MalaysianRinggit", "MexicanPeso", "NorweiganKroner", "SwedishKroner", "SouthAfricanRand", "SriLankaRupee", "SwissFranc", "TaiwaneseDollar", "ThaiBaht"};
		final String[] days = new String[32];
		days[0] = "Choose Day";
		for (int i=1; i<10; i++)
			days[i] = "0"+ i;
		for (int i =10; i<=31; i++)
			days[i]=Integer.toString(i);
		final String[] years = new String[42];
		years[0] = "Choose Year";
		for (int i=1970; i<=2010; i++)
			years[i-1969] = Integer.toString(i);

		//Dropdown for exchange rates
		final JComboBox xChangeDropDown = new JComboBox(currency);
		xChangeDropDown.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				currencyChosen = algoType;
			}
		});	
		
		//RadioButtons-----------------
		
		ButtonGroup radioGroup = new ButtonGroup();
		final JRadioButton nasdaqRadio = new JRadioButton("NASDAQ", false);
		nasdaqRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				nasdaqRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	

		final JRadioButton dowRadio = new JRadioButton("Dow Jones", false);
		dowRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				dowJonesRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		final JRadioButton snpRadio = new JRadioButton("S&P", false);
		snpRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				snpRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		final JRadioButton xChangeRadio = new JRadioButton("Exchange Rates", false);
		xChangeRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				xChangeRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				queryTwo.add(xChangeDropDown);
				//System.out.println("in qwe");
				xChangeDropDown.setMaximumSize(maximumSize);
				startScreen.revalidate();
				EconDataComp.frame.repaint();
				//remove currency comboBox if unchecked
				if (!xChangeRadioBool){
					queryTwo.remove(xChangeDropDown);
					startScreen.revalidate();
					EconDataComp.frame.repaint();
				}
			}
		});	
		
		final JRadioButton goldRadio = new JRadioButton("Gold", false);
		goldRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				goldRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		final JRadioButton intRateRadio = new JRadioButton("Interest Rates", false);
		intRateRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				intRateRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		final JRadioButton treasRadio = new JRadioButton("10 Year Treasuries", false);
		treasRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				treasRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		final JRadioButton oilRadio = new JRadioButton("Oil Prices", false);
		oilRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				oilRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		final JRadioButton gasRadio = new JRadioButton("Gas Prices", false);
		gasRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				gasRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
			}
		});	
		
		radioGroup.add(nasdaqRadio);
		radioGroup.add(dowRadio);
		radioGroup.add(snpRadio);
		radioGroup.add(xChangeRadio);
		radioGroup.add(goldRadio);
		radioGroup.add(intRateRadio);
		radioGroup.add(treasRadio);
		radioGroup.add(oilRadio);
		radioGroup.add(gasRadio);
		
		//CheckBoxes-----------------
		final JCheckBox nasdaq = new JCheckBox("NASDAQ");
		nasdaq.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						nasdaqBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox dowJones = new JCheckBox("Dow Jones");
		dowJones.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						dowJonesBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox snp = new JCheckBox("S&P");
		snp.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						snpBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox xChange = new JCheckBox("Exchange Rates");
		xChange.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						xChangeBool = (e.getStateChange() == ItemEvent.SELECTED);
						queryOne.add(xChangeDropDown);
						queryThree.add(xChangeDropDown);
						xChangeDropDown.setMaximumSize(maximumSize);
						startScreen.revalidate();
						EconDataComp.frame.repaint();
						//remove currency comboBox if unchecked
						if (!xChangeBool){
							queryOne.remove(xChangeDropDown);
							queryThree.remove(xChangeDropDown);
							startScreen.revalidate();
							EconDataComp.frame.repaint();
						}


					}
				}
		);
		final JCheckBox gold = new JCheckBox("Gold");
		gold.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						goldBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox treasury = new JCheckBox("Treasury Rates");
		treasury.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						treasBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox intRate = new JCheckBox("Interest Rates");
		intRate.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						intRateBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox oil = new JCheckBox("Oil Prices");
		oil.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						oilBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox events = new JCheckBox("Interesting Events");
		events.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						eventsBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);
		final JCheckBox gas = new JCheckBox("Gas Prices");
		gas.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// Set "ignore" whenever box is checked or unchecked.
						gasBool = (e.getStateChange() == ItemEvent.SELECTED);
					}
				}
		);

		//Textfields---------------

		final JTextField data = new JTextField("Enter Data Desired Here");
		data.setMaximumSize(new Dimension(200,40));
		


		// ComboBoxes----------------------


		//Dropdown for choose day
		final JComboBox dayDropDown = new JComboBox(days);
		dayDropDown.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				dayChosen = algoType;
			}
		});	

		//Dropdown for choose month
		final JComboBox monthDropDown = new JComboBox(months);
		monthDropDown.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				monthChosen = algoType;
			}
		});	

		//Dropdown for choose year
		final JComboBox yearDropDown = new JComboBox(years);
		yearDropDown.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				yearChosen = algoType;
			}
		});	
		
		//Dropdown for choose start avg day
		days[0] = "Choose Starting Day for Range";
		final JComboBox dayAvgStart = new JComboBox(days);
		dayAvgStart.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				avgDayStart = algoType;
			}
		});	
		days[0] = "Choose Ending Day for Range";

		//Dropdown for choose month
		months[0] = "Choose Starting Month for Range";
		final JComboBox monthAvgStart = new JComboBox(months);
		monthAvgStart.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				avgMonthStart = algoType;
			}
		});	
		months[0] = "Choose Ending Month for Range";

		//Dropdown for choose year
		years[0] = "Choose Starting Year for Range";
		final JComboBox yearAvgStart = new JComboBox(years);
		yearAvgStart.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				avgYearStart = algoType;
			}
		});	
		years[0] = "Choose Ending Month for Range";
		
		//Dropdown for choose day
		final JComboBox dayAvgEnd = new JComboBox(days);
		dayAvgEnd.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				avgDayEnd = algoType;
			}
		});	

		//Dropdown for choose month
		final JComboBox monthAvgEnd = new JComboBox(months);
		monthAvgEnd.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				avgMonthEnd = algoType;
			}
		});	

		//Dropdown for choose year
		final JComboBox yearAvgEnd = new JComboBox(years);
		yearAvgEnd.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				avgYearEnd = algoType;
			}
		});	
		
		//Submit button

		final JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (queryType ==1){
					dateChosen = econMain.yearChosen + "-" + monthChosen + "-" + econMain.dayChosen;
					//System.out.println(dateChosen);
					int queryCount = 0;
					if (queryCount >= 3)
						finalResult += "You entered too many selections. Please Choose 3 or fewer.";
					else{
						if (nasdaqBool && queryCount <= 3){
							try {
								finalResult += "The Closing Price for Nasdaq on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("close","nasdaq",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (treasBool && queryCount <= 3){
							try {
								finalResult += "The Treasury Rate on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("rate","bonds",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (dowJonesBool && queryCount <= 3){
							try {
								finalResult += "The Closing Price for Dow Jones on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("price","dow",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (gasBool && queryCount <= 3){
							try {
								finalResult += "The Average National Gas Price on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("price","gas",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (intRateBool && queryCount <= 3){
							try {
								finalResult += "The Interest Rate on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("rate","interest",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (goldBool && queryCount <= 3){
							try {
								finalResult += "The Gold price on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("price","gold",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (oilBool && queryCount <= 3){
							try {
								finalResult += "The Oil Price on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("price","oil",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (snpBool && queryCount <= 3){
							try {
								finalResult += "The Closing Price for S&P on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery("close","sp",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (xChangeBool && queryCount <= 3){
							try {
								finalResult += "The Exchange Rate on " + dateChosen + " was: " + printResults(JDBCexample.findDateQuery(currencyChosen,"exchange",dateChosen)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
					}

					System.out.println(finalResult);
					EconDataComp.resultPanelCreator(dateChosen, finalResult);
					EconDataComp.panel_1.setVisible(false);
					//EconDataComp.frame.add(EconDataComp.q1q3ResultPanel);
					//EconDataComp.q1q3ResultPanel.revalidate();
					//EconDataComp.q1q3ResultPanel.repaint();
					//EconDataComp.frame.repaint();
					EconDataComp.q1q3ResultPanel.setVisible(true);
					
					

				}
				else if (queryType ==3){
					avgStartDate = avgYearStart + "-" + avgMonthStart + "-" + avgDayStart;
					avgEndDate = avgYearEnd + "-" + avgMonthEnd + "-" + avgDayEnd;
					System.out.println("start: " + avgStartDate);
					System.out.println("end: " + avgEndDate);
					EconDataComp.panel_1.setVisible(false);
					EconDataComp.q1q3ResultPanel.setVisible(true);
					
					/**
					 * Let's try this:
					 */
					
					int queryCount = 0;
					if (queryCount >= 3)
						finalResult += "You entered too many selections. Please Choose 3 or fewer.";
					else{
						if (nasdaqBool && queryCount <= 3){
							try {
								finalResult += "The Average Closing Price for the NASDAQ between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("close","nasdaq",avgStartDate,avgEndDate)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
							
						}
						if (treasBool && queryCount <= 3){
							try {
								finalResult += "The Average Rate for 10 Year Treasury Bonds between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("rate","bonds",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (dowJonesBool && queryCount <= 3){
							try {
								finalResult += "The Average Closing Price for the Dow Jones between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("price","dow",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (gasBool && queryCount <= 3){
							try {
								finalResult += "The Average National Gasoline Price between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("price","gas",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (intRateBool && queryCount <= 3){
							try {
								finalResult += "The Average Federal Reserve Interest Rate between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("rate","interest",avgStartDate,avgEndDate)) + ".\n";
								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (goldBool && queryCount <= 3){
							try {
								finalResult += "The Average Gold Price between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("price","gold",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (oilBool && queryCount <= 3){
							try {
								finalResult += "The Average Oil price per barrel between " + "\n"+ avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("price","oil",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (snpBool && queryCount <= 3){
							try {
								finalResult += "The Average Closing Price for the S&P between "+ "\n"  + avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery("close","sp",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
						if (xChangeBool && queryCount <= 3){
							try {
								finalResult += "The Average Exchange Rate of "+ currencyChosen+"per US dollar "+ "\n" + "between " + avgStartDate + " and "+ avgEndDate + "was: " + printResults(JDBCexample.findAverageQuery(currencyChosen,"exchange",avgStartDate,avgEndDate)) + ".\n";								//System.out.println(finalResult);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							queryCount ++;
						}
					}
					
					System.out.println(finalResult);
					EconDataComp.resultPanelCreator(avgStartDate+" to "+avgEndDate, finalResult);
					EconDataComp.panel_1.setVisible(false);
					//EconDataComp.frame.add(EconDataComp.q1q3ResultPanel);
					//EconDataComp.q1q3ResultPanel.revalidate();
					//EconDataComp.q1q3ResultPanel.repaint();
					//EconDataComp.frame.repaint();
					EconDataComp.q1q3ResultPanel.setVisible(true);
					
				}
				else if(queryType ==2){
					
					if (gasRadioBool){
						try {
							dataDesired = data.getText();
							//System.out.println(dataDesired);
							finalResult += "The date(s) where Gas was equal to " + dataDesired + " was : " + printResults(JDBCexample.queryTwo("price","gas",dataDesired)) + ".\n";
							System.out.println(finalResult);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					EconDataComp.resultPanelCreator(finalResult, dataDesired);
					EconDataComp.panel_1.setVisible(false);
					EconDataComp.q2ResultPanel.setVisible(true);
				}
			}
		});



		//Dropdown to choose query
		JComboBox queryDropDown = new JComboBox(queries);
		queryDropDown.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				EconDataComp.frame.repaint();
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				//if first Query is chosen, show appropriate menus
				if (algoType ==  "Enter a date, Find data"){
					queryType = 1;
					months[0] = "Choose Month";
					days[0] = "Choose Day";
					years[0] = "Choose Year";

					queryOne.add(monthDropDown);
					monthDropDown.setMaximumSize(maximumSize);
					queryOne.add(dayDropDown);
					dayDropDown.setMaximumSize(maximumSize);
					queryOne.add(yearDropDown);
					yearDropDown.setMaximumSize(maximumSize);
					queryOne.add(nasdaq);
					queryOne.add(dowJones);
					queryOne.add(snp);
					queryOne.add(xChange);
					queryOne.add(gold);
					queryOne.add(treasury);
					queryOne.add(intRate);
					queryOne.add(oil);
					queryOne.add(events);
					queryOne.add(gas);
					queryOne.add(submit);
					queryAddRemove();
				}
				if (algoType == "Enter data, find a date"){
					queryType = 2;
					queryTwo.add(data);
					queryTwo.add(nasdaqRadio);
					queryTwo.add(dowRadio);
					queryTwo.add(snpRadio);
					queryTwo.add(xChangeRadio);
					queryTwo.add(goldRadio);
					queryTwo.add(intRateRadio);
					queryTwo.add(treasRadio);
					queryTwo.add(oilRadio);
					queryTwo.add(gasRadio);
					queryTwo.add(submit);
					queryAddRemove();
				}
				if ( algoType == "Enter Date Range, find averages"){
					queryType = 3;
					//System.out.println("here");

					queryThree.add(monthAvgStart);
					monthAvgStart.setMaximumSize(maximumSize);
					queryThree.add(dayAvgStart);
					dayAvgStart.setMaximumSize(maximumSize);
					queryThree.add(yearAvgStart);
					yearAvgStart.setMaximumSize(maximumSize);
					queryThree.add(monthAvgEnd);
					monthAvgEnd.setMaximumSize(maximumSize);
					queryThree.add(dayAvgEnd);
					dayAvgEnd.setMaximumSize(maximumSize);
					queryThree.add(yearAvgEnd);
					yearAvgEnd.setMaximumSize(maximumSize);
					queryThree.add(nasdaq);
					queryThree.add(dowJones);
					queryThree.add(snp);
					queryThree.add(xChange);
					queryThree.add(gold);
					queryThree.add(treasury);
					queryThree.add(intRate);
					queryThree.add(oil);
					queryThree.add(events);
					queryThree.add(gas);
					queryThree.add(submit);
					queryAddRemove();
				}

			}
		});	

		startScreen.add(queryDropDown);
		queryDropDown.setMaximumSize(maximumSize);


		return startScreen;
	}

	public static void queryAddRemove(){
		if (queryType == 1){
			startScreen.remove(queryTwo);
			startScreen.remove(queryThree);
			startScreen.add(queryOne);
			startScreen.revalidate();
			EconDataComp.frame.repaint();
			//System.out.println("nas Radio bool " + nasdaqRadioBool);
		}
		if (queryType ==2){
			startScreen.remove(queryOne);
			startScreen.remove(queryThree);
			startScreen.add(queryTwo);
			startScreen.revalidate();
			EconDataComp.frame.repaint();
			//System.out.println("NasBool" + nasdaqBool + "dowbool" + dowJonesBool + "snpbool" +snpBool );
		}
		if (queryType == 3){
			startScreen.remove(queryOne);
			startScreen.remove(queryTwo);
			startScreen.add(queryThree);
			startScreen.revalidate();
			EconDataComp.frame.repaint();
		}
	}

	static String printResults( ResultSet res )
	{
		String results = "";
		try {
		      while( res.next()) {
		         results += res.getString( 1 ) + " ";
		        //System.out.print( "|");
		        //System.out.printf("%20s", res.getString( 2 ));
		        //System.out.println( "" );
		      }
		      return results;
		    }
		    catch( SQLException e ) {
		      System.err.println( "Fetch failed: " + e.getMessage());
		    }
		return null;
	}
}


