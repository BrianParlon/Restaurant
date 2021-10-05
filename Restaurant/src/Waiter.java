public class Waiter extends Thread {
     Restaurant restaurant;
    public void serveCustomer() throws InterruptedException {
        synchronized (restaurant) 
        {
        	restaurant.notifyAll();
        	//when orderMade is made true it will progress
            while(restaurant.orderMade==false)
            restaurant.wait();
            System.out.println("Waiter goes to the kitchen");
            //a sleep is added 
            Thread.sleep(1000);
            //this will change orderTaken to true
            restaurant.getOrder();   
            //this allows the thread to wait till it's notified by the chef
            restaurant.wait();
            System.out.println("The Waiter has finished serving the customer");
            //this will call the method in the restaurant class
            restaurant.UserRecievesMeal();
        }
    }
     //allows the thread to run the method above
    @Override
    public void run() {
        try {
            serveCustomer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Waiter(Restaurant restaurant) {
        this.restaurant=restaurant;   
    }  
}