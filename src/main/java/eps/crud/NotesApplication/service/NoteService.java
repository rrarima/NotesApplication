package eps.crud.NotesApplication.service;

import eps.crud.NotesApplication.model.Note;
import eps.crud.NotesApplication.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes(){
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id){
        return noteRepository.findById(id);
    }

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id){
        noteRepository.deleteById(id);
    }
}
