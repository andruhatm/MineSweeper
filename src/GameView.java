package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Ein Fenster, welches das einfache Gestalten von Spielen erlaubt. Es wird eine Leinwand mit einer 16:9 Auflösung von
 * 960 * 540 Pixeln erzeugt, es kann Ton ausgegeben werden und Tastatur- und Mausereignisse werden zurückliefert.
 *
 * @author Andreas Berl
 */
public class GameView {

    private static class Version {
        private final static String VERSION = "2020.2.1";
        private final static String VERSION_SHORT = VERSION.substring(0, 6);
        private final static LocalDate DATE = LocalDate.parse("2020-10-11");
        private final static String STANDARDTITLE = "GameView";
        private final static String SIGNATURE = "Prof. Dr. Andreas Berl - TH Deggendorf";

        private static String getStatusSignature() {
            return "   " + STANDARDTITLE + " " + VERSION_SHORT + " - " + SIGNATURE + " ";
        }

        private static String getStandardtitle() {
            return STANDARDTITLE + " " + DATE.getYear();
        }
    }

    //Fenstergrößen für den Konstruktor.
    /** Ein kleines Fenster. */
    public static final int WINDOWSIZE_SMALL = 1;
    /** Ein großes Fenster. */
    public static final int WINDOWSIZE_LARGE = 2;
    /** Ein Maximiertes Fenster. */
    public static final int WINDOWSIZE_MAXIMIZED = 3;

    // Auflösung
    /** Breite der Leinwand in Pixeln. */
    public static final int WIDTH = 960;
    /** Höhe der Leinwand in Pixeln. */
    public static final int HEIGHT = 540;

    // Klassen
    private final Canvas canvas;
    private final Window window;
    private final Mouse mouse;
    private final Keyboard keyboard;
    private final Sound sound;

    // Farbpalette
    //Color palette
    private HashMap<Character, Color> colormap;

    /**
     * Es wird eine Leinwand mit einer 16:9 Auflösung von 960 * 540 Pixeln erzeugt (Breite = 960 Pixel, Höhe = 540
     * Pixel).
     * <pre>
     * <code>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * <br>
     * </code>
     * </pre>
     */
    public GameView() {
        this(WINDOWSIZE_SMALL);
    }

    /**
     * Es wird eine Leinwand mit einer 16:9 Auflösung von 960 * 540 Pixeln erzeugt (Breite = 960 Pixel, Höhe = 540
     * Pixel).
     * <pre>
     * <code>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * <br>
     * </code>
     * </pre>
     * <p>
     * Die Größe des Fensters kann festgelegt werden. Es gibt folgende Größen:
     * <pre>
     * <br>
     * GameView.WindowSize.SMALL
     * GameView.WindowSize.LARGE
     * GameView.WindowSize.MAXIMIZED
     * <br>
     * </pre>
     *
     * @param windowsize Die gewählte Fenstergröße.
     */
    public GameView(int windowsize) {

        // Klassen
        SwingAdapter swingAdapter = new SwingAdapter(windowsize);
        this.window = new Window(swingAdapter);
        this.mouse = new Mouse(swingAdapter);
        this.keyboard = new Keyboard(swingAdapter);
        this.sound = new Sound();
        this.canvas = new Canvas(colormap);
        swingAdapter.registerListeners(mouse, keyboard, sound);

        // Farbpalette
        initColormap();
    }

    private void initColormap() {
        HashMap<Character, Color> standardColorMap = new HashMap<>();
        standardColorMap.put('R', Color.RED);
        standardColorMap.put('r', Color.RED.brighter());
        standardColorMap.put('G', Color.GREEN);
        standardColorMap.put('g', Color.GREEN.brighter());
        standardColorMap.put('B', Color.BLUE);
        standardColorMap.put('b', Color.BLUE.brighter());
        standardColorMap.put('Y', Color.YELLOW);
        standardColorMap.put('y', Color.YELLOW.brighter());
        standardColorMap.put('P', Color.PINK);
        standardColorMap.put('p', Color.PINK.brighter());
        standardColorMap.put('C', Color.CYAN);
        standardColorMap.put('c', Color.CYAN.brighter());
        standardColorMap.put('M', Color.MAGENTA);
        standardColorMap.put('m', Color.MAGENTA.brighter());
        standardColorMap.put('O', Color.ORANGE);
        standardColorMap.put('o', Color.ORANGE.brighter());
        standardColorMap.put('W', Color.WHITE);
        standardColorMap.put('L', Color.BLACK);
        setColormap(standardColorMap);
    }

    /**
     * Setzt den Fenstertitel.
     *
     * @param title Der Fenstertitel
     */
    public void setWindowTitle(String title) {
        window.setTitle(title);
    }

    /**
     * Legt ein Symbol für die Titelleiste fest. Das Symbolfile muss in einem Verzeichnis "src/resources" liegen. Bitte
     * den Namen des Files ohne Verzeichnisnamen angeben, z.B.<code>setWindowIcon("Symbol.png")</code>.
     *
     * @param iconFileName Der Dateiname des Symbols.
     */
    public void setWindowIcon(String iconFileName) {
        window.setWindowIcon(iconFileName);
    }

    /**
     * Text, der in der Statuszeile angezeigt wird.
     *
     * @param statusText Text der Statuszeile.
     */
    public void setStatusText(String statusText) {
        window.setStatusText(statusText);
    }

    /**
     * Setzt die Hintergrundfarbe.
     *
     * @param backgroundColor Hintergrundfarbe
     */
    public void setBackgroundColor(Color backgroundColor) {
        canvas.setBackgroundColor(backgroundColor);
    }

    /**
     * Hier kann die Farbpalette festgelegt werden, die für die Methode {@link #addImageToCanvas(String, int, int, int)}
     * genutzt wird. Eine Beschreibung der Farbpalette und der Standardfarben ist in der Dokumentation der Methode
     * {@link #getColormap()} zu finden.
     *
     * @param colormap Die Farbpalette.
     * @see #getColormap()
     */
    public void setColormap(HashMap<Character, Color> colormap) {
        this.colormap = colormap;
        canvas.setColorMap(colormap);
    }

