package com.example.juan.proyectofinal;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;


public class ReservasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ////////////////////////////////////////////////////////////

    private String HOST = "http://192.168.0.12/parqueadero";
    ListView parqueadero;
    ListView parqueadero2;

    View view;
    private ArrayList datos = new ArrayList();

    AdaptadorParqueadero adaptador;
    AdaptadorParqueadero adaptador2;
    List<Parqueadero> lista;
    List<Parqueadero> lista2;


    ////////////////////////////////////////////////////////////

    private InicioFragment.OnFragmentInteractionListener mListener;

    public ReservasFragment() {
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_reservas, container, false);

        parqueadero = (ListView) view.findViewById(R.id.list);
        parqueadero2 = (ListView) view.findViewById(R.id.list2);

        lista = new ArrayList<Parqueadero>();
        lista2 = new ArrayList<Parqueadero>();

        adaptador = new AdaptadorParqueadero(getActivity(), lista);
        adaptador2 = new AdaptadorParqueadero(getActivity(), lista2);

        parqueadero.setAdapter(adaptador);
        parqueadero2.setAdapter(adaptador2);

        listaParqueadero();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void listaParqueadero(){
    String url = HOST+"/leer.php";

    Ion.with(getActivity())
            .load(url)
            .asJsonArray()
            .setCallback(new FutureCallback<JsonArray>() {
                @Override
                public void onCompleted(Exception e, JsonArray result) {

                    for (int i=0;i<result.size();i++)
                    {
                        JsonObject obj = result.get(i).getAsJsonObject();

                        Parqueadero p = new Parqueadero();

                        p.setLugar(obj.get("lugar").getAsString());
                        p.setHorario(obj.get("horario").getAsString());
                        p.setNombre(obj.get("nombres").getAsString());
                        p.setModelo(obj.get("modelo").getAsString());

                        if(obj.get("lugar").getAsString().equals("MARIANA")) {
                            lista.add(p);
                        } else{
                            lista2.add(p);
                        }
                    }
                    adaptador.notifyDataSetChanged();
                    adaptador2.notifyDataSetChanged();
                }
            });
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
