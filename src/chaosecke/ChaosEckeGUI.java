package chaosecke;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

/**
 * Diese Klasse generiert die graphische Oberflaeche des ChaosEckeProgramms, liest die Werte ein und gibt die Loesungen aus.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class ChaosEckeGUI {
	
	private JFrame frame1 = new JFrame("ChaosEcke");
	private NumberFormat format1 = NumberFormat.getInstance(); 
	private NumberFormat format2 = NumberFormat.getInstance();
	private NumberFormatter formatter1 = new NumberFormatter(format1);
    private NumberFormatter formatter2 = new NumberFormatter(format2);
    private JLabel labelBreite = new JLabel("Breite");
	private JLabel labelHoehe = new JLabel("Höhe");
	private JFormattedTextField feldBreite = new JFormattedTextField(formatter1);
	private JFormattedTextField feldHoehe = new JFormattedTextField(formatter2);
	private JButton buttonRechnen = new JButton("Berechne");
	private int loesung;
	
	private ChaosEckeGUI() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(200,120));
		frame1.setMinimumSize(new Dimension(200,120));
		frame1.setMaximumSize(new Dimension(400,240));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridLayout(3,1));
		
		JPanel paneBreite = new JPanel();
		paneBreite.setLayout(new BorderLayout());
		paneBreite.add(labelBreite,BorderLayout.WEST);
		paneBreite.add(feldBreite,BorderLayout.CENTER);
		
		JPanel paneHoehe = new JPanel();
		paneHoehe.setLayout(new BorderLayout());
		paneHoehe.add(labelHoehe,BorderLayout.WEST);
		paneHoehe.add(feldHoehe,BorderLayout.CENTER);
		
		frame1.add(paneBreite);
		frame1.add(paneHoehe);
		frame1.add(buttonRechnen);
		
		buttonRechnen.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent evt) {
	    		try {
	    			loesung = new ChaosEcke().berechne(Integer.valueOf(feldBreite.getText()),Integer.valueOf(feldHoehe.getText()));
		    		if(loesung==-1) {
		    			throw new NumberFormatException();
		    		} else if(loesung==-2) {
		    			JOptionPane.showMessageDialog(null, "Mit der Feldgröße "+Integer.valueOf(feldBreite.getText())+"x"+Integer.valueOf(feldHoehe.getText())+" ist dieses Rätsel nicht lösbar.", "Nicht lösbar", JOptionPane.INFORMATION_MESSAGE);
		    		} else {
		    			JOptionPane.showMessageDialog(null, "Bei der Feldgröße "+Integer.valueOf(feldBreite.getText())+"x"+Integer.valueOf(feldHoehe.getText())+" gibt es insgesamt "+loesung+" Berührungen.", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
		    		}
	    		} catch(Exception nfe) {
	    			JOptionPane.showMessageDialog(null, "Deine Eingabe ist ungültig."+System.getProperty("line.separator")+"Bitte benutze nur ganzzahlige positive Zahlen.", "Eingabe ungültig", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}	
	    });
		feldBreite.setHorizontalAlignment(SwingConstants.RIGHT);
		feldHoehe.setHorizontalAlignment(SwingConstants.RIGHT);
		format1.setGroupingUsed(false);
	    format2.setGroupingUsed(false);
	    formatter1.setAllowsInvalid(false);
	    formatter2.setAllowsInvalid(false);
	    
	    frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}

	public static void main(String[] args) {
		new ChaosEckeGUI();
	}
}