    /**
     * Gibt die aktuelle Farbpalette zurück, die für die Methode {@link #addImageToCanvas(String, int, int, int)}
     * genutzt wird. Dabei werden Buchst als Farb-Codes interpretiert. Als Standard sind folgende Farben definiert:
     * <pre>
     * <code>
     * <br>
     * HashMap<Character, Color> standardColorMap = new HashMap<>();
     * standardColorMap.put('R', Color.RED);
     * standardColorMap.put('r', Color.RED.brighter());
     * standardColorMap.put('G', Color.GREEN);
     * standardColorMap.put('g', Color.GREEN.brighter());
     * standardColorMap.put('B', Color.BLUE);
     * standardColorMap.put('b', Color.BLUE.brighter());
     * standardColorMap.put('Y', Color.YELLOW);
     * standardColorMap.put('y', Color.YELLOW.brighter());
     * standardColorMap.put('P', Color.PINK);
     * standardColorMap.put('p', Color.PINK.brighter());
     * standardColorMap.put('C', Color.CYAN);
     * standardColorMap.put('c', Color.CYAN.brighter());
     * standardColorMap.put('M', Color.MAGENTA);
     * standardColorMap.put('m', Color.MAGENTA.brighter());
     * standardColorMap.put('O', Color.ORANGE);
     * standardColorMap.put('o', Color.ORANGE.brighter());
     * standardColorMap.put('W', Color.WHITE);
     * standardColorMap.put('L', Color.BLACK);
     * setColormap(standardColorMap);
     * </code>
     * </pre>
     *
     * @return Die aktuelle Farbpalette.
     */
    public HashMap<Character, Color> getColormap() {
        return colormap;
    }

    /**
     * Schreibt den übergebenen Text auf die Leinwand (Canvas), ohne die bisherigen Inhalte zu löschen. Zusätzlich
     * werden Koordinaten ausgewertet: (0, 0) entspricht links oben.
     * <pre>
     * <code>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * <br>
     * </code>
     * </pre>
     * Negative Koordinaten können verwendet werden um Texte teilweise anzuzeigen. Leerzeichen sind durchsichtig
     * (Objekte im Hintergrund sind zu sehen).
     * <p>
     * In dieser Methode muss die Schriftgröße angegeben werden, dabei bedeutet z.B. </code>fontSize = 20</code>, dass
     * ein Buchstabe eine Fläche von 20 * 20 Pixeln belegt.
     *
     * @param text     Der anzuzeigende Text.
     * @param x        x-Koordinate, bei welcher der Text angezeigt werden soll. 0 ist links.
     * @param y        y-Koordinate, bei welcher der Text angezeigt werden soll. 0 ist oben.
     * @param fontSize Die Schriftgröße.
     * @param color    Die Farbe, in der der Text angezeigt werden soll.
     */
    public void addTextToCanvas(String text, int x, int y, int fontSize, Color color) {
        canvas.addTextToCanvas(text, x, y, fontSize, color);
    }

    /**
     * Diese Methode kann bunte Grafiken anzeigen. Dazu muss ein farb-codierter <code>String</code> übergeben werden,
     * der dann auf die Leinwand (Canvas) übertragen wird, ohne die bisherigen Inhalte zu löschen. Die im
     * <code>String</code> enthaltenen Buchstaben werden als Farben interpretiert. Jeder Buchstabe repräsentiert einen
     * Block mit der Größe blockSize * blockSize. Beispiel: Ein rotes Dreieck mit grüner Füllung.
     * <br>
     * <pre>
     * <code>
     * <br>
     * String rotesDreieckMitGruenerFuellung =
     * "   R   \n" +
     * "  RGR  \n" +
     * " RGGGR \n" +
     * "RRRRRRR\n";
     * <br>
     * </code>
     * </pre>
     * Um die Farbcodes zu interpretieren, wird eine Farbpalette ausgewertet. Eine Beschreibung der Farbpalette und der
     * Standardfarben ist in der Dokumentation der Methode {@link #getColormap()} zu finden.
     * <p>
     * Es sind nur Zeichen erlaubt, die in der Colormap vorkommen, das Leerzeichen (Space) und Zeilenumbrüche.
     * Zusätzlich werden Koordinaten ausgewertet: (0, 0) ist links oben.
     * <pre>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * </pre>
     * Negative Koordinaten können verwendet werden um Grafiken teilweise anzuzeigen.
     * <p>
     * Die Größe der Blöcke muss mit dem Parameter <code>blockSize</code> festgelegt werden. Beipielsweise bedeutet
     * <code>blockSize = 10</code>, dass ein Block die Fläche von 10 * 10 Pixeln belegt.
     *
     * @param image     Das Bild als farb-codierter String.
     * @param x         x-Koordinate, bei welcher der Text angezeigt werden soll. 0 ist links.
     * @param y         y-Koordinate, bei welcher der Text angezeigt werden soll. 0 ist oben.
     * @param blockSize Die Größe eines einzelnen Farbblocks.
     * @see #getColormap()
     */
    public void addImageToCanvas(String image, int x, int y, int blockSize) {
        canvas.addImageToCanvas(image, x, y, blockSize);
    }

    /**
     * Diese Methode kann eine farbige Linie auf die Leinwand (Canvas) zeichnen, ohne die bisherigen Inhalte zu
     * löschen.
     * <p>
     * Die Koordinaten werden wie folgt ausgewertet: (0, 0) ist links oben.
     * <pre>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * </pre>
     * Negative Koordinaten können verwendet werden um Linien teilweise anzuzeigen.
     *
     * @param xStart     x-Koordinate des Startpunkts der Linie. 0 ist links.
     * @param yStart     y-Koordinate des Startpunkts der Linie. 0 ist oben.
     * @param xEnd       x-Koordinate des Endpunkts der Linie. 0 ist links.
     * @param yEnd       y-Koordinate des Endpunkts der Linie. 0 ist oben.
     * @param lineWeight Die Linienstärke.
     * @param color      Die Farbe der Linie.
     */
    public void addLineToCanvas(int xStart, int yStart, int xEnd, int yEnd, int lineWeight, Color color) {
        canvas.addLineToCanvas(xStart, yStart, xEnd, yEnd, lineWeight, color);
    }

    /**
     * Diese Methode kann ein farbiges Oval auf die Leinwand (Canvas) zeichnen, ohne die bisherigen Inhalte zu löschen.
     * <p>
     * Die Koordinaten werden wie folgt ausgewertet: (0, 0) ist links oben.
     * <pre>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * </pre>
     * Negative Koordinaten können verwendet werden um Ovale teilweise anzuzeigen.
     *
     * @param xCenter    x-Koordinate des Mittelpunkts des Ovals. 0 ist links.
     * @param yCenter    y-Koordinate des Mittelpunkts des Ovals. 0 ist oben.
     * @param width      Breite des Ovals.
     * @param height     Höhe des Ovals.
     * @param lineWeight Die Linienstärke des Ovals.
     * @param filled     Legt fest, ob das Oval gefüllt werden soll oder nicht.
     * @param color      Die Farbe des Ovals.
     */
    public void addOvalToCanvas(int xCenter, int yCenter, int width, int height, int lineWeight, boolean filled,
                                Color color) {
        canvas.addOvalToCanvas(xCenter, yCenter, width, height, lineWeight, filled, color);
    }

