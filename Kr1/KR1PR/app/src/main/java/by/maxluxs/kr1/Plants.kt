package by.maxluxs.kr1

import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.model.vo.HomeFlowers
import java.util.*

object Plants {

    fun getPlug(): HomeFlowers =
        listOf(
            HomeFlower(
                category = "Кактус",
                instructions = "Данное растение некапризно и ухаживать за ним очень просто. Кстати, это выгодно выделяет его среди остальных кактусов данного семейства.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/12/1-62-700x660.jpg",
                name = "Нотокактус",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Кактус",
                instructions = "Очень любит свет, поэтому для его размещения надо выбирать хорошо освещенное место. При этом следует помнить, что такой кактус является медленнорастущим.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/12/1-61-700x675.jpg",
                name = "Лемэроцереус",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "фруктовый кустарник или дерево",
                instructions = "Многолетнее растение мушмула (Eryobotria), либо Локва представляет собой фруктовый кустарник или дерево. Оно является частью семейства Розовые подсемейства Яблоневые. В природных условиях такое растение встречается на территориях с мягким субтропическим климатом. Цветет оно в осеннее время, при этом его плодоношение приходится на зимние или весенние месяцы. Родом это растение из Китая и Япония.",
                photo = "https://rastenievod.com/wp-content/uploads/2018/07/1-15-700x634.jpg",
                name = "Мушмула",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Камнеломковые",
                instructions = "Такой род растений, как камнеломка (Saxifraga) имеет прямое отношение к семейству камнеломковые (Saxifragaceae). Он объединяет больше 400 видов травянистых растений, среди которых большинство многолетников, а остальные одно- либо двулетники. Она предпочитает в природе расти в местах с суровым климатом. Так, это растение можно повстречать в субарктических областях, в Альпах, на востоке Гренландии, а также в западной и восточной части Гималай. Камнеломка предпочитает селиться на каменистых насыпях, в расщелинах скал, на каменных кладках стен, а еще встречается и в низинных лугах.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/11/5-71-700x679.jpg",
                name = "Комнатная камнеломка",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Болотное",
                instructions = "это болотное, корневищное, травянистое растение является многолетником. Оно входит в число наиболее больших плотоядных растений. Его листочки, находящиеся снизу, чешуйчатые. Короткочерешковые ловчие листочки, отличающиеся достаточно большим размером, собраны в розетку. Они возвышаются над самим растением и строением чем-то напоминают урну с довольно широким отверстием сверху либо трубковидный кувшин.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/11/1-93-700x694.jpg",
                name = "Саррацения",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "камыш",
                instructions = "В природных условиях скирпус встречается на таких островах, как Сардиния и Корсика. Данное травянистое растение обладает декоративным внешним видом, а его стебли могут вырастать до 100 сантиметров в длину. Со временем данное растение принимает форму сферического кустика, который состоит из множества ползучих, сочных, сильно переплетающихся веток.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/11/1-94-700x631.jpg",
                name = "Скирпус",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Кактус",
                instructions = "Данное растение некапризно и ухаживать за ним очень просто. Кстати, это выгодно выделяет его среди остальных кактусов данного семейства.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/12/1-62-700x660.jpg",
                name = "Нотокактус",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Кактус",
                instructions = "Очень любит свет, поэтому для его размещения надо выбирать хорошо освещенное место. При этом следует помнить, что такой кактус является медленнорастущим.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/12/1-61-700x675.jpg",
                name = "Лемэроцереус",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "фруктовый кустарник или дерево",
                instructions = "Многолетнее растение мушмула (Eryobotria), либо Локва представляет собой фруктовый кустарник или дерево. Оно является частью семейства Розовые подсемейства Яблоневые. В природных условиях такое растение встречается на территориях с мягким субтропическим климатом. Цветет оно в осеннее время, при этом его плодоношение приходится на зимние или весенние месяцы. Родом это растение из Китая и Япония.",
                photo = "https://rastenievod.com/wp-content/uploads/2018/07/1-15-700x634.jpg",
                name = "Мушмула",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Камнеломковые",
                instructions = "Такой род растений, как камнеломка (Saxifraga) имеет прямое отношение к семейству камнеломковые (Saxifragaceae). Он объединяет больше 400 видов травянистых растений, среди которых большинство многолетников, а остальные одно- либо двулетники. Она предпочитает в природе расти в местах с суровым климатом. Так, это растение можно повстречать в субарктических областях, в Альпах, на востоке Гренландии, а также в западной и восточной части Гималай. Камнеломка предпочитает селиться на каменистых насыпях, в расщелинах скал, на каменных кладках стен, а еще встречается и в низинных лугах.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/11/5-71-700x679.jpg",
                name = "Комнатная камнеломка",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "Болотное",
                instructions = "это болотное, корневищное, травянистое растение является многолетником. Оно входит в число наиболее больших плотоядных растений. Его листочки, находящиеся снизу, чешуйчатые. Короткочерешковые ловчие листочки, отличающиеся достаточно большим размером, собраны в розетку. Они возвышаются над самим растением и строением чем-то напоминают урну с довольно широким отверстием сверху либо трубковидный кувшин.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/11/1-93-700x694.jpg",
                name = "Саррацения",
                uuid = UUID.randomUUID().toString()
            ),
            HomeFlower(
                category = "камыш",
                instructions = "В природных условиях скирпус встречается на таких островах, как Сардиния и Корсика. Данное травянистое растение обладает декоративным внешним видом, а его стебли могут вырастать до 100 сантиметров в длину. Со временем данное растение принимает форму сферического кустика, который состоит из множества ползучих, сочных, сильно переплетающихся веток.",
                photo = "https://rastenievod.com/wp-content/uploads/2016/11/1-94-700x631.jpg",
                name = "Скирпус",
                uuid = UUID.randomUUID().toString()
            )
        )

}