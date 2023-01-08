package com.example.noteapplication.feature_note.presentation.ad_edit_note


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.usecase.NoteUsaCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUsaCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hintText = "Enter title..."
    ))
    val noteTitle:State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hintText = "Enter some content"
    ))
    val noteContent:State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor:State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow:SharedFlow<UiEvent> = _eventFlow

    private var currentNoteId:Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNoteUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )

                        _noteColor.value = noteColor.value
                    }
                }
            }
        }
    }

    fun onEvent(event:AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle ->{
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus ->{
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.SaveNote ->{
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    }catch (e:Exception){
                        _eventFlow.emit(UiEvent.ShowSnackbar(
                            message = e.message ?: "Couldn't save note"
                        ))
                    }
                }
            }
            is AddEditNoteEvent.ChangeColor ->{
                _noteColor.value = event.color
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message:String):UiEvent()
        object SaveNote:UiEvent()
    }
}