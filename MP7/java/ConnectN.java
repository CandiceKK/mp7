

/**
 * A class that implements a ConnectN game.
 * <p>
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/5/">MP5 Documentation</a>
 */
public class ConnectN {

    /**
     * @param args hello
     */

    /** Class threshold that we used to check the input. */
    public static final int MAX_HEIGHT = 16;
    /** Class threshold that we used to check the input. */
    public static final int MAX_WIDTH = 16;
    /** Class threshold that we used to check the input. */
    public static final int MIN_HEIGHT = 6;
    /** Class threshold that we used to check the input. */
    public static final int MIN_WIDTH = 6;
    /** Class variable the title. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public String title;
    /** Class variable the width of the board. */
    private int width;
    /** Class variable the height of the board. */
    private int height;
    /** Class variable the n of the winning. */
    private final int n = 5;
    /** Class variable the board table. */
    private Player[][] board;
    /** Class variable the total number of the games. */
    private static int count = 0;
    /** Class variable that test if the game begins. */
    private boolean hasStart = false;
    /** Class variable that the unique id of the board. */
    @SuppressWarnings("unused")
    private int id;
    /** Class variable that test if the game over. */
    private boolean gameOver = false;
    /**
     *
     * @return integer count;
     */
    private static int nextID() {
        count++;
        return count;
    }


    /**
     *
     * @param setWidth set the width
     * @param setHeight set the height
     * @param setN set the n
     */
    public ConnectN(final int setWidth, final int setHeight, final int setN) {
        if (setWidth >= MIN_WIDTH && setWidth <= MAX_WIDTH) {
            this.width = setWidth;
        } else if (setWidth < MIN_WIDTH || setWidth > MAX_WIDTH) {
            this.width = 0;
        }
        if (setHeight >= MIN_HEIGHT && setHeight <= MAX_HEIGHT) {
            this.height = setHeight;
        } else {
            this.height = 0;
        }

        this.board = new Player[this.width][this.height];
        ConnectN.getTotalGames();
        this.id = ConnectN.nextID();
    }
    /**
     * default constructor.
     */
    public ConnectN() {
        this.board = new Player[this.width][this.height];
        ConnectN.getTotalGames();
        this.id = ConnectN.nextID();
    }

    /**
     *
     * @param setWidth set the width
     * @param setHeight set the height
     */
    public ConnectN(final int setWidth, final int setHeight) {

        if (setWidth >= MIN_WIDTH && setWidth <= MAX_WIDTH) {
            this.width = setWidth;
        } else {
            this.width = 0;
        }
        if (setHeight >= MIN_HEIGHT && setHeight <= MAX_HEIGHT) {
            this.height = setHeight;
        } else {
            this.height = 0;
        }
        this.board = new Player[this.width][this.height];
        ConnectN.getTotalGames();
        this.id = ConnectN.nextID();
    }
    /**
     *
     * @param otherboard the copy of the original board
     */
    public ConnectN(final ConnectN otherboard) {
        this.height = otherboard.height;
        this.width = otherboard.width;
        this.board = new Player[this.width][this.height];
        ConnectN.getTotalGames();
        this.id = ConnectN.nextID();
    }
    /**
     *
     * @return object
     */
    public static int getTotalGames() {
        return count;
    }
    /**
     *
     * @return id
     */
    public int getID() {
        return (ConnectN.getTotalGames() - 1);
    }
    /**
     *
     * @param i the width input
     * @return boolean true or false
     */
    public boolean setWidth(final int i) { // also false if the game get started
        if (hasStart == true) {
            return false;
        }
        if (i < MIN_WIDTH || i > MAX_WIDTH) {
            return false;
        }
        this.width = i;
        return true;
    }
    /**
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     *
     * @param i the input height
     * @return boolean true or false
     */
    public boolean setHeight(final int i) { // also false if the game get started
        if (hasStart) {
            return false;
        }
        if (i < MIN_HEIGHT || i > MAX_HEIGHT) {
            return false;
        }

        this.height = i;

        return true;
    }
    /**
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     *
     * @param player test if it is valid
     * @param setX the input x
     * @param setY the input y
     * @return boolean value
     */
    // any board parameters remain uninitialized, including width, height, and N
    // the player is invalid
    // the position is invalid for this board
    // the game has already ended.
    public boolean setBoardAt(final Player player, final int setX, final int setY) { // i is x
        if (width == 0 || height == 0 || gameOver
            || setX >= this.width || setX < 0 || setY >= this.height || setY < 0) {
            return false;
        }
        if (this.board[setX][setY] == null) {
                this.board[setX][setY] = player;
                hasStart = true;
                return true;
        }
        return false;
    }

