import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class UserAccount implements Serializable, ItemListener
 {
	
	String name;
	String password;
	String email;
	String username;
	String message;

	ArrayList <WorkOrder> wolist = new ArrayList<WorkOrder>();
	


	
	public void setName(String name)
	 {
		this.name = name;
	 }
	public String getName()
	 {
		return this.name;
	 }
	public void setPassword(String password)
	 {
		this.password = password;
	 }
	public String getPassword()
	 {
		return this.password;
	 }
	public void setEmail(String email)
	 {
		this.email = email;
	 }
	public void setUserName(String username)
	 {
		this.username = username;
	 }
	public String getUserName()
	 {
		return this.username;
	 }
	public void setMessage(String inputMessage)
	 {
		this.message = inputMessage;
	 }
	
	
	
	public void menu() throws ClassNotFoundException
	 {
		
		JFrame accountUI = new JFrame();
		Container content2;
		JButton addLogo;
		JButton messageButton;
		JButton delAcc;

		
		accountUI.setLayout(new FlowLayout());
		accountUI.setSize(800,500);
		accountUI.setResizable(false);
		accountUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content2 = accountUI.getContentPane();
        content2.setLayout(null);
        accountUI.setVisible(true);

        
        JPanel a = new JPanel();
		a.setBounds(180,80, 500, 380);
		a.setBackground(Color.white);
		a.setLayout(new BoxLayout(a,BoxLayout.Y_AXIS));
	    a.setAlignmentX(Component.CENTER_ALIGNMENT);

		        
        JRadioButton all = new JRadioButton("All");
        JRadioButton pending = new JRadioButton("Pending");
        JRadioButton completed = new JRadioButton("Completed");
        all.setBounds(290, 30, 100,30);
        pending.setBounds(390, 30, 100,30);
        completed.setBounds(490, 30, 100,30);
        ButtonGroup displayList=new ButtonGroup();
        

        
        displayList.add(all);
        displayList.add(pending);
        displayList.add(completed);
				


        all.addActionListener(new ActionListener() 
         {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
					
					System.out.println("Radio Button All");		
					reloadList(accountUI, a, 'a');
	
			}
		 });
        
        pending.addActionListener(new ActionListener() 
         {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
					System.out.println("Radio Button Pending");		
					reloadList(accountUI, a, 'b');
			 }
         });

        completed.addActionListener(new ActionListener() 
         {
	
        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	 {
        		System.out.println("Radio Button Completed");
        		reloadList(accountUI, a, 'c');
        	 }
         });
        
        content2.add(all);
        content2.add(pending);
        content2.add(completed);
        accountUI.add(a);


        
        JButton backButton = new JButton(new ImageIcon(getClass().getResource("/logout.PNG")));
		backButton.setFont(new Font("Arial", Font.BOLD, 14)); 
		backButton.setSize(32,28);
		backButton.setLocation(50, 360);
		backButton.addActionListener(new ActionListener() 
		 {
			@Override
			public void actionPerformed(ActionEvent backoutput) 
			 {
				try 
				 {
					Main.save();
					accountUI.dispose();
					new Main();
					
				 } 
				catch (ClassNotFoundException e) 
				 {
					e.printStackTrace();
				 }
				
			 }
		 });
			
		accountUI.add(backButton);
		
		JLabel account = new JLabel(new ImageIcon(getClass().getResource("/account.PNG")));
		account.setSize(100, 70);
		account.setLocation(15, 12);
		accountUI.add(account);

		JLabel loggedinas = new JLabel();
		loggedinas.setText(name);
		loggedinas.setFont(new Font("Calibri Light", Font.BOLD, 14)); 
		loggedinas.setLocation(100, 13);
		loggedinas.setSize(140, 70);
		accountUI.add(loggedinas);
		
		JButton i = new JButton(new ImageIcon(getClass().getResource("/info.PNG")));
		i.setLocation(750, 10);
		i.setSize(20,20);
		accountUI.add(i);
		i.addActionListener(new ActionListener() 
		 {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
				JOptionPane.showMessageDialog(accountUI,"On this page, you are able to \nAdd and Delete Work Orders\nSee the status of them\nSend a message to Admin\n and etc.");  
			 }
		 });
		
        addLogo = new JButton(new ImageIcon(getClass().getResource("/add.PNG")));
        addLogo.setFont(new Font("Arial", Font.BOLD, 14)); 
        addLogo.setSize(30,30); 
        addLogo.setLocation(50, 120); 
        addLogo.addActionListener(new ActionListener() 
         {
        	
        	@Override
			public void actionPerformed(ActionEvent ae)
        	 {
        		System.out.println("Add workorder");
        		accountUI.dispose();
        		addView();
        	 }
         }); 
        
        content2.add(addLogo);
        
        messageButton = new JButton(new ImageIcon(getClass().getResource("/message.PNG"))); 
        messageButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        messageButton.setSize(30, 30); 
        messageButton.setLocation(50, 180); 
        messageButton.addActionListener(new ActionListener() 
         {
        	
        	@Override
			public void actionPerformed(ActionEvent ae)
        	 {
        		System.out.println("Message Buttonn pressed");
        		accountUI.dispose();
        		messageWindow();
        		
        	 }
         }); 
        
        content2.add(messageButton);

       
        delAcc = new JButton(new ImageIcon(getClass().getResource("/accountremove.PNG"))); 
        delAcc.setFont(new Font("Arial", Font.BOLD, 14)); 
        delAcc.setSize(30, 30); 
        delAcc.setLocation(50, 240); 
        delAcc.addActionListener(new ActionListener() 
         {
        
        	@Override
			public void actionPerformed(ActionEvent ae)
        	 {
        		System.out.println("Delete Account");
        		int userChoice = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want\n to delete your account?" ,"Delete Account?",
        				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				        new Object[] { "Yes, Delete", "No, I want to keep it." }, JOptionPane.YES_OPTION);
        		
        		if(userChoice == JOptionPane.YES_OPTION)
        		 {
        			try  
        			 {
        				accountUI.dispose();
						Main.delete(name);
						new Main();
					 }
        			catch (ClassNotFoundException e) 
        			 {
						e.printStackTrace();
        			 }
        		 }
        		
        		
        	 }
         }); 
        
        content2.add(delAcc);

       
	 }
	
	
	public void messageWindow()
	 {
		JFrame window = new JFrame();
		Container content;
		window.setSize(400,300 );
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content = window.getContentPane();
        content.setLayout(null);
        window.setVisible(true);
        
        JLabel text = new JLabel();
        text.setSize(200,20);;
        text.setLocation(140, 10);
        text.setText("Message to admin");
	    text.setFont(new Font("Calibri Light", Font.PLAIN, 15)); 

        
        
        content.add(text);
        
        JTextArea Imessage = new JTextArea();
        Imessage.setBounds(40, 40, 300, 160);
        Imessage.setLineWrap(true);
        content.add(Imessage);
        
        JButton send = new JButton();
        send.setText("Send");
        send.setLocation(270, 210);
        send.setSize(70,30);
        content.add(send);
        send.addActionListener(new ActionListener() 
         {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
				setMessage(Imessage.getText());
				window.dispose();

				System.out.println("Send pressed");
				try 
				 {
					
					Main.save();
					menu();
				 }
				catch (ClassNotFoundException e) 
				 {
					e.printStackTrace();
				 }
				
			 }
         });
	      

        JButton back = new JButton();
        back.setText("Back");
        back.setLocation(40, 210);
        back.setSize(70,30);
        content.add(back);
        back.addActionListener(new ActionListener() 
         {
        		
			@Override
			public void actionPerformed(ActionEvent arg0) 
			 {
				window.dispose();
				try 
				 {
					System.out.println("Back Pressed");
					menu();
					
				 }
				catch (ClassNotFoundException e) 
				 {
					e.printStackTrace();
				 }
			 }
         });

	}
	
	public void reloadList(JFrame accountUI, JPanel a, char option)
	 {
		
		 Component[] removeComponents = a.getComponents();

	        for (Component component : removeComponents) 
	         {
	            a.remove(component);  
	         }

	        a.repaint();

	        listView(a,accountUI, option);

	 }
	
	public void addView()
	 {
		
		 JFrame addWindow = new JFrame();
		 Container content3;
		 JLabel headerL, dateL,descriptionL;
		 JTextField headerT;
		 JTextArea descriptionT;
		 JButton addButton;
		 JComboBox dropDown, day, month, year;
		 String option[] = {"Repair", "House Keeping", "IT Support"};
		 String dayOption[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		 String monthOption[] = {"Jan", "Feb", "Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		 String yearOption[] = {"2020","2021","2022","2023","2024"};
		
				
		 addWindow.setTitle("Add a new work order");
		 addWindow.setLayout(new FlowLayout());
		 addWindow.setSize(500,400);
		 addWindow.setResizable(false);
		 addWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 content3 = addWindow.getContentPane();
		 content3.setLayout(null);
	        
	       
	     	      
		 headerL = new JLabel("Work Order "); 
		 headerL.setFont(new Font("Calibri Light", Font.PLAIN, 17)); 
		 headerL.setSize(100, 18); 
		 headerL.setLocation(15, 40); 
		 content3.add(headerL); 
		 
		 headerT = new JTextField();
		 headerT.setFont(new Font("Calibri", Font.PLAIN, 15)); 
		 headerT.setSize(270, 25); 
		 headerT.setLocation(140, 37); 
		 content3.add(headerT);
		 
		 
		 headerL = new JLabel("WO Type:  "); 
		 headerL.setFont(new Font("Calibri Light", Font.PLAIN, 17)); 
		 headerL.setSize(100, 18); 
		 headerL.setLocation(15, 90); 
		 content3.add(headerL);
	       	
		 dateL = new JLabel("Date:  "); 
		 dateL.setFont(new Font("Calibri Light", Font.PLAIN, 17)); 
		 dateL.setSize(100, 18); 
		 dateL.setLocation(15, 140); 
		 content3.add(dateL); 
	       
		 dropDown = new JComboBox(option);
		 dropDown.setBounds(140,85,100,20);
	     content3.add(dropDown);
	       
	       
	       
	     day = new JComboBox(dayOption);
	     day.setBounds(140,135,60,20);
	     content3.add(day);
	     
	     month = new JComboBox(monthOption);
	     month.setBounds(230,135,100,20);
	     content3.add(month);
	       
	     year = new JComboBox(yearOption);
	     year.setBounds(360,135,100,20);
	     content3.add(year);
	       
	       
	     descriptionL = new JLabel("Description: "); 
	     descriptionL.setFont(new Font("Calibri Light", Font.PLAIN, 19)); 
	     descriptionL.setSize(100, 20); 
	     descriptionL.setLocation(15, 190); 
	     content3.add(descriptionL); 
	     
	     
	     descriptionT = new JTextArea();
	     descriptionT.setBounds(140, 190, 250, 100);
	     descriptionT.setLineWrap(true);
	     descriptionT.setFont(new Font("Calibri Light", Font.PLAIN, 14)); 
	     content3.add(descriptionT);
	     
	     JButton backButton = new JButton(new ImageIcon(getClass().getResource("/logout.PNG")));
	     backButton.setSize(28,28);
	     backButton.setLocation(50, 310);
	     backButton.addActionListener(new ActionListener() 
	      {
				
	    	 @Override
	    	 public void actionPerformed(ActionEvent arg0) 
	    	  {
					try 
					 {
						System.out.println("Back Button");
						addWindow.dispose();
						menu();
						
					 }
					catch (ClassNotFoundException e) 
					 {
						e.printStackTrace();
					 }
				}
	      });
			content3.add(backButton);
		

			addButton = new JButton("Add"); 
	        addButton.setFont(new Font("Arial", Font.BOLD, 14)); 
	        addButton.setSize(105, 20); 
	        addButton.setLocation(200, 315);
	        addButton.addActionListener(new ActionListener()
	         {

						@Override
						public void actionPerformed(ActionEvent pressed)
			 			 {
							String headertext = headerT.getText();
							String descriptionText = descriptionT.getText();
							

							try 
							 {
								
								if(headertext.isEmpty() || descriptionText.isEmpty())
							     {
									JOptionPane.showMessageDialog(content3, "Please fill all prompts.", "Alert" , JOptionPane.WARNING_MESSAGE);

							     }
								else 
								 {
									int dropDownChoice = dropDown.getSelectedIndex();
									String date = (String) day.getSelectedItem() +" "+month.getSelectedItem() +" "+ year.getSelectedItem();
									System.out.println("Date enetered is " + date);
								    System.out.println(dropDown.getSelectedIndex());

								add(headertext, descriptionText, dropDownChoice, date);
								Main.save();
								addWindow.dispose();
								 }
								
							 } catch (ClassNotFoundException e) 
							 	{
								 	e.printStackTrace();
							 	}
			 			 }
	        			
	        			});
	        content3.add(addButton);	        
	        addWindow.setVisible(true);
	        
	 }
	
	public void add(String head, String des, int type, String date) throws ClassNotFoundException
	 {
		
		if(type == 0)
		 {
			Repair wo = new Repair();
			System.out.println(head + des);
			wo.setType();
			wo.setDate(date);
			System.out.println("New " + wo.type + " work order made." );
			wo.generateWoId();
	 		wo.description = des;
	 		wo.setHeader(head);
	 		wo.done = false;
	 		wolist.add(wo);
	 		menu();
		 }
		
		else if (type == 1)
		 {
			HouseKeeping wo = new HouseKeeping();
			System.out.println(head + des);
			wo.setType();
			wo.setDate(date);
			System.out.println("New " + wo.type + " work order made." );
			wo.generateWoId();
	 		wo.description = des;
	 		wo.setHeader(head);
	 		wo.done = false;
	 		wolist.add(wo);
	 		menu();
		 }
		else if (type == 2)
	 	 {
			
			ITSupport wo = new ITSupport();
			System.out.println(head + des);
			wo.setType();
			wo.setDate(date);
			System.out.println("New " + wo.type + " work order made." );
			wo.generateWoId();
	 		wo.description = des;
	 		wo.setHeader(head);
	 		wo.done = false;
	 		wolist.add(wo);
	 		menu();
		 }
		
	 }
	
	public void listView(JPanel listPanel,JFrame accountUI, char option)
	 {   
		
		for ( int i = 0; i < wolist.size(); i++)
 		 {
			

 			System.out.println("\n"+ i +")"+ wolist.get(i).header);
 			JButton listButton = new JButton(wolist.get(i).woId+ ":	"+ "     "+wolist.get(i).header);
 			listButton.setFont(new Font("Arial", Font.BOLD, 14)); 
 			listButton.setSize(350, 30); 
 			listButton.setLocation(60, 30 + (i * 35));
 			int hello = i;
 			
 			JButton delButton = new JButton(new ImageIcon(getClass().getResource("/delete.PNG")));
 			delButton.setSize(30,30);
 			delButton.setLocation(420, 30 + (i*35));
 			delButton.addActionListener(new ActionListener() 
 			 {
				
				@Override
				public void actionPerformed(ActionEvent onDel) 
				 {
					
					int userChoice = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to delete:\n " + wolist.get(hello).header,"Delete?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					        new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
					
					if (userChoice == JOptionPane.YES_OPTION)			
					 {
						wolist.remove(hello);
						System.out.println("deleted");
						try 
						 {
							accountUI.dispose();
							Main.save();
							menu();
							
						 } 
						catch (ClassNotFoundException e) 
						 {
							e.printStackTrace();
						 }
					 }					
					
				 }
 			 });
 			
 			JButton editButton = new JButton(new ImageIcon(getClass().getResource("/edit.PNG")));
 			editButton.setSize(30,30);
 			editButton.setLocation(460, 30 + (i*35));
 			editButton.addActionListener(new ActionListener() 
 			 {
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				 {
					
					addView();
					wolist.remove(hello);
					accountUI.dispose();
				 }
 			 });
 			

 			if(option == 'b')
 			 {
 				
 				if(!wolist.get(i).done)
 			  	 {

 		 			listPanel.add(listButton);	
 		 			listPanel.add(delButton);
 		 			listPanel.add(editButton);
	
 			  	 }
 				
 			 }
 			
 			else if (option == 'c')
 			 {

 				if(wolist.get(i).done)
 				 {
 					listPanel.add(delButton);
 		 			listPanel.add(listButton);
 		 			listPanel.add(editButton);	
 				 }
 				
 			 }
 			else if (option == 'a') 
 			 {
 				
		 			listPanel.add(listButton);
		 			listPanel.add(delButton);
 		 			listPanel.add(editButton);


 			 }
 			
 			
 			if(wolist.get(i).done)
 			 {
 				listButton.setBackground(Color.green);
 			 }
 			
 			else
 			 {
 				listButton.setBackground(Color.red);

 			 }
 			
 			listButton.addActionListener(new ActionListener() 
 			 {
 				
 				@Override
				public void actionPerformed(ActionEvent eachButton)
 				 {
 					
 					JOptionPane.showMessageDialog( listPanel, "WO ID: " + wolist.get(hello).woId + "\nWO: " + wolist.get(hello).header + "\nType: " +wolist.get(hello).type+"\n"+ "Date: "+ wolist.get(hello).date+"\n"+"Description: "+wolist.get(hello).description);
 				 }	
 			 });
 		
 		}
	}


	@Override
	public void itemStateChanged(ItemEvent arg0) 
	 {
	 }
	
 }
