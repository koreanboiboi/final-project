import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlbumsComponent } from './components/SearchByType/albums.component';
import { ArtistsComponent } from './components/SearchByType/artists.component';
import { HomeComponent } from './components/home.component';
import { SpotifyAuthService } from './spotify-auth.service';
import { SpotifyService } from './spotify.service';
import { LoginComponent } from './components/Login/login.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { UserComponent } from './components/user/user.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ArtistsComponent,
    AlbumsComponent,
    FavoriteComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  
    
  ],
  providers: [SpotifyAuthService,SpotifyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
