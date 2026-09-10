'use client';

import type { Genre } from '@/lib/types';

export default function GenreFilterTags({
  genres,
  selectedId,
  onSelect,
}: {
  genres: Genre[];
  selectedId: number | null;
  onSelect: (id: number | null) => void;
}) {
  if (genres.length === 0) return null;

  return (
    <div className="flex flex-wrap gap-2">
      <button
        onClick={() => onSelect(null)}
        className={`rounded-full border px-4 py-1.5 text-sm font-medium transition-colors ${
          selectedId === null
            ? 'border-violet bg-violet text-ink-50'
            : 'border-noctra-border text-ink-400 hover:border-violet hover:text-ink-50'
        }`}
      >
        Todos
      </button>
      {genres.map((genre) => (
        <button
          key={genre.id}
          onClick={() => onSelect(genre.id)}
          className={`rounded-full border px-4 py-1.5 text-sm font-medium transition-colors ${
            selectedId === genre.id
              ? 'border-violet bg-violet text-ink-50'
              : 'border-noctra-border text-ink-400 hover:border-violet hover:text-ink-50'
          }`}
        >
          {genre.name}
        </button>
      ))}
    </div>
  );
}