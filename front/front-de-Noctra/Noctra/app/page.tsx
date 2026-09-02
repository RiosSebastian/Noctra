import Navbar from '@/components/Navbar';
import ContentCard from '@/components/ContentCard';
import TmdbMovieCard from '@/components/TmdbMovieCard';
import type { Content, TmdbMovie } from '@/lib/types';
import { API_URL, getPopularMovies } from '@/lib/api';

async function getAllContent(): Promise<Content[]> {
  const res = await fetch(`${API_URL}/api/content`, {
    cache: 'no-store',
  });
  if (!res.ok) throw new Error('No se pudo obtener el contenido');
  return res.json();
}

export default async function Home() {
  const movies = await getAllContent();

  // Si TMDB falla (token vencido, sin conexión, etc.) no queremos que se caiga
  // toda la home por eso - mostramos el resto igual y esta sección queda vacía.
  let popularMovies: TmdbMovie[] = [];
  try {
    popularMovies = await getPopularMovies();
  } catch {
    popularMovies = [];
  }

  return (
    <div className="min-h-screen">
      <Navbar />

      <section className="relative overflow-hidden bg-noctra-aurora pt-40 pb-24 px-8 md:px-12">
        <h1 className="font-display max-w-2xl text-4xl md:text-6xl font-extrabold leading-tight">
          Todo despierta cuando cae la noche.
        </h1>
        <p className="mt-4 max-w-md text-ink-400">
          El catálogo de Noctra, pensado para las horas en que el resto del mundo duerme.
        </p>
      </section>

      {popularMovies.length > 0 && (
        <section className="px-8 py-10 md:px-12">
          <h2 className="font-display text-2xl font-bold mb-6">Populares</h2>
          <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
            {popularMovies.map((movie) => (
              <TmdbMovieCard key={movie.id} movie={movie} />
            ))}
          </div>
        </section>
      )}

      <section className="px-8 py-10 md:px-12">
        <h2 className="font-display text-2xl font-bold mb-6">Contenido para ti</h2>
        <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
          {movies.map((movie) => (
            <ContentCard key={movie.id} content={movie} />
          ))}
        </div>
      </section>
    </div>
  );
}