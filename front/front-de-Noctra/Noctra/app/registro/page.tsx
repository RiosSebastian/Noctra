'use client';

import { useState, FormEvent } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { useAuth } from '@/lib/auth-context';

export default function RegisterPage() {
  const router = useRouter();
  const { register } = useAuth();

  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    setError(null);

    if (password.length < 8) {
      setError('La contraseña debe tener al menos 8 caracteres');
      return;
    }

    setIsSubmitting(true);
    try {
      await register(username, email, password);
      router.push('/');
    } catch (err) {
      setError(err instanceof Error ? err.message : 'No se pudo crear la cuenta');
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-noctra-aurora px-6">
      <div className="w-full max-w-sm">
        <Link
          href="/"
          className="font-display block text-center text-2xl font-extrabold tracking-tight text-violet-glow mb-8"
        >
          NOCTRA
        </Link>

        <form
          onSubmit={handleSubmit}
          className="rounded-lg border border-noctra-border bg-noctra-surface p-8 shadow-glow"
        >
          <h1 className="font-display text-xl font-bold mb-6">Crear cuenta</h1>

          {error && (
            <div className="mb-4 rounded-md border border-red-500/30 bg-red-500/10 px-4 py-2 text-sm text-red-300">
              {error}
            </div>
          )}

          <label className="block text-sm font-medium text-ink-400 mb-1" htmlFor="username">
            Nombre de usuario
          </label>
          <input
            id="username"
            type="text"
            required
            minLength={3}
            maxLength={30}
            autoComplete="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="mb-4 w-full rounded-md border border-noctra-border bg-noctra-surface2 px-3 py-2 text-sm text-ink-50 outline-none focus:border-violet"
            placeholder="tu_usuario"
          />

          <label className="block text-sm font-medium text-ink-400 mb-1" htmlFor="email">
            Email
          </label>
          <input
            id="email"
            type="email"
            required
            autoComplete="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="mb-4 w-full rounded-md border border-noctra-border bg-noctra-surface2 px-3 py-2 text-sm text-ink-50 outline-none focus:border-violet"
            placeholder="vos@ejemplo.com"
          />

          <label className="block text-sm font-medium text-ink-400 mb-1" htmlFor="password">
            Contraseña
          </label>
          <input
            id="password"
            type="password"
            required
            minLength={8}
            autoComplete="new-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="mb-1 w-full rounded-md border border-noctra-border bg-noctra-surface2 px-3 py-2 text-sm text-ink-50 outline-none focus:border-violet"
            placeholder="••••••••"
          />
          <p className="mb-6 text-xs text-ink-400">Mínimo 8 caracteres</p>

          <button
            type="submit"
            disabled={isSubmitting}
            className="w-full rounded-full bg-violet px-5 py-2.5 text-sm font-semibold text-ink-50 shadow-glow transition-colors hover:bg-violet-deep disabled:opacity-60"
          >
            {isSubmitting ? 'Creando cuenta...' : 'Crear cuenta'}
          </button>

          <p className="mt-6 text-center text-sm text-ink-400">
            ¿Ya tenés cuenta?{' '}
            <Link href="/login" className="text-violet-glow hover:underline">
              Iniciá sesión
            </Link>
          </p>
        </form>
      </div>
    </div>
  );
}
