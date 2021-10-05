import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Restaurant extends JFrame implements ActionListener {
    //booleans used 
	boolean orderMade;
    boolean orderTaken;
    boolean orderReady;
    boolean received;
    //Collection used to store the meal
    ArrayList<String> select = new ArrayList<String>();
    //all used to create the JFrame
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel success;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	JFrame frame;
	
    public void StartRestaurant() {
    	Order order = new Order(this);
        Chef chef = new Chef(this);
        Waiter waiter = new Waiter(this);
        //creating the threads
        Thread thread1 = new Thread(order);
        Thread thread2 = new Thread(waiter);
        Thread thread3 = new Thread(chef);
        //Starting the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
    //my GUI for a login system
    public static void LoginSystem() {
		Scanner scan = new Scanner(System.in);
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
			frame.setSize(350,250);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.add(panel);
			frame.setLocationRelativeTo(null);
			panel.setLayout(null);
			
			userLabel = new JLabel("User");
			userLabel.setBounds(10,20,80,25);
			panel.add(userLabel);
			
			userText = new JTextField(20);
			userText.setBounds(100,20,165,25);
			panel.add(userText);
			
			passwordLabel = new JLabel("password");
			passwordLabel.setBounds(10,50,80,25);
			panel.add(passwordLabel);
			
			passwordText = new JPasswordField();
			passwordText.setBounds(100,50,165,25);
			panel.add(passwordText);
			
			JLabel success = new JLabel("");
			success.setBounds(10,110,300,25);
			panel.add(success);
			
			button = new JButton("Login");
			button.setBounds(10,80,80,25);
			button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String user = userText.getText();
						String password = passwordText.getText();
						
						
						if(user.equals("Brian") && password.equals("bdiner")) {
							success.setText("Login succesful");
							Restaurant restaurant=new Restaurant();
							restaurant.StartRestaurant();	
							frame.setVisible(false);
						}
						else 
							{
							success.setText("Login unsuccesful");
							success.setForeground(Color.RED);
							}
					}
				});
			panel.add(button);
			frame.setVisible(true);	
			}
    	public static void main(String[] args) {
        
    	String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(systemLookAndFeelClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginSystem();
    	}
    	
    	//this method will change orderMade boolean to true, which allows the waiter class to progress
      public synchronized void orderMadeByUser() {
        while (orderMade) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderMade = true;
        System.out.println("Order Recieved" );
      }
    //this method will change orderTaken boolean to true, which allows the chef class to progress
      public synchronized void getOrder() {
        while (orderTaken) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderTaken = true;
        System.out.println("Gives order to chef ");
      }
    //this method will change received boolean to true, which allows the order class to progress
      public synchronized void OrderReceived() {
        while (!received) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        received = false;   
    }
    //this method will change received boolean to true, which allows the waiter class to progress
      public synchronized void UserRecievesMeal() {
        while (received) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        received = true;
        System.out.println("User recieved their meal "+select);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
    }
