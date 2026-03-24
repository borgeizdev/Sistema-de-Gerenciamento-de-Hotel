package com.mycompany.projetindadri;

import java.util.Scanner;

public class ProjetindaDri {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Estruturas de Dados
        String[] nomeHospede = new String[100];
        int[] numReserva = new int[100];
        boolean[] statusQuarto = new boolean[100];
        int[] diariasHospedagem = new int[100];
        int[][] consumoFrigobar = new int[100][4];
        
        double[] preco = {4.0, 10.0, 7.0, 12.0}; 
        double valorDiaria = 200.0;

        int opcao;

        do {
            System.out.println("\n---------- SISTEMA HOTEL ----------");
            System.out.println("1 - Reservar Quarto");
            System.out.println("2 - Cancelar Reserva");
            System.out.println("3 - Listar Reservas");
            System.out.println("4 - Consultar Hospede");
            System.out.println("5 - Editar Hospede (Nome)");
            System.out.println("6 - Registrar Consumo");
            System.out.println("7 - Realizar Check-out");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            
            opcao = entrada.nextInt();
            entrada.nextLine(); // Limpa buffer

            // 1. RESERVAR QUARTO
            if (opcao == 1) {
                int quarto;
                while (true) {
                    System.out.print("Numero do Quarto (1-100): ");
                    quarto = entrada.nextInt();
                    if (quarto >= 1 && quarto <= 100) break;
                    System.out.println("Digite um quarto valido (1 a 100)!");
                }

                int i = quarto - 1;
                if (!statusQuarto[i]) {
                    entrada.nextLine(); // Limpa buffer antes do nome
                    System.out.print("Nome do Hospede: ");
                    nomeHospede[i] = entrada.nextLine();
                    
                    System.out.print("Quantidade de Diarias: ");
                    diariasHospedagem[i] = entrada.nextInt();

                    // REGRA: Numero da reserva = Numero do quarto
                    numReserva[i] = quarto; 
                    statusQuarto[i] = true;
                    
                    System.out.println("Reserva realizada! Numero da Reserva: " + numReserva[i]);
                } else {
                    System.out.println("Este quarto ja esta ocupado!");
                }
            }

            // 2. CANCELAR RESERVA
            else if (opcao == 2) {
                int quarto;
                while (true) {
                    System.out.print("Quarto para cancelar (1-100): ");
                    quarto = entrada.nextInt();
                    if (quarto >= 1 && quarto <= 100) break;
                    System.out.println("Quarto invalido!");
                }

                int i = quarto - 1;
                if (statusQuarto[i]) {
                    statusQuarto[i] = false;
                    nomeHospede[i] = "";
                    numReserva[i] = 0;
                    diariasHospedagem[i] = 0;
                    for (int j = 0; j < 4; j++) consumoFrigobar[i][j] = 0;
                    System.out.println("Reserva do quarto " + quarto + " cancelada.");
                } else {
                    System.out.println("Este quarto ja esta vazio.");
                }
            }

            // 3. LISTAR RESERVAS
            else if (opcao == 3) {
                System.out.println("\n--- QUARTOS OCUPADOS ---");
                boolean tem = false;
                for (int i = 0; i < 100; i++) {
                    if (statusQuarto[i]) {
                        System.out.println("Quarto/Reserva: " + numReserva[i] + " | Hospede: " + nomeHospede[i]);
                        tem = true;
                    }
                }
                if (!tem) System.out.println("Reserva nao encontrada.");
            }

            // 4. CONSULTAR HÓSPEDE
            else if (opcao == 4) {
                int q;
                while (true) {
                    System.out.print("Digite o Numero do Quarto: ");
                    q = entrada.nextInt();
                    if (q >= 1 && q <= 100) break;
                    System.out.println("Quarto invalido!");
                }
                int i = q - 1;
                if (statusQuarto[i]) {
                    System.out.println("Hospede: " + nomeHospede[i]);
                    System.out.println("Reserva: " + numReserva[i]);
                    System.out.println("Diarias: " + diariasHospedagem[i]);
                } else {
                    System.out.println("Quarto esta vazio.");
                }
            }

            // 5. EDITAR HÓSPEDE
            else if (opcao == 5) {
                int q;
                while (true) {
                    System.out.print("Numero do quarto para editar nome: ");
                    q = entrada.nextInt();
                    if (q >= 1 && q <= 100) break;
                    System.out.println("Invalido!");
                }
                int i = q - 1;
                if (statusQuarto[i]) {
                    entrada.nextLine();
                    System.out.print("Novo Nome para o Hospede: ");
                    nomeHospede[i] = entrada.nextLine();
                    System.out.println("Nome atualizado com sucesso!");
                } else {
                    System.out.println("Este quarto esta vazio.");
                }
            }

            // 6. REGISTRAR CONSUMO
            else if (opcao == 6) {
                int q;
                while (true) {
                    System.out.print("Quarto que consumiu: ");
                    q = entrada.nextInt();
                    if (q >= 1 && q <= 100) break;
                    System.out.println("Invalido!");
                }
                int i = q - 1;
                if (statusQuarto[i]) {
                    System.out.println("1-Agua | 2-Refri | 3-Suco | 4-Chocolate");
                    int prod = entrada.nextInt();
                    if (prod >= 1 && prod <= 4) {
                        System.out.print("Quantidade: ");
                        int qtd = entrada.nextInt();
                        if (qtd > 0) {
                            consumoFrigobar[i][prod-1] += qtd;
                            System.out.println("Consumo registrado!");
                        }
                    }
                } else {
                    System.out.println("Quarto vazio. Nao pode registrar consumo.");
                }
            }

            // 7. CHECK-OUT
            else if (opcao == 7) {
                int q;
                while (true) {
                    System.out.print("Quarto para Check-out: ");
                    q = entrada.nextInt();
                    if (q >= 1 && q <= 100) break;
                    System.out.println("Invalido!");
                }
                int i = q - 1;
                if (statusQuarto[i]) {
                    double totalFrigobar = 0;
                    for (int j = 0; j < 4; j++) {
                        totalFrigobar += consumoFrigobar[i][j] * preco[j];
                    }
                    double totalHospedagem = diariasHospedagem[i] * valorDiaria;

                    System.out.println("\n=================== EXTRATO ========================");
                    System.out.println("Hospede: " + nomeHospede[i]);
                    System.out.println("Quarto/Reserva: " + numReserva[i]);
                    System.out.println("Total Diarias: R$ " + totalHospedagem);
                    System.out.println("Total Frigobar: R$ " + totalFrigobar);
                    System.out.println("TOTAL A PAGAR: R$ " + (totalHospedagem + totalFrigobar));
                    System.out.println("======================================================");

                    // Limpa tudo
                    statusQuarto[i] = false;
                    nomeHospede[i] = "";
                    numReserva[i] = 0;
                    diariasHospedagem[i] = 0;
                    for (int j = 0; j < 4; j++) consumoFrigobar[i][j] = 0;
                    System.out.println("Check-out concluido!");
                } else {
                    System.out.println("Quarto ja esta livre.");
                }
            }

        } while (opcao != 0);
        
        System.out.println("Sistema finalizado.");
    }
}