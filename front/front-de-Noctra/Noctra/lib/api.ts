const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://127.0.0.1:8080';

export interface AuthResponse {
  token: string;
}

export interface ApiError {
  message: string;
}

async function parseErrorMessage(res: Response): Promise<string> {
  try {
    const data = await res.json();
    // El backend de Spring devuelve distintas formas según el error
    // (validación de @Valid, excepción manual, etc.), así que probamos varias.
    if (typeof data === 'string') return data;
    if (data.message) return data.message;
    if (data.error) return data.error;
    if (data.errors && Array.isArray(data.errors) && data.errors[0]?.defaultMessage) {
      return data.errors[0].defaultMessage;
    }
  } catch {
    // el body no era JSON
  }
  if (res.status === 401 || res.status === 403) return 'Email o contraseña incorrectos';
  if (res.status === 409) return 'Ese email ya está registrado';
  return 'Ocurrió un error inesperado. Probá de nuevo.';
}

export async function login(email: string, password: string): Promise<AuthResponse> {
  const res = await fetch(`${API_URL}/api/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });

  if (!res.ok) {
    throw new Error(await parseErrorMessage(res));
  }

  return res.json();
}

export async function register(
  username: string,
  email: string,
  password: string
): Promise<AuthResponse> {
  const res = await fetch(`${API_URL}/api/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, email, password }),
  });

  if (!res.ok) {
    throw new Error(await parseErrorMessage(res));
  }

  return res.json();
}

/**
 * Wrapper para llamadas autenticadas: agrega el header Authorization
 * automáticamente si hay un token guardado.
 */
export async function apiFetch(path: string, options: RequestInit = {}): Promise<Response> {
  const token = typeof window !== 'undefined' ? localStorage.getItem('noctra_token') : null;

  const headers = new Headers(options.headers);
  headers.set('Content-Type', 'application/json');
  if (token) headers.set('Authorization', `Bearer ${token}`);

  return fetch(`${API_URL}${path}`, { ...options, headers });
}

export interface CurrentUser {
  id: number;
  username: string;
  email: string;
}

export async function getCurrentUser(): Promise<CurrentUser> {
  const res = await apiFetch('/api/users/me');
  if (!res.ok) throw new Error('No se pudo obtener el usuario actual');
  return res.json();
}

export async function addFavorite(contentId: number): Promise<void> {
  const res = await apiFetch('/api/favorites', {
    method: 'POST',
    body: JSON.stringify({ contentId }),
  });
  // 409 = ya estaba en favoritos, lo tratamos como éxito (idempotente para el usuario)
  if (!res.ok && res.status !== 409) {
    throw new Error(await parseErrorMessage(res));
  }
}

export async function removeFavorite(contentId: number): Promise<void> {
  const res = await apiFetch(`/api/favorites?contentId=${contentId}`, {
    method: 'DELETE',
  });
  if (!res.ok && res.status !== 404) {
    throw new Error(await parseErrorMessage(res));
  }
}

export async function getFavorites(userId: number): Promise<import('./types').Favorite[]> {
  const res = await apiFetch(`/api/favorites/${userId}`);
  if (!res.ok) throw new Error('No se pudo obtener tu lista');
  return res.json();
}

export async function getPopularMovies(page = 1): Promise<import('./types').TmdbMovie[]> {
  const res = await fetch(`${API_URL}/api/movies/popular?page=${page}`, {
    cache: 'no-store',
  });
  if (!res.ok) throw new Error('No se pudo obtener el catálogo de TMDB');
  return res.json();
}

export async function getTmdbMovieById(id: number): Promise<import('./types').TmdbMovie> {
  const res = await fetch(`${API_URL}/api/movies/tmdb/${id}`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudo obtener el detalle de la película');
  return res.json();
}

export async function getContentById(id: number): Promise<import('./types').Content> {
  const res = await fetch(`${API_URL}/api/content/${id}`, { cache: 'no-store' });
  if (!res.ok) throw new Error('No se pudo obtener el contenido');
  return res.json();
}

export async function searchContent(title: string): Promise<import('./types').Content[]> {
  const res = await fetch(`${API_URL}/api/content/search?title=${encodeURIComponent(title)}`, {
    cache: 'no-store',
  });
  if (!res.ok) throw new Error('No se pudo buscar en el catálogo');
  return res.json();
}

export async function searchTmdbMovies(
  query: string,
  page = 1
): Promise<import('./types').TmdbMovie[]> {
  const res = await fetch(
    `${API_URL}/api/movies/search?query=${encodeURIComponent(query)}&page=${page}`,
    { cache: 'no-store' }
  );
  if (!res.ok) throw new Error('No se pudo buscar en TMDB');
  return res.json();
}

export { API_URL };