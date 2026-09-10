import Navbar from '@/components/Navbar';
import ContentCard from '@/components/ContentCard';
import TmdbMovieCard from '@/components/TmdbMovieCard';
import TmdbSeriesCard from '@/components/TmdbSeriesCard';
import { searchContent, searchTmdbMovies, searchTmdbSeries } from '@/lib/api';

export default async function BuscarPage({
  searchParams,
}: {
  searchParams: Promise<{ q?: string }>;
}) {
  const { q } = await searchParams;
  const query = q?.trim() ?? '';

  if (!query) {
    return (
      <div className="min-h-screen">
        <Navbar />
        <section className="px-8 pt-32 pb-10 md:px-12">
          <p className="text-ink-400">Escribí algo para buscar.</p>
        </section>
      </div>
    );
  }

  const [ownResults, tmdbMovieResults, tmdbSeriesResults] = await Promise.all([
    searchContent(query).catch(() => []),
    searchTmdbMovies(query).catch(() => []),
    searchTmdbSeries(query).catch(() => []),
  ]);

  const hasResults =
    ownResults.length > 0 || tmdbMovieResults.length > 0 || tmdbSeriesResults.length > 0;

  return (
    <div className="min-h-screen">
      <Navbar />
      <section className="px-8 pt-32 pb-10 md:px-12">
        <h1 className="font-display text-2xl font-bold mb-8">
          Resultados para &ldquo;{query}&rdquo;
        </h1>

        {!hasResults && <p className="text-ink-400">No encontramos nada con ese nombre.</p>}

        {ownResults.length > 0 && (
          <div className="mb-10">
            <h2 className="font-display text-lg font-semibold mb-4 text-ink-400">En Noctra</h2>
            <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
              {ownResults.map((item) => (
                <ContentCard key={item.id} content={item} />
              ))}
            </div>
          </div>
        )}

        {tmdbMovieResults.length > 0 && (
          <div className="mb-10">
            <h2 className="font-display text-lg font-semibold mb-4 text-ink-400">Películas en TMDB</h2>
            <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
              {tmdbMovieResults.map((movie) => (
                <TmdbMovieCard key={movie.id} movie={movie} />
              ))}
            </div>
          </div>
        )}

        {tmdbSeriesResults.length > 0 && (
          <div>
            <h2 className="font-display text-lg font-semibold mb-4 text-ink-400">Series en TMDB</h2>
            <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
              {tmdbSeriesResults.map((s) => (
                <TmdbSeriesCard key={s.id} series={s} />
              ))}
            </div>
          </div>
        )}
      </section>
    </div>
  );
}