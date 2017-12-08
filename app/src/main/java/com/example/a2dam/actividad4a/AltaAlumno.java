package com.example.a2dam.actividad4a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AltaAlumno.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AltaAlumno#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaAlumno extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Definim les variables requerides
    EditText nombre, edad, ciclo, curso, nota;
    Button guardar;

    private OnFragmentInteractionListener mListener;

    public AltaAlumno() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AltaAlumno.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaAlumno newInstance(String param1, String param2) {
        AltaAlumno fragment = new AltaAlumno();
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
        View v = inflater.inflate(R.layout.alta_alumne, container, false);
        nombre = (EditText)v.findViewById(R.id.editNombre);
        edad  = (EditText)v.findViewById(R.id.editEdad);
        ciclo = (EditText)v.findViewById(R.id.editCiclo);
        curso = (EditText)v.findViewById(R.id.editCurso);
        nota = (EditText)v.findViewById(R.id.editNota);
        guardar = (Button)v.findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkData()) {
                    MainActivity.adaptadorBBDD.open();
                    MainActivity.adaptadorBBDD.insertarAlumno(nombre.getText().toString(), Integer.parseInt(edad.getText().toString()), ciclo.getText().toString(), curso.getText().toString(), Double.parseDouble(nota.getText().toString()));
                    nombre.setText("");
                    edad.setText("");
                    ciclo.setText("");
                    curso.setText("");
                    nota.setText("");
                    Toast.makeText(getActivity(),"Datos guardados",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Introduce datos validos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }

    public boolean checkData(){
        try {
            Integer.parseInt(edad.getText().toString());
            Double.parseDouble(nota.getText().toString());
        }catch (NumberFormatException e){
            return false;
        }
        return (nombre.getText().toString().length()>0)&&
                (ciclo.getText().toString().length()>0)&&
                (curso.getText().toString().length()>0);
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