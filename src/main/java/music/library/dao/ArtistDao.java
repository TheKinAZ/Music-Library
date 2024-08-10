package music.library.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import music.library.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

	Optional<Artist> findArtistByArtistName(String artistName);

} // end of class