    /**
     *
     * @param player the game player
     * @param setX the x
     * @return boolean value
     */
    public boolean setBoardAt(final Player player, final int setX) {
        if (gameOver == true) {
            return false;
        }
        if (width == 0 || height == 0
                || getWinner() != null
                || setX > this.width - 1 || setX < 0) {
            return false;
        }
        for (int j = 0; j < this.height; j++) {
            if (board[setX][j] == null) {
                if (j == 0) {
                    board[setX][j] = player;
                    hasStart = true;
                    return true;
                }
                if (j > 0) {
                    if (board[setX][j - 1] != null) {
                        board[setX][j] = player;
                        hasStart = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     *
     * @param getX x coordinate
     * @param getY y coordinate
     * @return the player
     */
    //Should return null if the board position is invalid,
    //if the game has not started,
    //or if nobody has played yet at that position.
    //Otherwise returns the player whose tile is at that position.
    public Player getBoardAt(final int getX, final int getY) {

        if (getX >= this.width || getX < 0 || getY >= this.height || getY < 0 || !hasStart
            || this.width == 0 || this.height == 0 || this.n == 0) {
            return null;
        }
        if (this.board[getX][getY] == null) {
            return null;
            }
        return this.board[getX][getY];
        }

    /**
     *
     * @return player
     */
    public Player[][] getBoard() {
        Player[][] newBoard = new Player[this.width][this.height];
        if (this.width == 0 || this.height == 0) {
            return null;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.board[i][j] != null) {
                    Player temp = new Player(board[i][j]);
                    newBoard[i][j] = temp;
                }
            }
        }
        return newBoard;
    }

    /**
     *
     * @return the winner
     */
    public Player getWinner() {
        int winner = 0;
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height - 1; j++) {
                    if (this.getBoard()[i][j] == null) {
                        winner = 0;
                    } else if (!this.getBoard()[i][j].equals(this.getBoard()[i][j + 1])) {
                        winner = 0;
                    }  else if (this.getBoard()[i][j] != null
                            && this.getBoard()[i][j].equals(this.getBoard()[i][j + 1])) {
                        winner++;
                    }
                    if (winner == (this.n - 1)) {
                        gameOver = true;
                        this.getBoardAt(i, j).addScore();
                        return this.getBoardAt(i, j);
                    }
                }
            }

            winner = 0;
            for (int j = 0; j < this.height; j++) {
                winner = 0;
                for (int i = 0; i < this.width - 1; i++) {
                    if (this.getBoard()[i][j] == null) {
                        winner = 0;
                    } else if (!this.getBoard()[i][j].equals(this.getBoard()[i + 1][j])) {
                        winner = 0;
                    }  else if (this.getBoard()[i][j] != null
                            && this.getBoard()[i][j].equals(this.getBoard()[i + 1][j])) {
                        winner++;
                    }
                    if (winner == (this.n - 1)) {
                        gameOver = true;
                        this.getBoardAt(i, j).addScore();
                        return this.getBoardAt(i, j);
                    }
                }
            }
            //corner
            winner = 0;
            for (int i = 0; i < this.width - 1; i++) {
                winner = 0;
                for (int j = 0; j < this.height - 2; j++) {
                    if (this.getBoard()[i][j] != null) {
                        for (int k = 0; k < n; k++) {
                            if (i + k + 1 < this.width && j + k + 1 < this.height) {
                               if (this.getBoard()[i + k][j + k] != null
                                    && board[i + k][j + k].equals(board[i + k + 1][j + k + 1])) {
                                winner++;
                               }
                            }
                        }
                    }
                    if (winner == (this.n - 1)) {
                        gameOver = true;
                        this.getBoardAt(i, j).addScore();
                        return this.getBoardAt(i, j);
                    }
                }
            }
            winner = 0;
            for (int i = 0; i < this.width - 1; i++) {
                winner = 0;
                for (int j = this.height - 1; j >= 0; j--) {
                    if (this.getBoard()[i][j] != null) {
                        for (int k = 0; k < n; k++) {
                            if (i + k + 1 < this.width && j - k - 1 >= 0) {
                               if (this.getBoard()[i + k][j - k] != null
                                && board[i + k][j - k].equals(board[i + k + 1][j - k - 1])) {
                                winner++;
                               }
                            }
                        }
                    }
                    if (winner == (this.n - 1)) {
                        gameOver = true;
                        this.getBoardAt(i, j).addScore();
                        return this.getBoardAt(i, j);
                    }
                }
            }
            return null;
        }

    /**
     *
     * @param width width
     * @param height height
     * @param n n
     * @return connectN
     */
    public static ConnectN create(final int width, final int height, final int n) {
        if (width < MIN_WIDTH || width > MAX_WIDTH
                ||
                height < MIN_HEIGHT || height > MAX_HEIGHT
               ) {
            return null;
        } else {
            return new ConnectN(width, height, n);
        }
    }

    /**
     * @param number create many
     * @param width the width
     * @param height the height
     * @param n the n
     * @return the array of connect
     */
    public static ConnectN[] createMany(final int number, final int width,
            final int height, final int n) {
        if (width < MIN_WIDTH || width > MAX_WIDTH
                ||
                height < MIN_HEIGHT || height > MAX_HEIGHT
               ) {
            return null;
        }
        ConnectN[] creatyMany = new ConnectN[number];
        for (int i = 0; i < number; i++) {
            creatyMany[i] = new ConnectN(width, height, n);
        }
        return creatyMany;
    }

    /**
     * @param firstBoard hid
     * @param secondBoard hid
     *
     *
     * @return boolean value
     */
    public static boolean compareBoards(final ConnectN firstBoard, final ConnectN secondBoard) {
        if (firstBoard == null || secondBoard == null) {
            return false;
        }
        if (firstBoard.width == secondBoard.width && firstBoard.height == secondBoard.height
                && firstBoard.n == secondBoard.n) {
            for (int i = 0; i < firstBoard.width; i++) {
                for (int j = 0; j < firstBoard.height; j++) {
                        if (firstBoard.board[i][j] != (secondBoard.board[i][j])) {
                            return false;
                        }
                    }
                }
            return true;
            }
        return false;
    }

    /**
     * @param boards this
     * @return boolean value
     */
    public static boolean compareBoards(final ConnectN... boards) {
        if (boards.length == 1) {
            return true;
        }
        for (int i = 0; i < boards.length - 1; i++) {
            if (!compareBoards(boards[i], boards[i + 1])) {
                return false;
            }
        }
        return true;
    }


    /**
     * @param string title
     */
    public void setTitle(final String string) {
        this.title = string;
    }

    /**
     * @return string title
     */
    public String getTitle() {
        return title;
    }

}
