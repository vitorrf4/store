import {Component} from '@angular/core';
import {SearchService} from "../services/search.service";
import {Movie} from "../models/movie";
import {StoreService} from "../services/store.service";

@Component({
  selector: 'app-search-movie',
  templateUrl: './search-movie.component.html',
  styleUrls: ['./search-movie.component.css']
})
export class SearchMovieComponent {
  movies! : Movie[];
  query : String = "";

  constructor(private searchService: SearchService,
              private storeService: StoreService) { }


  public searchMovie() {
    this.searchService.searchMovie(this.query).subscribe(res => {
      this.movies = res;
    });
  }

  public restockMovies() {
    let moviesToAdd : Movie[] = [];

    for (let movie of this.movies) {
      if (movie.copiesAmount > 0)
        moviesToAdd.push(movie)
    }

    this.storeService.restockMovies(moviesToAdd).subscribe(() => {
      this.storeService.getStoreInformation();
    });

  }
}
