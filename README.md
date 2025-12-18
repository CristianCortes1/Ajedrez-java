# ♟️ Ajedrez Java

A fully functional chess game implemented in Java with a graphical user interface using Swing. This project features a complete chess engine with move validation, special moves (castling, en passant, pawn promotion), and an intuitive GUI.

## 📋 Features

- **Complete Chess Rules Implementation**
  - All standard piece movements (King, Queen, Rook, Bishop, Knight, Pawn)
  - Special moves: Castling (short and long), En Passant, Pawn Promotion
  - Turn-based gameplay for two players
  - Move validation and legal move highlighting

- **Graphical User Interface**
  - Interactive chessboard with visual piece representation
  - Initial window for game setup
  - Game window with real-time board updates
  - Winner announcement window
  - Visual feedback for valid moves

- **Game Logic**
  - Check and checkmate detection
  - Captured pieces tracking
  - Move history
  - Game state management

## 🛠️ Technologies Used

- **Java 22**
- **Maven** - Dependency management and build tool
- **Swing** - GUI framework
- **JUnit 5** - Unit testing framework

## 📁 Project Structure

```
Ajedrez-java/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/mycompany/ajedrez/
│   │       │   └── Ajedrez.java          # Main application entry point
│   │       ├── controlador/
│   │       │   └── Controler.java        # MVC Controller
│   │       ├── modelo/
│   │       │   ├── Casilla.java          # Board square model
│   │       │   ├── Ficha.java            # Chess piece model
│   │       │   ├── Logic.java            # Game logic engine
│   │       │   └── Tablero.java          # Chess board model
│   │       └── vista/
│   │           ├── VentanaGanador.java   # Winner window
│   │           ├── VentanaInicial.java   # Initial setup window
│   │           ├── VentanaJuego.java     # Main game window
│   │           └── VentanaJugadaPeon.java # Pawn promotion window
│   └── test/
│       └── java/
│           └── modelo/
│               └── TableroTest.java      # Board unit tests
├── images/                               # Game assets
├── pom.xml                               # Maven configuration
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK) 22** or higher
- **Apache Maven 3.x**

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/Ajedrez-java.git
cd Ajedrez-java
```

2. Build the project with Maven:
```bash
mvn clean install
```

### Running the Application

#### Option 1: Using Maven
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.ajedrez.Ajedrez"
```

#### Option 2: Running the JAR
```bash
java -jar target/Ajedrez-1.0.jar
```

## 🎮 How to Play

1. Launch the application
2. The initial window will appear - set up your game preferences
3. The chess board will be displayed with all pieces in their starting positions
4. Click on a piece to see available moves
5. Click on a highlighted square to move the piece
6. Players alternate turns (White moves first)
7. The game continues until checkmate or stalemate

### Special Moves

- **Castling**: Click on your King and then on the Rook to castle
- **En Passant**: Available automatically when conditions are met
- **Pawn Promotion**: When a pawn reaches the opposite end, choose which piece to promote to

## 🧪 Testing

Run the test suite with Maven:
```bash
mvn test
```

## 🏗️ Architecture

The project follows the **Model-View-Controller (MVC)** pattern:

- **Model** (`modelo/`): Contains the game logic, board representation, and piece behavior
- **View** (`vista/`): Handles all GUI components and user interface
- **Controller** (`controlador/`): Manages interaction between model and view

## 📝 License

This project is available for educational purposes.

## 👤 Author

**bejar**

---

Made with ☕ and Java
