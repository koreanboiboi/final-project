import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { HomeComponent } from './components/home.component';
import { LoginComponent } from './components/Login/login.component';
import { AlbumsComponent } from './components/SearchByType/albums.component';
import { ArtistsComponent } from './components/SearchByType/artists.component';
import { UserComponent } from './components/user/user.component';


const routes: Routes = [
  {path: '', component:LoginComponent},
  {path: 'artists', component:ArtistsComponent},
  {path: 'albums', component:AlbumsComponent},
  {path: 'home' , component: HomeComponent},
  {path: 'user' , component: UserComponent},
  {path: 'favorite' , component: FavoriteComponent},
  { path: '**', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
