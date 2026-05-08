import React from 'react';

const Navbar = () => {
  return (
    <nav className="fixed top-0 w-full z-50 flex items-center p-6 bg-gradient-to-b from-black to-transparent">
      <h1 className="text-netflix-light-purple text-3xl font-bold tracking-tighter cursor-pointer">
        PURPLEFLIX
      </h1>
      <div className="ml-10 flex space-x-4 text-sm font-light">
        <span className="cursor-pointer hover:text-purple-400">Inicio</span>
        <span className="cursor-pointer hover:text-purple-400">Series</span>
        <span className="cursor-pointer hover:text-purple-400">Películas</span>
        <span className="cursor-pointer hover:text-purple-400">Mi Lista</span>
      </div>
    </nav>
  );
};

export default Navbar;