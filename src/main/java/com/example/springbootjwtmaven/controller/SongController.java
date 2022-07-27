package com.example.springbootjwtmaven.controller;

import com.example.springbootjwtmaven.dto.response.ResponseMessage;
import com.example.springbootjwtmaven.model.Song;
import com.example.springbootjwtmaven.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("api/song")
@RestController
public class SongController {
    @Autowired
    SongServiceImpl songService;
    @GetMapping
    public ResponseEntity<?> pageSong(@PageableDefault (sort = "nameSong",direction = Sort.Direction.ASC)Pageable pageable) {
        Page<Song> songPage = songService.findAll(pageable);
        if(songPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage,HttpStatus.OK);
    }
    @PostMapping("/create")
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if(!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.deleteById(song.get().getId());
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
}
