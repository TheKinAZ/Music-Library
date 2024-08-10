package music.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.library.entity.Album;

public interface AlbumDao extends JpaRepository<Album, Long> {

//	Set<Album> findAllByAlbumId(Set<String> albums);

}
