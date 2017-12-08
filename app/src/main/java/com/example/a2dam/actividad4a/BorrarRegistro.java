package com.example.a2dam.actividad4a;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BorrarRegistro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BorrarRegistro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BorrarRegistro extends Fragment implements AdapterView.OnItemLongClickListener, View.OnLongClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Definim les variables necesaries
    ListView alumnos, profesores;
    ArrayAdapter<String> adapterAlumnos, adapterProfesores;
    int idAlumno, idProfesor;

    private OnFragmentInteractionListener mListener;

    public BorrarRegistro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BorrarRegistro.
     */
    // TODO: Rename and change types and number of parameters
    public static BorrarRegistro newInstance(String param1, String param2) {
        BorrarRegistro fragment = new BorrarRegistro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.borrar_registro, container, false);
        alumnos = v.findViewById(R.id.listaAlumnos);
        profesores = v.findViewById(R.id.listaProfesores);
        adapterAlumnos = new ArrayAdapter<>(getActivity(), R.layout.listview_alumnes,idAlumnos());
        adapterProfesores = new ArrayAdapter<>(getActivity(),R.layout.listview_profesores,idProfesores());

        alumnos.setOnItemLongClickListener(this);
        profesores.setOnItemLongClickListener(this);

        alumnos.setAdapter(adapterAlumnos);
        profesores.setAdapter(adapterProfesores);
        return v;
    }

    public String[] idAlumnos(){
        MainActivity.adaptadorBBDD.open();
        ArrayList<Alumno> alAlumnos = MainActivity.adaptadorBBDD.devuelveTablaAlumnos();
        ArrayList<String> strAlumnos = new ArrayList<>();
        Iterator it = alAlumnos.iterator();
        String alumnoStr;
        while (it.hasNext()){
            Alumno a = (Alumno)it.next();
            alumnoStr = "Id: "+a.getId()+" Nombre: "+a.getNombre();
            strAlumnos.add(alumnoStr);
        }
        String[] resultado = new String[strAlumnos.size()];
        resultado = strAlumnos.toArray(resultado);
        return resultado;
    }

    public String[] idProfesores(){
        MainActivity.adaptadorBBDD.open();
        ArrayList<Profesor> alProfesores = MainActivity.adaptadorBBDD.devuelveTablaProfesores();
        ArrayList<String> strProfesores = new ArrayList<>();
        Iterator it = alProfesores.iterator();
        String profesorStr;
        while (it.hasNext()){
            Profesor p = (Profesor)it.next();
            profesorStr = "Id: "+p.getId()+" Nombre: "+p.getNombre();
            strProfesores.add(profesorStr);
        }
        String[] resultado = new String[strProfesores.size()];
        resultado = strProfesores.toArray(resultado);
        return resultado;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.listaAlumnos:
                idAlumno = Integer.parseInt(alumnos.getItemAtPosition(i).toString().substring(4, alumnos.getItemAtPosition(i).toString().indexOf("N")).trim());
                MainActivity.adaptadorBBDD.borrarRegistroAlumno(idAlumno);
                Toast.makeText(getActivity(),"Se ha borrado el registro: "+ idAlumno + "", Toast.LENGTH_LONG).show();
                //Borrem i tornem a unflar el fragment
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                return true;
            case R.id.listaProfesores:
                idProfesor = Integer.parseInt(profesores.getItemAtPosition(i).toString().substring(4, profesores.getItemAtPosition(i).toString().indexOf("N")).trim());
                MainActivity.adaptadorBBDD.borrarRegistroProfesor(idProfesor);
                Toast.makeText(getActivity(), "Se ha borrado el registro: "+ idProfesor + "", Toast.LENGTH_LONG).show();
                //Borrem i tornem a unflar el fragment
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
