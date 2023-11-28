package projectComma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class MyFrame extends JFrame {
    public MyFrame() {
    	// title
    	setTitle("TextTransformer");
    	
        // set UIManager properties for customizing look and feel
        UIManager.put("Label.background", new Color(57, 137, 186));
        UIManager.put("Label.foreground", Color.black);
        UIManager.put("Label.font", new Font("Helvetica", Font.BOLD, 12));
        UIManager.put("Button.background", new Color(238, 78, 88));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Helvetica", Font.BOLD, 12));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("TextField.background", new Color(200, 200, 200));
        UIManager.put("TextField.foreground", new Color(57, 137, 186));
        UIManager.put("TextField.font", new Font("Helvetica", Font.PLAIN, 12));

        // global UI
        JCheckBox checkBox = new JCheckBox("AutoCopy");
        
        // create labels and fields for custom letter replacement
        JLabel fromLabel = new JLabel("from ");
        fromLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JTextField fromField = new JTextField(5);
        fromField.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        fromField.setPreferredSize(new Dimension(80, fromField.getPreferredSize().height)); 
        fromField.setMaximumSize(new Dimension(80, fromField.getPreferredSize().height));
        fromField.setBackground(Color.WHITE);
        fromField.setText(" ");
        
        JLabel toLabel = new JLabel(" to");
        toLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JTextField toField = new JTextField(5);
        toField.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        toField.setPreferredSize(new Dimension(80, toField.getPreferredSize().height)); 
        toField.setMaximumSize(new Dimension(80, toField.getPreferredSize().height));
        toField.setBackground(Color.WHITE);
        toField.setText(",");
        
        // create input and result fields
        JLabel inputLabel = new JLabel("Input    :");
        inputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JTextField inputField = new JTextField(20);
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputField.setPreferredSize(new Dimension(inputField.getPreferredSize().width, 20));
        JLabel resultLabel = new JLabel("Result :");
        resultLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JTextField resultField = new JTextField(20);
        resultField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        resultField.setPreferredSize(new Dimension(inputField.getPreferredSize().width, 20));

        // create buttons
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(80, submitButton.getPreferredSize().height));
        submitButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JButton copyButton = new JButton("Copy");
        copyButton.setPreferredSize(new Dimension(80, copyButton.getPreferredSize().height));
        copyButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(80, clearButton.getPreferredSize().height));
        clearButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // create check box
        checkBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        checkBox.setSelected(true);
        
        // add action listeners to buttons
        submitButton.addActionListener(e -> {
            String replaceFrom = fromField.getText();
            String replaceTo = toField.getText();        	
            String result = inputField.getText();
            String replacedResult = result.replace(replaceFrom, replaceTo);
            //if(replacedResult.charAt(replacedResult.length() - 1) == ',') replacedResult = replacedResult.substring(0, replacedResult.length() - 1);
            resultField.setText(replacedResult);
            if (checkBox.isSelected()) Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(replacedResult), null);                
        });

        copyButton.addActionListener(e -> {
        	String result = resultField.getText();
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result), null);
        });
        
        clearButton.addActionListener(e -> {
        	inputField.setText("");
        	resultField.setText("");
        	fromField.setText("");
        	toField.setText("");
        });        

        // create replace panel with label, field
        JPanel replacePanel = new JPanel();
        replacePanel.setLayout(new BoxLayout(replacePanel, BoxLayout.X_AXIS));
        replacePanel.add(fromLabel);
        replacePanel.add(fromField);
        replacePanel.add(toLabel);
        replacePanel.add(toField);
        replacePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        
        // create input panel with label, field, and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(inputLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(inputField);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(submitButton);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // create result panel with label and field
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
        resultPanel.add(resultLabel);
        resultPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        resultPanel.add(resultField);
        resultPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        resultPanel.add(copyButton);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // create extra panel with check box and button
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonBox.add(checkBox);
        buttonBox.add(clearButton);
        buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // create main panel with input, result, and button panels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(replacePanel);        
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // add some vertical spacing
        panel.add(inputPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // add some vertical spacing
        panel.add(resultPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 8))); // add some vertical spacing
        panel.add(buttonBox);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // add some vertical spacing        
        

        // add panel to frame
        add(panel);

        // set frame properties
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // center the frame on the screen
        setLocationRelativeTo(null);
    }
}
