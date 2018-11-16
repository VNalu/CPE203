import java.util.Comparator;

public class ArtistComparator implements Comparator<Song> {

    public ArtistComparator(){}

    public int compare(Song songA, Song songB) {
        return (songA.getArtist()).compareTo(songB.getArtist());
    }

}