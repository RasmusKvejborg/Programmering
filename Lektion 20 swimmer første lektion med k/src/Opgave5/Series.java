package Opgave5;

import java.util.ArrayList;

public class Series {
    private String title;
    private ArrayList<String> cast;
    private final ArrayList<Episode> episodes = new ArrayList<>();

    public Series(String title, ArrayList<String> cast){
        this.title = title;
        this.cast = cast;
    }

    public ArrayList<Episode> getEpisodes(){
        return new ArrayList<>(episodes);
    }

    //er i tvivl om createEpisode gør det rigtigt: Har bare kopieret parametre fra Episode klassen. Hvad hedder de episoder jeg laver, hvis jeg laver flere?
    public Episode createEpisode(int number, ArrayList<String> guestActors, int lengthMinutes){
        Episode episode = new Episode(number, guestActors, lengthMinutes);
        episodes.add(episode);
        return episode;
    }

    /** * Return the total length (in minutes) of all the * episodes in the series. */
    public int totalLength(){
        int totalLength = 0;
        int x = 0; // fordi jeg godt kan lide enhanced for loops, men manglede en tæller, som plejer at hedde 'i'.

        for (Episode i : episodes){
            totalLength=totalLength + episodes.get(x).getLengthMinutes();
            x++;
        }
        return totalLength;
    }


    /** * Return the total list of all guest actors from all
     * episodes. */
    public ArrayList<String> getGuestActors(){
        ArrayList<String> allGuestActors = new ArrayList<>();
        int x = -1; // fordi jeg godt kan lide enhanced for loops, men manglede en tækker, som plejer at hedde 'i'.

        for (Episode i : episodes){
            x++;
            ArrayList<String> listOfGuestActorsInThisEpisode = episodes.get(x).getGuestActors();

                for (int n = 0; n < listOfGuestActorsInThisEpisode.size(); n++){
                    allGuestActors.add(listOfGuestActorsInThisEpisode.get(n));
                }

        }
        return allGuestActors;
    }



    public String getTitle() {
        return title;
    }

    public ArrayList<String> getCast() {
        return cast;
    }
}


