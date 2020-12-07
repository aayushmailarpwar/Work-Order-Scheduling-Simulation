import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.ImageIcon;


public class Main
 {	

	//creating an arraylist of instances of the USerAccount class
	static ArrayList <UserAccount> list = new ArrayList<UserAccount>();
	//boolean for inputValidation on sign up page
	static boolean emailfield, passfield;
	//creating windows for UI
	JFrame login = new JFrame("Login Page");
	JFrame signUpPage = new JFrame("Sign Up Page");
	
	

	public static void main (String[] args) throws ClassNotFoundException
	 {
		//constructor
		new Main();
		
	 }
	//consturcutor
	public Main() throws ClassNotFoundException
	 {
		//calls start every time a new Main is made		
			start();
			
		
	 }
	public void start() throws ClassNotFoundException
	 {
		//calls laod mehtod that deserializes the newData.dat file
		load();
		LoginWindow();
	 }
	
	public void LoginWindow() throws ClassNotFoundException
	 {
	//UI setup	
	Container content;
	JLabel title, userNameL, passWordL, newguy;
	JTextField tuserName;
	JPasswordField tpassWord;
	JButton signIn, signUp;
		
	//window setup	
	login.setLayout(new FlowLayout());
	login.setSize(500,400);
	login.setResizable(false);
	login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
	content = login.getContentPane();
        content.setLayout(null);

        //title on login page
	title = new JLabel("Work Order Request Form");
	title.setFont(new Font("Times New Roman", Font.BOLD, 22)); 
	title.setSize(300, 30); 
        title.setLocation(120, 30); 
	content.add(title); 
        
	//text and fields
        userNameL = new JLabel("Username: "); 
	userNameL.setFont(new Font("Calibri Light", Font.PLAIN, 19)); 
	userNameL.setSize(100, 20); 
	userNameL.setLocation(50, 132); 
        content.add(userNameL); 
        
        passWordL = new JLabel("Password: "); 
        passWordL.setFont(new Font("Calibri Light", Font.PLAIN, 19)); 
        passWordL.setSize(100, 20); 
        passWordL.setLocation(50, 200); 
        content.add(passWordL);
        
        tuserName = new JTextField();
        tuserName.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        tuserName.setSize(290, 30); 
        tuserName.setLocation(150, 125); 
        content.add(tuserName);        
        

        tpassWord = new JPasswordField();
        tpassWord.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        tpassWord.setSize(290, 30); 
        tpassWord.setLocation(150, 190); 
        content.add(tpassWord);        
        
        
        //imagecon
        JLabel SIpic = new JLabel(new ImageIcon(getClass().getResource("/login.PNG")));
        SIpic.setSize(20,30);
        SIpic.setLocation(174,246);
        content.add(SIpic);
        
        JLabel SUpic = new JLabel(new ImageIcon(getClass().getResource("/signup.PNG")));
        SUpic.setSize(30,30);
        SUpic.setLocation(168,305);
        content.add(SUpic);
        
        
        signIn = new JButton(" Sign In "); 
        signIn.setFont(new Font("Arial", Font.BOLD, 14));
        signIn.setSize(105, 25); 
        signIn.setLocation(200, 250); 
        content.add(signIn);
	//on button press of sign in button
        signIn.addActionListener(new ActionListener() 
         {
			
			public void actionPerformed(ActionEvent ae)  
			 {
        			System.out.println("Sign In Button has been pressed.");
        			String username = tuserName.getText();
					String password = tpassWord.getText();
					try 
					 {	
							authentication(username, password);
					 }
					catch (ClassNotFoundException e) 
					 {
							e.printStackTrace();
					 }
						
			 }
        	
         });
        
        
        
        newguy = new JLabel("New to this?");
        newguy.setFont(new Font("Calibri", Font.ITALIC, 12));
        newguy.setSize(70, 15);
        newguy.setLocation(224,285);
        content.add(newguy);
        
                
        signUp = new JButton("Sign Up"); 
        signUp.setFont(new Font("Arial", Font.BOLD, 14)); 
        signUp.setSize(105, 25); 
        signUp.setLocation(200, 310); 
        content.add(signUp);
	//on press of button sigh up button
        signUp.addActionListener(new ActionListener() 
         {
        	
			@Override
			public void actionPerformed(ActionEvent ae)
        	 {
        		System.out.println("Sign Up Button has been pressed.");
        		try 
        		 {
        			
        			createAccount();
        		
				 } 
        		 catch (ClassNotFoundException e) 
        		 {
					e.printStackTrace();
				 }
        	 }
        }); 
        
        
        
        
        login.setVisible(true);
     }
	
		
	//checks if usernam eand password is correct accordingly sends errormessage
	public void authentication(String username, String password) throws ClassNotFoundException
	 {
		char foundU = 'a';
		char foundP = 'a'; 
		
		//logic if admin
		if(username.equals("admin") && password.equals("12345"))
		 {
			login.dispose();
			Admin admin = new Admin();
			admin.adminMenu(list);
			
		 }
		//logic for admin but wrong pass
		else if(username.equals("admin")&& !password.equals("12345"))
		 {
			System.out.println("Incorret admin password.");
			JOptionPane.showMessageDialog(login, "Admin Password Incorrect", "Alert" , JOptionPane.WARNING_MESSAGE);

		 }
		//logic for empty fields
		else if(username.equals("") || password.equals(""))
		 {
			JOptionPane.showMessageDialog(login, "Please fill all fields.", "Alert" , JOptionPane.WARNING_MESSAGE);

		 }

		//logic is arraylist is not empty
		else if(!list.isEmpty())
		 {		//iterate through arraylist
				for ( int i = 0; i < list.size(); i++)
				 {
					UserAccount data = list.get(i);
					//username found
					if (username.equals(data.getUserName()))
					 {
						foundU = 'b';
						//password matches
						if(password.equals(data.getPassword()))
						 {
							foundP = 'b';
							login.dispose();
							data.menu();
						 }
					 }			
				 }
		//username found but pass is wrong
		if( foundP != 'b' && foundU =='b')
		 {
			
			System.out.println("Incorrect Password");
			JOptionPane.showMessageDialog(login, "Incorrect Password", "Alert" , JOptionPane.WARNING_MESSAGE);
			
		 }
		//user not found
		if (foundU != 'b')
		 {
			JOptionPane.showMessageDialog(login, "User does not exist", "Alert" , JOptionPane.WARNING_MESSAGE);
		 }
		}
		//array list is empty		
		else 
		 {
			System.out.println("User does not exist");
			JOptionPane.showMessageDialog(login, "No User exists, please Sign Up", "Alert" , JOptionPane.WARNING_MESSAGE);
		 }

	 }
	//sign up button leads to this
	public void createAccount() throws ClassNotFoundException
	 {
		//new instance
		UserAccount newAcc = new UserAccount();
		login.dispose();
		Container content1;
		JLabel display_email, display_name, display_password, check_display_pass, display_username;
		JTextField input_name, input_email, input_username;
		JPasswordField input_password, checkPassword;
		JButton signUpButton; 
		
		signUpPage.setLayout(new FlowLayout());
		signUpPage.setSize(500,420);
		signUpPage.setResizable(false);
		signUpPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
        content1 = signUpPage.getContentPane();
        content1.setLayout(null);
        
        display_name = new JLabel("Name: "); 
        display_name.setFont(new Font("Calibri Light", Font.PLAIN, 16)); 
        display_name.setSize(100, 20); 
        display_name.setLocation(30, 60); 
        content1.add(display_name); 
        
        input_name = new JTextField();
        input_name.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        input_name.setSize(270, 30); 
        input_name.setLocation(140, 55); 
        content1.add(input_name);
       
        
        display_email = new JLabel("Email: "); 
        display_email.setFont(new Font("Calibri Light", Font.PLAIN, 16)); 
        display_email.setSize(100, 20); 
        display_email.setLocation(30, 120); 
        content1.add(display_email); 
        

        JLabel errorEmail = new JLabel();
        errorEmail.setFont(new Font("Calibri", Font.PLAIN, 10)); 
        errorEmail.setForeground(Color.red);
        errorEmail.setSize(290,10);
        errorEmail.setLocation(150,150);
        
        
        input_email = new JTextField();
        input_email.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        input_email.setSize(270, 30); 
        input_email.setLocation(140, 115);
	//added keylisterner for pass and email valid
        input_email.addKeyListener(new KeyListener() 
         {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
								
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!(input_email.getText().contains("@") && input_email.getText().contains(".")))
				{
					errorEmail.setVisible(true);
					errorEmail.setText("Enter valid email eg johndoe@gmail.com");
					emailfield = false;
			        
				}
				else {
					errorEmail.setVisible(false);
					emailfield = true;
				}
			
				
			}
			
		});

        content1.add(input_email);
        content1.add(errorEmail);
        
        

       
        display_password = new JLabel("Password: "); 
        display_password.setFont(new Font("Calibri Light", Font.PLAIN, 15)); 
        display_password.setSize(100, 20); 
        display_password.setLocation(30, 180); 
        content1.add(display_password);
        

        input_password = new JPasswordField();
        input_password.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        input_password.setSize(270, 30); 
        input_password.setLocation(140, 175); 
        content1.add(input_password);
        
        
        check_display_pass = new JLabel("Confirm Password: "); 
        check_display_pass.setFont(new Font("Calibri Light", Font.PLAIN, 13)); 
        check_display_pass.setSize(105, 20); 
        check_display_pass.setLocation(30, 240); 
        content1.add(check_display_pass);
        
        JLabel passError = new JLabel();
        passError.setFont(new Font("Calibri", Font.PLAIN, 10)); 
        passError.setForeground(Color.red);
        passError.setSize(290,10);
        passError.setLocation(150,270);
        

        checkPassword = new JPasswordField();
        checkPassword.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        checkPassword.setSize(270, 30); 
        checkPassword.setLocation(140, 235); 
        checkPassword.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!checkPassword.getText().equals(input_password.getText()))
				 {
					passError.setVisible(true);
					passError.setText("Password does not match");
					passfield = false;
				 }
				else
				 {
					passError.setVisible(false);
					passfield = true;
				 }				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
								
			}
		});
        content1.add(checkPassword);
        content1.add(passError);
        

        display_username = new JLabel("Username: "); 
        display_username.setFont(new Font("Calibri Light", Font.PLAIN, 16)); 
        display_username.setSize(100, 20); 
        display_username.setLocation(30, 300); 
        content1.add(display_username);
             
        input_username = new JTextField();
        input_username.setFont(new Font("Calibri", Font.PLAIN, 13)); 
        input_username.setSize(270, 30); 
        input_username.setLocation(140, 295); 
        content1.add(input_username);
        
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setSize(90,25);
        backButton.setLocation(20,340);
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Back Button pressed.");
				signUpPage.dispose();
				try 
				 {
					start();
				 }
				catch (ClassNotFoundException e) 
				 {
					e.printStackTrace();
				 }
				
			}
		});
        content1.add(backButton);
        
        
		
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        signUpButton.setSize(105, 25); 
        signUpButton.setLocation(200, 340); 
        
        signUpButton.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent submitAction)
			 {
				
				String name = input_name.getText();
		        newAcc.setName(name);
		        String username = input_username.getText();
		        newAcc.setUserName(username);
		        String password = input_password.getText();
		        newAcc.setPassword(password);
		        String email = input_email.getText();
		        newAcc.setEmail(email);
		        if (name.isEmpty() || email.isEmpty() ||username.isEmpty()||password.isEmpty())
		         {
					JOptionPane.showMessageDialog(signUpPage, "Please fill all prompts.", "Alert" , JOptionPane.WARNING_MESSAGE);
					
		         }
		       
		        else if (!passfield || !emailfield)
		         {
					JOptionPane.showMessageDialog(signUpPage, "Please check all errors.", "Alert" , JOptionPane.WARNING_MESSAGE);

		         }
		        
		        
		        else
		         {
		        
		        	System.out.println("New account has been added.");
		        	list.add(newAcc);
				
		        	try 
		        	 {
		        		signUpPage.dispose();
		        		save();
		        		start();
		        	 }
				
		        	catch (ClassNotFoundException e) 
		        	 {
				
		        		e.printStackTrace();
		        	 }
				
		         }
			
			}
        	
        });
        
        content1.add(signUpButton); 
        signUpPage.setVisible(true);
      
		
	}
	
	public static void delete(String name) throws ClassNotFoundException 
	 {
		
		for(int i = 0; i < list.size(); i++)
	     {
			if(list.get(i).name.equals(name))
			 {
				list.remove(i);
				save();
			 }
		 }
	 }	
		

	public static void save() throws ClassNotFoundException
	 {
		 try
	      { 		

	          FileOutputStream fileout = new FileOutputStream("newData.dat");
	          ObjectOutputStream objectout = new ObjectOutputStream(fileout);        
	          objectout.writeObject(list);
	          objectout.close();
	          fileout.close();
	          System.out.println("Instances Saved");
	          
	      }

	      catch (Exception e) 
	       {
	    	  e.printStackTrace();
	          System.out.println( e.getMessage());
	          
	       }
	  }
	
	public void load() throws ClassNotFoundException
	 {
		 try
	      {
			    System.out.println("\nLoading previous files...");
	            FileInputStream filein = new FileInputStream("newData.dat");
	            ObjectInputStream objectin = new ObjectInputStream(filein);
	            list = (ArrayList<UserAccount>)objectin.readObject();
	            objectin.close();
	            filein.close();
	            System.out.println("\nLoaded instances...\n\n");

	            
	       }
		 
	      catch (IOException e) 
	       {
	             	System.out.print("No previous files available...\n\n");
	       }

     }

	
	
	
 }

