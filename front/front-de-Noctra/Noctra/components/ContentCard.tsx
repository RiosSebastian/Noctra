'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/lib/auth-context';
import * as api from '@/lib/api';

interface CardContent {
  id: number;
  title: string;
  genre: string;
}

export default function ContentCard({ content }: { content: CardContent }) {
  const { userId } = useAuth();
  const router = useRouter();
  const [isFavorite, setIsFavorite] = useState(false);
  const [isSaving, setIsSaving] = useState(false);

  async function toggleFavorite(e: React.MouseEvent) {
    e.preventDefault();

    if (!userId) {
      router.push('/login');
      return;
    }

    setIsSaving(true);
    try {
      if (isFavorite) {
        await api.removeFavorite(content.id);
        setIsFavorite(false);
      } else {
        await api.addFavorite(content.id);
        setIsFavorite(true);
      }
    } catch {
      // si falla, no cambiamos el estado visual
    } finally {
      setIsSaving(false);
    }
  }

  return (
    <div className="group relative overflow-hidden rounded-lg bg-noctra-surface border border-noctra-border transition-all duration-300 hover:-translate-y-1 hover:border-violet hover:shadow-glow">
      <div className="h-40 flex items-center justify-center bg-gradient-to-br from-violet-ink/40 to-noctra-surface2">
        <span className="px-3 text-center text-xs font-bold uppercase tracking-wide text-ink-50/90">
          {content.title}
        </span>
      </div>
      <div className="flex items-center justify-between p-3">
        <p className="text-sm font-medium text-ink-400 group-hover:text-violet-glow transition-colors">
          {content.genre}
        </p>
        <button
          onClick={toggleFavorite}
          disabled={isSaving}
          aria-label={isFavorite ? 'Quitar de mi lista' : 'Agregar a mi lista'}
          className={`text-lg leading-none transition-colors disabled:opacity-50 ${
            isFavorite ? 'text-violet-glow' : 'text-ink-400 hover:text-violet-glow'
          }`}
        >
          {isFavorite ? '♥' : '♡'}
        </button>
      </div>
    </div>
  );
}