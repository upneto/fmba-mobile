package br.com.fiap.fmba.dao;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.fmba.resources.exception.DaoException;

public abstract class AbstractDao {

    protected Context context = null;
    protected FirebaseDatabase database = null;
    protected DatabaseReference myRef = null;
    protected String dataBase;

    public AbstractDao(final Context context) {
        this.context = context;
    }

    public AbstractDao (final Context context, final String DATABASE) {
        this.context = context;
        this.database = FirebaseDatabase.getInstance();
        this.myRef = this.database.getReference("FMBA");
        this.dataBase = DATABASE;
    }

    public <T> void getAll(Class<T> responseType, ArrayAdapter<T> listaAdapter) throws DaoException {
        final List<String> errors = new ArrayList<String>();
        listaAdapter.clear();
        this.myRef.child(this.dataBase).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot item : dataSnapshot.getChildren()) {
                     T value = item.getValue(responseType);
                     if (value != null) {
                         listaAdapter.add(value);
                     }
                 }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errors.add(databaseError.getMessage());
            }
        });

        if(!errors.isEmpty()) {
            throw new DaoException(errors.get(0));
        }
    }

    public <T> void getById(T objToFind, ArrayAdapter<T> listaAdapter) throws DaoException {
        final List<String> errors = new ArrayList<String>();
        listaAdapter.clear();
        this.myRef.child(this.dataBase).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    T value = (T) item.getValue(objToFind.getClass());
                    if (value != null && value.equals(objToFind)) {
                        listaAdapter.add(value);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errors.add(databaseError.getMessage());
            }
        });

        if(!errors.isEmpty()) {
            throw new DaoException(errors.get(0));
        }
    }

    public <T> void insert(T objToInclude) throws DaoException {
        try {
            this.myRef.child(this.dataBase).push().setValue(objToInclude);
            Toast.makeText(context, "Operação realizada com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public <T> void update(T value) throws DaoException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            for (Field field : value.getClass().getDeclaredFields()) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                map.put(field.getName(), field.get(value));
            }
            this.myRef.child(this.dataBase).updateChildren(map);
            Toast.makeText(context, "Operação realizada com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Não foi possível realizar a operação!", Toast.LENGTH_SHORT).show();
            throw new DaoException(e);
        }
    }

    public <T> void delete(T objToRemove) throws DaoException {
        final List<String> errors = new ArrayList<String>();
        this.myRef.child(this.dataBase).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    T value = (T) item.getValue(objToRemove.getClass());
                    if (value != null && value.equals(objToRemove)) {
                        item.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errors.add(databaseError.getMessage());
            }
        });

        if(!errors.isEmpty()) {
            throw new DaoException(errors.get(0));
        }
    }
}
