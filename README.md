# project_git - Simple Todo CLI

Практическое занятие 2. Организация проекта с системой контроля версий и CI/CD.

**Студент:** Ковтуненко А. Д., группа ПР-23.103
**МДК:** 02.02 Инструментальные средства разработки программного обеспечения
**Специальность:** 09.02.07 Информационные системы и программирование

## Цели
- Освоить Git и модель GitHub Flow
- Разработать консольное приложение «список задач» на Java
- Реализовать тесты JUnit 5
- Настроить CI (сборка + тесты при push в main) и CD (GitHub Release, GitHub Packages)

## Возможности
add, remove, done, search, list, clear, exit

## Стек
Java 23, Gradle (wrapper + Shadow), JUnit 5, GitHub Actions

## Сборка
./gradlew build

## CI/CD
- ci.yml - сборка + тесты + создание Release с fat-JAR
- publish-package.yml - публикация JAR в GitHub Packages
- nightly.yml - ежедневная сборка и тесты

## Ссылки
- Actions: https://github.com/java-chill/project_git/actions
- Releases: https://github.com/java-chill/project_git/releases
- Packages: https://github.com/java-chill/project_git/packages

