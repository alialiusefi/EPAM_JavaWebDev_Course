package by.training.task2.entity;

import java.util.concurrent.ConcurrentLinkedQueue;

//Observable, State
public class Cashier implements Runnable{

    ConcurrentLinkedQueue<Customer> customersQueue;
    //State isServing;
    //State isAccepting;
    //Obeserver restaurant;

    //public void serveCustomer(Customer customer);
    //public void acceptCustomer(Customer customer)

    @Override
    public void run() {
    //serve customers one by one using certain methods
    // acceptCustomer() , serveCustomer()
    //
    }


}

