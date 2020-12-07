import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class Admin {
	
	public void adminMenu(ArrayList<UserAccount> userList)
	 {
		JFrame adminWindow = new JFrame();
		Container adminContent;
		adminWindow.setLayout(new FlowLayout());
		adminWindow.setSize(450,500);
		adminWindow.setResizable(false);
		adminWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		adminContent = adminWindow.getContentPane();
		adminContent.setLayout(null);
		adminWindow.setVisible(true);
       	accountlistView(userList, adminContent, adminWindow);
	 }
	
	public void accountlistView(ArrayList<UserAccount> userList, Container content, JFrame adminWindow)
	 {
		JLabel title = new JLabel();
		title.setSize(200,30);
        title.setFont(new Font("Calibri Light", Font.PLAIN, 19)); 
		title.setLocation(130,25);
		title.setText("All User Accounts");
		content.add(title);
		
		
		for ( int i = 0; i < userList.size(); i++)
		 {
			JButton userAccount = new JButton(userList.get(i).getName());
			userAccount.setFont(new Font("Arial", Font.BOLD, 14)); 
			userAccount.setSize(350, 30); 
			userAccount.setLocation(20, 70 + (i * 50));
 			content.add(userAccount);
			int num = i;
			
			JButton backButton = new JButton(new ImageIcon(getClass().getResource("/logout.PNG")));
			backButton.setSize(28,28);
			backButton.setLocation(10, 420);
			content.add(backButton);
			backButton.addActionListener(new ActionListener() 
			 {
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				 {
					try 
					 {
						adminWindow.dispose();
						new Main();
					 }
					catch (ClassNotFoundException e) 
					 {
						e.printStackTrace();
					 }
				 }
			 });
			
			JButton delButton = new JButton(new ImageIcon(getClass().getResource("/delete.PNG")));
 			delButton.setSize(30,30);
 			delButton.setLocation(385, 70 + (i*50));
 			content.add(delButton);
 			delButton.addActionListener(new ActionListener() 
 			 {
				
				@Override
				public void actionPerformed(ActionEvent onDel) 
				 {
					
					int userChoice = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want \nto delete this account.","Delete?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
					
					if (userChoice == JOptionPane.YES_OPTION)			
					 {
						
						try 
						 {
							adminWindow.dispose();
							Main.delete(userList.get(num).name);
						 }
						catch (ClassNotFoundException e) 
						 {
							e.printStackTrace();
						 }
						adminMenu(userList);
					 }
				
					
				 }
 			 });
 			userAccount.addActionListener(new ActionListener() 
 			 {
 				
 				@Override
				public void actionPerformed(ActionEvent eachButton)
 				 {
 					adminWindow.dispose();
 					woListView(userList,userList.get(num));
 				 }
 			 });
		 }
	 }
	
	public void woListView(ArrayList<UserAccount> userList,UserAccount account)
	 {
		JFrame workOrders = new JFrame();
		Container content;
		workOrders.setTitle("Work Order List");
		workOrders.setSize(500,600);
		workOrders.setLayout(new FlowLayout());
		workOrders.setResizable(false);
		workOrders.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		content = workOrders.getContentPane();
		content.setLayout(null);
		
		JButton messages = new JButton();
		messages.setText("Messages");
		messages.setSize(120,30);
		messages.setLocation(30,20);
		messages.addActionListener(new ActionListener() 
		 {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
			    JOptionPane.showMessageDialog(workOrders,"Message from " + account.name+":\n"+ account.message);  
			 }
		 });
		
		JLabel msg = new JLabel(new ImageIcon(getClass().getResource("/mail.PNG")));
		msg.setLocation(150, 20);
		msg.setSize(40,30);
		
		JLabel accname = new JLabel();
		accname.setLocation(210, 20);
		accname.setFont(new Font("Calibri Light", Font.PLAIN, 17)); 
		accname.setSize(250,30);
		accname.setText("Account Name: " + account.name );
		
		
		content.add(accname);
		content.add(messages);
		content.add(msg);
		
	    
		
		JButton backButton = new JButton();
	    backButton.setText("Back");
	    backButton.setSize(90,20);
	    backButton.setLocation(20,520);
	    backButton.addActionListener(new ActionListener() 
	     {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
				workOrders.dispose();
				adminMenu(userList);
								
			 }
			
	 
	     });
	    content.add(backButton);
	    
	  
		
		for ( int j = 0; j < account.wolist.size(); j++)
		 { 
			
			JButton userAccount = new JButton(account.wolist.get(j).header);
			userAccount.setFont(new Font("Calibri Light", Font.BOLD, 14)); 
			userAccount.setSize(190, 30); 
			userAccount.setLocation(140, 70 + (j * 50));
			int num = j;
			

 			JLabel date = new JLabel();
 			date.setText("WO ID: " +  account.wolist.get(j).woId);
 			date.setFont(new Font("Calibri Light", Font.BOLD, 13)); 
 			date.setSize(90, 30); 
 			date.setLocation(30, 70 + (j * 50));
 			content.add(date);
			
			if(account.wolist.get(num).done)
			 {
				userAccount.setBackground(Color.green);
				JLabel tick = new JLabel("Complete");
				tick.setSize(200,30);
		        tick.setFont(new Font("Calibri Light", Font.BOLD, 13)); 
		        tick.setForeground(Color.green);
				tick.setLocation(360, 70 + (num * 50));
				content.add(tick);

			 }
			else
			 {
				userAccount.setBackground(Color.red);
				JLabel cross = new JLabel("Incomplete");
				cross.setSize(200,30);
		        cross.setFont(new Font("Calibri Light", Font.BOLD, 13)); 
		        cross.setForeground(Color.red);
				cross.setLocation(360, 70 + (num * 50));
				content.add(cross);
			 }
			
 			userAccount.addActionListener(new ActionListener() 
 			 {
 				
 				@Override
				public void actionPerformed(ActionEvent eachButton)
 			     {
 					String display = "Work Order: " + account.wolist.get(num).header+"\nDate of entry: "+account.wolist.get(num).date  ;
 					int userChoice = JOptionPane.showOptionDialog(new JFrame(), display ,"Complete?",
				    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				    new Object[] { "Complete", "Incomplete" }, JOptionPane.YES_OPTION);
					
					if (userChoice == JOptionPane.YES_OPTION)	
				     {
						account.wolist.get(num).done = true;
						System.out.println("Marked as complete");
						
				     }
					
					else if (userChoice == JOptionPane.NO_OPTION)
					 {
						account.wolist.get(num).done = false;
						System.out.println("Marked as incomplete");

					 }
					
					try
					 {
						Main.save();
					 } 
					catch (ClassNotFoundException e) 
					 {
						e.printStackTrace();
					 }

					workOrders.dispose();
					woListView(userList,account);

 			     }
 			 });
 			content.add(userAccount);
		
			
		 }
		workOrders.setVisible(true);
		
		
	 }
}
