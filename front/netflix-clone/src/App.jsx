/* eslint-disable no-undef */
//import { useEffect, useState } from 'react';
import Navbar from './components/Navbar';
//import { getAllContent } from './api/apiService';

function App() {
  //const [movies, setMovies] = useState([]);

 /* useEffect(() => {
    // Llamamos a tu Backend de Spring Boot
    const fetchContent = async () => {
      const data = await getAllContent();
      setMovies(data);
    };
    fetchContent();
  }, []);*/

  return (
    <div className="min-h-screen bg-netflix-black">
      <Navbar />
      
      {/* Hero simple */}
      <div className="pt-32 px-12">
        <h2 className="text-5xl font-bold mb-4">Bienvenido</h2>
        <p className="text-gray-400 max-w-md">Descubre el mejor contenido en el clon más morado de internet.</p>
      </div>

      {/* Listado de contenido de tu API */}
      <div className="px-12 py-10">
        <h3 className="text-2xl font-semibold mb-6">Contenido para ti</h3>
        <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
          
          {movies.map((movie) => (
            <div key={movie.id} className="bg-zinc-900 rounded-md overflow-hidden hover:scale-105 transition duration-300 border-b-4 border-netflix-purple">
              <div className="h-40 bg-purple-900/20 flex items-center justify-center">
                 {/* Aquí iría una imagen, por ahora usamos el título */}
                 <span className="text-xs font-bold px-2 text-center uppercase">{movie.title}</span>
              </div>
              <div className="p-2">
                <p className="text-sm font-medium">{movie.genre}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;