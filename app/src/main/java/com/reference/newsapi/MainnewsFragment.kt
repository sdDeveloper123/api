package com.reference.newsapi

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.ViewModelProvider
import com.reference.newsapi.databinding.FragmentMainnewsBinding
import com.reference.newsapi.viewModel.DetailViewModelFactory
import com.reference.newsapi.viewModel.NewsViewModel

class MainnewsFragment : Fragment() {
      private lateinit var binding: FragmentMainnewsBinding
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
        binding = FragmentMainnewsBinding.inflate(inflater)
        val application = requireNotNull(activity).application

        val imageProperty = MainnewsFragmentArgs.fromBundle(requireArguments()).news

        val viewModelFactory = DetailViewModelFactory(imageProperty,application)

        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(NewsViewModel::class.java)
        return binding.root
    }

}