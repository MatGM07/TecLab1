package com.ejercicio.observador;

import java.util.ArrayList;
import java.util.List;

public class ProfesorObservable {
    private static List<ProfesorObserver> observadores = new ArrayList<>();

    public static void agregarObservador(ProfesorObserver obs) {
        observadores.add(obs);
    }

    public static void notificarCambio() {
        for (ProfesorObserver obs : observadores) {
            obs.actualizarLista();
        }
    }
}
