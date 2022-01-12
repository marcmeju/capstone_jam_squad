package learn.notbooking.domain;

import learn.notbooking.data.LocationRepository;
import learn.notbooking.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationServiceTest {

        @Autowired
        LocationService service;

        @MockBean
        LocationRepository locationRepository;

        @Test
        void shouldAdd() {
            Location location = new Location();

            location.setLocationAddress("locationAddress");
            location.setLocationCity("LocationCity");
            location.setLocationState("LocationState");
            location.setLocationZipCode(34567);

            Location mockOut = new Location(5, "LocationAddress", "LocationCity", "LocationState",34567);

            when(locationRepository.add(location)).thenReturn(mockOut);

            Result<Location> actual = service.add(location);
            assertEquals(ResultType.SUCCESS, actual.getType());
            assertEquals(mockOut, actual.getPayload());
        }

        @Test
        void shouldUpdate() {
            Location location = new Location();
            location.setLocationId(2);
            location.setLocationAddress("locationAddress");
            location.setLocationCity("LocationCity");
            location.setLocationState("LocationState");
            location.setLocationZipCode(34567);


            when(locationRepository.update(location)).thenReturn(true);
            Result<Location> actual = service.update(location);
            assertEquals(ResultType.SUCCESS, actual.getType());
        }

        @Test
        void shouldNotUpdateMissing() {
            Location location = new Location();
            location.setLocationId(89347985);
            location.setLocationAddress("locationAddress");
            location.setLocationCity("LocationCity");
            location.setLocationState("LocationState");
            location.setLocationZipCode(34567);

            when(locationRepository.update(location)).thenReturn(false);
            Result<Location> actual = service.update(location);
            assertEquals(ResultType.NOT_FOUND, actual.getType());

        }


        /* Delete Tests */
        @Test
        void shouldNotDeleteMissing() {
            when(locationRepository.deleteById(35000)).thenReturn(false);
            assertFalse(service.deleteById(35000));
        }

        @Test
        void shouldDelete() {
            when(locationRepository.deleteById(2)).thenReturn(true);
            assertTrue(service.deleteById(2));
        }


}