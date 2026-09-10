import Navbar from '@/components/Navbar';
import SeriesBrowser from '@/components/SeriesBrowser';
import type { Series, TmdbSeries, Genre } from '@/lib/types';
import { API_URL, getPopularSeries, getSeriesGenres } from '@/lib/api';

async function getAllSeries(): Promise<Series[]> {
  const res = await fetch(`${API_URL}/api/series`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudieron obtener las series');
  return res.json();
}

export default async function SeriesPage() {
  const series = await getAllSeries();

  let popularSeries: TmdbSeries[] = [];
  try {
    popularSeries = await getPopularSeries();
  } catch {
    popularSeries = [];
  }

  let genres: Genre[] = [];
  try {
    genres = await getSeriesGenres();
  } catch {
    genres = [];
  }

  return (
    <div className="min-h-screen">
      <Navbar />
      <SeriesBrowser ownSeries={series} initialPopularSeries={popularSeries} genres={genres} />
    </div>
  );
}