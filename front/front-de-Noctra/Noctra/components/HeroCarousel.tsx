'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';

export interface CarouselSlide {
  id: number;
  title: string;
  overview: string | null;
  backdropUrl: string | null;
  badge: 'Película' | 'Serie';
  year: string | null;
  href: string;
}

const AUTOPLAY_MS = 7000;

export default function HeroCarousel({ slides }: { slides: CarouselSlide[] }) {
  const [index, setIndex] = useState(0);

  useEffect(() => {
    if (slides.length <= 1) return;
    const timer = setInterval(() => {
      setIndex((i) => (i + 1) % slides.length);
    }, AUTOPLAY_MS);
    return () => clearInterval(timer);
  }, [slides.length]);

  if (slides.length === 0) return null;

  const slide = slides[index];

  function goTo(i: number) {
    setIndex((i + slides.length) % slides.length);
  }

  return (
    <section className="relative h-[420px] overflow-hidden md:h-[480px]">
      {slide.backdropUrl && (
        <div
          className="absolute inset-0 bg-cover bg-center transition-opacity duration-500"
          style={{ backgroundImage: `url(${slide.backdropUrl})` }}
        />
      )}
      <div className="absolute inset-0 bg-gradient-to-r from-noctra-bg via-noctra-bg/70 to-transparent" />
      <div className="absolute inset-0 bg-gradient-to-t from-noctra-bg via-transparent to-transparent" />

      <div className="relative flex h-full flex-col justify-center gap-4 px-8 pt-16 md:px-12">
        <span className="w-fit rounded-full bg-violet/90 px-3 py-1 text-xs font-semibold uppercase tracking-wide text-ink-50">
          {slide.badge}
          {slide.year ? ` · ${slide.year}` : ''}
        </span>

        <h2 className="font-display max-w-xl text-3xl font-extrabold leading-tight md:text-5xl">
          {slide.title}
        </h2>

        {slide.overview && (
          <p className="max-w-md text-sm text-ink-50/80 line-clamp-3 md:text-base">
            {slide.overview}
          </p>
        )}

        <Link
          href={slide.href}
          className="mt-2 w-fit rounded-full bg-violet px-6 py-2.5 text-sm font-semibold text-ink-50 shadow-glow transition-colors hover:bg-violet-deep"
        >
          Ver más
        </Link>
      </div>

      {slides.length > 1 && (
        <>
          <button
            onClick={() => goTo(index - 1)}
            aria-label="Anterior"
            className="absolute left-3 top-1/2 -translate-y-1/2 rounded-full bg-noctra-bg/50 p-2 text-ink-50 backdrop-blur transition-colors hover:bg-noctra-bg/80"
          >
            ‹
          </button>
          <button
            onClick={() => goTo(index + 1)}
            aria-label="Siguiente"
            className="absolute right-3 top-1/2 -translate-y-1/2 rounded-full bg-noctra-bg/50 p-2 text-ink-50 backdrop-blur transition-colors hover:bg-noctra-bg/80"
          >
            ›
          </button>

          <div className="absolute bottom-4 left-1/2 flex -translate-x-1/2 gap-2">
            {slides.map((s, i) => (
              <button
                key={s.id}
                onClick={() => goTo(i)}
                aria-label={`Ir a la slide ${i + 1}`}
                className={`h-1.5 rounded-full transition-all ${
                  i === index ? 'w-6 bg-violet-glow' : 'w-1.5 bg-ink-50/40'
                }`}
              />
            ))}
          </div>
        </>
      )}
    </section>
  );
}