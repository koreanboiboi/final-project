package mini.project.Server.models;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class SpotifyTrack {
    private String id;
    private String name;
    private List<SpotifyArtist> artists;
    private String albumId;
    private String imageUrl;
    private int durationMs;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<SpotifyArtist> getArtists() {
        return artists;
    }
    public void setArtists(List<SpotifyArtist> artists) {
        this.artists = artists;
    }
    public String getAlbumId() {
        return albumId;
    }
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getDurationMs() {
        return durationMs;
    }
    public void setDurationMs(int durationMs) {
        this.durationMs = durationMs;
    }

    
    public static SpotifyTrack create(JsonObject jo){

        SpotifyTrack spotifyTrack = new SpotifyTrack();
        spotifyTrack.setId(jo.getString("id"));
        spotifyTrack.setName(jo.getString("name"));
        spotifyTrack.setAlbumId(jo.getString("albumId"));
        spotifyTrack.setImageUrl(jo.getString("imageUrl"));

        return spotifyTrack;
    }


    public JsonObject toJson(){

        return Json.createObjectBuilder()
                   .add("id",id)
                   .add("name",name)
                   .add("albumId",albumId)
                   .add("imageUrl",imageUrl)
                   .build();
                
    }

    
    
}
