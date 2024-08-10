package music.library.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.library.controller.model.AlbumData;
import music.library.controller.model.ArtistData;
import music.library.controller.model.SongData;
import music.library.dao.AlbumDao;
import music.library.dao.ArtistDao;
import music.library.dao.SongDao;
import music.library.entity.Album;
import music.library.entity.Artist;
import music.library.entity.Song;

@Service
public class SongService {

	@Autowired
	private ArtistDao artistDao;

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private SongDao songDao;

	@Transactional(readOnly = false)
	public ArtistData saveArtist(ArtistData artistData) {
		Long artistId = artistData.getArtistId();
		Artist artist = findOrCreateArtist(artistId, artistData.getArtistName());

		setFieldsInArtist(artist, artistData);
		return new ArtistData(artistDao.save(artist));
	}

	private void setFieldsInArtist(Artist artist, ArtistData artistData) {
		artist.setArtistName(artistData.getArtistName());
		artist.setArtistYearFormed(artistData.getArtistYearFormed());
		artist.setArtistLatestYear(artistData.getArtistLatestYear());
	}

	private Artist findOrCreateArtist(Long artistId, String artistName) {
		Artist artist;

		if (Objects.isNull(artistId)) {
			Optional<Artist> optArtist = artistDao.findArtistByArtistName(artistName);

			if (optArtist.isPresent()) {
				throw new DuplicateKeyException("An artist named '" + artistName + "' already exists");
			}
			artist = new Artist();
		} else {
			artist = findArtistById(artistId);
		}
		return artist;
	}

	private Artist findArtistById(Long artistId) {
		return artistDao.findById(artistId)
				.orElseThrow(() -> new NoSuchElementException("Artist with ID=" + artistId + " was not found"));
	}

	@Transactional(readOnly = true)
	public List<ArtistData> retrieveAllArtists() {
		List<Artist> artists = artistDao.findAll();
		List<ArtistData> response = new LinkedList<>();

		for (Artist artist : artists) {
			response.add(new ArtistData(artist));
		}
		return response;
//		
//	// @formatter:off
//		return artistDao.findAll()
//		.stream()
//		.map(ArtistData::new);
//	// @formatter:on
	}

	@Transactional(readOnly = true)
	public ArtistData retrieveArtistById(Long artistId) {
		Artist artist = findArtistById(artistId);
		return new ArtistData(artist);
	}

	@Transactional(readOnly = false)
	public void deleteArtistById(Long artistId) {
		Artist artist = findArtistById(artistId);
		artistDao.delete(artist);
	}

	@Transactional(readOnly = false)
	public SongData saveSong(Long artistId, SongData songData) {
		// renamed from "insertSong" because insert & update methods are the same
		Artist artist = findArtistById(artistId);

//		Set<Album> albums = albumDao.findAllByAlbumIn(songData.getAlbums());

		Song song = findOrCreateSong(songData.getSongId());
		setSongFields(song, songData);
		
		song.setArtist(artist);
		artist.getSongs().add(song);
		
//		for(Album album : albums) {
//			album.getSongs().add(song);
//			song.getAlbums().add(album);
//		}
		
		Song dbSong = songDao.save(song);
		return new SongData(dbSong);
	}

	private void setSongFields(Song song, SongData songData) {
		song.setSongName(songData.getSongName());
		song.setSongId(songData.getSongId());
	}

	private Song findOrCreateSong(Long songId) {
		Song song;

		if (Objects.isNull(songId)) {
			song = new Song();
		} else {
			song = findSongById(songId);
		}
		return song;
	}

	private Song findSongById(Long songId) {
		return songDao.findById(songId)
			.orElseThrow(() -> new NoSuchElementException(
				"Song with ID=" + songId + " does not exist"));
	}

	public SongData retrieveSongById(Long songId) {
		Song song = songDao.findById(songId)
			.orElseThrow(() -> new NoSuchElementException(
				"Song with ID=" + songId + " does not exist"));
		return new SongData(song);
	}

	public void setAlbumToSong(Long songId, Long albumId) {
		Song song = findSongById(songId);
		Album album = findAlbumById(albumId);
			album.getSongs().add(song);
			song.getAlbums().add(album);
			songDao.save(song);
			albumDao.save(album);
	}
	
	private Album findAlbumById(Long albumId) {
		return albumDao.findById(albumId)
			.orElseThrow(() -> new NoSuchElementException(
				"Album with ID=" + albumId + " does not exist"));
	}

	public AlbumData retrieveAlbumById(Long albumId) {
		Album album = albumDao.findById(albumId)
			.orElseThrow(() -> new NoSuchElementException(
				"Album with ID=" + albumId + " does not exist"));
		return new AlbumData(album);
	}

} // end of class
