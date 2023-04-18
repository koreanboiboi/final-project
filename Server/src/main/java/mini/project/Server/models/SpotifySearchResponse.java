// package mini.project.Server.models;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.json.Json;
// import jakarta.json.JsonArray;
// import jakarta.json.JsonArrayBuilder;
// import jakarta.json.JsonObject;
// import jakarta.json.JsonObjectBuilder;


// public class SpotifySearchResponse {

//     private List<SpotifyAlbum> albums;
//     private List<SpotifyArtist> artists;
//     private List<SpotifyTrack> tracks;

//     public SpotifySearchResponse(JsonObject jo) {
//     }

//     public List<SpotifyAlbum> getAlbums() {
//         return albums;
//     }

//     public void setAlbums(List<SpotifyAlbum> albums) {
//         this.albums = albums;
//     }

//     public List<SpotifyArtist> getArtists() {
//         return artists;
//     }

//     public void setArtists(List<SpotifyArtist> artists) {
//         this.artists = artists;
//     }

//     public List<SpotifyTrack> getTracks() {
//         return tracks;
//     }

//     public void setTracks(List<SpotifyTrack> tracks) {
//         this.tracks = tracks;
//     }


// public static SpotifySearchResponse create(JsonObject jo, String objectType) {

//     SpotifySearchResponse searchResponse = new SpotifySearchResponse(jo);

//     // Parse the JSON object and populate the response object based on the objectType parameter
//     if (objectType.equals("albums")) {
//         JsonArray albumsJson = jo.getJsonObject("albums").getJsonArray("items");
//         List<SpotifyAlbum> albums = new ArrayList<>();
//         for (int i = 0; i < albumsJson.size(); i++) {
//             JsonObject albumJson = albumsJson.getJsonObject(i);
//             SpotifyAlbum album = SpotifyAlbum.create(albumJson);
//             albums.add(album);

//         }
//         searchResponse.setAlbums(albums);
//     } else if (objectType.equals("artists")) {
//         JsonArray artistsJson = jo.getJsonObject("artists").getJsonArray("items");
//         List<SpotifyArtist> artists = new ArrayList<>();
//         for (int i = 0; i < artistsJson.size(); i++) {
//             JsonObject artistJson = artistsJson.getJsonObject(i);
//             SpotifyArtist artist = SpotifyArtist.create(artistJson);
//             artists.add(artist);
//         }
//         searchResponse.setArtists(artists);
//     } else if (objectType.equals("tracks")) {
//         JsonArray tracksJson = jo.getJsonObject("tracks").getJsonArray("items");
//         List<SpotifyTrack> tracks = new ArrayList<>();
//         for (int i = 0; i < tracksJson.size(); i++) {
//             JsonObject trackJson = tracksJson.getJsonObject(i);
//             SpotifyTrack track = SpotifyTrack.create(trackJson);
//             tracks.add(track);
//         }
//         searchResponse.setTracks(tracks);
//     } else {
//         // Invalid objectType parameter
//         throw new IllegalArgumentException("Invalid objectType parameter");
//     }

//     return searchResponse;
// }

//     public static SpotifySearchResponse create(JsonObject jo) {

//     SpotifySearchResponse searchResponse = new SpotifySearchResponse(jo);
    
//     // Parse the JSON object and populate the response object
//     JsonArray albumsJson = jo.getJsonArray("albums");
//     List<SpotifyAlbum> albums = new ArrayList<>();
//     for (int i = 0; i < albumsJson.size(); i++) {
//         JsonObject albumJson = albumsJson.getJsonObject(i);
//         SpotifyAlbum album = SpotifyAlbum.create(albumJson);
//         albums.add(album);
//     }
//     searchResponse.setAlbums(albums);

//     JsonArray artistsJson = jo.getJsonArray("artists");
//     List<SpotifyArtist> artists = new ArrayList<>();
//     for (int i = 0; i < artistsJson.size(); i++) {
//         JsonObject artistJson = artistsJson.getJsonObject(i);
//         SpotifyArtist artist = SpotifyArtist.create(artistJson);
//         artists.add(artist);
//     }
//     searchResponse.setArtists(artists);

//     JsonArray tracksJson = jo.getJsonArray("tracks");
//     List<SpotifyTrack> tracks = new ArrayList<>();
//     for (int i = 0; i < tracksJson.size(); i++) {
//         JsonObject trackJson = tracksJson.getJsonObject(i);
//         SpotifyTrack track = SpotifyTrack.create(trackJson);
//         tracks.add(track);
//     }
//     searchResponse.setTracks(tracks);

//     return searchResponse;
//     }

    
//     public JsonObject toJson(){
    
//     JsonObjectBuilder builder = Json.createObjectBuilder();
    
//     // Add the albums field
//     JsonArrayBuilder albumsBuilder = Json.createArrayBuilder();
//     for (SpotifyAlbum album : albums) {
//         albumsBuilder.add(album.toJson());
//     }
//     builder.add("albums", albumsBuilder.build());
    
//     // Add the artists field
//     JsonArrayBuilder artistsBuilder = Json.createArrayBuilder();
//     for (SpotifyArtist artist : artists) {
//         artistsBuilder.add(artist.toJson());
//     }
//     builder.add("artists", artistsBuilder.build());
    
//     // Add the tracks field
//     JsonArrayBuilder tracksBuilder = Json.createArrayBuilder();
//     for (SpotifyTrack track : tracks) {
//         tracksBuilder.add(track.toJson());
//     }
//     builder.add("tracks", tracksBuilder.build());
    
//     return builder.build();
//     }

// }
