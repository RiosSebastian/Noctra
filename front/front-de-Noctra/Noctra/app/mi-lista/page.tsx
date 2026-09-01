'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';
import Navbar from '@/components/Navbar';
import { useAuth } from '@/lib/auth-context';
import * as api from '@/lib/api';
import type { Favorite } from '@/lib/types';

export default function MiListaPage() {
  const { userId, isLoading: authLoading } = useAuth();
  const [favorites, setFavorites] = useState<Favorite[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [removingId, setRemovingId] = useState<number | null>(null);

  useEffect(() => {
    if (authLoading) return;

    if (!userId) {
      setIsLoading(false);
      return;
    }

    api
      .getFavorites(userId)
      .then(setFavorites)
      .catch(() => setError('No se pudo cargar tu lista'))
      .finally(() => setIsLoading(false));
  }, [userId, authLoading]);

  async function handleRemove(contentId: number) {
    setRemovingId(contentId);
    try {
      await api.removeFavorite(contentId);
      setFavorites((prev) => prev.filter((f) => f.contentId !== contentId));
    } catch {
      setError('No se pudo quitar el ítem, probá de nuevo');
    } finally {
      setRemovingId(null);
    }
  }

  return (
    <div className="min-h-screen">
      <Navbar />

      <section className="px-8 pt-32 pb-10 md:px-12">
        <h1 className="font-display text-3xl font-bold mb-6">Mi lista</h1>

        {authLoading || isLoading ? (
          <p className="text-ink-400">Cargando...</p>
        ) : !userId ? (
          <div className="rounded-lg border border-noctra-border bg-noctra-surface p-8 max-w-md">
            <p className="text-ink-400 mb-4">Iniciá sesión para ver tu lista.</p>
            <Link
              href="/login"
              className="inline-block rounded-full bg-violet px-5 py-2.5 text-sm font-semibold text-ink-50 shadow-glow hover:bg-violet-deep transition-colors"
            >
              Iniciar sesión
            </Link>
          </div>
        ) : error ? (
          <p className="text-red-300">{error}</p>
        ) : favorites.length === 0 ? (
          <p className="text-ink-400">
            Todavía no agregaste nada. Marcá el corazón en el catálogo para guardar contenido acá.
          </p>
        ) : (
          <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
            {favorites.map((fav) => (
              <div
                key={fav.id}
                className="group relative overflow-hidden rounded-lg bg-noctra-surface border border-noctra-border"
              >
                <div className="h-40 flex items-center justify-center bg-gradient-to-br from-violet-ink/40 to-noctra-surface2">
                  <span className="px-3 text-center text-xs font-bold uppercase tracking-wide text-ink-50/90">
                    {fav.title}
                  </span>
                </div>
                <div className="p-3">
                  <button
                    onClick={() => handleRemove(fav.contentId)}
                    disabled={removingId === fav.contentId}
                    className="text-sm font-medium text-ink-400 hover:text-red-300 transition-colors disabled:opacity-50"
                  >
                    {removingId === fav.contentId ? 'Quitando...' : 'Quitar de mi lista'}
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
}