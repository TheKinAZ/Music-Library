package music.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import music.library.entity.Song;

public interface SongDao extends JpaRepository<Song, Long> {

}
