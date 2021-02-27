package com.dardev.yanote.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.dardev.yanote.R
import com.dardev.yanote.databinding.FragmentHomeBinding
import com.dardev.yanote.databinding.FragmentNouvelleYanoteBinding
import java.util.zip.Inflater

class NouvelleYanoteFrag : Fragment(R.layout.fragment_nouvelle_yanote) {

    private var _binding:FragmentNouvelleYanoteBinding?=null
    private val binding get()=_binding!!

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