package ru.skillbranch.devintensive.models

import android.os.Build.SERIAL
import android.provider.Contacts.PresenceColumns.IDLE
import androidx.core.text.isDigitsOnly

/**
 * Created by Dino944 on 14.07.2019.
 */

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question){
                Question.NAME -> Question.NAME.question
                Question.PROFESSION ->Question.PROFESSION.question
                Question.MATERIAL ->Question.MATERIAL.question
                Question.BDAY ->Question.BDAY.question
                Question.SERIAL ->Question.SERIAL.question
                Question.IDLE ->Question.IDLE.question

    }

    fun listenAnswer(answer:String) : Pair<String, Triple<Int, Int, Int>>{
        val valid = validate(answer, question)
        if (valid.isNotEmpty()) {
            return "$valid\n${question.question}" to status.color
        }

        if (question.answer.contains(answer.toLowerCase())) {
            question = question.nextQuestion()

            return "Отлично - ты справился\n${question.question}" to status.color
        } else {

            if (status == Status.CRITICAL) {
                status = Status.NORMAL
                question = Question.NAME

                return "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }

            status = status.nextStatus()
            return "Это неправильный ответ\n${question.question}"  to status.color
        }

    }


/*
        return if(question.answer.contains(answer)){

            question = question.nextQuestion()
            "Отлично- это правильный ответ!\n" +
                    "${question.question}" to status.color
        }else{

            status = status.nextStatus()
            "Это не правильный ответ!\n${question.question}" to status.color
        }
 */
 //   }


    /////////////////////////////
    fun validate(answer: String, question: Question): String {
        return when (question) {
            Question.NAME ->  if (answer.isNotEmpty() && Character.isUpperCase(answer[0])) "" else "Имя должно начинаться с заглавной буквы"
            Question.PROFESSION -> if (answer.isNotEmpty() && !Character.isUpperCase(answer[0])) "" else "Профессия должна начинаться со строчной буквы"
            Question.MATERIAL -> if (!answer.matches(Regex(".*\\d.*"))) "" else "Материал не должен содержать цифр"
            Question.BDAY -> if (answer.isDigitsOnly()) "" else "Год моего рождения должен содержать только цифры"
            Question.SERIAL -> if (answer.isDigitsOnly() && (answer.length == 7)) "" else "Серийный номер содержит только цифры, и их 7"
            Question.IDLE -> ""
        }
    }
//////////////////////////////

    enum class Status(val color: Triple<Int, Int, Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus(): Status {

            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }

    }

    enum class Question(val question: String, val answer: List<String>){
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL

            },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "matal", "iron", "wood")){
                override fun nextQuestion(): Question = BDAY
                },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
            },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
            },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
            };

        abstract fun nextQuestion(): Question

    }
}