package vn.devpro.bt9;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtTitle;
    private EditText edtContent;
    private Button btnAdd;
    private RecyclerView recyclerViewNotes;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;
    private NoteDatabase database;
    private NoteDao noteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        database = NoteDatabase.getInstance(this);
        noteDao = database.noteDao();

        noteList = new ArrayList<>();

        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter = new NoteAdapter(noteList);
        recyclerViewNotes.setAdapter(noteAdapter);
        loadNotes();

        btnAdd.setOnClickListener(v -> addNote());
    }

    private void addNote() {

        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();

        if (title.isEmpty()) {
            edtTitle.setError("Vui lòng nhập tiêu đề");
            edtTitle.requestFocus();
            return;
        }

        if (content.isEmpty()) {
            edtContent.setError("Vui lòng nhập nội dung");
            edtContent.requestFocus();
            return;
        }
        Note note = new Note(title, content, System.currentTimeMillis());
        new Thread(() -> {
            noteDao.insert(note);
            runOnUiThread(() -> {
                edtTitle.setText("");
                edtContent.setText("");
                Toast.makeText(MainActivity.this, "Đã thêm ghi chú", Toast.LENGTH_SHORT).show();
                loadNotes();
            });
        }).start();
    }
    private void loadNotes() {
        new Thread(() -> {
            List<Note> notes = noteDao.getAllNotes();
            runOnUiThread(() -> {
                noteAdapter.setNoteList(notes);
            });
        }).start();
    }
}