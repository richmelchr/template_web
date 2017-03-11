import com.daoimpl.*;
import com.entities.*;
import com.util.Table;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.String;
import java.util.Objects;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet implements javax.servlet.Servlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "no button found at all";
        String button = null;
        StringBuilder build = new StringBuilder();
//        String radioSelect = request.getParameter("radioSelect").trim();    //get selected radio button from DOM
        try {
            button = request.getParameter("button").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.equals(button, "writerBtn")) {
            String writerName = request.getParameter("writerName").trim();

            WriterDaoImpl writer = new WriterDaoImpl();
            writer.createWriterTable();
            Writer writer1 = new Writer(writerName);
            writer.insert(writer1);

            result = "Writer Table Updated";

        } else if (Objects.equals(button, "songBtn")) {
            String songName = request.getParameter("songName").trim();
            int yearWritten = Integer.parseInt(request.getParameter("yearWritten").trim());
            int length = Integer.parseInt(request.getParameter("length").trim());
            String genre = request.getParameter("genre").trim();
            int writerID = Integer.parseInt(request.getParameter("writerID").trim());
            int artistID = Integer.parseInt(request.getParameter("artistID").trim());
            int albumID = Integer.parseInt(request.getParameter("albumID").trim());

            SongDaoImpl song = new SongDaoImpl();
            song.createSongTable();
            Song song1 = new Song(songName, yearWritten, length, genre, writerID, artistID, albumID);
            song.insert(song1);

            result = "Song Table Updated";

        } else if (Objects.equals(button, "albumBtn")) {
            String albumName = request.getParameter("albumName").trim();
            int yearRel = Integer.parseInt(request.getParameter("yearRel").trim());

            AlbumDaoImpl album = new AlbumDaoImpl();
            album.createAlbumTable();
            Album album1 = new Album(albumName, yearRel);
            album.insert(album1);

            result = "Album Table Updated";

        } else if (Objects.equals(button, "artistBtn")) {
            String artistName = request.getParameter("artistName").trim();

            ArtistDaoImpl artist = new ArtistDaoImpl();
            artist.createArtistTable();
            Artist artist1 = new Artist(artistName);
            artist.insert(artist1);

            result = "Artist Table Updated";

        } else if (Objects.equals(button, "lookupBtn")) {
            result = button;

        } else if (Objects.equals(button, "radioBtn")) {
            int radioSelect = Integer.parseInt(request.getParameter("radioSelect").trim());

            ArrayList<String> queryResult = query(radioSelect);

            for (String temp : queryResult) {
                build.append("<tr><td>").append(temp).append("</td></tr>");
            }

            result = build.toString();

        } else {
            result = "ERROR: button selection not found";
        }

//        response.setContentType("text/plain");
        response.getWriter().print(result);                             //sends text back to DOM
    }

//    public ArrayList<String>[][] newQuery() {
//        ArrayList<String>[][] result;
//        result = new ArrayList<String>[][];
//        try {
//            Connection connection = ConnectionConfiguration.getConnection();
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return result;
//    }

    public ArrayList<String> query(int selection) {
        ArrayList<String> result = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();

            if (selection == 1) {
                Song song = new Song();
                int albumID = 2;
                preparedStatement = connection.prepareStatement("SELECT * FROM song WHERE albumID = ?");
                preparedStatement.setInt(1, albumID);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    song.setSongName(resultSet.getString("songName"));
                    result.add(song.getSongName());
                }
                return result;

            } else if (selection == 2) {
                Album album = new Album();
                int yearRel = 2015;
                preparedStatement = connection.prepareStatement("SELECT * FROM album WHERE yearRel = ?");
                preparedStatement.setInt(1, yearRel);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    album.setAlbumName(resultSet.getString("albumName"));
                    result.add(album.getAlbumName());
                }
                return result;


            } else if (selection == 3) {
                Artist artist = new Artist();
                String genre = "Pop";
                preparedStatement = connection.prepareStatement("SELECT * FROM artist, song WHERE song.genre = ?");
                preparedStatement.setString(1, genre);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    artist.setArtistName(resultSet.getString("artistName"));
                    result.add(artist.getArtistName());
                }
                return result;

            } else if (selection == 4) {
                Song song = new Song();
                String artistName = "Nirvana";
                int length = 300;
                preparedStatement = connection.prepareStatement("SELECT * FROM song, artist WHERE artist.artistName = ? AND song.length > ?");
                preparedStatement.setString(1, artistName);
                preparedStatement.setInt(2, length);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    song.setSongName(resultSet.getString("songName"));
                    result.add(song.getSongName());
                }
                return result;

            } else if (selection == 5) {
                Album album = new Album();
                int count2 = 10;
                int length = 180;
                preparedStatement = connection.prepareStatement("SELECT * FROM song WHERE albumID IN (SELECT albumID FROM song GROUP BY albumID HAVING count(*) > ?) AND song.length < ?");
                preparedStatement.setInt(1, count2);
                preparedStatement.setInt(2, length);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    album.setAlbumID(resultSet.getInt("albumID"));
                    result.add(String.valueOf(album.getAlbumID()));
                }
                return result;

            } else {
                result.add("ERROR: Selection not found");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
