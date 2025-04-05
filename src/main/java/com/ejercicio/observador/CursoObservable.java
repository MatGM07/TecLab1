package com.ejercicio.observador;

import java.util.ArrayList;
import java.util.List;

public class CursoObservable {
    private static List<CursoObserver> observadores = new ArrayList<>();

    public static void agregarObservador(CursoObserver obs) {
        observadores.add(obs);
    }

    public static void notificarCambio() {
        for (CursoObserver obs : observadores) {
            obs.actualizarLista();
        }
    }
}
