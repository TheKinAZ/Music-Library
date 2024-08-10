package music.library.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.library.controller.model.AlbumData;
import music.library.controller.model.ArtistData;
import music.library.controller.model.SongData;
import music.library.service.SongService;

@RestController
@RequestMapping("/music_library")
@Slf4j
public class SongController {
	@Autowired
	private SongService songService;

	@PostMapping("/artist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData insertArtist(@RequestBody ArtistData artistData) {
		log.info("Creating artist ID={}", artistData);
		return songService.saveArtist(artistData);
	}
	
	@PutMapping("/artist/{artistId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData updateArtist(@PathVariable Long artistId, @RequestBody ArtistData artistData) {
		artistData.setArtistId(artistId);
		log.info("Updating artist ID={}", artistData);
		return songService.saveArtist(artistData);
	}
	
	@GetMapping("/artist")
	public List<ArtistData> retrieveAllArtists() {
		log.info("Retrieve all artists called");
		return songService.retrieveAllArtists();
	}
	
	@GetMapping("/artist/{artistId}")
	public ArtistData retrieveArtistById (@PathVariable Long artistId) {
		log.info("Retrieving artist with ID={}", artistId);
		return songService.retrieveArtistById(artistId);
	}
	
	@DeleteMapping("/artist")
	public ArtistData deleteAllArtists() {
		log.info("Attempt to delete all artists");
		throw new UnsupportedOperationException("Attempt to delete all artists is NOT allowed!");
	}
	
	@DeleteMapping("/artist/{artistId}")
	public Map<String, String> deleteArtistById(@PathVariable Long artistId) {
		log.info("Deleting artist with ID=" + artistId);
		
		songService.deleteArtistById(artistId);
		
		return Map.of("message", "Deletion of Artist with ID=" + artistId + " was succesful");
	}

	@PostMapping("/artist/{artistId}/song")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SongData insertSong(@PathVariable Long artistId, @RequestBody SongData songData) {
		log.info("Creating song {} for Artist with ID={}", songData, artistId);
		
		return songService.saveSong(artistId, songData); 
	}

	@PostMapping("/artist/{artistId}/song/{songId}")
	public SongData updateSong(@PathVariable Long artistId, @PathVariable Long songId, 
		@RequestBody SongData songData) {

		songData.setSongId(songId);

		log.info("Updating song {} for Artist with ID={}", songData, artistId);

		return songService.saveSong(artistId, songData);
	}
	
	@PostMapping("/song/{songId}/album/{albumId}")
	public Map<String, String> updateAlbum(@PathVariable Long songId, @PathVariable Long albumId) {
		log.info("Added song {} to album {}", songId, albumId);
		
		songService.setAlbumToSong(songId, albumId);
		
		return Map.of("message", "Song with ID=" + songId + " was succesfully added to Album with ID=" + albumId);
	}

	@GetMapping("/song/{songId}")
	public SongData findSongById (@PathVariable Long songId) {
		log.info("Retrieving song with ID={}", songId);
		return songService.retrieveSongById(songId); // NOTE: private SongData findSongById already exists
	}
	
	@GetMapping("/album/{albumId}")
	public AlbumData findAlbumById (@PathVariable Long albumId) {
		log.info("Retrieving album with ID={}", albumId);
		return songService.retrieveAlbumById(albumId);
	}
	
} // end of class
