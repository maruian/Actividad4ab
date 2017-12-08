package com.example.a2dam.actividad4a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfesoresPorCicloCurso.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfesoresPorCicloCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfesoresPorCicloCurso extends Fragment implements AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Definim les variables necesaries
    Spinner spCurso,spCiclo;
    ListView profesores;
    String ciclo;
    int curso;
    ArrayAdapter<String> adapterProfesores;

    private OnFragmentInteractionListener mListener;

    public ProfesoresPorCicloCurso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstudiantesPorCiclo.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfesoresPorCicloCurso newInstance(String param1, String param2) {
        ProfesoresPorCicloCurso fragment = new ProfesoresPorCicloCurso();
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
        View v = inflater.inflate(R.layout.profesoresporciclocurso, container, false);

        ciclo="DAM";
        curso=1;
        spCurso = (Spinner)v.findViewById(R.id.spinnerCurso);
        spCiclo = (Spinner)v.findViewById(R.id.spinnerCiclo);

        ArrayAdapter<CharSequence> adapterCurso = ArrayAdapter.createFromResource(getContext(),R.array.arrayCursos, android.R.layout.simple_spinner_dropdown_item);
        adapterCurso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterCiclo = ArrayAdapter.createFromResource(getContext(),R.array.arrayCiclos, android.R.layout.simple_spinner_dropdown_item);
        adapterCiclo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCurso.setAdapter(adapterCurso);
        spCurso.setOnItemSelectedListener(this);

        spCiclo.setAdapter(adapterCiclo);
        spCiclo.setOnItemSelectedListener(this);

        profesores = (ListView)v.findViewById(R.id.listaProfesores);
        adapterProfesores = new ArrayAdapter<>(getActivity(), R.layout.listview_profesores,arrayProfesores());
        profesores.setAdapter(adapterProfesores);

        return v;
    }

    public String[] arrayProfesores(){
        MainActivity.adaptadorBBDD.open();
        ArrayList<Profesor> alProfesor = MainActivity.adaptadorBBDD.devuelveProfesoresCicloCurso(ciclo, curso);
        ArrayList<String> strProfesores = new ArrayList<>();
        Iterator it = alProfesor.iterator();
        String profesorStr;
        while (it.hasNext()){
            Profesor p = (Profesor) it.next();
            profesorStr = "Id: "+p.getId()+" Nombre: "+p.getNombre()+ " Curso: "+p.getCurso()+ " Edad: "+ p.getEdad()+" Despacho: "+p.getDespacho();
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        curso = spCurso.getSelectedItemPosition()+1;
        ciclo = spCiclo.getSelectedItem().toString();
        adapterProfesores = new ArrayAdapter<>(getActivity(), R.layout.listview_profesores,arrayProfesores());
        profesores.setAdapter(adapterProfesores);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