    /**
     * Diese Methode kann ein farbiges Rechteck auf die Leinwand (Canvas) zeichnen, ohne die bisherigen Inhalte zu
     * löschen.
     * <p>
     * Die Koordinaten werden wie folgt ausgewertet: (0, 0) ist links oben.
     * <pre>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * </pre>
     * Negative Koordinaten können verwendet werden um Rechtecke teilweise anzuzeigen.
     *
     * @param x          x-Koordinate des linken oberen Ecks des Rechtecks. 0 ist links.
     * @param y          y-Koordinate des linken oberen Ecks des Rechtecks. 0 ist oben.
     * @param width      Breite des Rechtecks.
     * @param height     Höhe des Rechtecks.
     * @param lineWeight Die Linienstärke.
     * @param filled     Legt fest, ob das Rechteck gefüllt werden soll oder nicht.
     * @param color      Die Farbe des Rechtecks.
     */
    public void addRectangleToCanvas(int x, int y, int width, int height, int lineWeight, boolean filled, Color color) {
        canvas.addRectangleToCanvas(x, y, width, height, lineWeight, filled, color);
    }

    /**
     * Diese Methode kann ein farbiges Polygon auf die Leinwand (Canvas) zeichnen, ohne die bisherigen Inhalte zu
     * löschen. Dazu müssen alle Punkte des Polygons angegeben werden. Der Letze angegebene Punkt wird mit dem ersten
     * Punkt des Polygons verbunden.
     * <p>
     * Die Koordinaten werden wie folgt ausgewertet: (0, 0) ist links oben.
     * <pre>
     * <br>
     * 0/0 . . . . . 960/0<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * . . . . . . . . . .<br>
     * 0/540 . . . 960/540<br>
     * </pre>
     * Negative Koordinaten können verwendet werden um Rechtecke teilweise anzuzeigen.
     *
     * @param xCoordinates Die x-Koordinaten der Punkte des Polygons.
     * @param yCoordinates Die y-Koordinaten der Punkte des Polygons.
     * @param lineWeight   Die Linienstärke.
     * @param filled       Legt fest, ob das Polygon gefüllt werden soll oder nicht.
     * @param color        Die Farbe des Rechtecks.
     */
    void addPolygonToCanvas(int[] xCoordinates, int[] yCoordinates, int lineWeight, boolean filled, Color color) {
        canvas.addPolygonToCanvas(xCoordinates, yCoordinates, lineWeight, filled, color);
    }

    /**
     * Zeigt den aktuellen Inhalt der Leinwand (Canvas) im Fenster an. Nach der Ausgabe wird der Inhalt der Leinwand
     * gelöscht. Ein Aufruf dieser Methode dauert immer 6 Millisekunden. Dies verhindert, dass die Methode zu oft
     * aufgerufen wird.
     */
    public void printCanvas() {
        window.printCanvas(canvas);
    }

    /**
     * Gibt den übergebenen Text direkt im Fenster aus. Es muss eine Darstellungsgröße gewählt werden:
     * <pre>
     * <br>
     * 1: 160 Textspalten * 90 Textzeilen
     * 2:  96 Textspalten * 54 Textzeilen
     * 3:  80 Textspalten * 45 Textzeilen
     * 4:  64 Textspalten * 36 Textzeilen
     * 5:  48 Textspalten * 27 Textzeilen
     * 6:  32 Textspalten * 18 Textzeilen
     * 7:  16 Textspalten *  9 Textzeilen
     * </pre>
     * <br>
     * Ein Aufruf dieser Methode dauert immer 6 Millisekunden. Dies verhindert, dass die Methode zu oft aufgerufen
     * wird.
     *
     * @param string   Der anzuzeigende String.
     * @param textSize Die Darstellungsgröße des Textes.
     */
    public void print(String string, int textSize) {
        int fontSize;
        switch (textSize) {
            case 7:
                fontSize = 60;
                break;
            case 6:
                fontSize = 30;
                break;
            case 5:
                fontSize = 20;
                break;
            case 4:
                fontSize = 15;
                break;
            case 3:
                fontSize = 12;
                break;
            case 2:
                fontSize = 10;
                break;
            case 1:
                fontSize = 6;
                break;
            default:
                throw new InputMismatchException("Falsche Darstellungsgröße (1 - 7): " + textSize);
        }
        setBackgroundColor(Color.BLACK);
        addTextToCanvas(string, 0, 0, fontSize, Color.WHITE);
        printCanvas();
    }

    /**
     * Liefert alle Tastenereignisse die seit dem letzten Aufruf dieser Methode aufgelaufen sind als Array zurück. Es
     * werden maximal die neuesten 25 Ereignisse zurückgegeben, alte Ereignisse werden gelöscht.
     * <p>
     * Das Array enthält Ereignisse vom Typ {@link java.awt.event.KeyEvent}. Der Typ des Events ist entweder<br>
     * <code>KeyEvent.KEY_PRESSED</code> (Taste wurde gedrückt),<br>
     * <code>KeyEvent.KEY_RELEASED</code> (Taste wurde losgelassen)<br>
     * oder <code>KeyEvent.KEY_TYPED</code>(Taste wurde getippt, funktioniert nur für sichtbare Zeichen).
     * <p>
     * Sichtbare Zeichen lassen sich mit der Methode {@link java.awt.event.KeyEvent#getKeyChar()} direkt auslesen.
     * <p>
     * Bei Tastenereignissen gibt es die sogenannte Anschlagverzögerung. Das bedeutet, dass wenn man eine Taste gedrückt
     * hält, dann wird die Taste einmal ausgelöst, dann folgt eine kurze Pause, dann folgt eine schnelle Wiederholung
     * des Tastendrucks. Falls dieses Verhalten nicht gewünscht ist (z.B. bei der Steuerung von Spielfiguren), sollte
     * statt dessen die Methode {@link #getKeyCodesOfCurrentlyPressedKeys()} verwendet werden.
     *
     * <pre>
     * <code>
     * <br>
     * package demo;
     *
     * import java.awt.event.KeyEvent;
     *
     * public class KeyEventTest {
     *   GameView gameView;
     *
     *   public KeyEventTest() {
     *     gameView = new GameView();
     *     loop();
     *   }
     *
     *   public void loop() {
     *     while (true) {
     *       KeyEvent[] keyEvents = gameView.pollKeyEvents();
     *       for (KeyEvent keyEvent : keyEvents) {
     *         if (keyEvent.getID() == KeyEvent.KEY_TYPED) {
     *           gameView.print("Taste: " + keyEvent.getKeyChar(), 6);
     *         }
     *       }
     *     }
     *   }
     * }
     * </code>
     * </pre>
     * <br>
     *
     * @return Alle <code>KeyEvent</code> Ereignisse seit dem letzten Aufruf dieser Methode.
     * @see java.awt.event.KeyEvent
     * @see #getKeyCodesOfCurrentlyPressedKeys()
     */
    public KeyEvent[] pollKeyEvents() {
        return keyboard.pollKeyEvents();
    }

