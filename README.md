Apuohjelma graafisella käyttöliittymällä yksiaakkostosalatun viestin murtamiseen frekvenssianalyysi-menetelmällä. Ohjelma laskee eri kirjainten määrän salatussa 
viestissä ja helpottaa viestin murtamista.
Ohjelma laskee myös ääkköset.

<strong>Edit: 24.2.2021</strong>
Päivitetty projekti maveniin ja lisätty pom.xml ja dependency javafx varten. Lisätty myös ohjeet ohjelman suorittamiseen.

# Ohjelman suoritus
Ohjelman voi suorittaa Windowsilla seuraavasti: 
1. [Lataa JavaFX SDK](https://gluonhq.com/products/javafx)
2. Pura tiedosto
3. Suorita seuraava komento komentoriviltä:  
 java --add-modules javafx.controls,javafx.fxml --module-path C:\Path\to\openjfx-11.0.2\javafx-sdk-11.0.2\lib -jar  path\to\target\Frekvenssianalyysi-apuohjelma-1.0-SNAPSHOT-jar-with-dependencies.jar  
 Ensimmäinen path on polku siihen sijaintiin, johon purit lataamasti tiedoston. Toinen path on polku jar-tiedoston sijaintiin.  
   
![image](https://user-images.githubusercontent.com/34383558/98965360-a1da2800-2512-11eb-998d-ed98f3709941.png)
