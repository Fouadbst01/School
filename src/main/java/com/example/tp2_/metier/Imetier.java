package com.example.tp2_.metier;

import java.util.List;

public interface Imetier<T> {
    List<T> GetAll();
    int Add(T p);
    T Get(int i);
    void Supp(int id);
    void Modifier(T p);
}
