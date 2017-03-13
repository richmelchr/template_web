import com.entities.Album;
import com.entities.Artist;
import com.entities.Playlist;
import com.util.Table;
import javafx.scene.control.Tab;

import java.lang.reflect.Field;
import java.lang.reflect.*;
import java.lang.Class;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {

    public static void main(String[] args) {




        Table table = new Table();

//        Table.insert(album);

        Playlist playlist = new Playlist();
        Playlist playlist1 = new Playlist(1, "1", 1);
        Playlist playlist2 = new Playlist(22, "22", 22);
        Playlist playlist3 = new Playlist(333, "333", 333);




//        playlist1.selectByID(5);

//        playlist1.addColumn("colTest", true, false);

//        playlist1.selectByID(9);

//        for (Objects play: newList) {
//            Playlist newPlay = new Playlist();
//            Field[] fields = play.getClass().getClass().getDeclaredFields();
//            System.out.println(fields[1].getName());
//            System.out.println(fields[2].getName());
//            System.out.println(fields[3].getName());
//
//            playlist1.getPlaylistName();
//        }


//        System.out.println(playlist4.update(15));

//        playlist1.dropTable("gt46u7");








//        playlist.fieldColumnMatch();









//        PersonDaoImpl pdi = new PersonDaoImpl();
//        WriterDaoImpl writer = new WriterDaoImpl();
//        SongDaoImpl song = new SongDaoImpl();
//        AlbumDaoImpl album = new AlbumDaoImpl();
//        ArtistDaoImpl artist = new ArtistDaoImpl();
// ---------------------------------------------------------
//        Create table
//        pdi.createPersonTable();
//        writer.createWriterTable();
//        song.createSongTable();
//        album.createAlbumTable();
//        artist.createArtistTable();
// --------------------------------------------------------
//        Insert a new record
//        Person person = new Person("Steve","Johnson");
//        pdi.insert(person);

//        Writer writer1 = new Writer("Noel Gallagher");
//        writer.insert(writer1);

//        Song song1 = new Song("Wonderwall", "4:19", "Alternative", "1995", "1", "1", "1");
//        song.insert(song1);
//
//        Album album1 = new Album("Noel Gallagher's High Flying Birds", "2011");
//        album.insert(album1);
//
//        Artist artist1 = new Artist("Oasis");
//        artist.insert(artist1);
// ------------------------------------------------------------------------------------------
//        Select by id
//        Person personSelect = pdi.selectById(2);
//        System.out.println(person.getId()+", "+person.getFirstName()+", "+person.getLastName());
//        Writer writerSelect = writer.selectById(1);
//        Song songSelect = song.selectById(1);
//        Album albumSelect = album.selectById(1);
//        Artist artistSelect = artist.selectById(1);
//
//        System.out.println(writerSelect.getWriterID() + ", " + writerSelect.getWriterName());
//        System.out.println(songSelect.getSongID() + ", " + songSelect.getSongName() + ", " + songSelect.getYearWritten() +
//                ", " + songSelect.getLength() + ", " + songSelect.getGenre() + ", " + songSelect.getWriterID() +
//                ", " + songSelect.getArtistID() + ", " + songSelect.getAlbumID());
//        System.out.println(albumSelect.getAlbumID() + ", " + albumSelect.getAlbumName() + ", " + albumSelect.getYearRel());
//        System.out.println(artistSelect.getArtistID() + ", " + artistSelect.getArtistName());
// ----------------------------------------------------------------------------------------------------------------------

//        Delete person by id
//        pdi.delete(3);
//        writer.delete(1);
//        song.delete(1);
//        album.delete(1);
//        artist.delete(1);
// -------------------------------------------------------------
//        Update person
//        Person personUpdate = new Person("Tom","Johnson");
//        pdi.update(personUpdate,1);
//
     // Select all songs
//        List<Song> songs = song.selectAll();
//        for(Song p : songs) {
//            System.out.println(p.getId()+", "+p.getFirstName()+", "+p.getLastName());
//        }


    }
}
