package com.apex.backend;

public class Purchase {
    Integer user_id; 
    Integer ticket_id;
    String paymenth_method; 

    

    public Purchase(Integer user_id, Integer ticket_id, String paymenth_method){
        this.user_id = user_id;
        this.ticket_id = ticket_id;
        this.paymenth_method = paymenth_method; 
    }
    
}