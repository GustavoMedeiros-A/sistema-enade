package br.com.enade.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    public void afterPhase(PhaseEvent arg0) {
    }

    public void beforePhase(PhaseEvent event) {
        System.out.println("FASE: " + event.getPhaseId());
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
