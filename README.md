# CHAIKIN with Java

Java project implementing **Chaikin's algorithm** with canvas rendering and simple user interaction.  
You can add points, visualize line smoothing, and step through the iterations.

---

## 📂 Project Structure
```

src/
│── app/Main.java                # Entry point
│── app/ChaikinApp.java      # Core application logic
│── algorithm/Chaikin.java   # Algorithm implementation
│── model/Point.java         # Point model
│── model/Line.java          # Line model
│── ui/Window.java          # Application window
│── ui/Canvas.java           # Drawing canvas
└── Makefile                 # Build & run commands

````

---

## 🚀 How to Run

1. Compile all sources:
   ```bash
   make build
````

2. Run the program:

   ```bash
   make run
   ```

---

## ✨ Features

* 🖱️ Add points with mouse clicks
* ⌨️ Keyboard shortcuts:

  * `Enter` → Start Chaikin’s algorithm
  * `Space` → Clear canvas
  * `Escape` → Exit application
* 🔄 Step-by-step animation (up to 7 iterations)
* 📊 Real-time rendering with Java Swing
* ⚡ Simple and lightweight design
