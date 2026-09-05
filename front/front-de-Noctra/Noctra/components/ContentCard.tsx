import Link from 'next/link';
import FavoriteToggleButton from '@/components/FavoriteToggleButton';

interface CardContent {
  id: number;
  title: string;
  genre: string;
}

export default function ContentCard({ content }: { content: CardContent }) {
  return (
    <div className="group relative overflow-hidden rounded-lg bg-noctra-surface border border-noctra-border transition-all duration-300 hover:-translate-y-1 hover:border-violet hover:shadow-glow">
      <Link href={`/catalogo/${content.id}`}>
        <div className="h-40 flex items-center justify-center bg-gradient-to-br from-violet-ink/40 to-noctra-surface2">
          <span className="px-3 text-center text-xs font-bold uppercase tracking-wide text-ink-50/90">
            {content.title}
          </span>
        </div>
      </Link>
      <div className="flex items-center justify-between p-3">
        <p className="text-sm font-medium text-ink-400 group-hover:text-violet-glow transition-colors">
          {content.genre}
        </p>
        <FavoriteToggleButton contentId={content.id} />
      </div>
    </div>
  );
}