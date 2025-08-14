import java.util.Scanner;
public class Main {

    // Função Quantidades de atendimentos registrados
    static int perguntaQuantidade(Scanner leia) {
        System.out.println("================================================================================");
        System.out.println("                  Bem-vindo ao CarWash Lavação Automotiva                       ");
        System.out.println("                  Aberto: Segunda a Sexta  --> 8h às 19h                             ");
        System.out.println("                                   Sábados --> 8h às 13h                             ");
        System.out.println("================================================================================");
        System.out.print("\nInforme a quantidade de atendimentos que deseja registrar: ");
        int totalAtendimentos = leia.nextInt();
        System.out.println();
        leia.nextLine(); // Limpa o buffer
        return totalAtendimentos; // retorna os total que deseja processar
    }

    // Função Registrar Atendimento - opção 1 do Menu
    static void registrarAtendimento(Scanner leia,String[] formaPagamentoTexto, double[][] matrizPrecos, String[] nomeCliente, String[] telefoneCliente, String[] nomeFuncionario, String[] placa, int[] tiposVeiculo, int[] tiposServico, int[] formaPagamento, double[] valoresAtendimentos, int atendimentosFeitos) {

        System.out.println("================================================================================");
        System.out.println("                      REGISTRANDO ATENDIMENTO...                                ");
        System.out.println("================================================================================");

            System.out.println("\n CADASTRO DO ATENDIMENTO --> " + (atendimentosFeitos + 1));

            System.out.print("Nome: ");
            nomeCliente[atendimentosFeitos] = leia.nextLine();

        String telefone;
        do {
            System.out.print("Informe o telefone (somente números, com DDD): ");
            telefone = leia.nextLine();

            // Remove espaços em branco
            telefone = telefone.trim();

            // Validação: deve conter apenas dígitos e ter entre 10 e 11 caracteres (ex: 48999999999)
            if (!telefone.matches("\\d{10,11}")) {
                System.out.println("Telefone inválido! Digite apenas números, com DDD (ex: 48123456789).");
            }
        } while (!telefone.matches("\\d{10,11}"));

            telefoneCliente[atendimentosFeitos] = telefone;

            String auxNomeFunci; // Variável auxiliar p/ validação dos nomes
            do {
                System.out.print("Informe o nome do lavador responsável (Kiko, Ricardo ou Jorge): ");
                auxNomeFunci = leia.nextLine().toUpperCase(); // toUpperCase transformando texto em maiúsculo
                if  (!auxNomeFunci.equals("KIKO") // se o nome digitado ! NÃO for kiko e ricardo e jorge, tente de novo
                        && !auxNomeFunci.equals("RICARDO")
                        && !auxNomeFunci.equals("JORGE")) {
                    System.out.println("\nNome inválido! Digite novamente...\n");

                }
            } while (!auxNomeFunci.equals("KIKO") // Quando achar o nome Ok ele quebra o laço
                    && !auxNomeFunci.equals("RICARDO")
                    && !auxNomeFunci.equals("JORGE"));

            nomeFuncionario[atendimentosFeitos] = auxNomeFunci; // vetor recebendo minha variavel auxiliar

             String placaDigitada; // Variável temporaria, depois jogo dentro da função, mais fácil para manipular
             boolean placaValida; // Variável auxuliar para guardar se a placa é válida ou não

          do { // Repete até digitar uma placa válida
             System.out.print("Placa do veiculo (formato: ABC1D23): ");
             placaDigitada = leia.nextLine().trim().toUpperCase(); // tira os espaços(trim()), (toUpperCase()) converte os caracter tudo em Maiúscula

             placaValida = validarPlaca(placaDigitada); // Chama a função que válida se a placa está no formato da condição

             if (!placaValida) { // Se a placa for inválida, informa o usuario
                System.out.println("\nPlaca inválida... Digite novamente.\n ");
             }

          } while (!placaValida); // enquanto a placa for inválida repete

            placa[atendimentosFeitos] = placaDigitada;
            // Vetor Placa recebendo a placa digita Valida
            // Armazena a placa no vetor na posição do atendimento atual
        System.out.println("\n================================================================================");

            // Verificando o tipo do veiculo se é  validando
            int tiposVeiculoValidados; // Variavel temporaria, depois gravo ela na função
            String tipoVeiculoTexto; // variavel temporaria

         while (true) { // Loop roda atá que algo pare ele (break)
             System.out.println("TIPO DE VEÍCULO:");
             System.out.print("1 - PEQUENO (POPULARES)\n2 - MÉDIO (SUV E PICAPE)\n3 - GRANDE (VAN E MICRO-ÔNIBUS)");
             System.out.println("\nESCOLHA (1 A 3):");
             tipoVeiculoTexto = leia.nextLine();
             tiposVeiculoValidados = Integer.parseInt(tipoVeiculoTexto); // Convertendo o texto em número

             if (validarOpcao(tiposVeiculoValidados , tipoVeiculoTexto)) { // Verificando o valor digitado é valido com a Função
                 System.out.println("Tipo de veiculo selecionado: " + tipoVeiculoTexto);
                 System.out.println("================================================================================");

                 tiposVeiculo[atendimentosFeitos] = tiposVeiculoValidados; // Vetor tiposVeiculo esta recebendo os valoresAtendimentos Atendimentos  digitados (tipo do veiculo(1,2,ou3)) já válidados
                 break; // Sai do laço quando o valor digitado é correto
             }
             System.out.println("================================================================================");

         }
         // Verificando o tipo de serviço se é válido
         int tiposServicoValidos; // Variável temporaria, depois jogo ela para o vetor tipoServico
         String tipoServicoTexto;

         while (true) { // Laço roda até que encontrar o break
             System.out.println("TIPO DO SERVIÇO:");
             System.out.print("1 - LAVAÇÃO EXTERNA\n2 - LAVAÇÃO EXTERNA + INTERNA\n3 - LAVAÇÃO EXTERNA + INTERNA + CERA");
             System.out.println("\nESCOLHA (1 A 3):");
             tipoServicoTexto = leia.nextLine();
             tiposServicoValidos = Integer.parseInt(tipoServicoTexto); // Convertendo de texto para número

             if (validarOpcao(tiposServicoValidos, tipoServicoTexto)) {
                 System.out.println("Tipo de Serviço selecionado: " + tipoServicoTexto);
                 System.out.println("================================================================================");

                 tiposServico[atendimentosFeitos] = tiposServicoValidos; // Depois de validar passo a variavel temporaria para o vetor fixo
                 break; // Quebra o laço quando a condição for verdadeira
             }
             System.out.println("================================================================================");
         }
         // Verificando a forma de pagamento se é válida
         int formaPagamentoValidada; // variável temporaria, depois jogo ela no Vetor formaPagamento[]
         String pagamentoTexto;
         while (true) { // Loop repete até que o usuário digite valor válido
             System.out.println("FORMA DE PAGAMENTO:");
             System.out.print("1 - DÉBITO\n2 - CRÉDITO\n3 - PIX");
             System.out.println("\nESCOLHA (1 A 3):");
             pagamentoTexto = leia.nextLine();
             formaPagamentoValidada = Integer.parseInt(pagamentoTexto); // Convertendo texto digitado para número

             if (validarOpcao(formaPagamentoValidada, pagamentoTexto)) {  // Verificando o valor digitado corresponde coma Função válidar
                 System.out.println("Forma de pagamento selecionada: " + pagamentoTexto);
                 System.out.println("================================================================================");

                 // Convertendo o número digitado em String com o operador Ternário
                 formaPagamentoTexto[atendimentosFeitos] =
                         (formaPagamentoValidada == 1) ? "DÉBITO" : // Se a forma for igual a 1 então(?) recebe "tal", senão, recebe(?) "tal"
                         (formaPagamentoValidada == 2) ? "CRÉDITO" : "PIX";

                 formaPagamento[atendimentosFeitos] = formaPagamentoValidada; // Vetor rececendo a forma de pagamento válida do usúario
                 break; // Quebra o laço while, quando a condição for true
             }
             System.out.println("================================================================================");
         }

         int veiculo = tiposVeiculo[atendimentosFeitos]; // Variável recebendo o Vetor tiposVeiculo[], fica melhor para por dentro da matriz, questão estética
         int servico = tiposServico[atendimentosFeitos];

         // Criando uma variável temporaria para pegar o preço da matriz
         double valorBase = matrizPrecos[veiculo - 1][servico - 1]; // matriz com o valor digitado do usuário o tipo de veículo (1,2 ou 3) e o tipo de serviço (1,2 ou 3)

         // Aplicando desconto se for no PIX
         double valorFinal; // variável temporária, depois jogo dentro do vetor, pq fica mais fácil para manipular e temos só incrementar ela dentro do vetor, e tbm ela vai receber a função com o desconto do Pix(com o valor do tipo serviço e tipo veículo)

         if (formaPagamentoValidada == 3) { // pagamento via PIX
            valorFinal = descontoPix(valorBase); // Variável temporaria recebe a função descontoPix e como parametro é o valor da matriz, que seria o tipoVeiculo e tipoServico
         } else { // se não pagou no Pix então paga só o valor Base
            valorFinal = valorBase; // sem desconto
         }
         valoresAtendimentos[atendimentosFeitos] = valorFinal; // Vetor recebendo o valor final, com o sem desconto

         System.out.println("ATENDIMENTO " + (atendimentosFeitos + 1) + " CADASTRADO COM SUCESSO!");
    }

