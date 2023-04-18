import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models';
import { SpotifyAuthService } from 'src/app/spotify-auth.service';
import { SpotifyService } from 'src/app/spotify.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {


  constructor(private spotAuthSvc: SpotifyAuthService, private fb: FormBuilder, private spotSvc: SpotifyService, private router: Router){

    this.spotAuthSvc.requestAccessToken();

    this.getUserProfile();
  
  }

  user!: User []
  userForm!: FormGroup
  loggedin = false;


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
