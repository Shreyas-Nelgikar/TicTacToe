# TicTacToe Game

A Java-based TicTacToe game implementation with support for human vs human, human vs bot gameplay, and multiple winning strategies.

## Features

- **Flexible Board Size**: Play on any board size (minimum 2x2)
- **Multiple Player Types**: Human players and AI bots
- **Bot Difficulty Levels**: Easy difficulty bot with strategic gameplay
- **Winning Strategies**: Row and Column winning detection
- **Undo Functionality**: Human players can undo moves
- **Game Status Tracking**: Win, draw, and in-progress states

## Project Structure

```
src/main/java/org/example/
├── Main.java                    # Entry point of the application
├── controller/
│   └── GameController.java      # Game flow controller
├── models/
│   ├── Game.java               # Core game logic with Builder pattern
│   ├── Board.java              # Game board representation
│   ├── Player.java             # Abstract player class
│   ├── HumanPlayer.java        # Human player implementation
│   ├── Bot.java                # AI bot player implementation
│   ├── Cell.java               # Board cell representation
│   ├── Symbol.java             # Player symbol (X, O, etc.)
│   ├── User.java               # User information
│   └── [Other model classes]   # Enums and data models
├── strategy/
│   ├── WiningStrategy.java     # Winning strategy interface
│   ├── RowWinningStrategy.java # Row-based win detection
│   ├── ColumnWinningStrategy.java # Column-based win detection
│   └── PlayingStrategy.java    # Bot playing strategy interface
├── factory/
│   └── PlayingStrategyFactory.java # Factory for bot strategies
└── exceptions/
    ├── InvalidBoardSizeException.java
    ├── InvalidPlayersSizeException.java
    └── NoStrategyException.java
```

## Design Patterns Used

- **Builder Pattern**: Game creation with validation
- **Strategy Pattern**: Winning strategies and bot playing strategies
- **Factory Pattern**: Bot strategy creation
- **Template Method**: Abstract Player class

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Dependencies

- **Lombok**: For reducing boilerplate code (annotations like @Getter, @NoArgsConstructor)

## How to Run

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd TicTacToe
   ```

2. **Compile the project**
   ```bash
   mvn compile
   ```

3. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

## Gameplay Instructions

1. **Board Size**: Enter the desired board size (minimum 2)

2. **Player Type**: Choose between:
   - `human`: Play with other human players only
   - `bot`: Include an AI bot in the game

3. **Player Setup**: 
   - For human players: Enter name, age, email, and symbol
   - Bot automatically uses symbol "O"

4. **Winning Strategies**: Select from:
   - `1`: Row Winning Strategy
   - `2`: Column Winning Strategy
   - Multiple strategies can be selected (e.g., "1|2")

5. **During Gameplay**:
   - Human players can undo moves by typing 'y' when prompted
   - Bot moves are automatic
   - Game ends when someone wins or board is full (draw)

## Game Rules

- Players take turns placing their symbols on the board
- Win by getting all symbols in a row or column (based on selected strategies)
- Human players can undo multiple moves
- Game supports any board size with corresponding number of players

## Architecture Highlights

### Game Creation
Uses Builder pattern with validation:
```java
Game game = Game.createGame()
    .ofBoardSize(boardSize)
    .ofPlayers(players)
    .ofStrategies(strategies)
    .build();
```

### Extensible Strategy System
- Easy to add new winning strategies (diagonal, anti-diagonal, etc.)
- Bot playing strategies can be extended for different difficulty levels

### Exception Handling
- Custom exceptions for invalid game configurations
- Proper validation before game creation

## Future Enhancements

- Diagonal winning strategies
- Multiple bot difficulty levels
- Network multiplayer support
- GUI interface
- Game replay functionality
- Tournament mode

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is open source and available under the MIT License.