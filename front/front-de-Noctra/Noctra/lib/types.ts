export interface Content {
  id: number;
  title: string;
  description: string;
  genre: string;
}

export interface Movie {
  id: number;
  title: string;
  genre: string;
  duration: number;
}

export interface Series {
  id: number;
  title: string;
  genre: string;
}

export interface Favorite {
  id: number;
  contentId: number;
  title: string;
}

export interface TmdbMovie {
  id: number;
  title: string;
  genre: string | null;
  duration: number | null;
  overview: string | null;
  posterUrl: string | null;
  backdropUrl: string | null;
  releaseDate: string | null;
  rating: number | null;
}

export interface TmdbSeries {
  id: number;
  title: string;
  genre: string | null;
  overview: string | null;
  posterUrl: string | null;
  backdropUrl: string | null;
  firstAirDate: string | null;
  rating: number | null;
}

export interface Genre {
  id: number;
  name: string;
}