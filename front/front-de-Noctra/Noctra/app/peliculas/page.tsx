import Navbar from '@/components/Navbar';
import MoviesBrowser from '@/components/MoviesBrowser';
import type { Movie, TmdbMovie, Genre } from '@/lib/types';
import { API_URL, getPopularMovies, getMovieGenres } from '@/lib/api';

async function getAllMovies(): Promise<Movie[]> {
  const res = await fetch(`${API_URL}/api/movies`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudieron obtener las películas');
  return res.json();
}

export default async function PeliculasPage() {
  const movies = await getAllMovies();

  let popularMovies: TmdbMovie[] = [];
  try {
    popularMovies = await getPopularMovies();
  } catch {
    popularMovies = [];
  }

  let genres: Genre[] = [];
  try {
    genres = await getMovieGenres();
  } catch {
    genres = [];
  }

  return (
    <div className="min-h-screen">
      <Navbar />
      <MoviesBrowser ownMovies={movies} initialPopularMovies={popularMovies} genres={genres} />
    </div>
  );
}