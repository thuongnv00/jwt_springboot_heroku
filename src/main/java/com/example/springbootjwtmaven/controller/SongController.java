package com.example.springbootjwtmaven.controller;

import com.example.springbootjwtmaven.dto.response.ResponseMessage;
import com.example.springbootjwtmaven.model.Song;
import com.example.springbootjwtmaven.repository.ISongRepository;
import com.example.springbootjwtmaven.security.userprincal.UserDetailService;
import com.example.springbootjwtmaven.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/auth")
@RestController
public class SongController {
    @Autowired
    SongServiceImpl songService;

    @GetMapping("/song/list")
    public ResponseEntity<?> pageSong(@PageableDefault (sort = "nameSong",direction = Sort.Direction.ASC)Pageable pageable) {
        Page<Song> songPage = songService.findAll(pageable);
        if(songPage.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Songs are empty !"),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage,HttpStatus.OK);
    }

    @GetMapping("/user/song/list")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> pageSongByUserId() {
        List<Song> songPage = songService.findAllByUserId();
        if(songPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER')or hasAuthority('ADMIN')")
    @PostMapping("/song/create")
    public ResponseEntity<?> listSong (@Valid @RequestBody Song song) {
        if(song.getAvatarURl()==null || song.getAvatarURl().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("noavatar"),HttpStatus.OK);
        }
        if(song.getMp3Url()==null || song.getMp3Url().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("nomp3url"),HttpStatus.OK);
        }
        songService.save(song);
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
    @DeleteMapping("/song/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if(!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.deleteById(song.get().getId());
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
}
