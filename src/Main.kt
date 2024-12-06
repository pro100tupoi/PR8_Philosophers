import java.util.*
import kotlin.random.Random

fun main() {
    while (true) {
        print("Введите количество философов >>> ")
        val count = readln().toIntOrNull() ?: continue
        if (count < 1) {
            continue
        }
        val forks = MutableList(count) { true } // Список, представляющий наличие вилок
        val indices = (0 until count).toMutableList()

        while (indices.isNotEmpty()) {
            val index = indices.random()
            indices.remove(index) // Удаление философа из очереди
            val takeRightFirst = Random.nextBoolean()
            var forkTaken = false
            var takenFrom: String? = null

            // Логика выбора вилки
            if (takeRightFirst) {
                // Сначала пробуем взять правую вилку
                if (forks[(index + 1) % count]) {
                    forks[(index + 1) % count] = false // Взятие вилки
                    forkTaken = true
                    takenFrom = "справа"
                }
                // Если не удалось, пробуем взять левую вилку
                else if (forks[index]) {
                    forks[index] = false // Взятие вилки
                    forkTaken = true
                    takenFrom = "слева"
                }
            } else {
                // Сначала пробуем взять левую вилку
                if (forks[index]) {
                    forks[index] = false // Взятие вилки
                    forkTaken = true
                    takenFrom = "слева"
                }
                // Если не удалось, пробуем взять правую вилку
                else if (forks[(index + 1) % count]) {
                    forks[(index + 1) % count] = false // Взятие вилки
                    forkTaken = true
                    takenFrom = "справа"
                }
            }

            // Вывод результатов
            if (forkTaken) {
                println("Философ ${index + 1} взял вилку $takenFrom")
            } else {
                println("Философ ${index + 1} не смог взять вилку, он думает.")
            }
        }

        print("Ещё раз? (y/n) >>> ")
        if (readln().lowercase(Locale.getDefault()) != "y") {
            break
        }
    }
}