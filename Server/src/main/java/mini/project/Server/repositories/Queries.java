package mini.project.Server.repositories;

public interface Queries {
    
    // public String SQL_SELECT_USER = "select * from user order by ID desc limit 1";
    
    // public String SQL_SEARCH = "select * from user where display_name like ?";

    // public String SQL_INSERT_USER = "INSERT INTO user VALUES ?";

    // public String SQL_SELECT_SEARCH = "SELECT * FROM search_results WHERE query=? AND type=? AND limit=?";

    // -------------------------------------ARTIST--------------------------------------------------------------------------------------------------------------------

    public String SQL_GET_ARTIST = "SELECT * FROM artist WHERE user_id = ?";

    public String SQL_INSERT_ARTIST = "INSERT INTO artist(spotify_artist_id, artist_name, popularity, link, image, user_id) values(?, ?, ?, ?, ?, ?)";

    // public String SQL_INSERT_ARTIST ="INSERT IGNORE INTO artist (spotify_artist_id, name, popularity, followers, link, id) VALUES (?, ?, ?, ?, ?, ?)";
    public String SQL_CHECK_ARTIST = "SELECT * FROM artist WHERE spotify_artist_id = ?";

    public String SQL_DELETE_ARTIST = "DELETE FROM artist WHERE spotify_artist_id = ?";

    // --------------------------------------ALBUM-------------------------------------------------------------------------------------------------------------------
    public String SQL_INSERT_ALBUM = "INSERT INTO album(spotify_album_id, album_name, release_date, link, image,user_id) values(?, ?, ?, ?, ?, ?)";

    public String SQL_CHECK_ALBUM = "SELECT * FROM album WHERE spotify_album_id = ?";

    public String SQL_GET_ALBUM = "SELECT * FROM album WHERE user_id = ?";

    public String SQL_DELETE_ALBUM = "DELETE FROM album WHERE spotify_album_id = ?";



    // --------------------------------------USER-------------------------------------------------------------------------------------------------------------------
    public String SQL_INSERT_USER = "INSERT IGNORE INTO user(unique_user_id, name) values(?, ?)";

    public String SQL_CHECK_USER = "SELECT * FROM user WHERE unique_user_id = ? AND name = ?";

}
