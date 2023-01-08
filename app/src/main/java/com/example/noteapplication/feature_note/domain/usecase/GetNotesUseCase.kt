package com.example.noteapplication.feature_note.domain.usecase

import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.repository.NoteRepository
import com.example.noteapplication.feature_note.domain.utils.NoteOrder
import com.example.noteapplication.feature_note.domain.utils.OrderBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val repository:NoteRepository) {

    operator fun invoke(noteOrder:NoteOrder = NoteOrder.Date(OrderBy.Descending)):Flow<List<Note>>{
        return repository.getAllNotes().map { notes ->
            when(noteOrder.orderBy){
                is OrderBy.Ascending ->{
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase()  }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp  }
                        is NoteOrder.Color -> notes.sortedBy { it.color  }
                    }
                }
                is OrderBy.Descending ->{
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase()  }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp  }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color  }
                    }
                }
            }
        }
    }
}