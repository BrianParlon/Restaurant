import java.util.Scanner;
public class Order extends Thread {
    Restaurant restaurant;
    String meal;
    Scanner scan = new Scanner(System.in);
    
    public void creatingAnOrder() throws InterruptedException{
        synchronized (restaurant) 
        {   
        	//Text printed
        	System.out.println("Welcome to Brians Diner");          
            System.out.println("What would you like to order?");
    		meal = scan.nextLine();
    		restaurant.select.add(meal);
    		
    		//calling this method changes the boolean to true
    		restaurant.orderMadeByUser();  
            System.out.println("End ordering"); 
            
            //while received is false it will wait till called in the waiter class
            while(restaurant.received==false)
            	restaurant.wait();         
            //calling this method will change the received boolean to false
            restaurant.OrderReceived();
            //as the process goes through the classes if done so correctly this will be the last line
            System.out.println("The process is complete.");
           }
    }
    //Run the thread to run the method above
    public void run() {
        try {
            creatingAnOrder();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Order(Restaurant restaurant) {
        this.restaurant=restaurant;
       }  
}




