public class MockProfessorService implements ProfessorService {

    @Override
    public String buscaProfessor(String nome) {

        if (nome == "Soned") {
            return ProfessorConst.Soned;
        } else if (nome == "Cris") {
            return ProfessorConst.Cris;
        } else if (nome == "Renso") {
            return ProfessorConst.Renso;
        } else {
            return "Professor não constado";
        }
    }

}
