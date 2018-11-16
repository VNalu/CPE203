import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator1()
   {
      ArtistComparator ac = new ArtistComparator();
      assertTrue((ac.compare(songs[0], songs[1])) < 0);
   }

   @Test
   public void testArtistComparator2()
   {
      ArtistComparator ac = new ArtistComparator();
      assertTrue((ac.compare(songs[1], songs[2])) > 0);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> songOrderer = (Song s1, Song s2) -> s1.getArtist().compareTo(s2.getArtist());
      assertTrue(songOrderer.compare(songs[0], songs[1]) < 0);
   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> songOrderer = Comparator.comparing(Song::getYear);
      assertTrue(songOrderer.compare(songs[2], songs[3]) > 0);
      
   }

   @Test
   public void testComposedComparator()
   {
      Comparator<Song> c1 = Comparator.comparing(Song::getArtist);
      Comparator<Song> c2 = Comparator.comparing(Song::getYear);
      ComposedComparator comp = new ComposedComparator(c1, c2);
      assertTrue(comp.compare(songs[5], songs[7]) < 0);
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> songComp = Comparator.comparing(Song::getArtist).thenComparing(Song::getYear);
      assertTrue(songComp.compare(songs[5], songs[7]) < 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      
         Comparator<Song> songComp = Comparator.comparing(Song::getArtist).thenComparing(Song::getTitle).thenComparing(Song::getYear);
      
         songList.sort(
         // pass comparator here
           songComp
      );

      assertEquals(songList, expectedList);
   }
}
