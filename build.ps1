
# Компиляция
Remove-Item -Recurse -Force build # Удаляем старые файлы, если существуют
New-Item -ItemType Directory -Force -Path build # Создаем выходную директорию

javac -d build src/main/java/com/homework/*.java src/main/java/com/homework/collection/*.java src/main/java/com/homework/entity/*.java

# Запуск приложения
java -ea -cp build com.homework.HomeworkApplication