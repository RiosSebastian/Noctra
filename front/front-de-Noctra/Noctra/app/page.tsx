import Navbar from '@/components/Navbar';
import HomeBrowser from '@/components/HomeBrowser';
import HeroCarousel, { CarouselSlide } from '@/components/HeroCarousel';
import type { Content, Genre, TmdbMovie, TmdbSeries } from '@/lib/types';
import { API_URL, getPopularMovies, getPopularSeries, getMovieGenres } from '@/lib/api';

async function getAllContent(): Promise<Content[]> {
  const res = await fetch(`${API_URL}/api/content`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudo obtener el contenido');
  return res.json();
}

function buildSlides(movies: TmdbMovie[], series: TmdbSeries[]): CarouselSlide[] {
  const movieSlides: CarouselSlide[] = movies
    .filter((m) => m.backdropUrl)
    .slice(0, 3)
    .map((m) => ({
      id: m.id,
      title: m.title,
      overview: m.overview,
      backdropUrl: m.backdropUrl,
      badge: 'Película' as const,
      year: m.releaseDate ? m.releaseDate.slice(0, 4) : null,
      href: `/tmdb/${m.id}`,
    }));

  const seriesSlides: CarouselSlide[] = series
    .filter((s) => s.backdropUrl)
    .slice(0, 3)
    .map((s) => ({
      id: s.id,
      title: s.title,
      overview: s.overview,
      backdropUrl: s.backdropUrl,
      badge: 'Serie' as const,
      year: s.firstAirDate ? s.firstAirDate.slice(0, 4) : null,
      href: `/tmdb-serie/${s.id}`,
    }));

  const slides: CarouselSlide[] = [];
  const max = Math.max(movieSlides.length, seriesSlides.length);
  for (let i = 0; i < max; i++) {
    if (movieSlides[i]) slides.push(movieSlides[i]);
    if (seriesSlides[i]) slides.push(seriesSlides[i]);
  }
  return slides;
}

export default async function Home() {
  const movies = await getAllContent();

  let popularMovies: TmdbMovie[] = [];
  let popularSeries: TmdbSeries[] = [];
  try {
    popularMovies = await getPopularMovies();
  } catch {
    popularMovies = [];
  }
  try {
    popularSeries = await getPopularSeries();
  } catch {
    popularSeries = [];
  }

  let genres: Genre[] = [];
  try {
    genres = await getMovieGenres();
  } catch {
    genres = [];
  }

  const slides = buildSlides(popularMovies, popularSeries);

  return (
    <div className="min-h-screen">
      <Navbar />
      <HeroCarousel slides={slides} />

      <section className="relative overflow-hidden bg-noctra-aurora pt-16 pb-16 px-8 md:px-12">
        <h1 className="font-display max-w-2xl text-4xl md:text-6xl font-extrabold leading-tight">
          Todo despierta cuando cae la noche.
        </h1>
        <p className="mt-4 max-w-md text-ink-400">
          El catálogo de Noctra, pensado para las horas en que el resto del mundo duerme.
        </p>
      </section>

      <HomeBrowser ownContent={movies} initialPopularMovies={popularMovies} genres={genres} />
    </div>
  );
}