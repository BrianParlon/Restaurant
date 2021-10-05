public class Chef extends Thread{
    Restaurant restaurant;

    public void foodProcess() throws InterruptedException {
        synchronized (restaurant) 
        {
        	restaurant.notifyAll();
        	//when orderTaken is made true it will progress
            while(restaurant.orderTaken==false)
            restaurant.wait();
             System.out.println("prepare to start making food");
            // sleeps for the amount of time in milliseconds to mock time spent to creating a burger
            Thread.sleep(3500);           
            System.out.println(restaurant.select.toString()+ " is ready");
            //will notify the waiter class
            restaurant.notify();
        }
    }
  //Run the thread to run the method above
    @Override
    public void run() {
        try {
        	foodProcess();
        } catch (InterruptedException e) {        
            e.printStackTrace();
        }
    }
    
    public Chef(Restaurant r) {
        this.restaurant=r;
        
    }
}