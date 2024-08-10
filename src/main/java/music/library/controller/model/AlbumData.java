package music.library.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.library.entity.Album;
import music.library.entity.Song;

@Data
@NoArgsConstructor
public class AlbumData {
	private Long albumId;
	private String albumName;
	private String albumReleaseYear;
	
	private Set<SongData> songs = new HashSet<>();
	public AlbumData(Album album) {
		albumId = album.getAlbumId();
		albumName = album.getAlbumName();
		albumReleaseYear = album.getAlbumReleaseYear();
		
		for(Song song : album.getSongs()) {
			songs.add(new SongData(song));
		}
	}

} // end of class
