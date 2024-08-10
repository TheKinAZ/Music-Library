package music.library.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;
	private String songName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "songs", cascade = CascadeType.PERSIST)
	private Set<Album> albums = new HashSet<>();

//	COMMENTED OUT BECAUSE "Song : Album :: Customer : Pet Store"
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(cascade = CascadeType.PERSIST)
//	@JoinTable(name = "album_song",
//		joinColumns = @JoinColumn(name = "album_id"),
//		inverseJoinColumns = @JoinColumn(name = "song_id"))
//	private Set<Album> albums = new HashSet<Album>();

} // end of class
