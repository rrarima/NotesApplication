package eps.crud.NotesApplication.controller;

import eps.crud.NotesApplication.model.Note;
import eps.crud.NotesApplication.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(){
        return ResponseEntity.ok(noteService.getNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.saveNote(note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        return noteService.getNoteById(id)
                .map(noteObj -> {
                    noteObj.setTitle(note.getTitle());
                    noteObj.setContent(note.getContent());
                    return ResponseEntity.ok(noteService.saveNote(noteObj));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.getNoteById(id).ifPresent(note -> noteService.deleteNoteById(id));
        return ResponseEntity.noContent().build();
    }
}
