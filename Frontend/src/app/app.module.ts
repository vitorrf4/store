import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './components/home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AddusersComponent } from './components/add-user/add-user.component';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { FormsModule } from '@angular/forms';
import { UserService } from './services/user.service';
import { SaleComponent } from './components/sale/sale-component';
import { SearchMovieComponent } from './components/search-movie/search-movie.component';
import { NgOptimizedImage} from "@angular/common";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule} from "@angular/material/card";
import { StoreComponent } from './components/store/store.component';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { StockComponent } from './components/stock/stock.component';
import { LoginComponent } from './components/login/login.component';
import { IndexComponent } from './components/index/index.component';
import {BasicAuthInterceptor } from "./helpers/http-request.interceptor";
import { ErrorInterceptor } from "./helpers/error.interceptor";


@NgModule({
  declarations: [
    HomeComponent,
    ListUsersComponent,
    AddusersComponent,
    SaleComponent,
    SearchMovieComponent,
    StoreComponent,
    StockComponent,
    LoginComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgOptimizedImage,
    BrowserAnimationsModule,
    MatCardModule,
    ScrollingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },],
  bootstrap: [IndexComponent]
})
export class AppModule { }
