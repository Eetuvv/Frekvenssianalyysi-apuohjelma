package frekvenssianalyysi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Eetu Väänänen
 */
public class FrekvenssianalyysiSovellus extends Application {
// Luodaan graafinen käyttöliittymä
    @Override
    public void start(Stage ikkuna) {
        Frekvenssianalyysi kryptografia = new Frekvenssianalyysi();
        Map<Character, Integer> mappi = new HashMap();

        FlowPane root = new FlowPane();
        root.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        
        
        Button btn = new Button("Syötä viesti");
        btn.setPrefSize(80, 150);
        Button btn2 = new Button("Resetoi viesti");
        btn2.setPrefSize(150, 30);

        root.setAlignment(Pos.TOP_CENTER);
        Label teksti = new Label();
        teksti.setFont(Font.font("Source Code Pro", FontWeight.MEDIUM, 15));
        teksti.setWrapText(true);
        teksti.setTextAlignment(TextAlignment.JUSTIFY);
        teksti.setMaxWidth(500);

        Label teksti2 = new Label();
        teksti2.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label teksti3 = new Label("");
        teksti3.setFont(Font.font("Soure Code Pro", FontWeight.LIGHT, 15));
        teksti3.setMaxWidth(500);
        teksti3.setWrapText(true);
        teksti3.setTextAlignment(TextAlignment.JUSTIFY);

        TextArea tekstikentta = new TextArea("");
        tekstikentta.setFocusTraversable(false);
        tekstikentta.setPromptText("Syotä salattu viesti tähän (max 700 merkkiä)");
        tekstikentta.setMinSize(300, 60);
        tekstikentta.setWrapText(true);

        Label korvattavak = new Label("");
        korvattavak.setFont(new Font("Arial", 15));
        Label korvaajak = new Label("");
        korvaajak.setFont(new Font("Arial", 15));
        TextField korvattava = new TextField();
        korvattava.setVisible(false);
        korvattava.setMaxSize(30, 1);
        TextField korvaaja = new TextField();
        korvaaja.setVisible(false);
        korvaaja.setMaxSize(30, 1);

        Button btn3 = new Button("Syötä avain");
        btn3.setVisible(false);
        Button btn4 = new Button("Tyhjennä");
        btn4.setVisible(false);


        VBox vb = new VBox();
        vb.getChildren().add(btn2);
        vb.getChildren().add(teksti2);
        vb.getChildren().addAll(teksti, teksti3);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(5, 5, 15, 5));
        vb.setSpacing(10);

        StringBuilder sb = new StringBuilder();

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(tekstikentta, btn);
        hb.setSpacing(5);
        hb.setPadding(new Insets(10, 5, 1, 5));
        
        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.setPadding(new Insets(20, 10, 15, 10));
        hb2.getChildren().addAll(korvattavak, korvattava, korvaajak, korvaaja, btn3, btn4);

        root.getChildren().addAll(hb,vb,hb2);
        
        //Luodaan kuvaaja, joka näyttää eri kirjainten määrän viestissä
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Å", "Ä", "Ö")));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Kirjainten määrä");

        StackedBarChart<String, Number> stackedBarChart
                = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("Kirjainten määrä viestissä");
        stackedBarChart.setMaxHeight(330);
        stackedBarChart.setMinWidth(595);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        stackedBarChart.setVisible(false);
            
        
        //Konfiguroidaan painikkeiden toiminnot
        btn.setOnAction((ActionEvent event) -> {
            if (tekstikentta.getText().length() > 560) {
                stackedBarChart.setMaxHeight(200);
            }
            
            teksti.setText(tekstikentta.getText().toUpperCase());
            teksti2.setText("Murrettava viesti");
            korvattavak.setText("Syötä kirjain");
            korvaajak.setText("Syötä kirjaimen avain");
            
            korvattava.setVisible(true);
            korvaaja.setVisible(true);
            btn3.setVisible(true);
            btn4.setVisible(true);
            stackedBarChart.setVisible(true);

            kryptografia.palautaKirjaimetHajautustauluna(tekstikentta.getText().toUpperCase());

            for (char aakkonen = 'A'; aakkonen <= 'Z'; aakkonen++) {
                String st = "" + aakkonen;
                series.getData().add(new XYChart.Data<>(st, kryptografia.haeKirjaimenmaara(aakkonen)));
            }
            series.getData().add(new XYChart.Data<>("Å", kryptografia.haeKirjaimenmaara('Å')));
            series.getData().add(new XYChart.Data<>("Ä", kryptografia.haeKirjaimenmaara('Ä')));
            series.getData().add(new XYChart.Data<>("Ö", kryptografia.haeKirjaimenmaara('Ö')));

            sb.append(teksti.getText());

            for (int in = 0; in < teksti.getText().length(); in++) {

                if (teksti.getText().charAt(in) != ' ') {

                    sb.setCharAt(in, '_');
                    teksti3.setText(sb.toString());
                }
            }

            btn.setDisable(true);
        });

        btn2.setOnAction((ActionEvent event) -> {
            series.getData().forEach(ac -> ac.setYValue(0));
            tekstikentta.setText("");
            btn.setDisable(false);

            teksti.setText("");
            teksti2.setText("");
            teksti3.setText("");
            korvattavak.setText("");
            korvaajak.setText("");
            korvaaja.setVisible(false);
            korvattava.setVisible(false);
            btn3.setVisible(false);
            btn4.setVisible(false);
            stackedBarChart.setVisible(false);
            sb.delete(0, sb.length());
        });

        btn3.setOnAction((ActionEvent event) -> {
            String st = korvattava.getText().toUpperCase();
            String s2 = korvaaja.getText().toUpperCase();
            
            
            if (!korvattava.getText().equals("")) {
                korvattava.setText("");
            }
            if (!korvaaja.getText().equals("")) {
                korvaaja.setText("");
            }
            
            
            for (int in = 0; in < teksti.getText().length(); in++) {

                if (teksti.getText().charAt(in) == st.charAt(0)) {
                    sb.setCharAt(in, s2.charAt(0));

                    teksti3.setText(sb.toString());
                }
            }

        });
        btn4.setOnAction((ActionEvent event) -> {
            for (int in = 0; in < teksti.getText().length(); in++) {

                if (teksti.getText().charAt(in) != ' ') {

                    sb.setCharAt(in, '_');
                    teksti3.setText(sb.toString());
                }
            }
        });

        stackedBarChart.getData().add(series);
        root.getChildren().add(stackedBarChart);

        Scene scene = new Scene(root, 570, 1010,Color.BLACK);
        ikkuna.setTitle("Frekvenssianalyysi apuohjelma");
        ikkuna.setScene(scene);
        ikkuna.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
