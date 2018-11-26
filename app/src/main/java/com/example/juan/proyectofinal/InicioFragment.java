package com.example.juan.proyectofinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;


public class InicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ////////////////////////////////////////////////////////////

    EditText lugar, horario, nombres, modelo;
    Button guardar;
    String si;
    private String HOST = "http://192.168.0.12/parqueadero";

    View view;

    ////////////////////////////////////////////////////////////

    private InicioFragment.OnFragmentInteractionListener mListener;

    public InicioFragment() {
        // Required empty public constructor
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

        view = inflater.inflate(R.layout.fragment_inicio, container, false);



        Spinner spinner = (Spinner)view.findViewById(R.id.lugar);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                si = item.toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            } });

        horario = (EditText) view.findViewById(R.id.horario);
        nombres = (EditText) view.findViewById(R.id.nombres);
        modelo = (EditText) view.findViewById(R.id.modelo);

        guardar = (Button) view.findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                String lugar1 = si;
                String horario1 = horario .getText().toString();
                String nombres1 = nombres.getText().toString();
                String modelo1 = modelo.getText().toString();

                String url = HOST+"/crear.php";

                if(lugar1.isEmpty()) {
                    lugar.setError("Campo obligatorio");
                }else{
                    Ion.with(InicioFragment.this)
                            .load(url)
                            .setBodyParameter("lugar", lugar1)
                            .setBodyParameter("horario", horario1)
                            .setBodyParameter("nombres", nombres1)
                            .setBodyParameter("modelo", modelo1)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                public void onCompleted(Exception e, JsonObject result) {
                                    if (result.get("CREATE").getAsString().equals("OK")){
                                        int idRetornado=Integer.parseInt(result.get("ID").getAsString());
                                        Toast.makeText(getActivity(), "Guardado correctamente, id" + idRetornado, Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }


        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
