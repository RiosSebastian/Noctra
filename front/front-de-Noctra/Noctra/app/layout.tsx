
import type { Metadata } from 'next';
import { Unbounded, Inter } from 'next/font/google';
import './globals.css';
 
// Tipografía de display: geométrica y con peso, para títulos y marca
const unbounded = Unbounded({
  subsets: ['latin'],
  weight: ['500', '700', '800'],
  variable: '--font-display-family',
});

const inter = Inter({
  subsets: ['latin'],
  variable: '--font-body-family',
});
 
export const metadata: Metadata = {
  title: 'Noctra',
  description: 'Tu contenido, en las horas en que todo despierta.',
};
 
export default function RootLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="es" className={`${unbounded.variable} ${inter.variable}`}>
      <body className="bg-noctra-bg text-ink-50 antialiased">{children}</body>
    </html>
  );
}