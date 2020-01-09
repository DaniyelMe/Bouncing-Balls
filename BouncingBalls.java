public class BouncingBalls {

    public static void main(String[] args) {
        // Number of bouncing balls
        int n = Integer.parseInt(args[0]);

        // Set boundary to box with coordinates between -1 and +1
        StdDraw.setCanvasSize(400, 400);
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        int colorIndex = 1;
        // Create an array of n random balls
        Ball[] balls = new Ball[n];
        for (int i = 0; i < n; i++) {
            // For each ball set the next color from args, beside 0 which is the number of
            // balls.
            balls[i] = new Ball(args[colorIndex++]);
            // Fix overlapping on start
            for (int j = 0; j < i; j++) {
                if (balls[i].getDistance(balls[j].rx, balls[j].ry) - balls[i].radius * 2 < 0) {
                    balls[i].setLocation(Math.random(), Math.random());
                    j = -1; // restart the loop
                }
            }
        }

        // Do the animation loop
        StdDraw.enableDoubleBuffering();

        while (true) {
            // Move the n balls
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Unless it's the same ball
                    if (balls[i] != balls[j]) {
                        // Check for collision
                        if (balls[i].getDistance(balls[j].rx, balls[j].ry) - balls[i].radius * 2 < 0) {
                            balls[i].collision(balls[j]);
                        }
                    }
                }
                balls[i].move();
            }

            // Draw the n balls
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            for (int i = 0; i < n; i++) {
                balls[i].draw();
            }
            // Copy offscreen buffer to onscreen
            StdDraw.show();
            // Pause for 20 ms
            StdDraw.pause(20);
        }

    }
}