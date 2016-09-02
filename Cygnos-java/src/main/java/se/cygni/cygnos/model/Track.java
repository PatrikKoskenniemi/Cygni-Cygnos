package se.cygni.cygnos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    private String id;
    private String previewUrl;
    private String artistName;
    private String trackName;
    private String albumName;

    private String albumImg;

    @JsonCreator
    public Track(
            @JsonProperty("id")  String id,
            @JsonProperty("previewUrl")  String previewUrl,
            @JsonProperty("artistName")  String artistName,
            @JsonProperty("trackName")  String trackName,
            @JsonProperty("albumName")  String albumName,
            @JsonProperty("albumImg")  String albumImg) {

        this.id = id;
        this.previewUrl = previewUrl;
        this.artistName = artistName;
        this.trackName = trackName;
        this.albumName = albumName;
        this.albumImg = albumImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
