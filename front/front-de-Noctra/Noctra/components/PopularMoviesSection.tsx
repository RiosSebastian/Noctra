'use client';

import { useState } from 'react';
import TmdbMovieCard from '@/components/TmdbMovieCard';
import { getPopularMovies } from '@/lib/api';
import type { TmdbMovie } from '@/lib/types';

export default function PopularMoviesSection({
  initialMovies,
  title = 'Populares',
}: {
  initialMovies: TmdbMovie[];
  title?: string;
}) {
  const [movies, setMovies] = useState(initialMovies);
  const [page, setPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);

  async function loadMore() {
    setIsLoading(true);
    try {
      const next = await getPopularMovies(page + 1);
      if (next.length === 0) {
        setHasMore(false);
      } else {
        setMovies((prev) => [...prev, ...next]);
        setPage((p) => p + 1);
      }
    } catch {
      setHasMore(false);
    } finally {
      setIsLoading(false);
    }
  }

  if (movies.length === 0) return null;

  return (
    <section className="px-8 py-10 md:px-12">
      <h2 className="font-display text-2xl font-bold mb-6">{title}</h2>
      <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
        {movies.map((movie) => (
          <TmdbMovieCard key={movie.id} movie={movie} />
        ))}
      </div>

      {hasMore && (
        <div className="mt-8 flex justify-center">
          <button
            onClick={loadMore}
            disabled={isLoading}
            className="rounded-full border border-noctra-border px-6 py-2.5 text-sm font-semibold text-ink-50 hover:border-violet transition-colors disabled:opacity-50"
          >
            {isLoading ? 'Cargando...' : 'Ver más'}
          </button>
        </div>
      )}
    </section>
  );
}