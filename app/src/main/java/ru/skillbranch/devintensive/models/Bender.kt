package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun asdQuestion(): String = when(question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question

    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int,Int,Int>>{
        val (answValid, check) = question.answerValidation(answer)
        if (check)
        {
            return if(question.answers.contains(answer)){
                question = question.nextQuestion()
                "Отлично - это правильный ответ \n ${question.question}" to status.color
            }else{
                status = status.nextStatus()
                "Это не правильный ответ \n ${question.question}" to status.color
            }
        }else{
            return answValid + "\n ${question.question}" to status.color
        }

    }
    enum class Status(val color: Triple<Int,Int,Int> ) {

        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values().first()
            }
        }

    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question =  PROFESSION
            override fun  answerValidation(answer: String): Pair<String, Boolean> {
                return if (answer[0].isLowerCase()){
                    Pair("Имя должно начинаться с заглавной буквы", false)
                }else{
                    Pair("", true)
                }
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question =  MATERIAL
            override fun  answerValidation(answer: String): Pair<String, Boolean> {
                return if (answer[0].isUpperCase()){
                    Pair("Профессия должна начинаться со строчной буквы", false)
                }else{
                    Pair("", true)
                }
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question =  BDAY
            override fun  answerValidation(answer: String): Pair<String, Boolean> {
                return if (Regex("""\d+""").containsMatchIn(answer)){
                    Pair("Материал не должен содержать цифр", false)
                }else{
                    Pair("", true)
                }
            }
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question =  SERIAL
            override fun  answerValidation(answer: String): Pair<String, Boolean> {
                return if (Regex("""\w+""").containsMatchIn(answer)){
                    Pair("Год моего рождения должен содержать только цифры", false)
                }else{
                    Pair("", true)
                }
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question =  IDLE
            override fun  answerValidation(answer: String): Pair<String, Boolean> {
                return if (Regex("""\w+""").containsMatchIn(answer) || answer.length != 6){
                    Pair("Серийный номер содержит только цифры, и их 7", false)
                }else{
                    Pair("", true)
                }
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question =  IDLE
            override fun  answerValidation(answer: String): Pair<String, Boolean> = Pair("", true)
        };

        abstract fun  nextQuestion(): Question
        abstract fun  answerValidation(answer: String):  Pair<String, Boolean>

    }
}