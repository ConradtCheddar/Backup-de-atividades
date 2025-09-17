package Controller;

import javax.swing.JPanel;

import View.Janela;

public class Navegador {
	private Janela janela;
	
	public Navegador(Janela janela) {
		this.janela = janela;
	}
	/**
	 * metodo para adicionar um Jpanel
	 * @param nome
	 * @param tela
	 */
	public void adicionarPainel(String nome, JPanel tela) {
		this.janela.adicionarTela(nome, tela);
	}
	
	/**
	 * metodo para navegar entre os panels
	 * @param nome
	 */
	public void navegarPara(String nome) {
		this.janela.mostrarTela(nome);
	}
	
	/**
	 * metodo para sair da janela
	 */
	public void sair() {
		this.janela.dispose();
	}

}
