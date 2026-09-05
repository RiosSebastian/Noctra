import { notFound } from 'next/navigation';
import Navbar from '@/components/Navbar';
import FavoriteToggleButton from '@/components/FavoriteToggleButton';
import { getContentById } from '@/lib/api';

export default async function CatalogoDetailPage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const { id } = await params;

  let content;
  try {
    content = await getContentById(Number(id));
  } catch {
    notFound();
  }

  return (
    <div className="min-h-screen">
      <Navbar />
      <section className="mx-auto max-w-3xl px-8 pt-32 pb-10 md:px-12">
        <div className="flex items-start justify-between gap-4">
          <h1 className="font-display text-3xl font-bold md:text-4xl">{content.title}</h1>
          <FavoriteToggleButton contentId={content.id} size="lg" />
        </div>
        <p className="mt-2 text-sm text-ink-400">{content.genre}</p>
        {content.description && (
          <p className="mt-6 leading-relaxed text-ink-50/90">{content.description}</p>
        )}
      </section>
    </div>
  );
}