export interface Content {
  id: number;
  title: string;
  description: string;
  genre: string;
}

export interface Movie {
  id: number;
  title: string;
  genre: string;
  duration: number;
}

export interface Series {
  id: number;
  title: string;
  genre: string;
}

export interface Favorite {
  id: number;
  contentId: number;
  title: string;
}