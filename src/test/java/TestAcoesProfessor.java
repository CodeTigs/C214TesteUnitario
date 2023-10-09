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
    AcoesProfessor acoesProfessor;

    @Before
    public void setup(){
        service = new MockProfessorService();
        acoesProfessor = new AcoesProfessor(service);
    }
    @Test
    public void TestBunscaInformacoesDoProfessor(){
        Professor professor = acoesProfessor.buscaInformacoesDoProfessor("Soned");
        List<String> predios = new ArrayList<>();
        predios.add("1");
        predios.add("2");

        //Faz assertion
        assertEquals("Soned", professor.getNome());
        assertEquals("10:00a16:00" , professor.getHorarioDeAtendimento());
        assertEquals("integral", professor.getPeriodo());
        assertEquals(1, professor.getSala());
        assertEquals(predios, professor.getPredio());
    }

    @Test
    public void TestbuscaHorarioDoProfessor(){
        String horarioProfessor = acoesProfessor.buscaHorarioDoProfessor("Cris");

        assertEquals("10:00a16:00" , horarioProfessor);
    }

    @Test
    public void TestconvertePrediosEmArray(){
        JsonArray predioProfessor = JsonParser
                .parseString(ProfessorConst.Renso)
                .getAsJsonObject()
                .get("predio")
                .getAsJsonArray();
        List<String> converter = acoesProfessor.convertePrediosEmArray(predioProfessor);

        assertEquals(new ArrayList<>(Arrays.asList("1","2")) , converter);
    }
}
