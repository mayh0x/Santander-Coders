public class Main {
    public static void main(String[] args) {
        // Criação de objetos
        Agenda agendaProf = new Agenda("Agenda professor", "a agenda do professor");
        Agenda agendaDiret = new Agenda("Agenda dirtetor", "a agenda do diretor");
        Agenda agendaSecret = new Agenda("Agenda secretário", "a agenda do secretário");
        Agenda agendaCoord = new Agenda("Agenda coordenador", "a agenda do coordenador");
        Professor professor1 = new Professor("Maria", "111111", 1200, agendaProf);
        Professor professor2 = new Professor("João", "222222", 1100, agendaProf);
        Artigo artigo1 = new Artigo("Tecnologia", "Matematica computacional", "a historia da matemática computacional");
        Artigo artigo2 = new Artigo("História", "Guerra mundial", "como foi a guerra mundial");
        Turma turma1 = new Turma("manhã", "programação");
        Turma turma2 = new Turma("noite", "letras");
        Diretor diretor = new Diretor("Paulo", "333333", 3000, agendaDiret);
        Secretario secretario = new Secretario("Junior", "444444", 500, agendaSecret);
        Coordenador coordenador = new Coordenador("Josefina", "555555", 6000, agendaCoord);

        // Executando métodos
        coordenador.alocarProfessor(professor1);
        coordenador.alocarProfessor(professor2);

        System.out.println("-------------------------------------------");

        secretario.listarAgenda(diretor);
        secretario.listarAgenda(coordenador);
        secretario.listarAgenda(professor1);
        secretario.listarAgenda(professor2);
        secretario.listarAgenda(secretario);

        System.out.println("-------------------------------------------");

        System.out.println(diretor.exibirProfessoresOrientados());
        diretor.orientarProfessor(professor1);
        System.out.println(diretor.exibirProfessoresOrientados());
        diretor.orientarProfessor(professor1);
        System.out.println(diretor.exibirProfessoresOrientados());
        diretor.orientarProfessor(professor2);
        System.out.println(diretor.exibirProfessoresOrientados());

        System.out.println("-------------------------------------------");

        professor1.cadastrarTurma(turma1);
        System.out.println(professor1);
        professor1.cadastrarTurma(turma1);
        System.out.println(professor1);
        professor2.cadastrarTurma(turma2);
        System.out.println(professor2);

        System.out.println("-------------------------------------------");

        professor1.cadastrarArtigo(artigo1);
        System.out.println(professor1);
        professor1.cadastrarArtigo(artigo1);
        System.out.println(professor1);
        professor2.cadastrarArtigo(artigo2);
        System.out.println(professor2);
    }
}