# project_git — Simple Todo CLI

Практическое занятие 2. Организация проекта с системой контроля версий и CI/CD.

**Студент:** Ковтуненко А. Д., группа ПР-23.103
**МДК:** 02.02 Инструментальные средства разработки программного обеспечения
**Специальность:** 09.02.07 Информационные системы и программирование

## Цели

- Освоить работу с системой контроля версий Git и моделью ветвления GitHub Flow.
- Разработать консольное приложение на Java (список задач).
- Реализовать юнит-тесты JUnit 5.
- Настроить CI (сборка + тесты при каждом push в `main`) и CD (GitHub Release и GitHub Packages).

## Возможности приложения

| Команда | Описание |
|---|---|
| `add <task>` | добавить задачу |
| `remove <index>` | удалить задачу по индексу |
| `done <index>` | отметить задачу выполненной |
| `search <text>` | поиск задач по подстроке |
| `list` | показать все задачи (с отметкой выполнения) |
| `clear` | очистить список |
| `exit` | выйти |

## Технологии

- Java 23
- Gradle (wrapper, Shadow plugin для fat-JAR)
- JUnit 5
- GitHub Actions (CI/CD)

## Сборка и запуск

```bash
./gradlew build          # сборка и тесты
java -jar build/libs/todo-app-<version>.jar
```

## CI/CD

- `.github/workflows/ci.yml` — при push в `main`: сборка, запуск тестов и создание GitHub Release с fat-JAR. При падении тестов workflow завершается с ошибкой.
- `.github/workflows/publish-package.yml` — после успешного CI публикует тот же JAR в GitHub Packages как Maven-артефакт `org.example:todo-app`.
- `.github/workflows/nightly.yml` — по расписанию (ежедневно) собирает проект и запускает тесты без релиза (проверка стабильности).

## Результаты (ссылки)

- **Actions:** https://github.com/java-chill/project_git/actions
- **Releases:** https://github.com/java-chill/project_git/releases
- **Packages:** https://github.com/java-chill/project_git/packages

## Использование пакета как зависимости

```gradle
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/java-chill/project_git")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
        }
    }
    mavenCentral()
}

dependencies {
    implementation 'org.example:todo-app:0.3.0'
}
```
