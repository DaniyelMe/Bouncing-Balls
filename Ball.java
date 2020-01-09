import java.awt.Color;

public class Ball {
  // instance variables
  public double rx, ry; // position
  public double vx, vy; // velocity/ speed
  public final double radius; // radius

  private double gravityx = 0;// -0.001;
  private double gravityy = 0;
  private double bounce_coef = 1; // bounce_coefficient
  private Color color;

  // constructor
  public Ball(String ballColor) {
    // Randomize the location
    rx = Math.random();
    ry = Math.random();

    // pick random speed
    vx = StdRandom.uniform(-0.02, 0.04);
    vy = StdRandom.uniform(-0.02, 0.04);

    radius = 0.1;

    setColor(ballColor);
  }

  // bounce of vertical wall by reflecting x-velocity
  private void bounceOffVerticalWall() {
    vx = -vx * bounce_coef;
  }

  // bounce of horizontal wall by reflecting y-velocity
  private void bounceOffHorizontalWall() {
    vy = -vy * bounce_coef;
  }

  public void setLocation(double rx, double ry) {
    this.rx = rx;
    this.ry = ry;
  }

  public void collision(Ball ball2) {
    double xVDiff = vx - ball2.vx;
    double yVDiff = vy - ball2.vy;

    double xDist = ball2.rx - rx;
    double yDist = ball2.ry - ry;

    // Prevent accidental overlap of balls
    if (xVDiff * xDist + yVDiff * yDist >= 0) {
      // Grab angle between the two colliding balls
      double angle = -Math.atan2(yDist, xDist);

      // Velocity before equation - rotate
      double u1x = vx * Math.cos(angle) - vy * Math.sin(angle);
      double u1y = vx * Math.sin(angle) + vy * Math.cos(angle);
      double u2x = ball2.vx * Math.cos(angle) - ball2.vy * Math.sin(angle);
      double u2y = ball2.vx * Math.sin(angle) + ball2.vy * Math.cos(angle);

      // Store mass in var for better readability in collision equation
      double m1 = 1;
      double m2 = 1;

      // Velocity after 1d collision equation
      double v1x = u1x * (m1 - m2) / (m1 + m2) + u2x * 2.1 * m2 / (m1 + m2);
      double v1y = u1y;

      double v2x = u2x * (m1 - m2) / (m1 + m2) + u1x * 2.1 * m2 / (m1 + m2);
      double v2y = u2y;

      // Final velocity after rotating axis back to original location
      double vFinal1 = v1x * Math.cos(-angle) - v1y * Math.sin(-angle);
      double vFinal2 = v2x * Math.cos(-angle) - v2y * Math.sin(-angle);

      // Swap balls velocities for realistic bounce effect
      vx = vFinal1;
      vy = v1y;

      ball2.vx = vFinal2;
      ball2.vy = v2y;
    }
  }

  // move the ball one step
  public void move() {
    if (Math.abs(rx + vx) + radius > 1.0)
      bounceOffVerticalWall();
    if (Math.abs(ry + vy) + radius > 1.0)
      bounceOffHorizontalWall();
    vy = vy + gravityy;
    vx = vx + gravityx;
    // vy = vy - gravity * dt;
    rx = rx + vx;
    ry = ry + vy;

    // bounce off wall according to law of elastic collision
    if ((ry - radius) < -1)
      ry = -0.1 + radius;
    if ((rx - radius) < -1)
      rx = -0.1 + radius;
  }

  // draw the ball
  public void draw() {
    StdDraw.setPenColor(color);
    StdDraw.filledCircle(rx, ry, radius);
  }

  public double getDistance(double x2, double y2) {
    double xDistance = x2 - rx;
    double yDistance = y2 - ry;
    return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
  }

  public void setColor(String ballColor) {
    if (ballColor.equals("RED")) {
      color = Color.red;
    } else if (ballColor.equals("BLUE")) {
      color = Color.blue;
    } else if (ballColor.equals("BLACK")) {
      color = Color.black;
    } else if (ballColor.equals("CYAN")) {
      color = Color.cyan;
    } else if (ballColor.equals("DARK_GRAY")) {
      color = Color.darkGray;
    } else if (ballColor.equals("GRAY")) {
      color = Color.gray;
    } else if (ballColor.equals("GREEN")) {
      color = Color.green;
    } else if (ballColor.equals("YELLOW")) {
      color = Color.yellow;
    } else if (ballColor.equals("LIGHT_GRAY")) {
      color = Color.lightGray;
    } else if (ballColor.equals("MAGENTA")) {
      color = Color.magenta;
    } else if (ballColor.equals("ORANGE")) {
      color = Color.orange;
    } else if (ballColor.equals("PINK")) {
      color = Color.pink;
    } else if (ballColor.equals("WHITE")) {
      color = Color.white;
    }
  }

}
