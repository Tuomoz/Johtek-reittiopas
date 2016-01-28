package fi.helsinki.cs.reittiopas;

import java.util.ArrayList;
import java.util.HashMap;
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
        LinkedList<Pysakki> solmulista = new LinkedList<>();
        HashSet<Pysakki> kasitellyt = new HashSet<>();
        HashMap<Pysakki, Tila> pysakkienTilat = new HashMap<>();
        
        Tila lahtotila = new Tila(lahto, null);
        pysakkienTilat.put(lahto, lahtotila);
        solmulista.add(lahto);
        
        while (!solmulista.isEmpty()) {
            Pysakki solmu = (Pysakki) solmulista.pop();
            Tila solmunTila = pysakkienTilat.get(solmu);
            if (solmu.equals(maali))
                return solmunTila;
            
            for (Pysakki naapuri : solmu.getNaapurit()) {
                if (!kasitellyt.contains(naapuri)) {
                    kasitellyt.add(naapuri);
                    solmulista.add(naapuri);
                    pysakkienTilat.put(naapuri, new Tila(naapuri, solmunTila));
                }
            }
        }
        return null;
    }
}