    /**
     * Legt fest, ob die Maus im Fenster benutzt werden soll. Falls sie nicht benutzt wird, wird der Cursor der Maus auf
     * den Default-Ansicht zurückgesetzt und die Maus wird ausgeblendet. Falls sie benutzt wird, werden Maus-Ereignisse
     * erzeugt, die verwendet werden können. Die Standardeinstellung ist <code>false</code>.
     *
     * @param useMouse Legt fest, ob die Maus im Fenster benutzt werden soll.
     */
    public void useMouse(boolean useMouse) {
        mouse.useMouse(useMouse);
    }

    /**
     * Legt ein neues Symbol für den Maus-Cursor fest. Die Bild-Datei muss im Verzeichnis "src/resources" liegen. Bitte
     * den Namen der Datei ohne Verzeichnisnamen angeben, z.B. <code>setMouseCursor("Cursor.png", false)</code>.
     *
     * @param fileName Name der Bild-Datei. Die Bild-Datei muss in einem Verzeichnis "src/resources" liegen.
     * @param centered Gibt an, ob der Hotspot des Cursors in der Mitte des Symbols oder oben links liegen soll.
     */
    public void setMouseCursor(String fileName, boolean centered) {
        mouse.setMouseCursor(fileName, centered);
    }

    /** Der Maus-Cursor wird auf das Standard-Icon zurückgesetzt. */
    public void resetMouseCursor() {
        mouse.setStandardMouseCursor();
    }

    /**
     * Falls die Maus mit {@link #useMouse(boolean)} aktiviert wurde, liefert diese Methode alle gerade im Moment
     * gedrückten Tasten als <code>KeyCode</code> der Klasse {@link java.awt.event.KeyEvent} als Array zurück. Die
     * Tasten sind in der Reihenfolge enthalten, in der sie gedrückt wurden. Diese Methode ist geeignet um die Steuerung
     * von Spielfiguren zu realisieren.
     * <p>
     * Ein Abgleich der KeyCodes kann über Konstanten der Klasse {@link java.awt.event.KeyEvent} erfolgen.
     * Beispielsweise kann die Leertaste mit Hilfe der Konstante {@link java.awt.event.KeyEvent#VK_SPACE} abgeglichen
     * werden.
     * <pre>
     * <code>
     * <br>
     * package demo;
     *
     * import java.awt.event.KeyEvent;
     *
     * public class PressedKeys {
     *   GameView gameView;
     *
     *   public PressedKeys() {
     *     gameView = new GameView();
     *     loop();
     *   }
     *
     *   private void loop() {
     *     while (true) {
     *       Integer[] pressedKeys = gameView.getKeyCodesOfCurrentlyPressedKeys();
     *       String result = "";
     *       for (int keyCode : pressedKeys) {
     *         if (keyCode == KeyEvent.VK_UP) {
     *           result += "UP\n";
     *         } else if (keyCode == KeyEvent.VK_DOWN) {
     *           result += "Down\n";
     *         } else if (keyCode == KeyEvent.VK_LEFT) {
     *           result += "Left\n";
     *         } else if (keyCode == KeyEvent.VK_RIGHT) {
     *           result += "Right\n";
     *         } else if (keyCode == KeyEvent.VK_SPACE) {
     *           result += "Space\n";
     *         }
     *       }
     *       gameView.print(result, 6);
     *     }
     *   }
     * }
     *
     * </code>
     * </pre>
     *
     * @return Alle gerade gedrückten Tasten als <code>KeyCode</code> in einem Array.
     * @see java.awt.event.KeyEvent
     */
    public Integer[] getKeyCodesOfCurrentlyPressedKeys() {
        return keyboard.getKeyCodesOfCurrentlyPressedKeys();
    }

    /**
     * Falls die Maus mit {@link #useMouse(boolean)} aktiviert wurde, liefert diese Methode alle Mausereignisse die seit
     * dem letzten Aufruf dieser Methode aufgelaufen sind als Array zurück. Es werden maximal die neuesten 25 Ereignisse
     * zurückgegeben, alte Ereignisse werden gelöscht. Diese Methode ist geeignet um die Texteingaben vom Benutzer zu
     * realisieren.
     * <p>
     * Das Array enthält Ereignisse vom Typ {@link java.awt.event.MouseEvent}. Das Ereignis enthält Koordinaten auf der
     * Leinwand (Canvas) und die Information ob die Maus gedrückt, losgelassen, gecklickt oder nur bewegt wurde. Um
     * festzustellen, wie die Maus betätigt wurde, kann der Typ des Ereignises mit {@link
     * java.awt.event.MouseEvent#getID()} abgefragt werden. Folgende Konstanten werden weitergeleitet:
     * <br>
     * <code>MouseEvent.MOUSE_PRESSED</code> <br>
     * <code>MouseEvent.MOUSE_RELEASED</code> <br>
     * <code>MouseEvent.MOUSE_CLICKED</code> <br>
     * <code>MouseEvent.MOUSE_MOVED</code> <br>
     * <br>
     * Die Fensterkoordinaten können mit den Methoden<br> {@link java.awt.event.MouseEvent#getX()} = X-Koordinate<br>
     * {@link java.awt.event.MouseEvent#getY()} = Y-Koordiante<br> abgerufen werden, um X und Y-Koordinate des
     * Ereignisses zu bestimmen.<br>
     * <br>
     * Beispiel zur Erkennung einer geklickten Maustaste:<br>
     *
     * <pre>
     * <code>
     * <br>
     * package demo;
     *
     * import java.awt.event.MouseEvent;
     *
     * public class MouseEventTest {
     *   GameView gameView;
     *
     *   public MouseEventTest() {
     *     gameView = new GameView();
     *     gameView.useMouse(true);
     *     loop();
     *   }
     *
     *   public void loop() {
     *     int x = 0;
     *     int y = 0;
     *     while(true) {
     *       MouseEvent[] mouseEvents = gameView.pollMouseEvents();
     *       for (MouseEvent mouseEvent : mouseEvents) {
     *         if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
     *           x = mouseEvent.getX();
     *           y = mouseEvent.getY();
     *         }
     *       }
     *       gameView.addTextToCanvas("X=" + x + " Y=" + y, x, y, 12, Color.WHITE);
     *       gameView.printCanvas();
     *     }
     *   }
     * }
     * </code>
     * </pre>
     * <p>
     * Mit {@link java.awt.event.MouseEvent#getButton()} ()} lässt sich ermitteln, welche Maustaste betätigt wurde
     * (links, rechts oder die Mitte).
     *
     * @return Alle Mausereignisse seit dem letzten Aufruf dieser Methode.
     * @see java.awt.event.MouseEvent
     */
    public MouseEvent[] pollMouseEvents() {
        return mouse.pollMouseEvents();
    }

