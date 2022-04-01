package Opgave_2_og_3.application.model;

public class Customer {
    private String name;

    public Customer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "";
    }
}
