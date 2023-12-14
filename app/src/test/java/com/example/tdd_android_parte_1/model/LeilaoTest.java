package com.example.tdd_android_parte_1.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    @Test
    public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
        //Criar cenário de teste
        Leilao leilao = new Leilao("Console");
        //Executar ação necessaria
        String descricaoDevolvida = leilao.getDescricao();
        //Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    //[nome do metodo] [estado de teste] [resultado esperado]
    //[Deve] [resultado esperado] [estado de teste]
    @Test
    //getMaiorLance_QuandoRecebeApenasUmLance_DevolveMaiorLance
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        Leilao leilao = new Leilao("Console");
        leilao.propoe(new Lance(new Usuario("Henrique"), 200.0));

        double maiorLance = leilao.getMaiorLance();
        //O delta serve para ajustar a dizima periodica quando o valor nao dar redondo.
        assertEquals(200.0, maiorLance, 0.0001);
    }

    @Test
    //getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMiorLance
    public void deve_DevolveMiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao leilaoComputador = new Leilao("Computador");
        leilaoComputador.propoe(new Lance(new Usuario("Henrique"), 2000.0));
        leilaoComputador.propoe(new Lance(new Usuario("Zequinha"), 3000.0));

        double maiorLance = leilaoComputador.getMaiorLance();
        //O delta serve para ajustar a dizima periodica quando o valor nao dar redondo.
        assertEquals(3000.0, maiorLance, 0.0001);
    }
    @Test
    //getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente_DevolveMiorLance
    public void deve_DevolveMiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        Leilao leilaoCarro = new Leilao("Carro");
        leilaoCarro.propoe(new Lance(new Usuario("Zequinha"), 10000.0));
        leilaoCarro.propoe(new Lance(new Usuario("Henrique"), 20000.0));

        double maiorLance = leilaoCarro.getMaiorLance();
        //O delta serve para ajustar a dizima periodica quando o valor nao dar redondo.
        assertEquals(20000.0, maiorLance, 0.0001);
    }

    @Test
    public void deve_DeveVolverMenorLance_QuandoRecebeUmLance() {
        Leilao leilaoTv = new Leilao("Leilão tv");
        leilaoTv.propoe(new Lance(new Usuario("Henrique"), 800.0));

        double menorLance = leilaoTv.getMenorLance();

        assertEquals(800.0, leilaoTv.getMenorLance(), 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao leilao = new Leilao("Leilão Moto");
        leilao.propoe(new Lance(new Usuario("Henrique"), 8000.0));
        leilao.propoe(new Lance(new Usuario("Juquinha"), 10000.0));

        assertEquals(8000.0, leilao.getMenorLance(), 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        Leilao leilao = new Leilao("Leilão Geladeira");
        leilao.propoe(new Lance(new Usuario("Juquinha"),  1090.0));
        leilao.propoe(new Lance(new Usuario("Henrique"),  990.0));

        assertEquals(990.0, leilao.getMenorLance(), 0.0001);
    }
}