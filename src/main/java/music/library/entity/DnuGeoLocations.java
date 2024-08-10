package music.library.entity;

import java.math.BigDecimal;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class DnuGeoLocations {
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	public DnuGeoLocations(DnuGeoLocations geoLocation) {
		this.latitude = geoLocation.latitude;
		this.longitude = geoLocation.longitude;
	}

} // end of class

// At 18 minutes into "Create Table Entity" video (API Week #1), he made the 
// GeoLocation variable embeddable on his PetPark entity; I have not used
// DnuGeoLocation so I took no action there (review for accuracy if needed)
