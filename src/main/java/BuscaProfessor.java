import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class BuscaProfessor {

    ProfessorService professorService;

    public BuscaProfessor(ProfessorService service){
        this.professorService = service;
    }

    ArrayList<String> convertePrediosEmArray(JsonArray prediosjson){
        ArrayList<String> predios = new ArrayList<>();

        for(int i = 0; i < prediosjson.size(); i++) {
            String numero = prediosjson.get(i).getAsString();
            predios.add(numero);
        }
        return predios;
    }

    public Professor buscaProfessor(String nome){
        String professorJson = professorService.busca(nome);
        JsonObject jsonObject = JsonParser.parseString(professorJson).getAsJsonObject();
        ArrayList<String> predio= convertePrediosEmArray(jsonObject.get("predio").getAsJsonArray());
        return new Professor(
                jsonObject.get("nomeDoProfessor").getAsString(),
                jsonObject.get("horarioDeAtendimento").getAsString(),
                jsonObject.get("periodo").getAsString(),
                jsonObject.get("sala").getAsInt(),
                predio
        );
    }

    public String buscaHorario(String nome){
        String professorJson = professorService.busca(nome);
        JsonObject jsonObject = JsonParser.parseString(professorJson).getAsJsonObject();

        return jsonObject.get("horarioDeAtendimento").getAsString();

    }

    public boolean verificaArrayListExistente(String nome){
        boolean professorExistente = professorService.professorExistente(nome);

        if(professorExistente){
            return true;
        }else{
            return false;
        }
    }
}