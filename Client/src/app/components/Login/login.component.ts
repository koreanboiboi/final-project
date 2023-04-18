import { Component, OnInit } from '@angular/core';
import { SpotifyAuthService } from 'src/app/spotify-auth.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 
  constructor (private spotSvc: SpotifyAuthService){}

  ngOnInit(): void {

   
      
  }

  login(){
    this.spotSvc.spotifyLogin();
    // this.spotSvc.getUserProfile()
  }
 
}


