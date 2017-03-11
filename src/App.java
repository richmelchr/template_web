import com.entities.Album;
import com.entities.Artist;
import com.entities.Playlist;
import com.util.Table;

import java.lang.reflect.Field;
import java.lang.reflect.*;
import java.lang.Class;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {




        Table table = new Table();

//        Table.insert(album);


        Playlist playlist = new Playlist(15, "hits");
        Album album = new Album();
        Artist artist = new Artist();
//        playlist.createTable();

//        playlist.insert();

//        playlist.update(3);

//        System.out.println(album.getPrimaryKey());

        Playlist playlist1 = new Playlist(2, "more hits");

        playlist1.update(6);


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
