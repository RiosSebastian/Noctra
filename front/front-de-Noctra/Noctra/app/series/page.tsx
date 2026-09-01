import Navbar from '@/components/Navbar';
import ContentCard from '@/components/ContentCard';
import type { Series } from '@/lib/types';
import { API_URL } from '@/lib/api';

async function getAllSeries(): Promise<Series[]> {
  const res = await fetch(`${API_URL}/api/series`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudieron obtener las series');
  return res.json();
}

export default async function SeriesPage() {
  const series = await getAllSeries();

  return (
    <div className="min-h-screen">
      <Navbar />

      <section className="px-8 pt-32 pb-10 md:px-12">
        <h1 className="font-display text-3xl font-bold mb-6">Series</h1>

        {series.length === 0 ? (
          <p className="text-ink-400">Todavía no hay series cargadas.</p>
        ) : (
          <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
            {series.map((s) => (
              <ContentCard key={s.id} content={s} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
}