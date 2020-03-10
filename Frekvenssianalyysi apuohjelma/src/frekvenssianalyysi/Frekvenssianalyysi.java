package frekvenssianalyysi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eetu Väänänen
 */
public class Frekvenssianalyysi {

    private final Map<Character, Integer> kirjaimet;

    public Frekvenssianalyysi() {
        this.kirjaimet = new HashMap();
    }

    private static Map<Character, Integer> haeKirjaintenMaara(String kirjain) { // Lasketaan eri kirjainten määrä viestissä
        Map<Character, Integer> mappi = new HashMap<>();

        if (kirjain != null) {
            for (Character k : kirjain.toCharArray()) {
                Integer maara = mappi.get(k);
                int uusiMaara = (maara == null ? 1 : maara + 1);
                mappi.put(k, uusiMaara);
            }
        }
        return mappi;
    }

    public Map palautaKirjaimetHajautustauluna(String viesti) { //Palautetaan hajautustaulu, jossa on kunkin kirjaimen esiintyvyyden määrä
        Map laskuri = haeKirjaintenMaara(viesti);

        for (char aakkonen = 'A'; aakkonen <= 'Z'; aakkonen++) {

            if (laskuri.get(aakkonen) == null) {
                kirjaimet.put(aakkonen, 0);
            } else {
                kirjaimet.put(aakkonen, (int) laskuri.get(aakkonen));
            }
        }

        if (laskuri.get('Å') != null) {
            kirjaimet.put('Å', (int) laskuri.get('Å'));
        } else {
            kirjaimet.put('Å', 0);
        }
        if (laskuri.get('Ä') != null) {
            kirjaimet.put('Ä', (int) laskuri.get('Ä'));
        } else {
            kirjaimet.put('Ä', 0);
        }
        if (laskuri.get('Ö') != null) {
            kirjaimet.put('Ö', (int) laskuri.get('Ö'));
        } else {
            kirjaimet.put('Ö', 0);
        }

        return laskuri;
    }
    
    public int haeKirjaimenmaara(char kirjain) { //Palauttaa tietyn kirjaimen määrän viestissä
        return kirjaimet.get(kirjain);
    }
}
