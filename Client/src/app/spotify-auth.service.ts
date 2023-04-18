import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class SpotifyAuthService {

  constructor(
    private http: HttpClient, 
    private router: Router, 
    private activatedRoute: ActivatedRoute) { }

  private client_id:string = '1987a0a7b4c546dfb0d35b687172b78d'
  private client_secret: string ='b1be59d2deba4e70840e874b1be791d2'
  private redirect_uri: string = 'http://localhost:4200/home'

  private state = this.generateRandomString(16);
  private scope = 'user-read-private&user-read-email&user-library-read&user-library-modify&user-top-read';
  private AUTHORIZE: string ='https://accounts.spotify.com/authorize'

  private refresh_token!: string
  private access_token!: string


// -----------------------------------------------SPOTIFY OAUTH--------------------------------------------------------------------------
  spotifyLogin(){
    let url = this.AUTHORIZE
      url += "?client_id=" + this.client_id;
      url += "&response_type=code";
      url += "&redirect_uri=" + encodeURI(this.redirect_uri);
      url += "&"+this.state;
      url += "&show_dialog=true";
      url += "&"+this.scope;
      
      window.location.href = url;
  
  }

  
  private generateRandomString(length: number) {
    let text = '';
    const possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

    for (let i = 0; i < length; i++) {
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    }

    return text;
  }


  requestAccessToken():any{
    this.activatedRoute.queryParams.subscribe(params => {
      let code = params['code'];
      let state = params['state'];

      if (state === null) {
        this.router.navigate(['/login'], { queryParams: { error: 'state_mismatch' }});
      } else {
        let headers = new HttpHeaders({
          'Authorization': 'Basic ' + btoa(this.client_id + ':' + this.client_secret),
          'Content-Type': 'application/x-www-form-urlencoded'
        });

        let body = new URLSearchParams();
        body.set('code', code);
        body.set('redirect_uri', this.redirect_uri);
        body.set('grant_type', 'authorization_code');

        this.http.post<any>('https://accounts.spotify.com/api/token', body.toString(), { headers }).subscribe(
          data => {
            // data contains my access token and refresh token
            console.log(data.access_token)
            this.access_token = data.access_token
            },
          error => {
            // Handle error
            console.error("Error: ", error)
          }
        );
      }
    });
    
    
  }

  refreshAccessToken() {
    let headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(this.client_id + ':' + this.client_secret),
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    let body = new URLSearchParams();
    body.set('grant_type', 'refresh_token');
    body.set('refresh_token', this.refresh_token);

    this.http.post<any>('https://accounts.spotify.com/api/token', body.toString(), { headers }).subscribe(
      data => {
        this.access_token = data.access_token;
        this.refresh_token = data.refresh_token
        // Handle success
        // console.log(this.access_token)
      },
      error => {
        // Handle error
        console.error(error)
      }
    );
  }

  // --------------------------------SPOTIFY OAUTH UNTIL HERE------------------------------------------------------------------


  //-------------------------------------------------URL HERE-----------------------------------------------------------------------------
  private userInfoURL = 'http://localhost:8080/api/user'
  // private searchURL = 'https://api.spotify.com/v1/search'

  // private apiUrl = 'http://localhost:8080/api/search';
  private searchArtistUrl = 'http://localhost:8080/api/artists';
  private searchAlbumUrl = 'http://localhost:8080/api/albums';
  private saveAlbumUrl = 'http://localhost:8080/api/albums/save'


  // --------------------------------API CALL----------------------------------------------------------------------------------
  
  getUserProfile(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.access_token
    });

    return this.http.get<any>(this.userInfoURL,{headers})
  }

  searchArtists(params: any) {

    // alert(JSON.stringify(params))
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.access_token
    });
    return this.http.get<any>(this.searchArtistUrl, {params, headers});
  }

  searchAlbum(params: any) {

    // alert(JSON.stringify(params))
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.access_token
    });
    return this.http.get<any>(this.searchAlbumUrl, {params, headers});
  }
//-----------------------------------------------------------------------------


//-----------------------------------------------------------------------------
// I cant call this method directly to spotify as I am only in dev mode and requries to get quote should i want to use it.
  saveAlbum(albumId: string){

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.access_token
    });
    const body = {
      albumId: albumId
    }
    // const url = this.saveAlbumUrl+`/${albumId}`
    this.http.put<any>(this.saveAlbumUrl,body, {headers}).subscribe(
      res => {
        console.log("Album saved!", res)
      },error => {
        console.log("Error saving album" , error)
      }
    );
  }
}

    // .subscribe(
    //   response => {
    //     console.log("Album saved successfully", response)
    //   },
    //   (error) =>{
    //     console.error("error >>> ", error)
    //   }
    // )

  //   const headers = new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     'Authorization': 'Bearer ' + this.access_token
  //   })
  //   return this.http.put<any>(this.saveAlbumUrl)
  // }

  // --------------------------------API CALL UNTIL HERE------------------------------------------------------------------------

