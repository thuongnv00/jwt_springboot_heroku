package com.example.springbootjwtmaven.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameSong;
    @NotBlank
    @Lob
    private String lyrics;
    private String avatarURl;
    private String mp3Url;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Song() {
    }

    public Song(Long id, String nameSong, String lyrics, String avatarURl, String mp3Url,User user) {
        this.id = id;
        this.nameSong = nameSong;
        this.lyrics = lyrics;
        this.avatarURl = avatarURl;
        this.mp3Url = mp3Url;
        this.user = user;
    }

    public Song(Long id, String nameSong, String lyrics, String avatarURl, String mp3Url) {
        this.id = id;
        this.nameSong = nameSong;
        this.lyrics = lyrics;
        this.avatarURl = avatarURl;
        this.mp3Url = mp3Url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getAvatarURl() {
        return avatarURl;
    }

    public void setAvatarURl(String avatarURl) {
        this.avatarURl = avatarURl;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
