'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/lib/auth-context';
import * as api from '@/lib/api';

export default function FavoriteToggleButton({
  contentId,
  size = 'md',
}: {
  contentId: number;
  size?: 'md' | 'lg';
}) {
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
        await api.removeFavorite(contentId);
        setIsFavorite(false);
      } else {
        await api.addFavorite(contentId);
        setIsFavorite(true);
      }
    } catch {
      // si falla, no cambiamos el estado visual
    } finally {
      setIsSaving(false);
    }
  }

  return (
    <button
      onClick={toggleFavorite}
      disabled={isSaving}
      aria-label={isFavorite ? 'Quitar de mi lista' : 'Agregar a mi lista'}
      className={`leading-none transition-colors disabled:opacity-50 ${
        size === 'lg' ? 'text-3xl' : 'text-lg'
      } ${isFavorite ? 'text-violet-glow' : 'text-ink-400 hover:text-violet-glow'}`}
    >
      {isFavorite ? '♥' : '♡'}
    </button>
  );
}