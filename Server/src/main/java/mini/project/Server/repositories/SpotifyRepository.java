package mini.project.Server.repositories;

import static mini.project.Server.repositories.Queries.SQL_CHECK_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_CHECK_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_CHECK_USER;
import static mini.project.Server.repositories.Queries.SQL_GET_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_INSERT_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_INSERT_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_INSERT_USER;
import static mini.project.Server.repositories.Queries.SQL_DELETE_ARTIST;
import static mini.project.Server.repositories.Queries.SQL_GET_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_DELETE_ALBUM;
import static mini.project.Server.repositories.Queries.SQL_INSERT_BLOB;
import static mini.project.Server.repositories.Queries.SQL_GET_BLOB;






import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mini.project.Server.models.Album;
import mini.project.Server.models.Artist;
import mini.project.Server.models.SpotifyAlbum;
import mini.project.Server.models.SpotifyArtist;
import mini.project.Server.models.User;

@Repository
public class SpotifyRepository {

    @Autowired
    private JdbcTemplate temp;

    private static String currentUserId;

    //save artist to mysql
    public List<SpotifyArtist> saveArtist(Map<String, Object> artistsData) {
        List<SpotifyArtist> savedArtists = new ArrayList<>();
        
        Map<String, Object> artistMap = (Map<String, Object>) artistsData.get("artist");
        System.out.println("ARTISTMAPPPPPPPPP"+artistMap);

        String spotifyArtistId = (String) artistMap.get("spotify_artist_id");
        String artistName = (String) artistMap.get("artist_name");
        int popularity = (int) artistMap.get("popularity");
        // int followers = (int) artistMap.get("followers");
        String link = (String) artistMap.get("external_urls");
        String image = (String) artistMap.get("image");
        String id = currentUserId;
    
        if (spotifyArtistId != null ) { // check if spotify_artist_id is not null
            // execute the SQL insert statement to save the artist data to the database
            temp.update(SQL_INSERT_ARTIST, spotifyArtistId, artistName, popularity, link, image,id);
    
            // retrieve the saved artist data from the database and add it to the list of saved artists
            // String sql = "SELECT * FROM artist WHERE spotify_artist_id = ?";
            // SpotifyArtist savedArtist = temp.queryForObject(SQL_CHECK_ARTIST, new Object[]{spotifyArtistId}, new BeanPropertyRowMapper<>(SpotifyArtist.class));
            
           
            SpotifyArtist savedArtist = temp.queryForObject(SQL_CHECK_ARTIST, new Object[]{spotifyArtistId}, new BeanPropertyRowMapper<>(SpotifyArtist.class));
            // while(savedArtist.next())
            // savedArtists.add(SpotifyArtist.createArtist(savedArtist));
            savedArtists.add(savedArtist);
        } else {
            // handle the case where spotify_artist_id is null
            // ...
            System.out.println("ERROR");
            System.out.println(spotifyArtistId);
        }
    
        return savedArtists;
    }
 
    //GET ARTIST FROM MYSQL
    public List<Artist> getArtist(String userId){
       
        System.out.println("USER ID STRING >>>>>>>>>>>>>>>>>> " + userId);
    //     List<SpotifyArtist> artists = temp.query(SQL_GET_ARTIST, new Object[]{userId},
    //     new RowMapperResultSetExtractor<SpotifyArtist>(new BeanPropertyRowMapper<>(SpotifyArtist.class))
    // );

        SqlRowSet rs = temp.queryForRowSet(SQL_GET_ARTIST,userId);

        List<Artist> artists = new LinkedList<>();
        while(rs.next())
            artists.add(Artist.createArtist(rs));

            return artists;

        // System.out.println("ARTIST LIST >>>>" + artists);
      
        // return artists;
        // return temp.queryForRowSet(SQL_GET_ARTIST,userId);
    }

    //DELETE ARTIST FROM MYSQL
    public void deleteArtist(String artistId){
        temp.update(SQL_DELETE_ARTIST, artistId);
    }

    //SAVE Album to MYSQL
    public List<SpotifyAlbum> saveAlbum(Map<String, Object> albumData) {
        List<SpotifyAlbum> savedAlbumlist = new ArrayList<>();
        
        Map<String, Object> albumMap = (Map<String, Object>) albumData.get("album");
        System.out.println("ALBUM MAP"+albumMap.toString());

        String spotifyAlbumId = (String) albumMap.get("spotify_album_id");
        String albumName = (String) albumMap.get("album_name");
        String releaseDate = (String) albumMap.get("release_date");
        String link = (String) albumMap.get("external_urls");
        String image = (String) albumMap.get("image");
        String id = currentUserId;
    
        if (spotifyAlbumId != null) { 
            temp.update(SQL_INSERT_ALBUM, spotifyAlbumId, albumName,releaseDate, link, image, id);
    
        
            SpotifyAlbum savedAlbum = temp.queryForObject(SQL_CHECK_ALBUM, new Object[]{spotifyAlbumId}, new BeanPropertyRowMapper<>(SpotifyAlbum.class));
            
            savedAlbumlist.add(savedAlbum);
        } else {
           
            System.out.println("ERROR");
        }
    
        return savedAlbumlist;
    }

    //GET Album FROM MYSQL
    public List<Album> getAlbum(String userId){
       
        System.out.println("USER ID STRING >>>>>>>>>>>>>>>>>> " + userId);

        SqlRowSet rs = temp.queryForRowSet(SQL_GET_ALBUM,userId);

        List<Album> album = new LinkedList<>();
        while(rs.next())
            album.add(Album.createAlbum(rs));

            return album;
  
    }

    //DELETE Album FROM MYSQL
    public void deleteAlbum(String albumId){
        temp.update(SQL_DELETE_ALBUM, albumId);
    }

    //save user to mysql
   public List<User> saveUser(Map<String, Object> userData) {
    List<User> userList = new ArrayList<>();

    List<Map<String, Object>> users = (List<Map<String, Object>>) userData.get("user");
    Map<String, Object> userMap = users.get(0);
    System.out.println("userMap"+userMap.toString());

    String userId = (String) userMap.get("id");
    currentUserId = userId;
    System.out.println("USERID>>>"+currentUserId);

    String userName = (String) userMap.get("display_name");

    if (userId != null) { 
        temp.update(SQL_INSERT_USER, userId, userName);

        User savedUser = temp.queryForObject(SQL_CHECK_USER, new Object[]{userId,userName}, new BeanPropertyRowMapper<>(User.class));
        
        userList.add(savedUser);
    } else {
        System.out.println("ERROR");
    }

    return userList;
}

    public void upload(byte[] bytes) {
        temp.update(SQL_INSERT_BLOB, bytes);
    }

    public byte[] getBlob(){
        
        byte[] blobData = temp.queryForObject(SQL_GET_BLOB,  new Object[]{}, byte[].class);

        return blobData;
    }

    // public ResponseEntity<byte[]> getBlob(){
    //     byte [] data = temp.queryForObject(SQL_GET_BLOB, new Object[] { }, byte[].class);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_JPEG);
    //     headers.setContentLength(data.length);

    //     return new ResponseEntity<>(data, headers, HttpStatus.OK);
    // }

    // public ResponseEntity<byte[]> getBlob() {

    // byte[] data = temp.queryForObject(SQL_GET_BLOB, new Object[]{}, byte[].class);
    
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.IMAGE_JPEG);
    // headers.setContentLength(data.length);

    // return new ResponseEntity<>(data, headers, HttpStatus.OK);


    
    

}
