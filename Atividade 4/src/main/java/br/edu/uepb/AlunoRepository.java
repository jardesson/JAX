package br.edu.uepb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlunoRepository {
    private TurmaRepository turmaRepository = new TurmaRepository();
    private static HashMap<Integer, Aluno> alunos = new HashMap<>();

    public List<Aluno> getAll() {
        List<Aluno> alunosList = new ArrayList<Aluno>(alunos.values());

        for (Aluno aluno : alunosList){
            aluno.setTurma(turmaRepository.getById(aluno.getIdTurma()));
            if (aluno.getTurma() == null)
                aluno.setIdTurma(0);
        }
        
        return alunosList;
    }

    public Aluno getById(int id) {
        Aluno aluno = alunos.get(id);

        if (aluno != null){
            aluno.setTurma(turmaRepository.getById(aluno.getIdTurma()));
            if (aluno.getTurma() == null)
                aluno.setIdTurma(0);
        }

        return aluno;
    }

    public void create(Aluno aluno) {
        if (aluno.getId() == 0)
            aluno.setId(generateId(alunos.size() + 1));

        aluno.setTurma(turmaRepository.getById(aluno.getIdTurma()));
        alunos.put(aluno.getId(), aluno);
    }

    public void delete(int id) {
        alunos.remove(id);
    }

    public void edit(Aluno aluno) {
        alunos.remove(aluno.getId());
        alunos.put(aluno.getId(), aluno);
    }

    private int generateId(final int possible) {
        if (alunos.containsKey(possible))
            return generateId(possible + 1);
        return possible;
    }
}
