package com.example.springbootjwtmaven.service.impl;

import com.example.springbootjwtmaven.model.Song;
import com.example.springbootjwtmaven.model.User;
import com.example.springbootjwtmaven.repository.ISongRepository;
import com.example.springbootjwtmaven.security.userprincal.UserDetailService;
import com.example.springbootjwtmaven.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    ISongRepository songRepository;
    @Autowired
    UserDetailService userDetailService;

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public List<Song> findAllByUserId() {
        if(userDetailService.getCurrentUser().getId()== null) {
            return null;
        } else return songRepository.findAllByUserId(userDetailService.getCurrentUser().getId());
    }

    @Override
    public Song save(Song song) {
        User user = userDetailService.getCurrentUser();
        song.setUser(user);
        return songRepository.save(song);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song edit(Song song) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

}
