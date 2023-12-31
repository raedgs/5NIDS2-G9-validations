package tn.esprit.spring.kaddem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class mockito {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private Equipe equipe;

    @Mock
    private Etudiant etudiant;
    @Test
    public void testEquipeEtudiantRelationship() {
        // Create some mock Etudiant objects
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiant1.setNomE("Alice");

        // Create a mock Equipe
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Team A");

        // Define the behavior for the relationship between Equipe and Etudiant
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant1);
        equipe.setEtudiants(etudiants);

        // Verify that the relationship is correctly set up
        Assertions.assertEquals(1, equipe.getEtudiants().size());
        Assertions.assertEquals("Alice", equipe.getEtudiants().iterator().next().getNomE());
    }
}
