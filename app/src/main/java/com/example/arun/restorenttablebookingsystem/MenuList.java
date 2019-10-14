package com.example.arun.restorenttablebookingsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView l;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuList.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuList newInstance(String param1, String param2) {
        MenuList fragment = new MenuList();
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
        View v= inflater.inflate(R.layout.fragment_menu_list, container, false);

        String []arr={"VEGITABLES----","Kadai Paneer ---  150","Shahi Paneer ---  150","Mutter Paneer ---  130","Palak Paneer ---  150",
                "Aaloo Mutter --- 80","Mix Veg --- 80","Chana masala ---90","Dall Makhani --- 100","Dum Aaloo --- 90",
        "CHAINEES----","Noodels---80","Chaumeen---80","Momos---60","SandWiches --- 80",
        "SANCKS --- ","Aloo Patties ---15","Paneer Paties --- 20","Chili Patteto paties --- 20","Burger ---30",
        "RICE ---","Plain Rice --- 40","Jeera Rice--- 50","Roti ---","Tandoori ---7","Butter Roti--- 8","NoN Roti --- 20","Parathe --- 25 ",
        "Tea --- 10","Coffee ---20","Miniral Water --- 20"};
        ListView l=(ListView)v.findViewById(R.id.llb1);
        ArrayAdapter<String> obj=new ArrayAdapter(getActivity(),R.layout.hold,arr);
        l.setAdapter(obj);
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_menu_list, container, false);
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
