package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Main {

	static GameView view;

	static GameLogics logic;

	public static void main(String[] args) {
		//initializing View provider class
		view = new GameView(GameView.WINDOWSIZE_SMALL);
		//initializing Logic class with absolute coordinates for board
		logic = new GameLogics(203,23,view);


		view.setWindowIcon("icon/mineIcon.jpg");
		view.setWindowTitle("MineSweeper");
		view.setStatusText("status text");

		//adding gray color to ColorMap
		HashMap<Character, Color>map =  view.getColormap();
		map.put('A',Color.gray);
		view.setColormap(map);

		//adding border for game board with tiles
		view.addRectangleToCanvas(200,20,517,517,5,false,Color.white);


		logic.newGame();
		//drawing board on screen
		logic.drawBoard();

		//allows user to use mouse
		view.useMouse(true);


		//поток вызывающий метод pollMouseEvents() и проверящий что новый клик был добавлен

	}

}
