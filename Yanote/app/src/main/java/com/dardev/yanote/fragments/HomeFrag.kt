package com.dardev.yanote.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dardev.yanote.R
import com.dardev.yanote.databinding.FragmentHomeBinding


class HomeFrag : Fragment(R.layout.fragment_home) {

    private var _binding:FragmentHomeBinding?=null
    private val binding get()=_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddYanote.setOnClickListener{ mView->
            mView.findNavController().navigate(R.id.action_homeFrag_to_nouvelleYanoteFrag)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}