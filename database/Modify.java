package database;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Modify {

	static int modType;
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
	static JPanel insert = new JPanel();
	static JPanel delete = new JPanel();
	static JPanel queryThree = new JPanel();
	static String table;
	static String attribType;

	static Dimension maximumSize = new Dimension(400,85);

	public static JPanel panelCreator(){

		// Layout of the control panel
		startScreen.setLayout(new BoxLayout(startScreen, BoxLayout.Y_AXIS));


		JButton home = new JButton("Back to Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//EconDataComp.q1q3ResultPanel.removeAll();
				//EconDataComp.q2ResultPanel.removeAll();
				EconDataComp.panel_2.setVisible(false);
				EconDataComp.panel.setVisible(true);
				EconDataComp.projectTitle.setVisible(true);
				EconDataComp.projectAuthors.setVisible(true);
			}
		});
		startScreen.add(home);
		insert.setLayout(new BoxLayout(insert, BoxLayout.Y_AXIS));
		delete.setLayout(new BoxLayout(delete, BoxLayout.Y_AXIS));
		queryThree.setLayout(new BoxLayout(queryThree, BoxLayout.Y_AXIS));



		// Strings-------------------------
		String[] modifications = {"Choose Query", "Insert", "Delete"};
		final String[] months = {"Choose Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] currency = {"Choose Currency", "JapaneseYen", "BritishPound", "SwissFranc", "CanadianDollar", "Euro"};
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
				table = "exchange";
				attribType = algoType;
			}
		});	


		//RadioButtons-----------------

		ButtonGroup radioGroup = new ButtonGroup();
		final JRadioButton nasdaqRadio = new JRadioButton("NASDAQ", false);
		nasdaqRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				nasdaqRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "nasdaq";
				attribType = "close";
			}
		});	

		final JRadioButton dowRadio = new JRadioButton("Dow Jones", false);
		dowRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				dowJonesRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "dow";
				attribType = "price";
			}
		});	

		final JRadioButton snpRadio = new JRadioButton("S&P", false);
		snpRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				snpRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "sp";
				attribType = "close";
			}
		});	

		final JRadioButton xChangeRadio = new JRadioButton("Exchange Rates", false);
		xChangeRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				xChangeRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				if (modType ==1)
					insert.add(xChangeDropDown);
				else
					delete.add(xChangeDropDown);
				//System.out.println("in qwe");
				xChangeDropDown.setMaximumSize(maximumSize);
				startScreen.revalidate();
				EconDataComp.frame.repaint();
				//remove currency comboBox if unchecked
				if (!xChangeRadioBool){
					if (modType==1)
						insert.remove(xChangeDropDown);
					else
						delete.remove(xChangeDropDown);
					//delete.remove(xChangeDropDown);
					startScreen.revalidate();
					EconDataComp.frame.repaint();
				}
			}
		});	

		final JRadioButton goldRadio = new JRadioButton("Gold", false);
		goldRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				goldRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "gold";
				attribType = "price";
			}
		});	

		final JRadioButton intRateRadio = new JRadioButton("Interest Rates", false);
		intRateRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				intRateRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "interest";
				attribType = "rate";
			}
		});	

		final JRadioButton treasRadio = new JRadioButton("10 Year Treasuries", false);
		treasRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				treasRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "bonds";
				attribType = "rate";
			}
		});	

		final JRadioButton oilRadio = new JRadioButton("Oil Prices", false);
		oilRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				oilRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "oil";
				attribType = "price";
			}
		});	

		final JRadioButton gasRadio = new JRadioButton("Gas Prices", false);
		gasRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e ) {
				gasRadioBool = (e.getStateChange() == ItemEvent.SELECTED);
				table = "gas";
				attribType = "price";
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
				avgDayEnd = algoType;
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

		final JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dateChosen = yearChosen + "-" + monthChosen + "-" + dayChosen;
				//Do things here!
				boolean errorCheck = false;
				boolean dontGo = false;

				dataDesired = data.getText();

				if (modType == 1){//Insert
					
					Float dataInt = null;
					try{
						dataInt = Float.parseFloat(dataDesired);
					}
					catch (Exception ee){
						System.out.println("An error has occurred");
						errorCheck = true;//avoid inserting
					}

					if (errorCheck){
						JOptionPane.showMessageDialog(startScreen, "Please Enter Valid Data.");
						errorCheck = false;
						dontGo = true;
					}
					else{
						dontGo = false;

						try {
							String throwawayVariable = JDBCexample.insertQuery(attribType,table,dateChosen,dataDesired) + ".\n";

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				if (modType == 2){//Delete
					try {
						String throwawayVariable = JDBCexample.deleteQuery(dateChosen,table) + ".\n";
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				//End with this!!

				if (!dontGo){

					EconDataComp.resultPanelCreator(null,null);

					EconDataComp.panel_2.setVisible(false);
					EconDataComp.modPanel.setVisible(true);
				}
			}
		});


		//Dropdown to choose query
		JComboBox queryDropDown = new JComboBox(modifications);
		queryDropDown.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent queryDropDown ) {
				EconDataComp.frame.repaint();
				JComboBox aType = (JComboBox)queryDropDown.getSource();
				String algoType = (String)aType.getSelectedItem();
				//if first Query is chosen, show appropriate menus
				if (algoType ==  "Insert"){
					//System.out.println("here");
					modType = 1;
					months[0] = "Choose Month";
					days[0] = "Choose Day";
					years[0] = "Choose Year";

					insert.add(monthDropDown);
					monthDropDown.setMaximumSize(maximumSize);
					insert.add(dayDropDown);
					dayDropDown.setMaximumSize(maximumSize);
					insert.add(yearDropDown);
					yearDropDown.setMaximumSize(maximumSize);
					insert.add(data);
					insert.add(nasdaqRadio);
					insert.add(dowRadio);
					insert.add(snpRadio);
					insert.add(xChangeRadio);
					insert.add(goldRadio);
					insert.add(intRateRadio);
					insert.add(treasRadio);
					insert.add(oilRadio);
					insert.add(gasRadio);
					insert.add(submit);
					modAddRemove();
				}
				if (algoType == "Delete"){
					modType = 2;

					delete.add(monthDropDown);
					monthDropDown.setMaximumSize(maximumSize);
					delete.add(dayDropDown);
					dayDropDown.setMaximumSize(maximumSize);
					delete.add(yearDropDown);
					yearDropDown.setMaximumSize(maximumSize);

					delete.add(nasdaqRadio);
					delete.add(dowRadio);
					delete.add(snpRadio);
					delete.add(xChangeRadio);
					delete.add(goldRadio);
					delete.add(intRateRadio);
					delete.add(treasRadio);
					delete.add(oilRadio);
					delete.add(gasRadio);
					delete.add(submit);
					modAddRemove();
				}

			}
		});	

		startScreen.add(queryDropDown);
		queryDropDown.setMaximumSize(maximumSize);


		return startScreen;
	}

	public static void modAddRemove(){
		if (modType == 1){
			startScreen.remove(delete);
			startScreen.remove(queryThree);
			startScreen.add(insert);
			startScreen.revalidate();
			EconDataComp.frame.repaint();
			//System.out.println("nas Radio bool " + nasdaqRadioBool);
		}
		if (modType ==2){
			startScreen.remove(insert);
			startScreen.remove(queryThree);
			startScreen.add(delete);
			startScreen.revalidate();
			EconDataComp.frame.repaint();
			//System.out.println("NasBool" + nasdaqBool + "dowbool" + dowJonesBool + "snpbool" +snpBool );
		}
	}

}
