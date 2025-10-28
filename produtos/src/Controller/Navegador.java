package Controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

import Model.Usuario;
import View.Janela;

public class Navegador {
    private Janela janela;
    private Usuario currentUser;
    private Map<String, Runnable> showListeners;
    
    public Navegador(Janela janela) {
        this.janela = janela;
        this.showListeners = new HashMap<>();
        
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
		// Notify listeners when screen is shown
        if (showListeners.containsKey(nome)) {
            showListeners.get(nome).run();
        }
	}
	
	/**
	 * metodo para sair da janela
	 */
	public void sair() {
		this.janela.dispose();
	}
	
	public void setCurrentUser(Usuario user) {
		this.currentUser = user;
	}
	public Usuario getCurrentUser() {
		return this.currentUser;
	}
	
	/**
     * Método para voltar à tela anterior
     */
    public void voltar() {
        navegarPara("menu");
    }
    
    /**
     * Adiciona um listener para ser notificado quando uma tela específica é exibida
     */
    public void addShowListener(String screenName, Runnable listener) {
        showListeners.put(screenName, listener);
    }

}