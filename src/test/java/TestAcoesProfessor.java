import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestAcoesProfessor {

    ProfessorService service;
    BuscaProfessor acoesProfessor;

    @Before
    public void setup(){
        service = new MockProfessorService();
        acoesProfessor = new BuscaProfessor(service);
    }
    @Test
    public void TestBunscaInformacoesDoProfessor(){
        Professor professor = acoesProfessor.buscaProfessor("Soned");
        List<String> predios = new ArrayList<>();
        predios.add("2");

        //Faz assertion
        assertEquals("Soned", professor.getNomeDoProfessor());
        assertEquals("10:00-16:00" , professor.getHorarioDeAtendimento());
        assertEquals("integral", professor.getPeriodo());
        assertEquals(7, professor.getSala());
        assertEquals(predios, professor.getPredio());
    }

    @Test
    public void TestbuscaHorarioDoProfessor(){
        String horarioProfessor = acoesProfessor.buscaHorario("Cris");

        assertEquals("10:00-16:00" , horarioProfessor);
    }

    @Test
    public void TestconvertePrediosEmArray(){
        JsonArray predioProfessor = JsonParser
                .parseString(ProfessorConst.Renzo)
                .getAsJsonObject()
                .get("predio")
                .getAsJsonArray();
        List<String> converter = acoesProfessor.convertePrediosEmArray(predioProfessor);

        assertEquals(new ArrayList<>(Arrays.asList("1","2")) , converter);
    }
}
