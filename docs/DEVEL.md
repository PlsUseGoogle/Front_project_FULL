

# 🛠️ Development & Build Guide

Poniżej znajduje się ściąga z najważniejszych zadań Gradle (Tasks) używanych w tym projekcie do budowania, testowania i zarządzania zależnościami.

## 📦 Build / Packaging
Proces tworzenia artefaktów i paczek.

| Task | Opis |
| :--- | :--- |
| `build` | **Pełny cykl:** Kompiluje, uruchamia testy, składa artefakty (agreguje `assemble` i `check`). |
| `assemble` | Składa artefakty (JAR) **bez** uruchamiania testów (szybkie budowanie). |
| `bootJar` | Tworzy "Fat JAR" Spring Boot (zawiera wszystkie zależności, gotowy do uruchomienia). |
| `jar` | Tworzy zwykły JAR (tylko klasy projektu, bez zależności). |
| `bootBuildImage` | Buduje obraz OCI (Docker/Podman) bezpośrednio z kodu, bez Dockerfile. |
| `classes` | Tylko kompilacja plików źródłowych Java. |

### 🧹 Clean build + artefakt
Linux/macOS:
```bash
./gradlew clean bootJar
```
Windows:
```bat
.\gradlew.bat clean bootJar
```

Po zbudowaniu plik JAR znajduje się w: `build/libs/`

### ▶️ Uruchamianie JAR
Linux/macOS:
```bash
java -jar build/libs/<nazwa-pliku>.jar
```
Windows:
```bat
java -jar build\libs\<nazwa-pliku>.jar
```

## 🧪 Testy i Weryfikacja
Zapewnienie jakości kodu.

* **`test`** – Uruchamia testy jednostkowe i integracyjne.
* **`check`** – Wykonuje wszystkie weryfikacje (głównie `test`, ale też lintery jeśli zostaną dodane).
* **`bootTestRun`** – Uruchamia aplikację w trybie testowym (na testowym classpath).

## 🚀 Uruchamianie (Localhost)
* **`bootRun`** – Startuje aplikację Spring Boot bezpośrednio z kodu źródłowego (Hot Swap możliwy).
* Po starcie aplikacja jest dostępna domyślnie pod: **http://localhost:8080**

Linux/macOS:
```bash
./gradlew bootRun
```
Windows (PowerShell):
```bat
.\gradlew.bat bootRun
```

## 🔍 Analiza Zależności (Dependency Hell Fighter)
Narzędzia do debugowania konfliktów wersji i struktury projektu.

```bash
# Wyświetla pełne drzewo zależności
./gradlew dependencies

# Analizuje konkretną bibliotekę (dlaczego jest w projekcie i w jakiej wersji)
./gradlew dependencyInsight --dependency <nazwa>

```
