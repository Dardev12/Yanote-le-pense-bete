package com.dardev.yanote.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dardev.yanote.MainActivity
import com.dardev.yanote.R
import com.dardev.yanote.adapter.YanoteAdapter
import com.dardev.yanote.databinding.*
import com.dardev.yanote.model.Yanote
import com.dardev.yanote.viewmodel.YanoteViewModel


class HomeFrag : Fragment(R.layout.fragment_home),
SearchView.OnQueryTextListener{

    private var _binding:FragmentHomeBinding?=null
    private val binding get()=_binding!!

    private lateinit var yanoteViewModel: YanoteViewModel
    private lateinit var yanoteAdapter: YanoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yanoteViewModel=(activity as MainActivity).yanoteViewModel
        setUpRercyclerView()

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


    private fun setUpRercyclerView(){
        yanoteAdapter= YanoteAdapter()


        binding.recyclerView.apply {

            layoutManager =StaggeredGridLayoutManager(
                    5,
                    StaggeredGridLayoutManager.VERTICAL
            )

            setHasFixedSize(true)
            adapter=yanoteAdapter


        }

        activity?.let{
            yanoteViewModel.getAllYanote().observe(viewLifecycleOwner,{ yanote->

                yanoteAdapter.differ.submitList(yanote)
                updateUI(yanote)
            })
        }
    }


    private fun updateUI(yanote:List<Yanote>){

        if(yanote.isNotEmpty()){
            binding.recyclerView.visibility=View.VISIBLE
            binding.tvNoNoteAvailable.visibility=View.GONE
        }else{
            binding.recyclerView.visibility=View.GONE
            binding.tvNoNoteAvailable.visibility=View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)

        val mMenuSearch=menu.findItem(R.id.app_bar_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled=true
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if(query !=null){
            searchNotes(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText !=null){
            searchNotes(newText)
        }
        return true
    }

    private fun searchNotes(query:String?){
        val searchQuery="%$query%"
        yanoteViewModel.searchYanote(searchQuery).observe(this,{list->

            yanoteAdapter.differ.submitList(list)
        })
    }
}