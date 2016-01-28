package fi.helsinki.cs.reittiopas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Reittiopas {

    /**
     * Toteuta leveyssuuntainen haku. Palauta reitti taaksepäin linkitettynä
     * listana Tila-olioita, joista ensimmäinen osoittaa maalipysäkkiin ja
     * jokainen tuntee pysäkin ja tilan, josta kyseiseen tilaan päästiin
     * (viimeisen solmun Pysäkki on lähtöpysäkki ja edellinen tila on null).
     *
     * Voit selvittää pysäkin naapuripysäkit, eli pysäkit joihin pysäkiltä on
     * suora yhteys, kutsumalla pysäkin getNaapurit() -metodia.
     *
     * @param lahto Lahtopysakin koodi
     * @param maali Maalipysakin koodi
     * @return Tila-olioista koostuva linkitetty lista maalista lähtötilaan
     */
    public Tila haku(Pysakki lahto, Pysakki maali) {
        lahto.getNaapurit();

        Tila edellinenTila = new Tila(lahto, null);
        LinkedList solmulista = new LinkedList<Pysakki>();
        HashSet kasitellyt = new HashSet<Pysakki>();
        while (!solmulista.isEmpty()) {
            Pysakki solmu = (Pysakki) solmulista.pop();
            if (!kasitellyt.contains(solmu)) {
                kasitellyt.add(solmu);
                solmulista.addAll(solmu.getNaapurit());
            }
        }
        return edellinenTila;
    }
}
