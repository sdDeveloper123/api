package com.reference.newsapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.reference.newsapi.adapter.listAdapter
import com.reference.newsapi.viewModel.DashboardViewholder
import com.reference.newsapi.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val ViewModel: DashboardViewholder by lazy {
        ViewModelProviders.of(this).get(DashboardViewholder::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModel
        ViewModel.getNews()
        binding.recycleView.adapter = listAdapter(listAdapter.OnClickListener{
            ViewModel.displayPropertyDetails(it)
        })
        observer()
        return binding.root
    }
    private fun observer(){
        ViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToMainnewsFragment(it))
                ViewModel.displayPropertyDetailsComplete()
                Toast.makeText(context, it.publishedAt,Toast.LENGTH_SHORT).show()

            }
        })
    }

}