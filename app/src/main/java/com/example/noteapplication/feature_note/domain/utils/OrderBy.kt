package com.example.noteapplication.feature_note.domain.utils

sealed class OrderBy{
    object Ascending : OrderBy()
    object Descending : OrderBy()
}
