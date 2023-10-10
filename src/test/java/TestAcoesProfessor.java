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
    //Testes de sucesso
    @Test
    public void testBuscaProfessorNaoExistenteRetornaNulo(){
        Professor professor = acoesProfessor.buscaProfessor("Professor");
        assertNull(professor);
    }

    @Test
    public void testBuscaInformacoesDoProfessor(){
        Professor professor = acoesProfessor.buscaProfessor("Soned");
        List<String> predios = new ArrayList<>();
        predios.add("2");

        assertEquals("Soned", professor.getNomeDoProfessor());
        assertEquals("10:00-16:00", professor.getHorarioDeAtendimento());
        assertEquals("integral", professor.getPeriodo());
        assertEquals(7, professor.getSala());
        assertEquals(predios, professor.getPredio());
    }
    @Test
    public void testBuscaInformacoesDeSalaErrada(){
        Professor professor = acoesProfessor.buscaProfessor("Cris");
        List<String> predios = new ArrayList<>();
        predios.add("1");

        assertEquals("Cris", professor.getNomeDoProfessor());
        assertEquals("11:00-17:00", professor.getHorarioDeAtendimento());
        assertEquals("integral", professor.getPeriodo());
        assertNotEquals(5, professor.getSala());
        assertEquals(predios, professor.getPredio());
    }

    @Test
    public void testBuscaInformacoesErradasDoProfessor(){
        Professor professor = acoesProfessor.buscaProfessor("Renzo");

        assertNotEquals("Soned", professor.getNomeDoProfessor());
        assertNotEquals("integral", professor.getPeriodo());
    }

    @Test
    public void testBuscaHorarioDoProfessor(){
        String horarioProfessor = acoesProfessor.buscaHorario("Cris");

        assertEquals("11:00-17:00", horarioProfessor);
    }

    @Test
    public void testBuscaHorarioErradoDoProfessor(){
        String horarioProfessor = acoesProfessor.buscaHorario("Cris");

        assertNotEquals("10:00-16:00", horarioProfessor);
    }

    @Test
    public void testConvertePrediosEmArray(){
        JsonArray predioProfessor = JsonParser
                .parseString(ProfessorConst.Renzo)
                .getAsJsonObject()
                .get("predio")
                .getAsJsonArray();
        List<String> converter = acoesProfessor.convertePrediosEmArray(predioProfessor);

        assertEquals(new ArrayList<>(Arrays.asList("6")), converter);
    }

    @Test
    public void testBuscaProfessorPorNomeExistente(){
        Professor professor = acoesProfessor.buscaProfessor("Soned");
        assertNotNull(professor);
    }

    @Test
    public void testBuscaProfessorPorNomeNaoExistente(){
        Professor professor = acoesProfessor.buscaProfessor("ProfessorInexistente");
        assertNull(professor);
    }

    @Test
    public void testBuscaSalaDoProfessorExistente(){
        int sala = acoesProfessor.buscaProfessor("Soned").getSala();
        assertEquals(7, sala);
    }

    @Test
    public void testBuscaSalaDoProfessorNaoExistente(){
        try {
            int sala = acoesProfessor.buscaProfessor("ProfessorNaoExistente").getSala();
            fail("entrada invalida");
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"Professor.getSala()\" because the return value of \"BuscaProfessor.buscaProfessor(String)\" is null", e.getMessage());
        }
    }
    //Testes de falha
    @Test
    public void testBuscaProfessorNomeNulo(){
        Professor professor = acoesProfessor.buscaProfessor(null);
        assertNull(professor);
    }
    @Test
    public void testBuscaProfessorNomeVazio(){
        Professor professor = acoesProfessor.buscaProfessor("");
        assertNull(professor);
    }

    @Test
    public void testBuscaHorarioProfessorNomeNulo(){
        String horarioProfessor = acoesProfessor.buscaHorario(null);
        assertNull(horarioProfessor);
    }

    @Test
    public void testBuscaSalaDoProfessorNomeNulo(){
        //int sala = acoesProfessor.buscaProfessor(null).getSala();
        try {
            int sala = acoesProfessor.buscaProfessor(null).getSala();
            fail("entrada invalida");
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"Professor.getSala()\" because the return value of \"BuscaProfessor.buscaProfessor(String)\" is null", e.getMessage());
        }
    }

    @Test
    public void testConvertePrediosEmArrayJsonNulo(){
        try {
            List<String> converter = acoesProfessor.convertePrediosEmArray(null);
            fail("Deveria lançar uma exceção para JSON nulo.");
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"com.google.gson.JsonArray.size()\" because \"prediosjson\" is null", e.getMessage());
        }
    }



    @Test
    public void testBuscaHorarioProfessorNomeInexistente(){
        String horarioProfessor = acoesProfessor.buscaHorario("ProfessorInexistente");
        assertNull(horarioProfessor);
    }

    @Test
    public void testConvertePrediosEmArrayJsonVazio(){
        JsonArray predioProfessor = JsonParser.parseString("[]").getAsJsonArray();
        List<String> converter = acoesProfessor.convertePrediosEmArray(predioProfessor);
        assertTrue(converter.isEmpty());
    }

    @Test
    public void testConvertePrediosEmArrayJsonInvalido(){
        try {
            JsonArray predioProfessor = JsonParser.parseString("{invalid}").getAsJsonArray();
            List<String> converter = acoesProfessor.convertePrediosEmArray(predioProfessor);
            fail("Deveria lançar uma exceção para JSON inválido.");
        } catch (Exception e) {
            assertTrue(e instanceof com.google.gson.JsonSyntaxException);
        }
    }
    @Test
    public void testBuscaHorarioProfessorNomeVazio(){

        String horarioProfessor = acoesProfessor.buscaHorario("");
        assertNull(horarioProfessor);

    }
}