import { Component, OnInit, Output } from '@angular/core';

import { SpotifyAuthService } from './spotify-auth.service';
import { SpotifyService } from './spotify.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  user:any = '31n5a7ehido2unabq44nhnsjv734'

  // localStorage.setItem('user_id','31n5a7ehido2unabq44nhnsjv734')

  ngOnInit(): void {
    this.spotAuthSvc.requestAccessToken();
    this.getUserProfile()
    localStorage.setItem('user_id',this.user)
  }

  constructor(private spotAuthSvc: SpotifyAuthService, private spotSvc: SpotifyService){}


  paypalMe(){
    window.open('https://www.paypal.me/koreanboiboi','_blank')
  }


  async getUserProfile(){
    try {
      const data = await this.spotAuthSvc.getUserProfile().toPromise();
      this.user = data;
      console.log("DATA",data)
      console.log("ID>>>" , data[0].id)
      const userId = data[0].id
      localStorage.setItem("user_id",userId)

      this.spotSvc.saveUser(data).subscribe(response => {
        alert("Saved Successfully")
      });
  
    } catch (error) {
      console.error(error);
    }
  }


}
