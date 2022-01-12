package learn.notbooking.domain;

import learn.notbooking.data.PackageRepository;
import learn.notbooking.models.Package;
import learn.notbooking.models.Package;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PackageServiceTest {


        @Autowired
        PackageService service;

        @MockBean
        PackageRepository packRepository;

        @Test
        void shouldAdd() {
            Package pack = new Package();

            pack.setPackageName("packageName");
            pack.setTierId(1);
            pack.setPackagePrice(750);
            Package mockOut = new Package(5, "PackageName",  1,750);

            when(packRepository.add(pack)).thenReturn(mockOut);

            Result<Package> actual = service.add(pack);
            assertEquals(ResultType.SUCCESS, actual.getType());
            assertEquals(mockOut, actual.getPayload());
        }

        @Test
        void shouldUpdate() {
            Package pack = new Package();
            pack.setPackageId(2);
            pack.setPackageName("packageName");
            pack.setTierId(1);
            pack.setPackagePrice(750);


            when(packRepository.update(pack)).thenReturn(true);
            Result<Package> actual = service.update(pack);
            assertEquals(ResultType.SUCCESS, actual.getType());
        }

        @Test
        void shouldNotUpdateMissing() {
            Package pack = new Package();
            pack.setPackageId(89347985);
            pack.setPackageName("packageName");
            pack.setTierId(1);
            pack.setPackagePrice(750);

            when(packRepository.update(pack)).thenReturn(false);
            Result<Package> actual = service.update(pack);
            assertEquals(ResultType.NOT_FOUND, actual.getType());

        }


        /* Delete Tests */
        @Test
        void shouldNotDeleteMissing() {
            when(packRepository.deleteById(35000)).thenReturn(false);
            assertFalse(service.deleteById(35000));
        }

        @Test
        void shouldDelete() {
            when(packRepository.deleteById(2)).thenReturn(true);
            assertTrue(service.deleteById(2));
        }


    }