    /**
     * Spielt einen Sound ab (z.B. eine wav.-Datei). Das Soundfile muss in einem Verzeichnis "src/resources" liegen.
     * Bitte den Namen des Files ohne Verzeichnisnamen angeben, z.B. <code>playSound("sound.wav", false)</code>. Der
     * Sound beendet sich selbst, sobald er fertig abgespielt wurde. Der Parameter "replay" kann genutzt werden um den
     * Sound endlos zu wiederholen. Mit der Methode {@link #stopSound(int)} kann ein Sound frühzeitig beendet werden.
     * Mit der Methode {@link #stopAllSounds()} können alle laufenden Sounds beendet werden.
     *
     * @param sound  Name des Soundfiles. Das Soundfile muss in einem Verzeichnis "src/resources" liegen.
     * @param replay Legt fest, ob der Sound endlos wiederholt werden soll.
     * @return Die eindeutige Nummer des Soundfiles wird zurückgegeben. Dise Nummer kann genutzt werden um mit der
     * Methode {@link #stopSound(int)} das Abspielen des Sounds zu beenden.
     */
    public int playSound(String sound, boolean replay) {
        return this.sound.playSound(sound, replay);
    }

    /**
     * Stoppt den Sound mit der angegebenen Nummer. Falls der Sound schon gestoppt wurde, passiert nichts.
     *
     * @param number Der eindeutige Nummer des Soundfiles, das gestoppt werden soll.
     */
    public void stopSound(int number) {
        sound.stopSound(number);
    }

    /**
     * Stoppt alle gerade spielenden Sounds.
     */
    public void stopAllSounds() {
        sound.stopAllSounds();
    }

    /**
     * Schließt entweder nur das GameView-Fenster oder die ganze Anwendung.
     *
     * @param terminateEverything Wenn <code>true</code> ausgewählt wird, wird die komplette Anwendung mit
     *                            <code>System.exit(0)</code>beendet. Ansonsten wird nur das Fenster geschlossen.
     */
    public void closeGameView(boolean terminateEverything) {
        window.closeWindow(terminateEverything);
    }

    private static class PrintObject {
        int x;
        int y;
        Color color;

