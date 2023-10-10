public class MockProfessorService implements ProfessorService {

    @Override
    public String busca(String nome) {

        if (nome == "Soned") {
            return ProfessorConst.Soned;
        } else if (nome == "Cris") {
            return ProfessorConst.Cris;
        } else if (nome == "Renzo") {
            return ProfessorConst.Renzo;
        }else if(nome == null){
            return "Professor não constado";
        }else {
            return "Professor não constado";
        }
    }

    @Override
    public boolean professorExistente(String nome) {
        return false;
    }
}