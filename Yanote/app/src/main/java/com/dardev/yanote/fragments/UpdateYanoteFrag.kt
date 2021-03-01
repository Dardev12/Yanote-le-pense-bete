package com.dardev.yanote.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dardev.yanote.MainActivity
import com.dardev.yanote.R
import com.dardev.yanote.databinding.FragmentUpdateYanoteBinding
import com.dardev.yanote.model.Yanote
import com.dardev.yanote.toast
import com.dardev.yanote.viewmodel.YanoteViewModel


class UpdateYanoteFrag : Fragment(R.layout.fragment_update_yanote) {

    private var _binding: FragmentUpdateYanoteBinding?=null
    private val binding get() = _binding!!

    private val args:UpdateYanoteFragArgs by navArgs()
    private lateinit var currentYanote:Yanote
    private lateinit var yanoteViewModel: YanoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateYanoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yanoteViewModel=(activity as MainActivity).yanoteViewModel

        currentYanote=args.yanote!!

        binding.yaNoteTitreUpdate.setText(currentYanote.yaTitre)
        binding.yaNoteContenuUpdate.setText(currentYanote.yaContenu)

        binding.btnModifier.setOnClickListener {
            val titre=binding.yaNoteTitreUpdate.text.toString().trim()
            val contenu=binding.yaNoteContenuUpdate.text.toString().trim()

            if(titre.isNotEmpty()){
                val yanote=Yanote(currentYanote.TAG,titre,contenu)
                yanoteViewModel.updateYanote(yanote)
                activity?.toast("Yanote Modifié!!")
                view.findNavController().navigate(
                        R.id.action_updateYanoteFrag_to_homeFrag
                )
            }else{
                activity?.toast("Entrer un titre!!")
            }
        }
    }

    private fun eraseYanote(){

        AlertDialog.Builder(activity).apply {
            setTitle("Effacer Yanote")
            setMessage("Etes vous sûr d'effacer cette yanote?")
            setPositiveButton("Supprimer"){_,_->
                yanoteViewModel.deleteYanote(currentYanote)
                view?.findNavController()?.navigate(
                        R.id.action_updateYanoteFrag_to_homeFrag
                )

            }
            setNegativeButton("Annuler",null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_menu->{
                eraseYanote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}