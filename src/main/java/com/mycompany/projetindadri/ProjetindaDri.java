package com.mycompany.ProjetindaDri;

import java.util.Scanner;

public class ProjetindaDri {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        String[] nomeHospede = new String[100];
        int[] numReserva = new int[100];
        boolean[] statusQuarto = new boolean[100];
        int[] diariasHospedagem = new int[100];
        int[][] consumoFrigobar = new int[100][4];
        
        double[] preco = {4.0, 10.0, 7.0, 12.0}; 
        double valorDiaria = 5000.0;

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
            entrada.nextLine();

            switch (opcao) {

            case 1:
                int quarto;
                while (true) {
                    System.out.print("Numero do Quarto (1-100): ");
                    quarto = entrada.nextInt();
                    if (quarto >= 1 && quarto <= 100) break;
                    System.out.println("Digite um quarto valido (1 a 100)!");
                }

                int i = quarto - 1;
                if (statusQuarto[i] == false) {
                    entrada.nextLine();
                    System.out.print("Nome do Hospede: ");
                    nomeHospede[i] = entrada.nextLine();
                    
                    int d;
                    do {
                        System.out.print("Quantidade de Diarias: ");
                        d = entrada.nextInt();
                        if (d <= 0) {
                            System.out.println("Digite um valor maior que 0!");
                        }
                    } while (d <= 0);
                    diariasHospedagem[i] = d;

                    numReserva[i] = quarto; 
                    statusQuarto[i] = true;
                    
                    System.out.println("Reserva realizada! Numero da Reserva: " + numReserva[i]);
                } else {
                    System.out.println("Este quarto ja esta ocupado!");
                }
                break;

            case 2:
                int quarto2;
                while (true) {
                    System.out.print("Quarto para cancelar (1-100): ");
                    quarto2 = entrada.nextInt();
                    if (quarto2 >= 1 && quarto2 <= 100) break;
                    System.out.println("Quarto invalido!");
                }

                int i2 = quarto2 - 1;

                if (statusQuarto[i2] == true) {

                    double totalFrigobar = 0;
                    for (int j = 0; j < 4; j++) {
                        totalFrigobar += consumoFrigobar[i2][j] * preco[j];
                    }

                    double totalHospedagem = diariasHospedagem[i2] * valorDiaria;

                    System.out.println("\n=====================================");
                    System.out.println("Hospede: " + nomeHospede[i2]);
                    System.out.println("Quarto: " + numReserva[i2]);
        
                    System.out.println("Total Frigobar: R$ " + totalFrigobar);
                    System.out.println("TOTAL A PAGAR: R$ " + (totalFrigobar));
                    System.out.println("=====================================");

                    statusQuarto[i2] = false;
                    nomeHospede[i2] = "";
                    numReserva[i2] = 0;
                    diariasHospedagem[i2] = 0;
                    for (int j = 0; j < 4; j++) consumoFrigobar[i2][j] = 0;

                    System.out.println("Reserva cancelada com cobrança.");
                } else {
                    System.out.println("Este quarto ja esta vazio.");
                }
                break;

            case 3:
                System.out.println("\n--- QUARTOS OCUPADOS ---");
                boolean tem = false;
                for (int i3 = 0; i3 < 100; i3++) {
                    if (statusQuarto[i3] == true) {
                        System.out.println("Quarto/Reserva: " + numReserva[i3] + " | Hospede: " + nomeHospede[i3]);
                        tem = true;
                    }
                }
                if (tem == false) System.out.println("Nenhum quarto ocupado.");
                break;

            case 4:
                int q;
                while (true) {
                    System.out.print("Digite o Numero do Quarto: ");
                    q = entrada.nextInt();
                    if (q >= 1 && q <= 100) break;
                    System.out.println("Quarto invalido!");
                }
                int i4 = q - 1;
                if (statusQuarto[i4] == true) {
                    System.out.println("Hospede: " + nomeHospede[i4]);
                    System.out.println("Reserva: " + numReserva[i4]);
                    System.out.println("Diarias: " + diariasHospedagem[i4]);
                } else {
                    System.out.println("Quarto esta vazio.");
                }
                break;

            case 5:
                int q5;
                while (true) {
                    System.out.print("Numero do quarto para editar nome: ");
                    q5 = entrada.nextInt();
                    if (q5 >= 1 && q5 <= 100) break;
                    System.out.println("Invalido!");
                }
                int i5 = q5 - 1;
                if (statusQuarto[i5] == true) {
                    entrada.nextLine();
                    System.out.print("Novo Nome para o Hospede: ");
                    nomeHospede[i5] = entrada.nextLine();
                    System.out.println("Nome atualizado com sucesso!");
                } else {
                    System.out.println("Este quarto esta vazio.");
                }
                break;

            case 6:
                int q6;
                while (true) {
                    System.out.print("Quarto que consumiu: ");
                    q6 = entrada.nextInt();
                    if (q6 >= 1 && q6 <= 100) break;
                    System.out.println("Invalido!");
                }
                int i6 = q6 - 1;
                if (statusQuarto[i6] == true) {
                    System.out.println("1-Agua | 2-Refri | 3-Suco | 4-Chocolate");
                    int prod = entrada.nextInt();

                    if (prod >= 1 && prod <= 4) {
                        System.out.print("Quantidade: ");
                        int qtd = entrada.nextInt();

                        if (qtd > 0) {
                            consumoFrigobar[i6][prod-1] += qtd;
                            System.out.println("Consumo registrado!");
                        } else {
                            System.out.println("Quantidade invalida!");
                        }
                    } else {
                        System.out.println("Produto invalido!");
                    }
                } else {
                    System.out.println("Quarto vazio. Nao pode registrar consumo.");
                }
                break;

            case 7:
                int q7;
                while (true) {
                    System.out.print("Quarto para Check-out: ");
                    q7 = entrada.nextInt();
                    if (q7 >= 1 && q7 <= 100) break;
                    System.out.println("Invalido!");
                }
                int i7 = q7 - 1;
                if (statusQuarto[i7] == true) {
                    double totalFrigobar = 0;
                    for (int j = 0; j < 4; j++) {
                        totalFrigobar += consumoFrigobar[i7][j] * preco[j];
                    }
                    double totalHospedagem = diariasHospedagem[i7] * valorDiaria;

                    System.out.println("\n=================== EXTRATO ========================");
                    System.out.println("Hospede: " + nomeHospede[i7]);
                    System.out.println("Quarto/Reserva: " + numReserva[i7]);
                    System.out.println("Total Diarias: R$ " + totalHospedagem);
                    System.out.println("Total Frigobar: R$ " + totalFrigobar);
                    System.out.println("TOTAL A PAGAR: R$ " + (totalHospedagem + totalFrigobar));
                    System.out.println("======================================================");

                    statusQuarto[i7] = false;
                    nomeHospede[i7] = "";
                    numReserva[i7] = 0;
                    diariasHospedagem[i7] = 0;
                    for (int j = 0; j < 4; j++) consumoFrigobar[i7][j] = 0;
                    System.out.println("Check-out concluido!");
                } else {
                    System.out.println("Quarto ja esta livre.");
                }
                break;
            }

        } while (opcao != 0);
        
        System.out.println("Sistema finalizado.");
    }
}
