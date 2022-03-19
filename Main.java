import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner input = new Scanner(System.in);
        int opcao = 1;
        while (opcao != 0){
            System.out.println("___________*___________");
            System.out.println("1  >CADASTRAR BANCO    ");
            System.out.println("2  >LISTAR BANCOS      ");
            System.out.println("3  >CADASTRAR CONTA    ");
            System.out.println("4  >DEPOSITAR          ");
            System.out.println("5  >SACAR              ");
            System.out.println("6  >TRANSFERIR         ");
            System.out.println("7  >EXIBIR SALDO       ");
            System.out.println("8  >EXIBIR EXTRATO     ");
            System.out.println("9  >MOSTRAR RENDIMENTO ");
            System.out.println("10 >DESATIVAR CONTA    ");
            System.out.println("11 >REATIVAR CONTA     ");
            System.out.println("0  >FINALIZAR SISTEMA  ");
            System.out.println("___________*___________");

            opcao = Integer.parseInt(input.nextLine());
            if(opcao == 1){
                System.out.println(">>>>>> CADASTRAR BANCO <<<<<<");
                String nomeBanco = input.nextLine();

                sistema.cadastrarBanco(new Banco(nomeBanco));
            }else if(opcao == 2){
                System.out.println(">>>>>> LISTAR BANCOS <<<<<<");
                sistema.listarBancos();
            }else if(opcao == 3){
                System.out.println(">>>>>> CADASTRAR CONTA <<<<<<");

                System.out.println("DIGITE NOME CLIENTE: ");
                String cliente = input.nextLine();
                
                System.out.println("DIGITE NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();

                System.out.println("DIGITE O SALDO INICIAL:");
                String saldo = input.nextLine();

                System.out.println("DIGITE TIPO DA CONTA: (1. CORRENTE, 2. POUPANÇA, 3. ESPECIAL)");
                String tipo = input.nextLine();

                System.out.println("DIGITE CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();

                String banco = input.nextLine();
                ContaGeral conta = null;

                if(tipo.equals("1")){
                    conta = new ContaPoupanca(cliente, numero, agencia,  Double.parseDouble(saldo), 0.0);
                }else  if(tipo.equals("2")) {
                    conta = new ContaCorrente(cliente, numero, agencia,  Double.parseDouble(saldo), 0.0);
                }else  if(tipo.equals("3")) {
                    System.out.println("DIGITE O LIMITE DA CONTA ESPECIAL: ");
                    String limit = input.nextLine();
                    conta = new ContaEspecial(cliente, numero, agencia,  Double.parseDouble(saldo), Double.parseDouble(limit));
                }
                sistema.cadastrarConta(conta, Integer.parseInt(banco));
            }
            else if(opcao == 4){
                System.out.println(">>>>>> DEPOSITAR <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();
                
                System.out.println("DIGITE CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();
                String banco = input.nextLine();

                System.out.println("DIGITE O VALOR A SER DEPOSITADO:");
                String valor = input.nextLine();

                sistema.depositar(Integer.parseInt(banco), numero, agencia, Double.parseDouble(valor));
            }
            else if(opcao == 5){
                System.out.println(">>>>>> SACAR <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();

                System.out.println("DIGITE O CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();
                String banco = input.nextLine();

                System.out.println("DIGITE O VALOR DE SAQUE:");
                String valor = input.nextLine();

                sistema.sacar(Integer.parseInt(banco), numero, agencia, Double.parseDouble(valor));
            }else if(opcao == 6){
                System.out.println(">>>>>> TRANSFERIR <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();

                System.out.println("DIGITE O CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();

                String banco = input.nextLine();


                System.out.println("DIGITE O NÚMERO DA CONTA DE DESTINO:");
                String numeroDestino = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA DA CONTA DE DESTINO:");
                String agenciaDestino = input.nextLine();

                System.out.println("DIGITE O CÓDIGO DO BANCO DA CONTA DE DESTINO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();

                String bancoDestino = input.nextLine();

                System.out.println("DIGITE O VALOR A SER TRANSFERIDO:");
                String valor = input.nextLine();

                sistema.transferir(Integer.parseInt(banco), numero, agencia, Integer.parseInt(bancoDestino), numeroDestino, agenciaDestino, Double.parseDouble(valor));
            }else if(opcao == 7){
                System.out.println(">>>>>> EXIBIR SALDO <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();
                
                System.out.println("DIGITE O CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();
                String banco = input.nextLine();

                sistema.exibirSaldo(Integer.parseInt(banco), numero, agencia);
            }else if(opcao == 8){
                System.out.println(">>>>>> EXIBIR EXTRATO <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE A AGÊNCIA:");
                String agencia = input.nextLine();

                System.out.println("DIGITE O CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();
                String banco = input.nextLine();
                
                sistema.exibirExtrato(Integer.parseInt(banco), numero, agencia);
            }else if(opcao == 9){
                System.out.println(">>>>>> MOSTRAR RENDIMENTO <<<<<<");
            }else if(opcao == 10){
                System.out.println(">>>>>> DESATIVAR CONTA <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();

                System.out.println("DIGITE O CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();

                String banco = input.nextLine();
                sistema.encerrarConta(Integer.parseInt(banco), numero, agencia);
            }else if(opcao == 11){
                System.out.println(">>>>>> REATIVAR CONTA <<<<<<");

                System.out.println("DIGITE O NÚMERO DA CONTA:");
                String numero = input.nextLine();

                System.out.println("DIGITE O NÚMERO DA AGÊNCIA:");
                String agencia = input.nextLine();

                System.out.println("DIGITE O CÓDIGO DO BANCO (CONFIRA AS OPÇÕES):");
                sistema.listarBancos();
                String banco = input.nextLine();

                sistema.reativarConta(Integer.parseInt(banco), numero, agencia);
            }else if(opcao == 0){
                System.out.println(">>>>>> FINALIZANDO O SISTEMA <<<<<<");
                break;
            }
        }

    }
}
