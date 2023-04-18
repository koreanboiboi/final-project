package mini.project.Server.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini.project.Server.models.SpotifyAlbum;
import mini.project.Server.models.SpotifyArtist;
import mini.project.Server.models.User;
// import mini.project.Server.repositories.UserRepository;
// import se.michaelthelin.spotify.SpotifyApi;
// import se.michaelthelin.spotify.requests.data.library.SaveAlbumsForCurrentUserRequest;

@Service
public class SpotifyService {

    private static final String SEARCHURL = "https://api.spotify.com/v1/search";
    private static final String SAVEALBUMURL = "https://api.spotify.com/v1/me/albums";
    private static final String UserURL = "https://api.spotify.com/v1/me";

    // @Autowired
    // private UserRepository spotRepo;


    // public List<User> getUser(){     
    //     return spotRepo.getUser();
    // }

    // public List<User> saveUser(String user) {
    //     return spotRepo.insertUser(user);
    // }

    //getUserProfile service
    public List<User> getUserProfile(Map<String,String> headers){
        String authHeader = headers.toString();
        String authToken = authHeader.split(",\\s*")[12].replace("authorization=Bearer ", "");
        System.out.println("headers>>>" + headers.toString());
        System.out.println("AuthToken: >>>>> "+ authToken);

        String getUserUrl = UriComponentsBuilder.fromUriString(UserURL).toUriString();
        RequestEntity<Void> req = RequestEntity.get(getUserUrl).header("Authorization", "Bearer "+authToken).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> resp = restTemp.exchange(req, String.class);
        String payload = resp.getBody();
        System.out.println("payload>>>>>>>>>>>>>>>>>>>>"+payload);

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();

        // JsonArray data = jsonObject.asJsonArray();
        // System.out.println("object >>>>>>>>>>>" + jsonObject);
        // List<User> userList = new LinkedList<>();
        // JsonObject jo = data.getJsonObject(0);
        // userList.add(User.create(jo));
        // System.out.println("JO >>>>>>>>>>>>>>"+jo);


        User user = User.create(jsonObject);
        List<User> userList = new LinkedList<>();
        userList.add(user);

        // System.out.println("userList>>>>>>>>>" + userList.toString());
        // for(int i = 0 ; i < data.size(); i++){
        //     JsonObject jo = data.getJsonObject(i);
        //     userList.add(User.create(jo));
        //         }
            
    
            // System.out.println("userlist>>>>>>>>>>>>>"+userList.toString());
    
            return  userList;

    }

    //searchArtist service
    public List<SpotifyArtist> searchArtist(Map<String,String> param, Map<String,String> headers){

        String authHeader = headers.toString();
        String authToken = authHeader.split(",\\s*")[12].replace("authorization=Bearer ", "");

        System.out.println("AuthToken: >>>>> "+ authToken);


        // HttpHeaders header = new HttpHeaders();
        // header.set("Authorization", "Bearer "+ authToken);

        String params = param.toString()
                                .replace("{", "")
                                .replace("}", "")
                                .replace(", ", "&");

        String searchArtistUrl = UriComponentsBuilder.fromUriString(SEARCHURL).query(params).toUriString();

        RequestEntity<Void> req = RequestEntity.get(searchArtistUrl).header("Authorization", "Bearer "+authToken).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> resp = restTemp.exchange(req, String.class);

        String payload = resp.getBody();

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray data = jsonObject.getJsonObject("artists").getJsonArray("items");

        List<SpotifyArtist> artistList = new LinkedList<>();

        for(int i = 0 ; i < data.size(); i++){
        JsonObject jo = data.getJsonObject(i);
        artistList.add(SpotifyArtist.create(jo));
            }
        

        System.out.println("artistList>>>>>>>>>>>>>"+artistList.toString());

        return  artistList;

    }


    //searchAlbum service
    public List<SpotifyAlbum> searchAlbum(Map<String,String> param, Map<String,String> headers){

        String authHeader = headers.toString();
        String authToken = authHeader.split(",\\s*")[12].replace("authorization=Bearer ", "");

        System.out.println("AuthToken: >>>>> "+ authToken);


        String params = param.toString()
                                .replace("{", "")
                                .replace("}", "")
                                .replace(", ", "&");

        String searchAlbumUrl = UriComponentsBuilder.fromUriString(SEARCHURL).query(params).toUriString();

        RequestEntity<Void> req = RequestEntity.get(searchAlbumUrl).header("Authorization", "Bearer "+authToken).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> resp = restTemp.exchange(req, String.class);

        String payload = resp.getBody();
        System.out.println("payload>>>>"+payload);
        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray data = jsonObject.getJsonObject("albums").getJsonArray("items");
        System.out.println("data>>>>>>>>>>>"+data);

        List<SpotifyAlbum> albumList = new LinkedList<>();

        for(int i = 0 ; i < data.size(); i++){
        JsonObject jo = data.getJsonObject(i);
        albumList.add(SpotifyAlbum.create(jo));
            }
        

        System.out.println("albumlist>>>>>>>>>>>>>"+albumList.toString());

        return  albumList;

    }
//saveAlbum
    public ResponseEntity<String> saveAlbum(String albumIds, Map<String,String> headers){

        System.out.println("IDS>>>" + albumIds);
        
        String authHeader = headers.toString();
        // System.out.println("authHeader>>>>>>>>" + authHeader);
        String authToken = authHeader.split(",\\s*")[13].replace("authorization=Bearer ", "");

        System.out.println("authToken >>>" + authToken);

        String requestBody =  albumIds;

        // URI saveAlbumUrl = URI.create("https://api.spotify.com/v1/me/albums");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(authToken);

        String saveAlbumUrl = UriComponentsBuilder.fromUriString(SAVEALBUMURL).toUriString();

        // RequestEntity<String> req = new RequestEntity<String>(requestBody,header,HttpMethod.PUT, saveAlbumUrl);

        // String url = String.format("%s?ids=%s", SAVEALBUMURL, ids);
        RequestEntity<String> req = RequestEntity.put(saveAlbumUrl).header("Authorization", "Bearer "+ authToken).accept(MediaType.APPLICATION_JSON).body(albumIds);

   
        // System.out.println("globalAuthToken>>>>" + authToken);
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> resp = restTemp.exchange(req, String.class);
        // String payload = resp.getBody();
        System.out.println("Response>>>>" + resp);

        return resp;
    }

   

}




