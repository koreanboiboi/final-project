import { Component, OnInit } from '@angular/core';
import { SpotifyAlbums, SpotifyArtists, sqlAlbum, sqlArtist } from 'src/app/models';
import { SpotifyService } from 'src/app/spotify.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit{

  showArtist = false;
  showAlbum = false;

  userId: any
  constructor(private spotSvc: SpotifyService){}

  artist!: sqlArtist []
  album!: sqlAlbum []

  ngOnInit(): void {
      const userID = localStorage.getItem('user_id')
      this.userId = userID
  }

  // getArtist(){
  //     this.spotSvc.getArtist(this.user).subscribe(response => {
  //     console.log(this.user)
  //   })
  // }

  async getArtist(){
    this.showArtist = true;
    this.showAlbum = false;
    console.log("artist>>>",this.artist)
    try{
    const artistData = await this.spotSvc.getArtist(this.userId).toPromise()

    this.artist = artistData
    console.log("artistdata>>>",artistData)
    console.log("artist>>>",this.artist)
    } catch (error) {
      console.log(error)
    }
  }

  deleteArtist(removeById: any){

    this.spotSvc.deleteArtist(removeById).subscribe(response => {
      console.log("deleted",removeById)
      this.getArtist()
    })

  }

  async getAlbum(){
    this.showArtist = false;
    this.showAlbum = true;
    console.log("album USER ID",this.userId)
    try {
    const albumData = await this.spotSvc.getAlbum(this.userId).toPromise()

    this.album = albumData
    console.log("ALBUMDATA",albumData)
      
    } catch (error) {
      console.log(error)
    }
  }
  
  deleteAlbum(removeById: any){

    this.spotSvc.deleteAlbum(removeById).subscribe(response => {
      console.log("deleted",removeById)
      this.getAlbum()
    })

  }


  share() {
    const user = this.artist
    console.info('>>>> user: ', user)
    navigator.share({
      title: 'My awesome web app',
      text: 'Check out this cool content!',
      url: 'http://localhost:4200/',
      files: [new File(['hello'], 'hello.txt', { type: 'text/plain' })]
    })
    .then(result => {
      console.info('>>> share result: ', result)
    })
    .catch(err => {
      console.error('>>> share error: ', err)
    })
    /*
    this.webshare.share({
      title: user.name,
      text: `Email: ${user.email}, Phone: ${user.phone}`
    })
    .then(result => {
      console.info('>>> share result: ', result)
    })
    .catch(err => {
      console.error('>>> share error: ', err)
    })
    */
  }

}
