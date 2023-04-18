// package mini.project.Server.repositories;

// import java.util.LinkedList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.support.rowset.SqlRowSet;
// import org.springframework.stereotype.Repository;

// import jakarta.json.JsonObject;
// import mini.project.Server.models.SpotifyAlbum;
// // import mini.project.Server.models.SpotifySearchResponse;
// import mini.project.Server.models.User;
// import static mini.project.Server.repositories.Queries.*;

// @Repository
// public class UserRepository {

//     @Autowired
//     private JdbcTemplate temp;

//     public List<User> getUser(){
//         SqlRowSet rs = temp.queryForRowSet(SQL_SELECT_USER);
//         List<User> results = new LinkedList<>();
//         while(rs.next())
//         results.add(User.createUser(rs));

//         return results;
//     }

//     // public List<SpotifySearchResponse> getSearch(){
//     //     SqlRowSet rs = temp.queryForRowSet(SQL_SELECT_SEARCH);
//     //     List<SpotifySearchResponse> results = new LinkedList<>();

//     //     while(rs.next())
//     //     results.add(SpotifySearchResponse.createSearchResponse(rs));
//     // }

//     public List<User> insertUser(String user){

//         System.out.println(user);
//         List<User> users = new LinkedList<>();

//         // SqlRowSet rs = temp.queryForRowSet(SQL_SEARCH,name);
//         SqlRowSet insert = temp.queryForRowSet(SQL_INSERT_USER,user);

//         // if(rs != null)
//         while(insert.next())
//             users.add(User.createUser(insert));

//         return users;
//     }
    
// }
