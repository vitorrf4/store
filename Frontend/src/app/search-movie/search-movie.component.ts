import {Component, Input} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {SearchService} from "../services/search.service";
import {Movie} from "../models/movie";

@Component({
  selector: 'app-search-movie',
  templateUrl: './search-movie.component.html',
  styleUrls: ['./search-movie.component.css']
})
export class SearchMovieComponent {
  @Input() movies! : BehaviorSubject<Movie[]>;
  query : String = "";

  constructor(private service: SearchService) { }

  public searchMovie() {
    this.service.searchMovie(this.query);
    this.movies = this.service.movies;
  }

  public restockMovies() {
    let moviesToAdd : Movie[] = [];

    for (let movie of this.movies.value) {
      if (movie.copiesAmount > 0){
        console.log(`movie: ${movie.movieTitle} | copies: ${movie.copiesAmount}`);
        moviesToAdd.push(movie)
      }
    }
    this.service.restockMovies(moviesToAdd);
  }
}
