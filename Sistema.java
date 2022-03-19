import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public  class Sistema implements Aplicacao {

    public Sistema() {
    }

    public void cadastrarBanco(Banco banco){
        if(BancoDeDados.salvaBanco(banco)) {
            System.out.println("Banco " + banco.getNome() + " cadastrado com sucesso!");
        }else{
            System.out.println("Banco " + banco.getNome() + " erro ao cadastrar!");
        }
    }

    public void cadastrarConta(ContaGeral conta, Integer codigoDoBanco){
        Conta contaCheck = this.buscaConta(codigoDoBanco, conta.numero(), conta.agencia());
        if (contaCheck == null){
            System.out.println("Conta cadastrada com sucesso!");
            BancoDeDados.salvaConta(conta, codigoDoBanco);
        }else{
            System.out.println("Conta já existente");
        }
    };

    public void depositar(Integer codigoBanco, String numeroConta, String agencia, Double valor){
        ContaGeral conta = BancoDeDados.getUmaConta(codigoBanco, numeroConta, agencia);
        if (valor <= 0){
            System.out.println("Informe um valor válido!");
        }
        if(!checkAccountStatus(conta)){
            System.out.println("Conta inativa");
        }
        else if (conta != null) {
            conta.depositar(valor);
            BancoDeDados.atualizarSaldo(conta.getId(), conta.getSaldo());
            System.out.println("Deposito realizado com sucesso!");
        }else{
            System.out.println("Erro ao depositar");
        }
    }


    public void sacar(Integer codigoBanco, String numeroConta, String agencia, Double valor){
        if (valor <= 0){
            System.out.println("Informe um valor válido!");
        }

        ContaGeral conta = BancoDeDados.getUmaConta(codigoBanco, numeroConta, agencia);
        
        if(!checkAccountStatus(conta)){
            System.out.println("Conta inativa");
        }else {
            if (conta != null && checkSaldo(conta.getSaldo(), valor)) {
                conta.sacar(valor);
                BancoDeDados.atualizarSaldo(conta.getId(), conta.getSaldo());
                System.out.println("Saque de R$: "+ valor + " realizado com sucesso!");
            }else if (conta.getSaldo() < valor){
                System.out.println("Saldo da conta insuficiente");
            }
        }
    }
    public void exibirSaldo(Integer codigoBanco, String numeroConta, String agencia){
        Conta conta = BancoDeDados.getUmaConta(codigoBanco, numeroConta, agencia);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
        }else{
            conta.exibirSaldo();
        }
    }

    public void transferir(Integer codigoBancoOrigin, String numeroContaOrigin, String agenciaOrigin, Integer codigoBancoDestino, String numeroContaDestino, String agenciaDestino, Double valorDaTransferencia){
        // pega dados da conta de origin
        ContaGeral contaOrigin = BancoDeDados.getUmaConta(codigoBancoOrigin , numeroContaOrigin, agenciaOrigin);
        ContaGeral contaDestino = BancoDeDados.getUmaConta(codigoBancoDestino, numeroContaDestino, agenciaDestino);
        if(valorDaTransferencia < 0)
            System.out.println("Valor incorreto!");
        else if (contaOrigin == null || contaDestino == null){
            System.out.println("Dados da conta inválidos!");
        }
        else if(!checkSaldo(contaOrigin.getSaldo(), valorDaTransferencia)){
            System.out.println("Saldo insuficiente");
        }
        else if(!checkAccountStatus(contaOrigin) || !checkAccountStatus(contaDestino)){
            System.out.println("Conta origem ou conta destino não podem realizar transação validar status da conta");
        }
        else {
            Double valor = contaOrigin.transferir(contaDestino, valorDaTransferencia);
            if(valor > 0) {
                BancoDeDados.atualizarSaldo(contaDestino.getId(), contaDestino.getSaldo());
                BancoDeDados.atualizarSaldo(contaOrigin.getId(), contaOrigin.getSaldo());
                System.out.println("R$ : " + valorDaTransferencia + " transferido de :" + contaOrigin.numero() + " para " + contaDestino.numero());

            }
        }
    }


    public boolean encerrarConta(Integer codigoBanco, String numeroConta, String agencia){
        ContaGeral conta = BancoDeDados.getUmaConta(codigoBanco, numeroConta, agencia);
        if(conta == null){
            return false;
        }
        if (conta.getSaldo()  > 0){
            System.out.println("Necessário realizar saque para poder encerrar a conta");
            return false;
        }
        boolean status = BancoDeDados.atualizarStatus(conta.getId(), 0);
        if(status == false){
            System.out.println("Erro ao tentar encerrada conta!");
        }else{
            System.out.println("Conta encerrada");
        }
        return status;
    }


    public boolean reativarConta(Integer codigoBanco, String numeroConta, String agencia){
        ContaGeral conta = BancoDeDados.getUmaConta(codigoBanco, numeroConta, agencia);
        if(conta == null){
            return false;
        }

        boolean status = BancoDeDados.atualizarStatus(conta.getId(), 1);
        if(status == false){
            System.out.println("A conta já está ativa!");
        }else{
            System.out.println("Conta ativada");
        }
        return status;
    }

    public void exibirExtrato(Integer codigoBanco, String numero, String agencia) {
        ContaGeral conta = BancoDeDados.getUmaConta(codigoBanco, numero, agencia);
        if(conta == null){
            System.out.println("Conta não encontrada|");;
        }else{
            List<Historico> historicos = BancoDeDados.getHistorico(conta.getId());
            conta.setHistoricos(historicos);
            conta.exibirExtrato();
        }
    }

    private Conta buscaConta(Integer codigoBanco, String numeroConta, String agencia){

        Conta conta = BancoDeDados.getUmaConta(codigoBanco, numeroConta, agencia);;
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return null;
        }
        return conta;
    }

    private Banco buscaBanco(Integer codigo){
        List<Banco> bancos = BancoDeDados.getBancos();
        for (Banco b: bancos) {
            if(b.getId().equals(codigo)){
                return b;
            }
        }
        return null;
    }

    public void listarBancos(){
        List<Banco> bancos = BancoDeDados.getBancos();
        for (Banco b: bancos ) {
            System.out.println(b);
        }
    }

    private Boolean checkSaldo(Double saldoContaOrigem, Double valorRetirado){
        if (saldoContaOrigem >= valorRetirado){
            return true;
        }else{
            return false;
        }
    }

    private Boolean checkAccountStatus(ContaGeral conta){

        if(conta.getAtiva() == 1){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void calcularRendimento(String data) {

    }
}