    //Função Para Validar Campos(FormaPagamento, tipoVeiculo, TipoServiço) Opção (1 a 3)
    static boolean validarOpcao(int opcaoUsuario, String textoOpcaoUsuario) {
        if (opcaoUsuario >= 1 && opcaoUsuario <= 3) {
            return true; // se o valor for maior que 1 e menor que 3, Retorna Verdadeiro
        } else { // False entre no else
            System.out.println();
            System.out.println("Erro valor: "+ textoOpcaoUsuario + " é inválido!");
            System.out.println("Por favor, digite um número entre 1 a 3.");
            return false; // Senão Retorna Falso
        }
    }

    // Função Validar Placa, verificar se a placa de um veículo digitada pelo usuário está no formato correto
    static boolean validarPlaca(String placa) { // se a validação for Ok, armazena o valor da placa no vetor placa[]

        return placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}"); // Ela só valida se a string segue o formato correto
    }

    // Função Buscar por Placa - opção 2 do Menu
    static void buscaPlaca(Scanner leia, String[] nomeFuncionario, String[] nomeCliente, String[] placa, String[]  telefoneCliente, int[] tiposVeiculo, int[] tiposServico, String[] opcoesVeiculos, String[] opcoesServicos, double[] valoresAtendimentos) {

        System.out.println("================================================================================");
        System.out.println("                             BUSCAR POR PLACA                                   ");
        System.out.println("================================================================================");

        // Laço infinito para repetir a busca até o usuário escolher sair ou voltar ao menu
        while (true) {
            System.out.print("\nDigite a placa para busca: ");
            String placaBusca = leia.nextLine().trim().toUpperCase();
            System.out.println();

            boolean encontrado = false; // Inicia a variável com falso, não achou

            // Procura a placa nos registros
            for (int i = 0; i < placa.length; i++) {
                if (placa[i] != null && placa[i].equalsIgnoreCase(placaBusca)) { // Confirma se a placa não esta vazia(null) e Compara se a placa cadastrada na posição i é igual à placa que o usuário digitou (placaBusca)
                    // Encontrou a Placa
                    System.out.println("==============================================================================");
                    System.out.println("                        ATENDIMENTO ENCONTRADO                                ");
                    System.out.println("==============================================================================");
                    System.out.printf(
                                    " • Cliente......................: %s\n" +
                                    " • Telefone.....................: %s\n" +
                                    " • Veículo......................: %s\n" +
                                    " • Placa........................: %s\n" +
                                    " • Serviço......................: %s\n" +
                                    " • Valor total..................: R$ %.2f\n",
                            nomeCliente[i],
                            telefoneCliente[i],
                            opcoesVeiculos[tiposVeiculo[i] - 1],
                            placa[i],
                            opcoesServicos[tiposServico[i] - 1],
                            valoresAtendimentos[i]
                    );
                    encontrado = true; // A placa foi encontrada
                    break; // Quebra o For quando a condição for verdadeira
                }
            }
            if (encontrado) { // Se a placa for encontrada sai do while
                return; // Sai do While
            }
            int escolha; // variáveis para receber oq usuario digitou
            String escolhaTexto;
            // Se não encontrou a placa, entra nessa opção
            System.out.println();
            System.out.println("PLACA NÃO ENCONTRADA!");
            System.out.println("1. BUSCAR NOVAMENTE");
            System.out.println("2. VOLTAR AO MENU PRINCIPAL");
            System.out.println("3. SAIR DO PROGRAMA");
            System.out.println("ESCOLHA (1 A 3): ");
            escolhaTexto = leia.nextLine();
            escolha = Integer.parseInt(escolhaTexto); // Convertendo a variavel para número

            if (!validarOpcao(escolha, escolhaTexto)) {
                  continue; // volta para o começo do while para pedir opção novamente a opção
            }
                    // Tratamento das opções escolhidas pelo usuário
            switch (escolha) {
                case 1:
                    System.out.println("OK, BUSCANDO NOVAMENTE...\n");
                    break;
                case 2:
                    System.out.println("VOLTANDO AO MENU PRINCIPAL...");
                    return; // Volta no menu externo
                case 3:
                    System.out.println("ENCERRANDO O PROGRAMA...");
                    System.exit(0); // Encerra o programa completamente
                    break;
            }
            System.out.println("=============================================================================");
        }
    }
    // Função Desconto PIX (5%)
    static double descontoPix(double valor) {
        double desconto = valor * 0.05; // - 4,50
        System.out.printf(" • Pagamento via Pix confirmado." +
                             "Desconto de 5%% aplicado R$%.2f\n", desconto);
        System.out.println("================================================================================");
        return valor - desconto; // retorna o valor com o desconto aplicado
    }

    // Função para percorrer os nomes dos funcionarios, depois mostro a qtd de serviço por nome
    static void exibirAtedimentoFuncionario(String[] nomeFuncionario,String[] nomeFixofuncionario, int atendimentosFeitos) {
        
        int[] contFuncionario = new int[nomeFixofuncionario.length]; // Vetor que vai contar quantos atendimentos cada funcionário fez

        // loop para percorrer todos os atendimentos realizados
        for (int i = 0; i < atendimentosFeitos; i++) { // Percorro o total de atendimentos feitos
                if (nomeFuncionario[i] != null) { // verifica se há nome na posição atual
                    for (int j = 0; j < nomeFixofuncionario.length; j++) { // percorrendo os meus nomes Fixos
                        if (nomeFuncionario[i].equalsIgnoreCase(nomeFixofuncionario[j])) { // se o nome digitado for igual ao nome que tenho fixo, então armazeno o valor
                            contFuncionario[j]++; // então o nome daquele funcionario acumula o valor
                            break; // Quebra o for, achei oq queria, pode sair
                        }
                    }
                }
        }
        System.out.println("\n==============================================================================");
        System.out.println("                       ATENDIMENTOS POR FUNCIONÁRIO                             ");
        System.out.println("================================================================================");
        // Listando o nome e a quantidade de atendimento por funcionário
        for (int i = 0; i < nomeFixofuncionario.length; i++) {
            System.out.printf("• %-10s --> (%d atendimento(s))\n",nomeFixofuncionario[i], contFuncionario[i]);
        }
  }

    // Função Relatorio Geral (Listagem média, percentual tipoVeiculo, percentual tipoServico, valorTotal) - opção 3 do Menu
    static void relatorioGeral(String[]  telefoneCliente, String[] placa, String[] nomeFuncionario, String[] formaPagamentoTexto, double[] valoresAtendimentos, String[] nomeCliente, int[] tiposVeiculo, int[] tiposServico, String[] opcoesVeiculos, String[] opcoesServicos, int atendimentosFeitos) {

        System.out.println("================================================================================");
        System.out.println("                            RELATÓRIO GERAL                                     ");
        System.out.println("================================================================================");

        double valorTotal = 0; // Acumulador para somar o valor total arrecadado
        int[] contVeiculo = new int[opcoesVeiculos.length]; // Vetor p/ contar qtd de atendimento p/ tipo de veiculo
        int[] contServico = new int[opcoesServicos.length];// Vetor p/ contar qtd de atendimento p/ tipo de serviço

        // Relação geral dos clientes: nome, tipo, serviço e valor pago
        for (int i = 0; i < atendimentosFeitos; i++) {

            System.out.println("ATENDIMENTO " + (i + 1) + ":");
            System.out.printf(
                            " • Cliente......................: %s\n" +
                            " • Telefone.....................: %s\n" +
                            " • Veículo......................: %s\n" +
                            " • Placa........................: %s\n" +
                            " • Servíço......................: %s\n" +
                            " • Forma de Pagamento...........: %s\n" +
                            " • Valor Total..................: R$ %.2f\n" +
                            " • Funcionário..................: %s\n",
                    nomeCliente[i],
                    telefoneCliente[i],
                    opcoesVeiculos[tiposVeiculo[i] - 1],
                    placa[i],
                    opcoesServicos[tiposServico[i] - 1],
                    formaPagamentoTexto[i],
                    valoresAtendimentos[i],
                    nomeFuncionario[i]
            );
            System.out.println("---------------------------------------------------------------------------");


            valorTotal += valoresAtendimentos[i]; // Acumula o valor total arrecadado
            contVeiculo[tiposVeiculo[i] - 1]++; // Incrementa o contador para do tipo de veiculo
            contServico[tiposServico[i] - 1]++; // Incrementa o contador para o tipo do serviço que foi escolhido
        }
        // Calcula a média do valor por atendimento, só se houver atendimentos registrados
        // Garantindo que não ira ter divisão por zero
        System.out.println("\n============================ RESUMO FINANCEIRO =================================");

        double mediaValor = (atendimentosFeitos > 0) ? valorTotal / atendimentosFeitos : 0;
        System.out.printf(" • %-35s -->     R$ %6.2f\n", "Valor Total Arrecadado", valorTotal); // Valor total arrecadado
        System.out.printf(" • %-35s -->     R$ %6.2f\n", "Média por Atendimento", mediaValor); // Média por atendimento
        System.out.println("================================================================================");

        System.out.println("Percentual por Tipo de Veiculo: ");
        for (int i = 0; i < opcoesVeiculos.length; i++) { // Loop para imprimir o percentual e quantidade de atendimentos por tipo de veiculo

            // Garantindo que não vai ter divisão por zero
            double perceVeiculo = (atendimentosFeitos > 0) ? (contVeiculo[i] * 100.0) / atendimentosFeitos : 0; // Calcula o percentual por tipo de veiculo

            //  Nome do tipo do veículo, o percentual e a quantidade de atendimentos
            System.out.printf(" • %-35s --> %6.1f%% (%d atendimento(s))\n", opcoesVeiculos[i], perceVeiculo, contVeiculo[i]);
        }
        System.out.println("===============================================================================");
        System.out.println("Percentual por Tipo de Serviço: ");
        for (int i = 0; i < opcoesServicos.length; i++) { // Loop para imprimir o percentual e quantidade de atendimentos por tipo de serviço

            double perceServico = (atendimentosFeitos > 0) ? (contServico[i] * 100.0) / atendimentosFeitos : 0; // Calcula o percentual de cada tipo de serviço

            // Aqui mostra o Nome do tipo do serviço, o percentual e a quantidade de atendimentos
            System.out.printf(" • %-35s --> %6.1f%% (%d atendimento(s))\n", opcoesServicos[i], perceServico, contServico[i]);
        }
        System.out.println();

    }

    // Função para exibir
    static void exibirEstatisticasUtilizacao(int[] tiposServico, int[] tiposVeiculo, String[] opcoesVeiculos, String[] opcoesServicos, int atendimentosFeitos) {
        System.out.println("================================================================================");
        System.out.println("                            ESTATÍSTICAS DE UTILIZAÇÃO                          ");
        System.out.println("================================================================================");

        if (atendimentosFeitos == 0) {
            System.out.println("Não há atendimentos regístrados para gerar estatísticas de utilização.");
            return;
        }
        // Arrays para contar atendimentos
        int[] contServicos = new int[opcoesServicos.length];
        int[] contVeiculos = new int[opcoesVeiculos.length];

        // Conta quantas vezes cada serviço e veiculo foi utilizado
        for (int i = 0; i < atendimentosFeitos; i++) {
            contServicos[tiposServico[i] - 1]++; // "-1" porque o índice começa em 1, mas vetor começa em 0
            contVeiculos[tiposVeiculo[i] - 1]++; // Acumulando o tipoVeiculo (1,2 ou 3)
        }

        // Função auxiliar para mostrar estatísticas
        mostrarEstatisticas("Veículos", opcoesVeiculos, contVeiculos);
        System.out.println("==========================================================================================");
        mostrarEstatisticas("Serviços", opcoesServicos, contServicos);
    }
    // Função para exibir o (Tipos Utilizados o Maior, menor e igual)
    static void mostrarEstatisticas(String titulo, String[] nomes, int[] contagens) {
        System.out.println("\n" + titulo + ":");

        int maior = contagens[0];
        int menor = contagens[0];

        // Encontra o maior e menor valor no vetor
        for (int i = 1; i < contagens.length; i++) {
            if (contagens[i] > maior) {
                maior = contagens[i];
            }
            if (contagens[i] < menor) {
                menor = contagens[i];
            }
        }
        for (int i = 0; i < contagens.length; i++) {
            String observacao = ""; // Vai guardar as mensagens que vão aparecer (ex: menos usado)
            boolean temTexto = false; // Variável para controlar se já adicionou texto

            // Verificando se é o maior valor
            if (contagens[i] == maior) {
                observacao += "MAIS usado";
                temTexto = true; // Agora tem texto
            }
            // Verificando se é o menor valor
            if (contagens[i] == menor) {
                if (temTexto) {
                    observacao += " / "; // Adiciona o separador só se já tinha texto antes
                }
                observacao += "MENOS usado"; // se for o menor escreve
                temTexto = true; // Agora tem texto
            }
            // Verifica se existe valores iguais
            for (int j = 0; j < contagens.length; j++) {
                if (i != j && contagens[i] == contagens[j]) { // garante que não compare a categoria com ela mesma e verifica se categoria i é igual a categoria j
                    if (temTexto) {
                        observacao += " / "; // Adiciona o separador só se já tinha texto antes
                    }
                    observacao += "IGUAL a " + nomes[j]; // mostra o nome da outra categoria que tem amesma qtd
                    break; // só mostra uma igualdade e não fica repetindo várias vezes
                }
            }
            System.out.printf(" • %-35s --> %4d atendimento(s)    %s%n", nomes[i], contagens[i], observacao);
        }
    }

    public static void main(String[] args) { // Programa começa executar por aqui

        Scanner leia = new Scanner(System.in);

        int totalAtendimentos = perguntaQuantidade(leia); // Chama a função para perguntar a quantidade

        // Inicialização dos arrays
        String[] nomeCliente = new String[totalAtendimentos];
         String[]  telefoneCliente = new String[totalAtendimentos];
        String[] placa = new String[totalAtendimentos];
        int[] tiposVeiculo = new int[totalAtendimentos];
        int[] tiposServico = new int[totalAtendimentos];
        int[] formaPagamento = new int[totalAtendimentos];
        double[] valoresAtendimentos = new double[totalAtendimentos];
        int atendimentosFeitos = 0; // Contador de atendimentos realizados
        String[] formaPagamentoTexto = new String[totalAtendimentos];
        String[] nomeFuncionario = new String[totalAtendimentos]; // guarda quem atendeu cada cliente
        String[] nomeFixofuncionario = {"Kiko", "Ricardo", "Jorge"}; // nomes que já tenho fixo no meu sistema

        String[] opcoesVeiculos = {
                "PEQUENO (POPULARES)",
                "MÉDIO (SUV E PICAPE)",
                "GRANDE (VAN E MICRO-ÔNIBUS)"
        }; // VETOR TIPO VEÍCULOS

        String[] opcoesServicos = {
                "LAVAÇÃO EXTERNA",
                "LAVAÇÃO EXTERNA + INTERNA",
                "LAVAÇÃO EXTERNA + INTERNA + CERA"
        }; // VETOR TIPO SERVIÇOS

        double[][] matrizPrecos = { // Matriz de preços [veiculo][serviço]
                {50, 70, 90}, // 1 - Lavação externa - TipoVeiculo1
                {70, 90, 110}, // 2 - Lavação externa + interna - TipoVeiculo2
                {90, 110, 130} // 3 - Lavação externa + interna + cera TipoVeiculo 3
        };
        boolean sair = false; // variavel auxiliar para encerrar o programa
        // Laço, repete até que sair seja true
        while (!sair) {
            System.out.println("===============================================================================");
            System.out.println("                                    MENU                                        ");
            System.out.println("===============================================================================");
            System.out.println("1. REGISTRAR ATENDIMENTO");
            System.out.println("2. BUSCAR POR PLACA");
            System.out.println("3. RELATÓRIO GERAL");
            System.out.println("4. ESTATÍSTICAS DE UTILIZAÇÃO");
            System.out.println("5. ATENDIMENTOS POR FUNCIONÁRIO");
            System.out.println("6. SAIR");
            System.out.println("ESCOLHA  (1 A 6): ");

            int opcao = leia.nextInt();
            leia.nextLine(); // Limpar buffer
            System.out.println();

            if (opcao < 1 || opcao > 6) { // validando a opcao do menu
                System.out.println("Opção inválida! Digite novamente entre 1 e 6.");
                continue;  // volta para o início do while para pedir a opção de novo

            }
            // Chamando as funções para cada opção que o usuario digitar
            switch (opcao) {
                case 1:
                    if (atendimentosFeitos == totalAtendimentos) { // Condição para que o porgrama cadastre q qtd que o usuario informou
                        System.out.println("Limite de atendimentos atingido! Não é possível registrar mais.\n");
                    } else {
                        registrarAtendimento(leia,formaPagamentoTexto, matrizPrecos, nomeCliente,  telefoneCliente, nomeFuncionario, placa, tiposVeiculo, tiposServico, formaPagamento, valoresAtendimentos, atendimentosFeitos);
                        atendimentosFeitos++; // Acumulando os atendimentos feitos
                    }
                    break;
                case 2:
                    buscaPlaca(leia, nomeFuncionario, nomeCliente, placa,  telefoneCliente, tiposVeiculo, tiposServico, opcoesVeiculos, opcoesServicos, valoresAtendimentos);
                    break;
                case 3:
                    relatorioGeral(telefoneCliente, placa, nomeFuncionario, formaPagamentoTexto, valoresAtendimentos, nomeCliente, tiposVeiculo, tiposServico, opcoesVeiculos, opcoesServicos, atendimentosFeitos);
                    break;
                case 4:
                    exibirEstatisticasUtilizacao(tiposServico, tiposVeiculo, opcoesVeiculos, opcoesServicos, atendimentosFeitos);
                    break;
                case 5:
                    exibirAtedimentoFuncionario(nomeFuncionario,nomeFixofuncionario,atendimentosFeitos);
                    break;
                case 6:
                    String confirmar;
                    do {
                        System.out.print("Tem certeza que deseja sair? (S/N): ");
                        confirmar = leia.nextLine().trim().toUpperCase();

                        if (confirmar.equals("S")) { // Verifica a resposta do usuário se for S entra
                            System.out.println("Obrigado por utilizar o sistema da CarWash. Até a próxima!");
                            System.out.println("Programa encerrado...");
                            sair = true; // verdadeiro encerra o programa
                        } else if (confirmar.equals("N")) { // Se digitou "N", apenas informa que a ação foi cancelada e retorna ao menu
                            System.out.println("Cancelado. Voltando ao menu...");
                        } else {
                            System.out.println("Entrada inválida. Digite apenas S ou N.");
                        }
                        // Repete o processo enquanto a resposta não for "S" nem "N"
                    } while (!confirmar.equals("S") && !confirmar.equals("N"));
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente entre 1 e 6.");
                    break;
            }

        }
        leia.close();
    }

}








