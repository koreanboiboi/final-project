import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable, Subject } from 'rxjs';
import { User } from './models';
// import { User } from 'spotify-api.js';


@Injectable({
  providedIn: 'root'
})
export class SpotifyService {
  
  private backend = 'http://localhost:8080'


  constructor(private http: HttpClient) { }

    saveUser(user:any){
      const body = {user}
      return this.http.post(`${this.backend}/api/saveUser`,body)
    }

    saveArtist(artist:any){
      const body = {artist}
      return this.http.post(`${this.backend}/api/saveArtist`,body)
    }

    saveAlbum(album:any){
      const body = {album}
      return this.http.post(`${this.backend}/api/saveAlbum`,body)
    }
      

    getArtist(userId:any){
      return this.http.get<any>(`${this.backend}/api/getArtist/${userId}`)
    }

    deleteArtist(deleteById: any){
      return this.http.delete<any>(`${this.backend}/api/deleteArtist/${deleteById}`)
    }

    getAlbum(userId: any){
      return this.http.get<any>(`${this.backend}/api/getAlbum/${userId}`)
    }

    
    deleteAlbum(deleteById: any){
      return this.http.delete<any>(`${this.backend}/api/deleteAlbum/${deleteById}`)
    }

    getBlob(){
      return this.http.get<any>(`${this.backend}/api/viewGallery`)
    }
      
}
