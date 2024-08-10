package music.library.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.library.entity.Song;

@Data
@NoArgsConstructor
public class SongData {
	private Long songId;
	private String songName;
//	private Set<Album> albums = new HashSet<>();
	
	public SongData(Song song) {
		songId = song.getSongId();
		songName = song.getSongName();
		
//		for(Album album : song.getAlbums()) {
//			albums.add(album);
//		}
	}
	
//	@Data
//	@NoArgsConstructor
//	public static class SongArtist {
//		private Long artistId;
//		private String artistName;
//		private String artistYearFormed;
//		private String artistLatestYear;
//		
//		public SongArtist(Artist artist) {
//			artistId = artist.getArtistId();
//			artistName = artist.getArtistName();
//			artistYearFormed = artist.getArtistYearFormed();
//			artistLatestYear = artist.getArtistLatestYear();
//		}
//
//	}

} // end of class
