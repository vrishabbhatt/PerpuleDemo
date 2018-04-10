package com.example.demo;

public class Customer implements  Identifiable {
    private Long id;
    private String firstName;
    private String LastName;

    public Customer()
    {

    }
    public Customer(Long id , String firstName , String lastName){

        id = id;
        firstName = firstName;
        lastName = lastName;
    }

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName(){
        return  this.firstName;
    }

    public String getLastName(){
        return this.LastName;
    }

    public void setFirstName(String fname)
    {
        this.firstName = fname;
    }
    public void setLastName(String Lname)
    {
        this.LastName = Lname;
    }

}
