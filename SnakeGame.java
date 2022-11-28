import java.util.Random;
import java.awt.Font;
public class SnakeGame {
   static final int GAME_HEIGHT = 600;
   static final int GAME_WIDTH = 600;
   static final int UNIT_SIZE = 10;
   static final int GAME_UNITS = (GAME_WIDTH * GAME_HEIGHT)/UNIT_SIZE;
   boolean running = false;
   int applex;
   int appley;
   int x[] = new int[GAME_UNITS];
   int y[] = new int[GAME_UNITS];
   int snakeLength = 2;
   char direction = 'R';
   Random random;
   int score = 0;
   int constraint = GAME_HEIGHT/UNIT_SIZE;
   Font font = new Font("Arial", Font.BOLD, 60);
   SnakeGame () {
      random = new Random();
      startGame();
   }
   public void startGame () {
      x[0] = UNIT_SIZE * random.nextInt(constraint);
      y[0] = UNIT_SIZE * random.nextInt(constraint);
      StdDraw.setCanvasSize(GAME_WIDTH, GAME_HEIGHT);
      StdDraw.setScale(0, GAME_HEIGHT);
      running = true;
      newApple();
      draw();
   }
   public void draw() {
      while (running) {
         checkApple();
         checkCollisions();
         StdDraw.enableDoubleBuffering();
         StdDraw.clear();
         StdDraw.setPenColor(StdDraw.BLUE);
         StdDraw.filledCircle(applex, appley, UNIT_SIZE);
         for (int i = 0; i < snakeLength; i++) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
         }
         StdDraw.show();
         StdDraw.pause(35);
         move();
         if (StdDraw.hasNextKeyTyped()) {
            char temp = StdDraw.nextKeyTyped();
            switch (temp) {
               case 'w':
               direction = 'U';
               break;
               case 'a':
               direction = 'L';
               break;
               case 's':
               direction = 'D';
               break;
               case 'd':
               direction = 'R';
               break;
            }
         }
      }
   }
   public void move() {
      for (int i = snakeLength; i>0;i--) {
         x[i] = x[i - 1];
         y[i] = y[i - 1];
      }
      switch(direction) {
      case 'U':
         y[0] = y[0] + UNIT_SIZE;
         break;
      
      case 'D':
         y[0] = y[0] - UNIT_SIZE;
         break;
      case 'L':
         x[0] = x[0] - UNIT_SIZE;
         break;
      case 'R':
         x[0] = x[0] + UNIT_SIZE;
         break;
      }
   }
   public void newApple() {
      applex = UNIT_SIZE * random.nextInt(constraint);
      appley = UNIT_SIZE * random.nextInt(constraint);
   }
   public void checkCollisions () {
      for (int i = snakeLength; i>0; i--) {
         if (x[0] == x[i]&&y[0] == y[i]) {
            gameOver();
         }
      }
      if (x[0] < 0) {
         gameOver();
      }
      if (y[0] < 0) {
         gameOver();
      }
      if (x[0] > GAME_WIDTH) {
         gameOver();
      }
      if (y[0] > GAME_HEIGHT) {
         gameOver();
      }
   }
   public void checkApple () {
      if ((applex == x[0]) && (appley == y[0])) {
         newApple();
         score++;
         snakeLength++;
      }
   }
   public void gameOver () {
      running = false;
      StdDraw.clear();
      StdDraw.setFont(font);
      StdDraw.text(GAME_WIDTH/2, GAME_HEIGHT/2, "GAME OVER");

   }
}
