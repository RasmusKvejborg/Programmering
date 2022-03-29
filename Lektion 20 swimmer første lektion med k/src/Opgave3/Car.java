package Opgave3;

public class Car {
    private final String licence; //hvorfor er der 3 attributter men 2 konstruktorer?
    private double pricePerDay;
    private final int purchaseYear;

    public Car(String licence, int year){
        this.licence = licence;
        this.purchaseYear = year;
        this.pricePerDay =200;
    }

    public Car(String licence, int year, int pricePerDay){
        this.licence = licence;
        this.purchaseYear = year;
        this.pricePerDay = pricePerDay;
    }


    public void setDayPrice(Double price){
        pricePerDay = price;
    }

    public double getDayPrice(){
        return pricePerDay;
    }

    public String getLicence(){
        return licence;
    }

    public int getPurchaseYear(){
        return purchaseYear;
    }



















}
