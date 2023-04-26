package com.example.networkcall.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.dev.adnetworkm.CheckNetworkStatus
import com.example.networkcall.R
import com.example.networkcall.adapter.DummyAdapter
import com.example.networkcall.databinding.ActivityMainBinding
import com.example.networkcall.model.NetworkResult

class MainActivity : AppCompatActivity() {


    val viewModel: TodoViewmodel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // start shimmer effect
        binding.shimmerViewContainer.startShimmerAnimation()
//        gotonextativity()
        checknetworkstatus()

    }

    private fun checknetworkstatus() {
        CheckNetworkStatus.getNetworkLiveData(applicationContext).observe(this, Observer { t ->
            when (t) {
                true -> {
                    showdummy()

                    call()
                }
                false -> {
                    Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show()
                }
                null -> {
                    // TODO: Handle the connection...
                }
            }
        })
    }

    private fun call() {
        viewModel.callApi("id")
    }


    private fun showdummy() {

        viewModel.movieResponse.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Failure -> {
//                    Log.e("error", "onCreate: "+it.errorMessage )
//                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
//                    binding.progressbar.isVisible = false
                    Toast.makeText(this, "Failds", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success -> {
/*                    movieAdapter.updateMovies(it.data)
                    binding.progressbar.isVisible = false*/
                    binding.shimmerViewContainer.stopShimmerAnimation()
                    binding.shimmerViewContainer.visibility = View.GONE
                    binding.recyclerView.adapter = DummyAdapter(it.data, applicationContext)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                    Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                }

            }
/*
        viewModel.firstTodo.observe(this, Observer { t ->

            binding.shimmerViewContainer.stopShimmerAnimation()
            binding.shimmerViewContainer.visibility = View.GONE
            binding.recyclerView.adapter = DummyAdapter(t, applicationContext)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        })*/
        }

        fun gotonextativity() {
            binding.fab.setOnClickListener {
                val intent = Intent(this, ReadActivity::class.java)
                val transitionName = getString(R.string.transition_string)
                val viewStart: View = findViewById(R.id.fab)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, viewStart,  // Starting view
                    transitionName // The String
                )
                ActivityCompat.startActivity(this, intent, options.toBundle())
            }
        }
    }
}