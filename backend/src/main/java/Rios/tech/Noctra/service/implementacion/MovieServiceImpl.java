package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.MovieRequestDTO;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;
import Rios.tech.Noctra.entity.Movie;
import Rios.tech.Noctra.exception.ContentNotFoundException;
import Rios.tech.Noctra.mapper.MovieMapper;
import Rios.tech.Noctra.repository.MovieRepository;
import Rios.tech.Noctra.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponseDTO create(MovieRequestDTO dto) {
        Movie movie = movieMapper.toEntity(dto);
        movie = movieRepository.save(movie);
        return movieMapper.toResponse(movie);
    }

    @Override
    public MovieResponseDTO getById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Película no encontrada"));
        return movieMapper.toResponse(movie);
    }

    @Override
    public List<MovieResponseDTO> getAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    @Override
    public MovieResponseDTO update(Long id, MovieRequestDTO dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Película no encontrada"));
        movieMapper.updateEntity(movie, dto);
        movie = movieRepository.save(movie);
        return movieMapper.toResponse(movie);
    }

    @Override
    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Película no encontrada"));
        movieRepository.delete(movie);
    }
}