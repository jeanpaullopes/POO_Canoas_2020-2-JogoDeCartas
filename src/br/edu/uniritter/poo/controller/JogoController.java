package br.edu.uniritter.poo.controller;

import br.edu.uniritter.poo.jogoDeCartas.model.Baralho;
import br.edu.uniritter.poo.jogoDeCartas.model.Jogador;
import br.edu.uniritter.poo.jogoDeCartas.model.Lixo;
import br.edu.uniritter.poo.jogoDeCartas.view.JogadorView;
import br.edu.uniritter.poo.jogoDeCartas.view.JogoView;

import java.util.ArrayList;
import java.util.List;

public class JogoController {

    private JogoView jogoView;
    private JogadorView jogadorView;
    private List<Jogador> jogadores;
    private Lixo lixo;
    private Baralho bar;
    private int jogAtual = 0;
    public JogoController() {
        this.jogoView = new JogoView(this);
        this.jogadorView = new JogadorView(this);
        jogadores = new ArrayList<>();
        jogadores.add(new Jogador("Jean"));
        jogadores.add( new Jogador("Paul"));
        jogadores.add(new Jogador("Lopes"));
        lixo = new Lixo();

        bar = new Baralho(1);
    }

    public void preJogo() {
        for(int i = 1; i<=9; i++) {
            jogadores.get(0).recebeCarta(bar.comprar());
            jogadores.get(1).recebeCarta(bar.comprar());
            jogadores.get(2).recebeCarta(bar.comprar());
        }
    }

    public void iniciaRodada() {

        // isto não está de acordo com MVC
            jogadorView.mostraMao(jogadores.get(jogAtual));
            jogoView.mostraLixo(lixo);
            jogoView.ondeComprar();

    }
    public void escolhaCompraJogador(int escolha) {
        if (escolha == 2 && !lixo.estaVazio()) {
            jogadores.get(jogAtual).recebeCarta(lixo.comprarCartaDeCima());
        } else {
            jogadores.get(jogAtual).recebeCarta(bar.comprar());
        }
        jogadorView.escolheCartaDescartar(jogadores.get(jogAtual));
    }

    public  void escolhaDescarteJogador(int escolha) {
        System.out.println(jogadores.get(jogAtual).getNome() + " escolheu " + escolha);
        lixo.recebeDescarte( jogadores.get(jogAtual).descartar(escolha) );
        proximoJogador();
    }
    public void proximoJogador() {
        jogAtual++;
        if(jogAtual == jogadores.size()){
            jogAtual = 0;
        }
        iniciaRodada();
    }

}
