import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpotifyAuthService } from '../spotify-auth.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';

import {User} from '../models' 
import { Router } from '@angular/router';
import { SpotifyService } from '../spotify.service';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private spotAuthSvc: SpotifyAuthService, private fb: FormBuilder, private spotSvc: SpotifyService, private router: Router){

    this.spotAuthSvc.requestAccessToken();

    this.getUserProfile();
  
  }

  user!: User []
  userForm!: FormGroup
  loggedin = false;

  // users:any

  ngOnInit(): void {
    
    
  }

  async getUserProfile(){
    try {
      const data = await this.spotAuthSvc.getUserProfile().toPromise();
      this.user = data;
      console.log("DATA",data)
      console.log("ID>>>" , data[0].id)
      const userId = data[0].id
      localStorage.setItem("user_id",userId)

      // const userData = {
      //   "unique_user_id": this,
      //   "name": this.user
      // };
      // console.log("USERDATA",userData)

      this.spotSvc.saveUser(data).subscribe(response => {
        alert("Saved Successfully")
      });
  
    } catch (error) {
      console.error(error);
    }
  }

//  async getUserProfile(){
//   try {
//     const data = await this.spotAuthSvc.getUserProfile().toPromise();
//     this.user= data;
    
//     const userData = {
//       "unique_user_id": this.user,
//       "name": this.users.display_name
//   };
  

//   console.log("user id >>>> ", this.users.id)
//   console.log("user name >>>> ", this.users.display_name)

//   this.spotSvc.saveUser(userData).subscribe(response => {
//       alert("Saved Successfully")
//   })

//   } catch (error) {
//     console.error(error);
//   }

  
//  }

 

  // getUserInfo(){
  //   this.spotAuthSvc.getUserInfo().subscribe(
  //     data => {
  //       console.log(data);
  //       this.user = data;
  //       console.log('id>>>>>',data.id)
  //       console.log('name>>>',data.display_name)
  //       console.log('external url>>>>>',data.external_urls.spotify)
  //       console.log('image url>>>>>',data.images[0].url)
  //       console.log('followers>>>>>',data.followers.total)

  //       this.userForm= this.fb.group({
  //         id: this.fb.control<string>(data.id),
  //         display_name: this.fb.control<string>(data.display_name),
  //         external_urls: this.fb.control<string>(data.external_urls.spotify),
  //         images: this.fb.control<string>(data.images[0].url),
  //         followers: this.fb.control<string>(data.followers.total),

  //       })
  //       //bind
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //   )

  // }

  // postUserInfo(){
   

  //   const userFormData = this.userForm.value

  //   this.spotSvc.postUserInfo(userFormData)
  //     .then(response => {
  //       if (response.message === 'User profile saved'){
  //         this.router.navigate(['/playlist'])
  //       }
  //       else {
  //         alert("something went wrong")
  //       }
  //     })
  //     .catch(error => {
  //       console.log(error)
  //       // alert(error.error)
  //     })
  // }

  



  

}
