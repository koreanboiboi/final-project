package mini.project.Server.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import mini.project.Server.models.Album;
import mini.project.Server.models.Artist;
import mini.project.Server.models.Gallery;
import mini.project.Server.repositories.SpotifyRepository;
import mini.project.Server.services.SpotifyService;


@RestController
@RequestMapping("/api")
public class SpotifyRestController {

    @Autowired
    private SpotifyService spotSvc;
    private static final String SAVEALBUMURL ="https://api.spotify.com/v1/me/albums";

    @Autowired 
    private SpotifyRepository spotRepo;

    @Autowired
    private JdbcTemplate temp;

    

// // save album controller
// @PutMapping(path = "/albums/save/{ids}")
// @ResponseBody
// public ResponseEntity<String> saveAlbum(@RequestBody Map<String,String> albumId,@RequestHeader Map<String,String> headers){

//     System.out.println("ID >>>>>>>>>>>>>" + albumId );
//     System.out.println("HEADERS >>>>>>>>> " +headers);

   
//     String albumIds = albumId.get("albumId");
//     System.out.println("IDS>>>>>>>" +albumIds);

    
//      spotSvc.saveAlbum(albumIds,headers);



//      return ResponseEntity.ok("Album saved successfully!");
    
// }

//SAVE USER
@PostMapping("/saveUser")
@ResponseBody
public ResponseEntity<String> saveUser(@RequestBody Map<String,Object> userData){

System.out.println("userData" + userData);
spotRepo.saveUser(userData);

return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved User to Mysql Database");

}

//SAVE ARTIST
@PostMapping("/saveArtist")
@ResponseBody
public ResponseEntity<String> saveArtist(@RequestBody Map<String,Object> artistsData){

// String artData = artistsData.toString().substring(8).replace("}}", "}");
System.out.println("artistData" + artistsData);
spotRepo.saveArtist(artistsData);

return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved Artist to Mysql Database");

}

//SAVE ALBUM
@PostMapping("/saveAlbum")
@ResponseBody
public ResponseEntity<String> saveAlbum(@RequestBody Map<String,Object> albumData){

System.out.println("albumData" + albumData);
spotRepo.saveAlbum(albumData);

return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved Album to Mysql Database");

}
//--------------------------------------------------------MYSQL------------------------------------------------------------
//get list of artist from mysql

@GetMapping("/getArtist/{userId}")
public List<Artist> getArtist(@PathVariable String userId ) {
  
   
  List<Artist> artist =  spotRepo.getArtist(userId);

  return artist;
    
    // return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Saved Album to Mysql Database");

}

@DeleteMapping("/deleteArtist/{artistId}")
public void deleteArtist(@PathVariable String artistId) {

    spotRepo.deleteArtist(artistId);
}

@GetMapping("/getAlbum/{userId}")
public List<Album> getAlbum(@PathVariable String userId) {
    List<Album> album = spotRepo.getAlbum(userId);

    return album;
}

@DeleteMapping("/deleteAlbum/{albumId}")
public void deleteAlbum(@PathVariable String albumId) {

    spotRepo.deleteAlbum(albumId);
}

// -----------------------------------------post blob----------------------------------------------------------------------
@PostMapping("/gallery")
public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){

    try {
        byte[] bytes = file.getBytes();
        spotRepo.upload(bytes);
        return ResponseEntity.ok().body("File uploaded successfully");
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }

}
// @PostMapping("/gallery")
// public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
//                                      @RequestParam("title") String title,
//                                      @RequestParam("description") String description) {

//     try {
//         byte[] bytes = file.getBytes();
//         Gallery gallery = new Gallery();
//         gallery.setTitle(title);
//         gallery.setDescription(description);
//         gallery.setImage(bytes);
//         System.out.println("gallery>>>>>>>>>>>>>>>>>"+gallery);

//         return ResponseEntity.ok().body("File uploaded successfully");
//     } catch (IOException e) {
//         e.printStackTrace();
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
//     }

// }

// @GetMapping("/viewGallery")
// public List<Gallery> getAllGallery() {
//     String query = "SELECT * FROM gallery";
//     List<Gallery> galleryList = new ArrayList<>();
//     List<Map<String, Object>> rows = temp.queryForList(query);
//     for (Map<String, Object> row : rows) {
//         Gallery gallery = new Gallery();
//         gallery.setId((int) row.get("id"));
//         gallery.setTitle((String) row.get("title"));
//         gallery.setDescription((String) row.get("description"));
//         gallery.setUrl((String) row.get("url"));
//         galleryList.add(gallery);
//     }
//     return galleryList;
// }



@GetMapping("/viewGallery")
public byte[] getBlob(){

    byte[] blobData = spotRepo.getBlob();

    return blobData;

}
// @GetMapping("/viewGallery")
// public StreamingResponseBody getBlob(HttpServletResponse response){

//     byte[] blobData = spotRepo.getBlob();

//     response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//     response.setContentLength(blobData.length);

//     return outputStream -> {
//         try {
//             outputStream.write(blobData);
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }
//     };

// }


// @GetMapping("/viewGallery")
// public ResponseEntity<byte[]> getBlob() {

//     // spotRepo.getBlob();
//     byte[] data = temp.queryForObject("select * from gallery", new Object[]{}, byte[].class);
    
//     HttpHeaders headers = new HttpHeaders();
//     headers.setContentType(MediaType.IMAGE_JPEG);
//     headers.setContentLength(data.length);

//     return new ResponseEntity<>(data, headers, HttpStatus.OK);
// }
    
}
    

