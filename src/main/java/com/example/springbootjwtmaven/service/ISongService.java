package com.example.springbootjwtmaven.service;

import com.example.springbootjwtmaven.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    Page<Song> findAll(Pageable pageable);
    List<Song> findAllByUserId();
    Song save(Song song);
    Optional<Song>  findById (Long id);
    Song edit(Song song);
    void deleteById(Long id);
}
