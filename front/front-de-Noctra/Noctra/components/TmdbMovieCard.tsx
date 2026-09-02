import type { TmdbMovie } from '@/lib/types';

export default function TmdbMovieCard({ movie }: { movie: TmdbMovie }) {
  return (
    <div className="group relative overflow-hidden rounded-lg bg-noctra-surface border border-noctra-border transition-all duration-300 hover:-translate-y-1 hover:border-violet hover:shadow-glow">
      <div className="h-56 bg-noctra-surface2">
        {movie.posterUrl ? (
          // eslint-disable-next-line @next/next/no-img-element
          <img
            src={movie.posterUrl}
            alt={movie.title}
            className="h-full w-full object-cover"
          />
        ) : (
          <div className="flex h-full items-center justify-center px-3 text-center text-xs font-bold uppercase tracking-wide text-ink-50/90">
            {movie.title}
          </div>
        )}
      </div>
      <div className="p-3">
        <p className="truncate text-sm font-medium text-ink-50 group-hover:text-violet-glow transition-colors">
          {movie.title}
        </p>
        {movie.rating != null && (
          <p className="text-xs text-ink-400">★ {movie.rating.toFixed(1)}</p>
        )}
      </div>
    </div>
  );
}