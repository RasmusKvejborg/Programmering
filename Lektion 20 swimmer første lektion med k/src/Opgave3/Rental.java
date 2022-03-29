package Opgave3;

import java.util.ArrayList;

public class Rental {
    private int number;
    private int days;
    private String date;
    private ArrayList<Car> cars = new ArrayList<>();

    public Rental(int number, String date, int days){
        this.number = number;
        this.date = date;
        this.days = days;
    }


    public ArrayList<Car> getCars(){
        return cars;
    }


    public void addCar(Car car){
        cars.add(car);
    }

    public void removeCar(Car car){
        cars.remove(car);
    }

    public double getPrice() {// Metoden udregner prisen for en udlejning ved at summere alle de tilhørende bilers pris pr. dag og gange med det antal dage, som udlejningen varer.
        int price = 0;
        for (int i = 0; i < cars.size(); i = i + 1) {
            price = (int) (price + getCars().get(i).getDayPrice() * getDays());
        }
        return price;
    }

    public void setDays(int days){
        this.days = days;
    }

    public int getDays(){
        return days;
    }








}
