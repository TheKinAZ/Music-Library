package music.library.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;
	private String albumName;
	private String albumReleaseYear;
	
//	COMMENTED OUT BECAUSE "Song : Album :: Customer : Pet Store" 
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(mappedBy = "albums")
//	private Set<Song> songs = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "album_song",
		joinColumns = @JoinColumn(name = "album_id"),
		inverseJoinColumns = @JoinColumn(name = "song_id"))
	private Set<Song> songs = new HashSet<>();


} // end of class
