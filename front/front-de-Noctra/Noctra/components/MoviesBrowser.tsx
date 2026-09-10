'use client';

import { useEffect, useState } from 'react';
import ContentCard from '@/components/ContentCard';
import TmdbMovieCard from '@/components/TmdbMovieCard';
import GenreFilterTags from '@/components/GenreFilterTags';
import { discoverMoviesByGenre, getPopularMovies } from '@/lib/api';
import type { Genre, Movie, TmdbMovie } from '@/lib/types';

export default function MoviesBrowser({
  ownMovies,
  initialPopularMovies,
  genres,
}: {
  ownMovies: Movie[];
  initialPopularMovies: TmdbMovie[];
  genres: Genre[];
}) {
  const [selectedGenreId, setSelectedGenreId] = useState<number | null>(null);
  const [tmdbMovies, setTmdbMovies] = useState<TmdbMovie[]>(initialPopularMovies);
  const [page, setPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);

  const selectedGenre = genres.find((g) => g.id === selectedGenreId) ?? null;

  useEffect(() => {
    let cancelled = false;

    async function load() {
      setIsLoading(true);
      try {
        const results = selectedGenreId
          ? await discoverMoviesByGenre(selectedGenreId, 1)
          : await getPopularMovies(1);
        if (!cancelled) {
          setTmdbMovies(results);
          setPage(1);
          setHasMore(results.length > 0);
        }
      } catch {
        if (!cancelled) {
          setTmdbMovies([]);
          setHasMore(false);
        }
      } finally {
        if (!cancelled) setIsLoading(false);
      }
    }

    if (selectedGenreId !== null) load();
    else {
      setTmdbMovies(initialPopularMovies);
      setPage(1);
      setHasMore(true);
    }

    return () => {
      cancelled = true;
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedGenreId]);

  async function loadMore() {
    setIsLoading(true);
    try {
      const next = selectedGenreId
        ? await discoverMoviesByGenre(selectedGenreId, page + 1)
        : await getPopularMovies(page + 1);
      if (next.length === 0) {
        setHasMore(false);
      } else {
        setTmdbMovies((prev) => [...prev, ...next]);
        setPage((p) => p + 1);
      }
    } catch {
      setHasMore(false);
    } finally {
      setIsLoading(false);
    }
  }

  const filteredOwnMovies = selectedGenre
    ? ownMovies.filter((m) => m.genre?.toLowerCase().includes(selectedGenre.name.toLowerCase()))
    : ownMovies;

  return (
    <div>
      <section className="px-8 pt-32 md:px-12">
        <h1 className="font-display text-3xl font-bold mb-6">Películas</h1>
        <GenreFilterTags genres={genres} selectedId={selectedGenreId} onSelect={setSelectedGenreId} />
      </section>

      <section className="px-8 pt-8 pb-10 md:px-12">
        {filteredOwnMovies.length === 0 ? (
          <p className="text-ink-400">No hay películas propias en esta categoría.</p>
        ) : (
          <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
            {filteredOwnMovies.map((m) => (
              <ContentCard key={m.id} content={m} />
            ))}
          </div>
        )}
      </section>

      <section className="px-8 pb-10 md:px-12">
        <h2 className="font-display text-2xl font-bold mb-6">
          {selectedGenre ? `${selectedGenre.name} en TMDB` : 'Populares en TMDB'}
        </h2>

        {tmdbMovies.length === 0 ? (
          <p className="text-ink-400">
            {isLoading ? 'Cargando...' : 'No encontramos nada en esta categoría.'}
          </p>
        ) : (
          <>
            <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
              {tmdbMovies.map((movie) => (
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
          </>
        )}
      </section>
    </div>
  );
}