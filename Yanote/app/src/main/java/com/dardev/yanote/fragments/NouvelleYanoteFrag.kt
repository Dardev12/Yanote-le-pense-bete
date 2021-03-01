package com.dardev.yanote.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dardev.yanote.MainActivity
import com.dardev.yanote.R
import com.dardev.yanote.databinding.FragmentHomeBinding
import com.dardev.yanote.databinding.FragmentNouvelleYanoteBinding
import com.dardev.yanote.model.Yanote
import com.dardev.yanote.toast
import com.dardev.yanote.viewmodel.YanoteViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.zip.Inflater

class NouvelleYanoteFrag : Fragment(R.layout.fragment_nouvelle_yanote) {

    private var _binding:FragmentNouvelleYanoteBinding?=null
    private val binding get()=_binding!!


    private lateinit var yanoteViewModel:YanoteViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentNouvelleYanoteBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yanoteViewModel=(activity as MainActivity).yanoteViewModel
        mView=view
    }

    private fun saveYanote(view: View){
        val yanoteTitle=binding.yaNoteTitre.text.toString().trim()
        val yanoteBody=binding.yaNoteContenu.text.toString().trim()

        if(yanoteTitle.isNotEmpty()){
            val yanote=Yanote(0,yanoteTitle,yanoteBody)

            yanoteViewModel.addYanote(yanote)
            Snackbar.make(
                    view,
                    "Yanote Sauvegardé",
                    Snackbar.LENGTH_SHORT
            ).show()

            view.findNavController().navigate(
                    R.id.action_nouvelleYanoteFrag_to_homeFrag
            )
        }else{
            activity?.toast("Entrer un titre !")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save_menu->{
                saveYanote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_menu,menu)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}