# CPU Scheduler – Command Line Program

## Overview

This project is a **Java command-line CPU scheduling simulator**. It reads a JSON file describing a set of processes, runs the scheduler, and outputs the results to another JSON file.

The program is designed to run **entirely from the terminal** without requiring any IDE such as IntelliJ or Eclipse.

It should work on any machine with:

* **Java installed**
* A **standard terminal / command prompt**

The repository already includes the required **Jackson JSON libraries**, so no additional dependencies need to be installed.

---

# System Requirements

## Java

You must have **Java 8 or newer** installed.

You can check if Java is installed by running:

```
java -version
```

You should see output similar to:

```
java version "17.0.x"
```

If Java is not installed, install the **Java Development Kit (JDK)**.

Important:
The **JDK must be installed**, not just the Java runtime.

You can verify the compiler exists by running:

```
javac -version
```

---

# Supported Operating Systems

This project should run on:

* Windows
* macOS
* Linux

Two scripts are provided:

| Script    | OS            |
| --------- | ------------- |
| `run.bat` | Windows       |
| `run.sh`  | macOS / Linux |

Use whichever matches your system.

---

# Project Structure

```
CPUScheduler
│
├── src
│   ├── Main.java
│   ├── Process.java
│   ├── Scheduler classes...
│
├── lib
│   ├── jackson-core.jar
│   ├── jackson-databind.jar
│   └── jackson-annotations.jar
│
├── examples
│   ├── json1.JSON
│   └── json2.JSON
│
├── run.bat
├── run.sh
└── README.md
```

### Explanation of folders

**src**
Contains all Java source code.

**lib**
Contains the required Jackson libraries used for reading and writing JSON files.

These are already included, so **no external downloads are required**.

**examples**
Contains example JSON input files for testing the scheduler.

These files are optional and only included for convenience.

---

# Running the Program

## Step 1 – Open a terminal

Navigate to the **root project directory**.

Example:

```
cd CPUScheduler
```

The terminal must be opened **in the folder that contains**:

* `src`
* `lib`
* `run.bat`
* `run.sh`

---

# Windows Instructions

Run the program using:

```
run input.json output.json
```

Example:

```
run examples/json1.JSON result.JSON
```

What happens when this command runs:

1. The Java files in `src` are compiled.
2. The Jackson libraries in `lib` are loaded.
3. The scheduler reads the input JSON file.
4. The results are written to the output JSON file.

---

# macOS / Linux Instructions

First make the script executable:

```
chmod +x run.sh
```

Then run:

```
./run.sh input.json output.json
```

Example:

```
./run.sh examples/json1.JSON result.JSON
```

---

# Input JSON File

The **first argument** is the input file.

This file **must exist before running the program**.

Example:

```
run test.JSON result.JSON
```

The program will read `test.JSON`.

The file can be located:

* in the project root
* inside the examples folder
* in any directory (if the full path is provided)

Example:

```
run examples/json2.JSON output.JSON
```

or

```
run myTest.JSON output.JSON
```

---

# Output JSON File

The **second argument** is the output file.

This file **does NOT need to exist beforehand**.

The program will create it automatically.

Example:

```
run input.JSON result.JSON
```

After execution, `result.JSON` will contain the scheduler output.

---

# Using Your Own Test Files

You are not limited to the provided example files.

You may create your own JSON file anywhere.

Example:

```
run professorTest.JSON output.JSON
```

or

```
run tests/test1.JSON output.JSON
```

The program will process any valid JSON file.

---

# Important Notes

### Terminal Location

The terminal must be opened **in the project root directory**.

Correct:

```
CPUScheduler>
```

Incorrect:

```
CPUScheduler/src>
```

If the program is run inside `src`, the libraries will not be found.

---

### JSON File Paths

If the JSON file is not in the root folder, provide the path.

Example:

```
run examples/json1.JSON result.JSON
```

---

### Compilation

Each time the program runs, the script automatically compiles the Java files.

You do **not** need to compile manually.

---

# Troubleshooting

### "java is not recognized"

Java is not installed or not added to the system PATH.

Install the JDK and restart the terminal.

---

### "javac not found"

The Java compiler is missing.

Install the **Java Development Kit (JDK)** instead of only the runtime.

---

### "file not found"

The input JSON file path is incorrect.

Check:

* file spelling
* directory location
* correct path

Example:

```
run examples/json1.JSON result.JSON
```

---

# Example Run

Example command:

```
run examples/json1.JSON output.JSON
```

Expected terminal output:

```
Compiling...
Running scheduler...
```

After execution:

```
output.JSON
```

will contain the results of the CPU scheduling simulation.

---

# Summary

1. Install Java (JDK).
2. Open a terminal in the project folder.
3. Run the program with:

```
run input.json output.json
```

Example:

```
run examples/json1.JSON result.JSON
```

The program will compile automatically, execute the scheduler, and produce the output file.

---

# End of Instructions
