package com.example.tdd_android_parte_1.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class LeilaoRefatoradoTest {
    public static final double DELTA = 0.0001;
    //o static vai crashar pois ele mantem a mesma referencia para o objeto.
    //private static Leilao leilao = new Leilao("Console");
    private final Leilao LEILAO = new Leilao("Console");
    private final Usuario HENRIQUE = new Usuario("Henrique");

    @Test
    public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
        String descricaoDevolvida = LEILAO.getDescricao();
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        LEILAO.propoe(new Lance(HENRIQUE, 200.0));

        double maiorLance = LEILAO.getMaiorLance();
        assertEquals(200.0, maiorLance, DELTA);
    }

    @Test
    public void deve_DevolveMiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        LEILAO.propoe(new Lance(HENRIQUE, 2000.0));
        LEILAO.propoe(new Lance(new Usuario("Zequinha"), 3000.0));

        double maiorLance = LEILAO.getMaiorLance();
        assertEquals(3000.0, maiorLance, DELTA);
    }
    @Test
    public void deve_DevolveMiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        LEILAO.propoe(new Lance(new Usuario("Zequinha"), 10000.0));
        LEILAO.propoe(new Lance(HENRIQUE, 20000.0));

        double maiorLance = LEILAO.getMaiorLance();
        assertEquals(20000.0, maiorLance, DELTA);
    }

    @Test
    public void deve_DeveVolverMenorLance_QuandoRecebeUmLance() {
        LEILAO.propoe(new Lance(HENRIQUE, 800.0));

        double menorLance = LEILAO.getMenorLance();

        assertEquals(800.0, LEILAO.getMenorLance(), DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        LEILAO.propoe(new Lance(HENRIQUE, 8000.0));
        LEILAO.propoe(new Lance(new Usuario("Zequinha"), 10000.0));

        assertEquals(8000.0, LEILAO.getMenorLance(), DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        LEILAO.propoe(new Lance(new Usuario("Zequinha"),  1090.0));
        LEILAO.propoe(new Lance(HENRIQUE,  990.0));

        assertEquals(990.0, LEILAO.getMenorLance(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        LEILAO.propoe(new Lance(HENRIQUE, 200.0));
        LEILAO.propoe(new Lance(new Usuario("juquinha"), 400.0));
        LEILAO.propoe(new Lance(new Usuario("Zequinha"), 300.0));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());

        assertEquals(400, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLance() {
        List<Lance> tresMaioresLancesDevolvidos = LEILAO.tresMaioresLances();
        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        LEILAO.propoe(new Lance(HENRIQUE,400.0));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO.tresMaioresLances();
        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }
    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances() {
        LEILAO.propoe(new Lance(HENRIQUE,400.0));
        LEILAO.propoe(new Lance(new Usuario("Zequinha"),200.0));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO.tresMaioresLances();
        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {
        LEILAO.propoe(new Lance(HENRIQUE, 600.0));
        final Usuario ZEQ = new Usuario("Zequinha");
        LEILAO.propoe(new Lance(ZEQ, 500.0));
        LEILAO.propoe(new Lance(HENRIQUE, 400.0));
        LEILAO.propoe(new Lance(ZEQ, 300.0));
        LEILAO.propoe(new Lance(HENRIQUE, 200.0));

        List<Lance> tresMaioresLancesParaCincoLances = LEILAO.tresMaioresLances();

        assertEquals(600.0, tresMaioresLancesParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesParaCincoLances.get(2).getValor(), DELTA);
    }
}