package Opgave5;

import java.util.ArrayList;

public class Episode {

    private final int number;
    private final ArrayList<String> guestActors;
    private final int lengthMinutes;

    // er sgu lidt i tvivl her. Hvad vil det sige at initialisere alle attributter i klassen?
    public Episode(int number, ArrayList<String> guestActors, int lengthMinutes){
        this.number = number;
        this.guestActors = guestActors;
        this.lengthMinutes = lengthMinutes;
    }


    public int getNumber() {
        return number;
    }

    public ArrayList<String> getGuestActors() {
        return guestActors;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }
}
