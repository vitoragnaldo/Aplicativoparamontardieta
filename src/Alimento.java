import java.util.ArrayList;
import java.util.Scanner;

record Alimento(String nome, double calorias, double proteinas, double carboidratos, double gorduras) {
    // Construtor

    // Exibe as informações do alimento
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Calorias: " + calorias + " kcal");
        System.out.println("Proteínas: " + proteinas + " g");
        System.out.println("Carboidratos: " + carboidratos + " g");
        System.out.println("Gorduras: " + gorduras + " g");
        System.out.println("----------------------------");
    }
}

class Dieta {
    private final double caloriasMeta;
    private final double proteinasMeta;
    private final double carboidratosMeta;
    private final double gordurasMeta;
    private final ArrayList<Alimento> alimentos;

    public Dieta(double caloriasMeta, double proteinasMeta, double carboidratosMeta, double gordurasMeta) {
        this.caloriasMeta = caloriasMeta;
        this.proteinasMeta = proteinasMeta;
        this.carboidratosMeta = carboidratosMeta;
        this.gordurasMeta = gordurasMeta;
        this.alimentos = new ArrayList<>();
    }

    public void adicionarAlimento(Alimento alimento) {
        alimentos.add(alimento);
    }

    public void montarPlano() {
        double caloriasTotais = 0;
        double proteinasTotais = 0;
        double carboidratosTotais = 0;
        double gordurasTotais = 0;

        System.out.println("\nPlano de Dieta Sugerido:");
        for (Alimento alimento : alimentos) {
            caloriasTotais += alimento.calorias();
            proteinasTotais += alimento.proteinas();
            carboidratosTotais += alimento.carboidratos();
            gordurasTotais += alimento.gorduras();
            alimento.exibirInformacoes();
        }

        System.out.println("Total de calorias consumidas: " + caloriasTotais + " kcal");
        System.out.println("Meta de calorias: " + caloriasMeta + " kcal");
        System.out.println("Total de proteínas consumidas: " + proteinasTotais + " g");
        System.out.println("Meta de proteínas: " + proteinasMeta + " g");
        System.out.println("Total de carboidratos consumidos: " + carboidratosTotais + " g");
        System.out.println("Meta de carboidratos: " + carboidratosMeta + " g");
        System.out.println("Total de gorduras consumidas: " + gordurasTotais + " g");
        System.out.println("Meta de gorduras: " + gordurasMeta + " g");

        if (caloriasTotais > caloriasMeta) {
            System.out.println("Atenção! Você excedeu a meta de calorias.");
        } else {
            System.out.println("Você está dentro da sua meta de calorias.");
        }
    }
}

class AppDieta {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alimento> bancoAlimentos = new ArrayList<>();

        // Pre-cadastrando alguns alimentos
        bancoAlimentos.add(new Alimento("Arroz", 130, 2.5, 28, 0.3));
        bancoAlimentos.add(new Alimento("Feijão", 80, 5.0, 15, 0.5));
        bancoAlimentos.add(new Alimento("Frango", 200, 30.0, 0, 5.0));
        bancoAlimentos.add(new Alimento("Banana", 90, 1.0, 23, 0.3));
        bancoAlimentos.add(new Alimento("Ovo", 155, 13.0, 1.0, 11.0));

        // Definindo as metas do usuário
        System.out.print("Digite sua meta de calorias diárias: ");
        double caloriasMeta = scanner.nextDouble();
        System.out.print("Digite sua meta de proteínas diárias (g): ");
        double proteinasMeta = scanner.nextDouble();
        System.out.print("Digite sua meta de carboidratos diários (g): ");
        double carboidratosMeta = scanner.nextDouble();
        System.out.print("Digite sua meta de gorduras diárias (g): ");
        double gordurasMeta = scanner.nextDouble();

        Dieta dieta = new Dieta(caloriasMeta, proteinasMeta, carboidratosMeta, gordurasMeta);

        int opcao = 0;
        while (opcao != 3) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar alimento à dieta");
            System.out.println("2. Montar plano de dieta");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Mostrar alimentos disponíveis
                    System.out.println("\nAlimentos disponíveis:");
                    for (int i = 0; i < bancoAlimentos.size(); i++) {
                        System.out.println(i + 1 + ". " + bancoAlimentos.get(i).nome());
                    }
                    System.out.print("Escolha o número do alimento que deseja adicionar à dieta: ");
                    int alimentoEscolhido = scanner.nextInt() - 1;
                    if (alimentoEscolhido >= 0 && alimentoEscolhido < bancoAlimentos.size()) {
                        dieta.adicionarAlimento(bancoAlimentos.get(alimentoEscolhido));
                        System.out.println("Alimento adicionado com sucesso!");
                    } else {
                        System.out.println("Alimento inválido.");
                    }
                    break;

                case 2:
                    dieta.montarPlano();
                    break;

                case 3:
                    System.out.println("Saindo do aplicativo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
