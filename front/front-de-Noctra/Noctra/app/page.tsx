import Navbar from '@/components/Navbar';
import ContentCard from '@/components/ContentCard';
import type { Content } from '@/lib/types';
 
async function getAllContent(): Promise<Content[]> {
  const res = await fetch('http://localhost:8080/api/content', {
    cache: 'no-store',
  });
  if (!res.ok) throw new Error('No se pudo obtener el contenido');
  return res.json();
}
 
export default async function Home() {
  const movies = await getAllContent();
 
  return (
    <div className="min-h-screen">
      <Navbar />
 
      {/* Hero: degradé "aurora" nocturno como elemento de identidad de la marca */}
      <section className="relative overflow-hidden bg-noctra-aurora pt-40 pb-24 px-8 md:px-12">
        <h1 className="font-display max-w-2xl text-4xl md:text-6xl font-extrabold leading-tight">
          Todo despierta cuando cae la noche.
        </h1>
        <p className="mt-4 max-w-md text-ink-400">
          El catálogo de Noctra, pensado para las horas en que el resto del mundo duerme.
        </p>
      </section>
 
      <section className="px-8 py-10 md:px-12">
        <h2 className="font-display text-2xl font-bold mb-6">Contenido para ti</h2>
        <div className="grid grid-cols-2 gap-4 md:grid-cols-4 lg:grid-cols-6">
          {movies.map((movie) => (
            <ContentCard key={movie.id} movie={movie} />
          ))}
        </div>
      </section>
    </div>
  );
}