package com.example.a2dam.actividad4a;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment_barra.OnFragmentInteractionListener,
        AltaAlumno.OnFragmentInteractionListener, AltaProfesor.OnFragmentInteractionListener,
        BorrarRegistro.OnFragmentInteractionListener, EstudiantesPorCiclo.OnFragmentInteractionListener,
        EstudiantesPorCurso.OnFragmentInteractionListener, EstudiantesPorCicloCurso.OnFragmentInteractionListener,
        TodosEstudiantes.OnFragmentInteractionListener,ProfesoresPorCiclo.OnFragmentInteractionListener,
        ProfesoresPorCicloCurso.OnFragmentInteractionListener, ProfesoresPorCurso.OnFragmentInteractionListener,
        TodosProfesores.OnFragmentInteractionListener, TodosProfesoresAlumnos.OnFragmentInteractionListener{

    static AdaptadorBBDD adaptadorBBDD;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        adaptadorBBDD = new AdaptadorBBDD(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean estaFragmentDinamic() {
        Fragment f = fm.findFragmentById(R.id.fragment_dinamic);
        if (f == null) {
            return false;
        } else
            return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

