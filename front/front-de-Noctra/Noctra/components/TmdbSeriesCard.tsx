import Link from 'next/link';
import type { TmdbSeries } from '@/lib/types';

export default function TmdbSeriesCard({ series }: { series: TmdbSeries }) {
  return (
    <Link
      href={`/tmdb-serie/${series.id}`}
      className="group relative block overflow-hidden rounded-lg bg-noctra-surface border border-noctra-border transition-all duration-300 hover:-translate-y-1 hover:border-violet hover:shadow-glow"
    >
      <div className="h-56 bg-noctra-surface2">
        {series.posterUrl ? (
          <img src={series.posterUrl} alt={series.title} className="h-full w-full object-cover" />
        ) : (
          <div className="flex h-full items-center justify-center px-3 text-center text-xs font-bold uppercase tracking-wide text-ink-50/90">
            {series.title}
          </div>
        )}
      </div>
      <div className="p-3">
        <p className="truncate text-sm font-medium text-ink-50 group-hover:text-violet-glow transition-colors">
          {series.title}
        </p>
        {series.rating != null && <p className="text-xs text-ink-400">★ {series.rating.toFixed(1)}</p>}
      </div>
    </Link>
  );
}