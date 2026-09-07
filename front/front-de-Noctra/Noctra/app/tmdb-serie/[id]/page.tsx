import { notFound } from 'next/navigation';
import Navbar from '@/components/Navbar';
import { getTmdbSeriesById } from '@/lib/api';

export default async function TmdbSeriesDetailPage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const { id } = await params;

  let series;
  try {
    series = await getTmdbSeriesById(Number(id));
  } catch {
    notFound();
  }

  return (
    <div className="min-h-screen">
      <Navbar />
      <section className="relative pt-24">
        {series.backdropUrl && (
          <div
            className="absolute inset-0 h-[420px] bg-cover bg-center opacity-30"
            style={{ backgroundImage: `url(${series.backdropUrl})` }}
          />
        )}
        <div className="relative mx-auto flex max-w-5xl flex-col gap-8 px-8 py-10 md:flex-row md:px-12">
          <div className="w-full max-w-[280px] flex-shrink-0 overflow-hidden rounded-lg border border-noctra-border bg-noctra-surface2">
            {series.posterUrl ? (
              <img src={series.posterUrl} alt={series.title} className="w-full" />
            ) : (
              <div className="flex aspect-[2/3] items-center justify-center px-3 text-center text-sm font-bold uppercase text-ink-50/90">
                {series.title}
              </div>
            )}
          </div>
          <div className="flex-1">
            <h1 className="font-display text-3xl font-bold md:text-4xl">{series.title}</h1>
            <div className="mt-3 flex flex-wrap items-center gap-3 text-sm text-ink-400">
              {series.firstAirDate && <span>{series.firstAirDate.slice(0, 4)}</span>}
              {series.genre && <span>· {series.genre}</span>}
              {series.rating != null && <span>· ★ {series.rating.toFixed(1)}</span>}
            </div>
            {series.overview && (
              <p className="mt-6 max-w-2xl leading-relaxed text-ink-50/90">{series.overview}</p>
            )}
          </div>
        </div>
      </section>
    </div>
  );
}