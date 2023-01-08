package com.example.noteapplication.feature_note.domain.utils

sealed class NoteOrder(val orderBy: OrderBy) {
    class Title(orderBy: OrderBy) : NoteOrder(orderBy)
    class Date(orderBy: OrderBy) : NoteOrder(orderBy)
    class Color(orderBy: OrderBy) : NoteOrder(orderBy)

    fun copy(orderBy: OrderBy):NoteOrder{
        return when(this){
            is Title -> Title(orderBy)
            is Date -> Date(orderBy)
            is Color -> Color(orderBy)
        }
    }
}