package com.example.a2dam.actividad4a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_barra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_barra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_barra extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Definim un FragmentManager
    FragmentManager fm;
    //Definim un FragmentTransaction
    FragmentTransaction ft;

    //Definim botons
    Button altaAlumne, altaProfesor, borrar, consultas;

    public Fragment_barra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_barra.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_barra newInstance(String param1, String param2) {
        Fragment_barra fragment = new Fragment_barra();
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
        View v = inflater.inflate(R.layout.barra, container, false);
        fm = getFragmentManager();

        borrar = v.findViewById(R.id.borrar);
        registerForContextMenu(borrar);

        altaAlumne = v.findViewById(R.id.alta_alumno);
        altaAlumne.setOnClickListener(this);

        altaProfesor = v.findViewById(R.id.alta_profesor);
        altaProfesor.setOnClickListener(this);

        consultas = v.findViewById(R.id.consultas);
        registerForContextMenu(consultas);
        registerForContextMenu(consultas);

        return v;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getActivity().getMenuInflater();
        switch (v.getId()){
            case R.id.consultas:
                inflater.inflate(R.menu.menu_consultas, menu);
                break;
            case R.id.borrar:
                inflater.inflate(R.menu.menu_borrar,menu);
                break;
            default: break;
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.borrar_registro:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,BorrarRegistro.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,BorrarRegistro.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.borrar_bbdd:
                ft = fm.beginTransaction();
                Toast.makeText(getActivity(),"Borrando base de datos",Toast.LENGTH_SHORT).show();
                MainActivity.adaptadorBBDD.open();
                MainActivity.adaptadorBBDD.vaciarTablas();
                if (mListener.estaFragmentDinamic()){
                    ft.remove(fm.findFragmentById(R.id.fragment_dinamic));
                }
                ft.commit();
                return true;
            case R.id.estudiantesPorCiclo:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,EstudiantesPorCiclo.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,EstudiantesPorCiclo.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.estudiantesPorCurso:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,EstudiantesPorCurso.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,EstudiantesPorCurso.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.estudiantesPorCicloCurso:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,EstudiantesPorCicloCurso.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,EstudiantesPorCicloCurso.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.estudiantesTodos:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,TodosEstudiantes.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,TodosEstudiantes.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.profesoresPorCiclo:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,ProfesoresPorCiclo.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,ProfesoresPorCiclo.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.profesoresPorCurso:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,ProfesoresPorCurso.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,ProfesoresPorCurso.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.profesoresPorCicloCurso:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,ProfesoresPorCicloCurso.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,ProfesoresPorCicloCurso.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.profesoresTodos:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,TodosProfesores.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,TodosProfesores.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            case R.id.profosoresAlumnosTodos:
                ft = fm.beginTransaction();
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,TodosProfesoresAlumnos.newInstance("",""));
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,TodosProfesoresAlumnos.newInstance("",""));
                    ft.addToBackStack(null);
                }
                ft.commit();
                return true;
            default: return false;
        }
        // return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        Fragment aa = AltaAlumno.newInstance("","");
        Fragment ap = AltaProfesor.newInstance("","");
        switch (view.getId()){
            case R.id.alta_alumno:
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic,aa);
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic,aa);
                    ft.addToBackStack(null);
                }
                ft.commit();
                break;
            case R.id.alta_profesor:
                if (mListener.estaFragmentDinamic()){
                    ft.replace(R.id.fragment_dinamic, ap);
                    ft.addToBackStack(null);
                } else {
                    ft.add(R.id.fragment_dinamic, ap);
                    ft.addToBackStack(null);
                }
                ft.commit();
                break;
            default: break;

        }
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
        boolean estaFragmentDinamic();
    }
}
