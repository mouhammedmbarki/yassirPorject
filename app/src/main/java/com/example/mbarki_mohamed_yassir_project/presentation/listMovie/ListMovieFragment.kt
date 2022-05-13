package com.example.mbarki_mohamed_yassir_project.presentation.listMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.R
import com.example.mbarki_mohamed_yassir_project.databinding.FragmentListMovieBinding
import com.example.mbarki_mohamed_yassir_project.presentation.listMovie.adapter.ListMovieAdapter
import com.example.mbarki_mohamed_yassir_project.presentation.listMovie.adapter.MovieApdaterViewInetcation
import com.example.mbarki_mohamed_yassir_project.presentation.listMovie.intent.ListMovieIntent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListMovieFragment : Fragment(), MovieApdaterViewInetcation {
    private val viewModel: ListMovieViewModel by viewModels()
    private var _binding: FragmentListMovieBinding? = null

    @Inject
    lateinit var movieAdapter: ListMovieAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeListMove()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        setUpUI()
        lifecycleScope.launchWhenStarted {
            viewModel.listMovieIntent.send(ListMovieIntent.loadListMovies)
        }
        return view
    }

    private fun setUpUI() {
        binding.movieList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            movieAdapter.setMovieApdaterViewInetcation(this@ListMovieFragment)
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }
    }

    private fun observeListMove() {
        lifecycleScope.launchWhenStarted {
            viewModel.listMoveDataState.collect {
                when (it) {
                    is DataState.Success -> {
                        movieAdapter.setData(it.data)
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

    override fun openHikeMovie(id: Int) {
        findNavController().navigate(R.id.detailMovieFragment, Bundle().apply {
            putInt("id", id)
        })
    }
}