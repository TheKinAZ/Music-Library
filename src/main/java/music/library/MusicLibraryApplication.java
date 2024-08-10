package music.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// API Week 1, Video "Create Maven Project" explains this @ starts component scan in package 
public class MusicLibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(MusicLibraryApplication.class, args);
	} 

} // end of class