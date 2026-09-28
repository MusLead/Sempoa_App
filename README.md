# Sempoa App

A collection of Java arithmetic-practice programs for working through exercises with a physical abacus (sempoa). The programs show addition or subtraction questions, check your answers, and write dated text logs in the directory from which you launch them. They do **not** draw a virtual abacus.

## What is in each folder?

| Folder | Entry point | Interface | What distinguishes it |
| --- | --- | --- | --- |
| [`Sempoa/`](Sempoa/) | `AppRev` or `AppTerminal` | Swing window or terminal | Contains two separate implementations. `AppRev` offers a small window with an operation button for addition/subtraction; `AppTerminal` prompts in the terminal and also includes a structured subtraction training mode. |
| [`SempoaPrime/`](SempoaPrime/) | `App_GUI` | Small Swing window | Another implementation of the addition/subtraction GUI. Despite its name, it does **not** practise prime numbers. It keeps question generation for both operations in one `questions()` method and starts in its 400 × 200 layout. The source also contains an unused larger-layout method. |
| [`SempoaBig/`](SempoaBig/) | `Sempoa_big` | Larger Swing window | A larger presentation of the same addition/subtraction exercise concept (600 × 300). It starts in the enlarged layout, increases the font sizes, repositions controls and centres some labels. Its exercise logic is particularly close to `SempoaPrime`. |

`SempoaPrime` and `SempoaBig` are therefore very similar in functionality: their main visible difference is the layout. `Sempoa/src/AppRev.java` implements the same basic exercises with separate addition and subtraction question methods and a different mode-change flow. These are alternative programs, not three parts of one application. The `bin/` directories contain compiled classes, and the root or folder-level dated `.txt` files are previous exercise logs. The nested `README/` folders contain starter VS Code Java instructions rather than additional programs.

**Interaction note:** In the Swing versions, you can focus the answer field and press Enter to start a question, submit an answer and move to the next question. The source attaches the Start button and answer field to separate listener instances while keeping the current answer as instance state; starting with the button can therefore lead to incorrect checking. Use Enter in the answer field for the most reliable path through these versions.

## Compared with [Abacus-javaFX](https://github.com/MusLead/abacus-javaFX)

| | Sempoa App | Abacus-javaFX |
| --- | --- | --- |
| UI | Small Java Swing programs, plus one terminal version | Multiscreen JavaFX application |
| Operations | Addition and subtraction | Addition, subtraction, multiplication and division |
| Settings | Predetermined number ranges in the source | Number range and operation selected in the app |
| Results | Timestamped plain-text exercise logs | Player profiles, correct/wrong totals and session history saved locally as YAML |
| Build | Compile a selected standalone Java source file | Gradle wrapper with JavaFX and fulib dependencies |

Both projects generate arithmetic questions; neither is a simulation of abacus beads. See the [Abacus-javaFX README](https://github.com/MusLead/abacus-javaFX#readme) for its separate setup instructions.

## Requirements

Use **JDK 17 or later**. The source calls `Random.nextInt(origin, bound)`, which is available in Java 17. Swing is included with the JDK. No Gradle, JavaFX or Graphviz installation is needed for these Sempoa programs. You need a graphical desktop to display the Swing windows; the terminal version runs in a terminal.

On macOS with Homebrew, install and select JDK 17 for your current Terminal session:

```bash
brew install openjdk@17
export JAVA_HOME="$(brew --prefix openjdk@17)/libexec/openjdk.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
java -version
```

The version check should report 17. Re-run the `export` lines when you open a new Terminal session if another JDK is selected.

## Compile and run

Clone the repository once:

```bash
git clone https://github.com/MusLead/Sempoa_App.git
cd Sempoa_App
```

Choose **one** of the programs below. Each command compiles the source into its own `bin/` folder and runs it from that program's folder, so new exercise logs appear alongside its source folder.

### Sempoa: Swing window

```bash
cd Sempoa
mkdir -p bin
javac -d bin src/AppRev.java
java -cp bin AppRev
```

### Sempoa: terminal version

If you are already inside `Sempoa/`, run:

```bash
javac -d bin src/AppTerminal.java
java -cp bin AppTerminal
```

At the prompt, enter `+` for random addition or `-` for subtraction. The subtraction path asks whether you want random exercises (`y`) or structured training (`n`). Follow the on-screen prompts to answer and continue.

### SempoaPrime: small Swing window

From the repository root:

```bash
cd SempoaPrime
mkdir -p bin
javac -d bin src/App_GUI.java
java -cp bin App_GUI
```

### SempoaBig: larger Swing window

From the repository root:

```bash
cd SempoaBig
mkdir -p bin
javac -d bin src/Sempoa_big.java
java -cp bin Sempoa_big
```

After running one program, use `cd ..` to return to the repository root before following another folder's instructions. The `SempoaBig/build-jar/` and `SempoaPrime/build-jar/` folders also contain older packaged JARs, but the commands above compile the checked-in source directly.
