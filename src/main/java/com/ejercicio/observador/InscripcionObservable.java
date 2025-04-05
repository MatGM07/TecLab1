package com.ejercicio.observador;

import java.util.ArrayList;
import java.util.List;

public class InscripcionObservable {
    private static List<InscripcionObserver> observadores = new ArrayList<>();

    public static void agregarObservador(InscripcionObserver obs) {
        observadores.add(obs);
    }

    public static void notificarCambio() {
        for (InscripcionObserver obs : observadores) {
            obs.actualizarLista();
        }
    }
}