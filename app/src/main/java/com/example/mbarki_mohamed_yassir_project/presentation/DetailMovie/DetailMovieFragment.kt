package com.example.mbarki_mohamed_yassir_project.presentation.DetailMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.databinding.FragmentDetailMovieBinding
import com.example.mbarki_mohamed_yassir_project.presentation.DetailMovie.intent.DetailMovieIntent
import com.example.mbarki_mohamed_yassir_project.utils.networksecurity.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private val viewModel: DetailMovieViewModel by viewModels()
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private var id:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments.let { it?.getInt("id") ?: 0 }

        observeDetailMovie()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        lifecycleScope.launchWhenStarted {
            id?.let {
        //        viewModel.detailMovieIntent.send(DetailMovieIntent.loadMovie(it))
            }
        }
        return view
    }

    private fun observeDetailMovie() {
        lifecycleScope.launchWhenStarted {
            viewModel.detailMoveDataState.collect {
                when (it) {
                    is DataState.Success -> {
                        binding.detailMovieDesc.text = it.data.overview
                        binding.detailDateMovie.text = it.data.dateRelease
                        binding.detailTitleMovie.text = it.data.Title
                        GlideApp.with(requireContext()).load(it.data.movieUrl)
                            .into(binding.detailMovieImage)
                    }
                    is DataState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.exception.message ?: "dmlf",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    }
}