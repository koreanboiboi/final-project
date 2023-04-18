import { Component } from '@angular/core';
import { SpotifyService } from 'src/app/spotify.service';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent {



  constructor( private spotSvc: SpotifyService){
  
   this.getBlob()
    
  }

  // async getBlob(){

  //   try {
  //     const data = await this.spotSvc.getBlob().toPromise();
  //     console.log("data string>>>>>>",JSON.stringify(data))
  //     this.blobData = data;

  //   } catch (error) {
  //     console.error(error);
  //   }
  // }

  getBlob(){

  fetch('http://localhost:8080/api/viewGallery')
  .then(response => response.blob())
  .then(blob => {
    
    console.log('Download success:', blob);
    // do something with the blob data, like displaying an image
    const img = document.createElement('img');
    img.src = URL.createObjectURL(blob);
    // document.body.appendChild(img);
  const container = document.getElementById('image-container');
  container?.appendChild(img);
  
})
  .catch(error => {
    console.error('Download error:', error);
  });
  }

}
