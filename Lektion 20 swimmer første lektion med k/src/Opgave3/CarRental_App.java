package Opgave3;

public class CarRental_App {
    public static void main(String[] args) {
        Car c1 = new Car("nummerplade1",1999);
        Car c2 = new Car("nummerplade2",2002,259);
        Car c3 = new Car("nummerplade3",2004);
        Car c4 = new Car("nummerplade4",2007);
        Car c5 = new Car("nummerplade5",2010);



        Rental r1 = new Rental(3,"idag",4);
        Rental r2 = new Rental(4,"idag",5);
        r1.addCar(c1);
        r1.addCar(c2);
        r2.addCar(c3);

        System.out.println(r2.getCars().get(0).getLicence());

        System.out.println(c1.getDayPrice());

        System.out.println(r1.getPrice());

    }
}