        public PrintObject(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    private static class Text extends PrintObject {
        String text;
        int fontSize;

        public Text(String text, int x, int y, int fontSize, Color color) {
            super(x, y, color);
            this.text = text;
            this.fontSize = fontSize;
        }
    }

    private static class Block extends PrintObject {
        int blockSize;

        public Block(int x, int y, int blockSize, Color color) {
            super(x, y, color);
            this.blockSize = blockSize;
        }
    }

    private static class Oval extends PrintObject {
        int width;
        int height;
        int lineWeight;
        boolean filled;

        public Oval(int xCenter, int yCenter, int width, int height, int lineWeight, boolean filled, Color color) {
            super(xCenter, yCenter, color);
            this.width = width;
            this.height = height;
            this.lineWeight = lineWeight;
            this.filled = filled;
        }
    }

    private static class Rectangle extends PrintObject {
        int width;
        int height;
        int lineWeight;
        boolean filled;

        public Rectangle(int xCenter, int yCenter, int width, int height, int lineWeight, boolean filled, Color color) {
            super(xCenter, yCenter, color);
            this.width = width;
            this.height = height;
            this.lineWeight = lineWeight;
            this.filled = filled;
        }
    }

    private static class Line extends PrintObject {
        int xEnd;
        int yEnd;
        int lineWeight;

        public Line(int xStart, int yStart, int xEnd, int yEnd, int lineWeight, Color color) {
            super(xStart, yStart, color);
            this.xEnd = xEnd;
            this.yEnd = yEnd;
            this.lineWeight = lineWeight;
        }
    }

    private static class Polygon extends PrintObject {
        int[] xCoordinates;
        int[] yCoordinates;
        int lineWeight;
        boolean filled;

        public Polygon(int[] xCoordinates, int[] yCoordinates, int lineWeight, boolean filled, Color color) {
            super(xCoordinates[0], yCoordinates[0], color);
            this.xCoordinates = xCoordinates;
            this.yCoordinates = yCoordinates;
            this.lineWeight = lineWeight;
            this.filled = filled;
        }
    }

    private static class Canvas implements Cloneable {
        private Color backgroundColor;
        private final ArrayList<PrintObject> printObjects;
        private HashMap<Character, Color> colorMap;

        Canvas(HashMap<Character, Color> colorMap) {
            this.backgroundColor = Color.black;
            this.colorMap = colorMap;
            this.printObjects = new ArrayList<>(30000);
        }

        void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        Color getBackgroundColor() {
            return backgroundColor;
        }

        void setColorMap(HashMap<Character, Color> colorMap) {
            this.colorMap = colorMap;
        }

        ArrayList<PrintObject> getPrintObjects() {
            return printObjects;
        }

        void addTextToCanvas(String text, int x, int y, int fontSize, Color color) {
            String[] lines = text.split("\\R");
            for (int i = 0; i < lines.length; i++) {
                printObjects.add(new Text(lines[i], x, y + (i * fontSize), fontSize, color));
            }
        }

        void addImageToCanvas(String image, int x, int y, int blockSize) {
            String[] lines = image.split("\\R");
            for (int i = 0; i < lines.length; i++) {
                char[] blocks = lines[i].toCharArray();
                for (int j = 0; j < blocks.length; j++) {
                    printObjects.add(new Block(x + (j * blockSize), y + (i * blockSize), blockSize,
                            colorMap.get(blocks[j])));
                }
            }
        }

        void addRectangleToCanvas(int x, int y, int width, int height, int lineWeight, boolean filled, Color color) {
            printObjects.add(new Rectangle(x, y, width, height, lineWeight, filled, color));
        }

        void addLineToCanvas(int xStart, int yStart, int xEnd, int yEnd, int lineWeight, Color color) {
            printObjects.add(new Line(xStart, yStart, xEnd, yEnd, lineWeight, color));
        }

        void addOvalToCanvas(int xCenter, int yCenter, int width, int height, int lineWeight, boolean filled,
                             Color color) {
            printObjects.add(new Oval(xCenter, yCenter, width, height, lineWeight, filled, color));
        }

        void addPolygonToCanvas(int[] xCoordinates, int[] yCoordinates, int lineWeight, boolean filled, Color color) {
            if (xCoordinates.length != yCoordinates.length) {
                throw new InputMismatchException("Die Anzahl der X- und Y-Koordinaten ist nicht gleich!");
            }
            printObjects.add(new Polygon(xCoordinates, yCoordinates, lineWeight, filled, color));
        }
    }

    private static class Frame extends JFrame {

        private Mouse mouse;
        private Keyboard keyboard;

        private final JPanel statusBar;
        private JLabel statusLabelLinks;

        void registerListeners(Mouse mouse, Keyboard keyboard) {
            // Klassen
            this.mouse = mouse;
            this.keyboard = keyboard;
        }

        Frame(int windowSize, TextPanel textPanel) {

            statusBar = new JPanel() {
                {
                    setLayout(new BorderLayout());
                    setBorder(BorderFactory.createRaisedBevelBorder());
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    statusLabelLinks = new JLabel();
                    statusLabelLinks.setBackground(Color.WHITE);
                    statusLabelLinks.setForeground(Color.BLACK);
                    statusLabelLinks.setHorizontalAlignment(JLabel.LEFT);

                    JLabel statusLabelRechts = new JLabel(Version.getStatusSignature());
                    statusLabelRechts.setBackground(Color.WHITE);
                    statusLabelRechts.setForeground(Color.BLACK);
                    statusLabelRechts.setHorizontalAlignment(JLabel.RIGHT);
                    add(statusLabelLinks, BorderLayout.WEST);
                    add(statusLabelRechts, BorderLayout.EAST);
                }
            };

            // Struktur
            textPanel.setPreferredSize(new Dimension(GameView.WIDTH, GameView.HEIGHT));

            //            Box box = new Box(BoxLayout.Y_AXIS);
            //            box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            //            box.add(Box.createVerticalGlue());
            //            box.add(textPanel);
            //            box.add(Box.createVerticalGlue());

            JPanel textPanelAndStatusBar = new JPanel(new BorderLayout(5, 5));
            textPanelAndStatusBar.setBackground(Color.BLACK);
            textPanelAndStatusBar.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)), BorderLayout.NORTH);
            textPanelAndStatusBar.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)), BorderLayout.EAST);
            textPanelAndStatusBar.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)), BorderLayout.WEST);
            textPanelAndStatusBar.add(textPanel, BorderLayout.CENTER);
            textPanelAndStatusBar.add(statusBar, BorderLayout.SOUTH);
            add(textPanelAndStatusBar);

            // Eigenschaften
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle(Version.getStandardtitle());
            textPanel.requestFocus();
            setResizable(true);


            // Listeners
            addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent keyEvent) {
                    all(keyEvent);
                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {
                    all(keyEvent);
                }

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    all(keyEvent);
                }

                private void all(KeyEvent keyEvent) {
                    if (keyboard != null) {
                        keyboard.update(keyEvent);
                    }
                }
            });
            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent mouseEvent) {
                    all(mouseEvent);
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    all(mouseEvent);
                }

                @Override
                public void mouseMoved(MouseEvent mouseEvent) {
                    all(mouseEvent);
                }

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    all(mouseEvent);
                }

                private void all(MouseEvent mouseEvent) {
                    if (mouse != null) {
                        mouse.update(mouseEvent);
                    }
                }
            };
            textPanel.addMouseMotionListener(mouseAdapter);
            textPanel.addMouseListener(mouseAdapter);

            // Größe des Fensters
            pack();
            setMinimumSize(getSize());
            if (windowSize == GameView.WINDOWSIZE_MAXIMIZED) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else if (windowSize == GameView.WINDOWSIZE_LARGE) {
                int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
                int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
                double scaleX = screenWidth * 4d / 5 / GameView.WIDTH;
                double scaleY = screenHeight * 4d / 5 / GameView.HEIGHT;
                double scale = Math.min(scaleX, scaleY);
                setSize(new Dimension((int) (GameView.WIDTH * scale), (int) (GameView.HEIGHT * scale)));
            }
            // Location und Ausgeben
            setLocationRelativeTo(null);
            setVisible(true);
        }

        JLabel getStatusLabelLinks() {
            return statusLabelLinks;
        }

        JPanel getStatusBar() {
            return statusBar;
        }
    }

    private static class Keyboard {
        private final SwingAdapter swingAdapter;
        private final ArrayBlockingQueue<KeyEvent> keyboardEvents;
        private final ArrayBlockingQueue<Integer> keyCodesOfCurrentlyPressedKeys;

        private final static int KEY_EVENT_BUFFER_SIZE = 25;

        Keyboard(SwingAdapter swingAdapter) {
            this.swingAdapter = swingAdapter;
            keyboardEvents = new ArrayBlockingQueue<>(KEY_EVENT_BUFFER_SIZE, true);
            keyCodesOfCurrentlyPressedKeys = new ArrayBlockingQueue<>(10, true);
        }

        void update(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();
            if (KeyEvent.VK_ESCAPE == code) {
                swingAdapter.closeWindow(true);
            }
            if (keyboardEvents.size() == KEY_EVENT_BUFFER_SIZE) {
                keyboardEvents.remove();
            }
            keyboardEvents.add(keyEvent);
            if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                if (!keyCodesOfCurrentlyPressedKeys.contains(keyEvent.getKeyCode()))
                    keyCodesOfCurrentlyPressedKeys.add(keyEvent.getKeyCode());
            } else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
                keyCodesOfCurrentlyPressedKeys.remove(keyEvent.getKeyCode());
            }
        }

        KeyEvent[] pollKeyEvents() {
            KeyEvent[] events = new KeyEvent[0];
            if (keyboardEvents.size() > 0) {
                events = keyboardEvents.toArray(events);
                keyboardEvents.clear();
            }
            return events;
        }

        Integer[] getKeyCodesOfCurrentlyPressedKeys() {
            Integer[] keyCodes = new Integer[0];
            if (keyCodesOfCurrentlyPressedKeys.size() > 0) {
                keyCodes = keyCodesOfCurrentlyPressedKeys.toArray(keyCodes);
            }
            return keyCodes;
        }
    }

    private static class Mouse implements ActionListener {
        private final SwingAdapter swingAdapter;

        private boolean invisibleMouseCursor;
        private boolean invisibleMouseCursorMoved;
        private final Timer invisibleMouseTimer;

        private final static int MOUSE_EVENT_BUFFER_SIZE = 25;
        private final ArrayBlockingQueue<MouseEvent> mousePointerEvents;

        private boolean useMouse;

        Mouse(SwingAdapter swingAdapter) {
            this.swingAdapter = swingAdapter;
            this.invisibleMouseCursor = false;
            this.invisibleMouseCursorMoved = true;
            this.mousePointerEvents = new ArrayBlockingQueue<>(MOUSE_EVENT_BUFFER_SIZE, true);
            this.invisibleMouseTimer = new Timer(500, this);
            setMouseInvisible();
        }

        private void setMouseInvisible() {
            this.useMouse = false;
            setInvisibleMouseCursor();
            if (!invisibleMouseTimer.isRunning()) {
                invisibleMouseTimer.start();
            }
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (invisibleMouseCursorMoved) {
                if (invisibleMouseCursor) {
                    setStandardMouseCursor();
                }
                invisibleMouseCursorMoved = false;
            } else {
                if (!invisibleMouseCursor) {
                    setInvisibleMouseCursor();
                }
            }
        }

        void useMouse(boolean useMouse) {
            if (useMouse == this.useMouse) {
                return;
            }
            if (useMouse) {
                this.useMouse = true;
                setStandardMouseCursor();
                invisibleMouseTimer.stop();
            } else {
                setMouseInvisible();
            }
        }

        void setStandardMouseCursor() {
            this.invisibleMouseCursor = false;
            swingAdapter.setStandardMouseCursor();
        }

        void setMouseCursor(String cursorImageFile, boolean centered) {
            this.invisibleMouseCursor = false;
            swingAdapter.setMouseCursor(cursorImageFile, centered);
        }

        private void setInvisibleMouseCursor() {
            invisibleMouseCursor = true;
            swingAdapter.setInvisibleMouseCursor();
        }

        void update(MouseEvent mouseEvent) {
            if (useMouse) {
                int mouseEventY = GameView.HEIGHT * mouseEvent.getY() / swingAdapter.getTextDisplaySize().height;
                int mouseEventX = GameView.WIDTH * mouseEvent.getX() / swingAdapter.getTextDisplaySize().width;
                MouseEvent fixedMouseEvent = new MouseEvent(mouseEvent.getComponent(), mouseEvent.getID(),
                        mouseEvent.getWhen(), mouseEvent.getModifiersEx(), mouseEventX, mouseEventY,
                        mouseEvent.getClickCount(), mouseEvent.isPopupTrigger(), mouseEvent.getButton());
                if (mousePointerEvents.size() == MOUSE_EVENT_BUFFER_SIZE) {
                    mousePointerEvents.remove();
                }
                mousePointerEvents.add(fixedMouseEvent);
            } else {
                invisibleMouseCursorMoved = true;
            }
        }

        MouseEvent[] pollMouseEvents() {
            MouseEvent[] events = new MouseEvent[0];
            if (mousePointerEvents.size() > 0) {
                events = mousePointerEvents.toArray(events);
                mousePointerEvents.clear();
            }
            return events;
        }
    }

    private static class Sound {

        private final ConcurrentHashMap<Integer, Clip> clips;
        private static int soundCounter;

        Sound() {
            this.clips = new ConcurrentHashMap<>();
            soundCounter = 0;
        }

        int playSound(String sound, boolean replay) {
            final int number = ++soundCounter;
            new Thread(() -> {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameView.class.getResource(
                            "/resources/" + sound));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clips.put(number, clip);
                    clip.addLineListener(event -> {
                        if (event.getType().equals(LineEvent.Type.STOP)) {
                            event.getLine().close();
                            clips.remove(number);
                        }
                    });
                    if (replay) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } else {
                        clip.start();
                    }
                } catch (Exception e) {
                    System.err.println("Soundfile \"" + sound + "\" konnte nicht abgespielt werden!");
                    e.printStackTrace();
                    System.exit(1);
                }
            }).start();
            return number;
        }

        void stopSound(int number) {
            Clip clip = clips.get(number);
            if (clip != null) {
                clip.stop();
            }
        }

        void stopAllSounds() {
            for (Clip clip : this.clips.values()) {
                if (clip != null) {
                    clip.stop();
                }
            }
        }
    }

    private static class SwingAdapter {
        //Swing Klassen
        private final TextPanel textPanel;
        private final Frame frame;
        private Sound sound;
        private Mouse mouse;
        private Font font;
        FontMetrics metric;
        BufferedImage bufferedImage;
        Graphics2D g2D;


        void registerListeners(Mouse mouse, Keyboard keyboard, Sound sound) {
            frame.registerListeners(mouse, keyboard);
            this.sound = sound;
            this.mouse = mouse;
        }

        SwingAdapter(int windowSize) {
            this.textPanel = new TextPanel();
            this.frame = new Frame(windowSize, textPanel);
            this.bufferedImage = new BufferedImage(GameView.WIDTH, GameView.HEIGHT, BufferedImage.TYPE_INT_RGB);
            this.g2D = bufferedImage.createGraphics();
            Map<TextAttribute, Object> fontMap = new HashMap<>();
            fontMap.put(TextAttribute.FAMILY, "Monospaced");
            fontMap.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
            //fontMap.put(TextAttribute.WIDTH, TextAttribute.WIDTH_SEMI_EXTENDED);
            this.font = new Font(fontMap);
            this.metric = bufferedImage.getGraphics().getFontMetrics(font);
        }

        // Anzeige
        void setStatusText(String statusText) {
            SwingUtilities.invokeLater(() -> {
                frame.getStatusLabelLinks().setText(statusText);
                int minWidth = frame.getStatusBar().getPreferredSize().width + 50;
                frame.setMinimumSize(new Dimension(minWidth, minWidth / 16 * 9));
            });
        }

        void printToDisplay(ArrayList<PrintObject> printObjects, Color backgroundColor) {
            bufferedImage = new BufferedImage(GameView.WIDTH, GameView.HEIGHT, BufferedImage.TYPE_INT_RGB);
            g2D = bufferedImage.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(backgroundColor);
            g2D.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);
            char[] chars;
            for (PrintObject p : printObjects) {
                if (p.color != null) {
                    g2D.setColor(p.color);
                    if (p.getClass() == Text.class) {
                        Text text = (Text) p;
                        font = font.deriveFont((float) text.fontSize);
                        g2D.setFont(font);
                        metric = g2D.getFontMetrics(font);
                        chars = text.text.toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            g2D.drawChars(chars, i, 1,
                                    text.x + (text.fontSize * i) + (text.fontSize - metric.charWidth('W')) / 2,
                                    text.y + (text.fontSize + metric.getAscent() - metric.getDescent() - text.fontSize / 10) / 2);
                        }
                    } else if (p.getClass() == Block.class) {
                        Block block = (Block) p;
                        g2D.fillRect(block.x, block.y, block.blockSize, block.blockSize);
                    } else if (p.getClass() == Oval.class) {
                        Oval oval = (Oval) p;
                        g2D.setStroke(new BasicStroke(oval.lineWeight));
                        g2D.drawOval(oval.x - oval.width / 2, oval.y - oval.height / 2, oval.width, oval.height);
                        if (oval.filled) {
                            g2D.fillOval(oval.x - oval.width / 2, oval.y - oval.height / 2, oval.width, oval.height);
                        }
                    } else if (p.getClass() == Line.class) {
                        Line line = (Line) p;
                        g2D.setStroke(new BasicStroke(line.lineWeight));
                        g2D.drawLine(line.x, line.y, line.xEnd, line.yEnd);
                    } else if (p.getClass() == Rectangle.class) {
                        Rectangle rectangle = (Rectangle) p;
                        g2D.setStroke(new BasicStroke(rectangle.lineWeight));
                        g2D.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                        if (rectangle.filled) {
                            g2D.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                        }
                    } else if (p.getClass() == Polygon.class) {
                        Polygon polygon = (Polygon) p;
                        g2D.setStroke(new BasicStroke(polygon.lineWeight));
                        g2D.drawPolygon(polygon.xCoordinates, polygon.yCoordinates, polygon.xCoordinates.length);
                        if (polygon.filled) {
                            g2D.fillPolygon(polygon.xCoordinates, polygon.yCoordinates, polygon.xCoordinates.length);
                        }
                    }
                }
            }
            g2D.dispose();
            textPanel.bufferedImage = bufferedImage;
            textPanel.repaint();
        }

        // Fenster-Dekorationen
        void setTitle(String title) {
            frame.setTitle(title);
        }

        void setWindowIcon(String windowIcon) {
            Image fensterSymbol = null;
            try {
                fensterSymbol = new ImageIcon(GameView.class.getResource("/src/resources/" + windowIcon)).getImage();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Symbolfile \"" + windowIcon + "\" konnte nicht gefunden werden!");
            }
            frame.setIconImage(fensterSymbol);
        }

        // Maus Cursor
        void setMouseCursor(String cursor, boolean centered) {
            try {
                Image im = new ImageIcon(GameView.class.getResource("/resources/" + cursor)).getImage();
                SwingUtilities.invokeLater(() -> textPanel.setCursor(createCursor(im, centered)));
            } catch (Exception e) {
                System.out.println("Cursorfile konnte nicht gefunden werden!");
                System.exit(1);
            }
        }

        private Cursor createCursor(Image im, boolean centered) {
            Toolkit toolkit = textPanel.getToolkit();
            Dimension cursorSize = Toolkit.getDefaultToolkit().getBestCursorSize(64, 64);
            Point cursorHotSpot = new Point(0, 0);
            if (centered) {
                cursorHotSpot = new Point(cursorSize.width / 2, cursorSize.height / 2);
            }
            return toolkit.createCustomCursor(im, cursorHotSpot, "Cross");
        }

        void setStandardMouseCursor() {
            SwingUtilities.invokeLater(() -> textPanel.setCursor(Cursor.getDefaultCursor()));
        }

        void setInvisibleMouseCursor() {
            Image im = new ImageIcon("").getImage();
            SwingUtilities.invokeLater(() -> textPanel.setCursor(createCursor(im, false)));
        }

        // Beenden
        void closeWindow(boolean terminateEverything) {
            frame.dispose();
            sound.stopAllSounds();
            mouse.invisibleMouseTimer.stop();
            if (terminateEverything) {
                System.exit(0);
            }
        }

        Dimension getTextDisplaySize() {
            return textPanel.getSize();
        }
    }

    private static class TextPanel extends JPanel {

        volatile BufferedImage bufferedImage;

        TextPanel() {
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
            bufferedImage = new BufferedImage(GameView.WIDTH, GameView.HEIGHT, BufferedImage.TYPE_INT_RGB);
            setDoubleBuffered(false);
            setIgnoreRepaint(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            AffineTransform trans = g2D.getTransform();
            trans.scale(getWidth() * 1d / GameView.WIDTH, getHeight() * 1d / GameView.HEIGHT);
            g2D.setTransform(trans);
            g2D.drawImage(bufferedImage, 0, 0, null);
            g2D.dispose();
        }
    }

    private static class Window {

        private final SwingAdapter swingAdapter;

        Window(SwingAdapter swingAdapter) {
            this.swingAdapter = swingAdapter;
        }

        void printCanvas(Canvas canvas) {
            long startTime = System.currentTimeMillis();
            swingAdapter.printToDisplay(canvas.getPrintObjects(), canvas.getBackgroundColor());
            canvas.getPrintObjects().clear();
            long timePassed = System.currentTimeMillis() - startTime;
            try {
                Thread.sleep(Math.max(0, 6 - timePassed));
            } catch (InterruptedException ignored) {
            }
        }

        void setStatusText(String statusText) {
            swingAdapter.setStatusText(statusText);
        }

        void setWindowIcon(String windowIcon) {
            swingAdapter.setWindowIcon(windowIcon);
        }

        void setTitle(String title) {
            swingAdapter.setTitle(title);
        }

        void closeWindow(boolean terminateEverything) {
            swingAdapter.closeWindow(terminateEverything);
        }
    }
}
