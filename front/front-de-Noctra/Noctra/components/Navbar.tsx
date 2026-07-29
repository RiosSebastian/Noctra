'use client';
 
import { useEffect, useState } from 'react';
 
interface NavLink {
  label: string;
  href: string;
}
 
const LINKS: NavLink[] = [
  { label: 'Inicio', href: '/' },
  { label: 'Series', href: '/series' },
  { label: 'Películas', href: '/peliculas' },
  { label: 'Mi lista', href: '/mi-lista' },
];
 
export default function Navbar() {
  const [scrolled, setScrolled] = useState<boolean>(false);
 
  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 8);
    window.addEventListener('scroll', onScroll);
    return () => window.removeEventListener('scroll', onScroll);
  }, []);
 
  return (
    <header
      className={`fixed top-0 inset-x-0 z-50 transition-colors duration-300 ${
        scrolled
          ? 'bg-noctra-bg/90 backdrop-blur border-b border-noctra-border'
          : 'bg-gradient-to-b from-noctra-bg/80 to-transparent'
      }`}
    >
      <nav className="flex items-center justify-between px-8 py-4 md:px-12">
        <a href="" className="font-display text-2xl font-extrabold tracking-tight text-violet-glow">
          NOCTRA
        </a>
 
        <ul className="hidden md:flex items-center gap-8">
          {LINKS.map((link) => (
            <li key={link.href}>
              <a
                href={link.href}
                className="text-sm font-medium text-ink-50/80 hover:text-violet-glow transition-colors"
              >
                {link.label}
              </a>
            </li>
          ))}
        </ul>
 
        <a
          href="/login"
          className="rounded-full bg-violet px-5 py-2 text-sm font-semibold text-ink-50 shadow-glow hover:bg-violet-deep transition-colors"
        >
          Iniciar sesión
        </a>
      </nav>
    </header>
  );
}