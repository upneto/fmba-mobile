package br.com.fiap.fmba.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.fmba.resources.exception.DaoException;

public abstract class AbstractDao {

    protected FirebaseDatabase database = null;
    protected DatabaseReference myRef = null;
    protected String dataBase;

    public AbstractDao() { }

    public AbstractDao (final String DATABASE) {
        this.database = FirebaseDatabase.getInstance();
        this.myRef = this.database.getReference("FMBA");
        this.dataBase = DATABASE;
    }

    public <T> List<T> getAll(Class<T> responseType) throws DaoException {
        final List<T> list = new ArrayList<T>();
        final List<String> errors = new ArrayList<String>();
        this.myRef.child(this.dataBase).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                T value = dataSnapshot.getValue(responseType);
                list.add(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errors.add(databaseError.getMessage());
            }
        });

        if(!errors.isEmpty()) {
            throw new DaoException(errors.get(0));
        }

        return list;
    }

    public <T> T getById(Class<T> responseType, Map.Entry<String, Object> entry) throws DaoException {
        Query query = this.myRef.child(this.dataBase)
                .orderByChild(entry.getKey())
                .equalTo(entry.getKey());

        final List<T> list = new ArrayList<T>();
        final List<String> errors = new ArrayList<String>();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                T value = dataSnapshot.getValue(responseType);
                list.add(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errors.add(databaseError.getMessage());
            }
        });

        if(!errors.isEmpty()) {
            throw new DaoException(errors.get(0));
        }

        return list.get(0);
    }

    public <T> void insert(T value) throws DaoException {
        try {
            this.myRef.child(this.dataBase).setValue(value);
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
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void delete(Map.Entry<String, Object> entry) throws DaoException {
        Query query = this.myRef.child(this.dataBase)
                .orderByChild(entry.getKey())
                .equalTo(entry.getKey());

        final List<String> errors = new ArrayList<String>();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
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
