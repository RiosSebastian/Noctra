import Navbar from '@/components/Navbar';
import ContentCard from '@/components/ContentCard';
import type { Movie } from '@/lib/types';
import { API_URL } from '@/lib/api';

async function getAllMovies(): Promise<Movie[]> {
  const res = await fetch(`${API_URL}/api/movies`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudieron obtener las películas');
  return res.json();
}

export default async function PeliculasPage() {
  const movies = await getAllMovies();

  return (
    <div className="min-h-screen">
      <Navbar />

      <section className="px-8 pt-32 pb-10 md:px-12">
        <h1 className="font-display text-3xl font-bold mb-6">Películas</h1>

        {movies.length === 0 ? (
          <p className="text-ink-400">Todavía no hay películas cargadas.</p>
        ) : (
          <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
            {movies.map((m) => (
              <ContentCard key={m.id} content={m} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
}