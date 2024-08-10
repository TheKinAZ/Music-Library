package music.library.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.library.entity.Artist;
import music.library.entity.Song;

@Data
@NoArgsConstructor
public class ArtistData {
	private Long artistId;
	private String artistName;
	private String artistYearFormed;
	private String artistLatestYear;
	private Set<SongData> songs = new HashSet<>();

	public ArtistData(Artist artist) {
		artistId = artist.getArtistId();
		artistName = artist.getArtistName();
		artistYearFormed = artist.getArtistYearFormed();
		artistLatestYear = artist.getArtistLatestYear();
		
		for(Song song : artist.getSongs()) {
			songs.add(new SongData(song));
		}
	}

//	@Data
//	@NoArgsConstructor
//	static class SongResponse {
//		private Long songId;
//		private String songName;
//		private Set<Album> albums = new HashSet<>();
//		
//		SongResponse(Song song) {
//			songId = song.getSongId();
//			songName = song.getSongName();
//			
//			for(Album album : song.getAlbums()) {
//				albums.add(album);
//			}
//		}
//	}

} // end of class
