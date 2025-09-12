package br.edu.infnet.pedidosapi.model.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedidosapi.interfaces.AuditoriaService;

public class SpyAuditoriaService implements AuditoriaService {

    private final List<String> eventosRegistrados = new ArrayList<>();
    private final List<String> detalhesRegistrados = new ArrayList<>();

    @Override
    public void registrarEvento(String evento, String detalhes) {
        this.eventosRegistrados.add(evento);
        this.detalhesRegistrados.add(detalhes);
    }

    public List<String> getEventosRegistrados() {
        return new ArrayList<>(eventosRegistrados);
    }

    public List<String> getDetalhesRegistrados() {
        return new ArrayList<>(detalhesRegistrados);
    }

    public String getEvento() {
        return eventosRegistrados.isEmpty() ? null : eventosRegistrados.get(eventosRegistrados.size() - 1);
    }

    public String getDetalhes() {
        return detalhesRegistrados.isEmpty() ? null : detalhesRegistrados.get(detalhesRegistrados.size() - 1);
    }

    public boolean isFoiChamado() {
        return !eventosRegistrados.isEmpty();
    }

    public void reset() {
        this.eventosRegistrados.clear();
        this.detalhesRegistrados.clear();
    }
}